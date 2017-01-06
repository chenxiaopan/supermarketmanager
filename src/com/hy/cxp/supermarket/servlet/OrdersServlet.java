package com.hy.cxp.supermarket.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.hy.cxp.supermarket.biz.GoodsBiz;
import com.hy.cxp.supermarket.biz.OrdersBiz;
import com.hy.cxp.supermarket.biz.SuppliersBiz;
import com.hy.cxp.supermarket.biz.impl.GoodsBizImpl;
import com.hy.cxp.supermarket.biz.impl.OrdersBizImpl;
import com.hy.cxp.supermarket.biz.impl.SuppliersBizImpl;
import com.hy.cxp.supermarket.entity.Goods;
import com.hy.cxp.supermarket.entity.GoodsView;
import com.hy.cxp.supermarket.entity.Orders;
import com.hy.cxp.supermarket.entity.OrdersDetail;
import com.hy.cxp.supermarket.entity.OrdersView;
import com.hy.cxp.supermarket.entity.Suppliers;
import com.hy.cxp.supermarket.util.Page;

public class OrdersServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public OrdersServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		/*
		 * // 处理中文乱码，一定要写在获得PrintWriter前
		 * response.setHeader("content-type","text/html;charset=UTF-8");
		 * response.setCharacterEncoding("UTF-8"); PrintWriter
		 * out=response.getWriter();
		 */

		// 获取动作参数，判断要执行的动作
		String action = request.getParameter("action");
		System.out.println("orders------------------>");
		// ///////////////////执行动作/////////////////////////////
		if (action == null) {
			System.out.println("orders------------------>1");

			// 1.实例化业务对象
			OrdersBiz orderBiz = new OrdersBizImpl();
			// 2.调用方法
			List<OrdersView> list = orderBiz.list();

			// 3.获得总个数
			int count = list.size();
			// 4.设置当前页
			int pageIndex = 1;
			String str = request.getParameter("pageIndex");
			pageIndex = (str == null) ? (1) : (Integer.parseInt(str));
			// 5.每页显示的个数
			int pageSize = 3;
			// 6.创建分页对象
			Page pages = new Page(pageSize, count, pageIndex);
			// 计算总页数
			pages.setTotalCount(count);

			// 7.调用方法
			List<OrdersView> ordersList = orderBiz.getPageInfo(pages);
			// 得到总页数
			pages.getTotalPageCount();
			// 8.保存数据在请求域
			SuppliersBiz supplierBiz = new SuppliersBizImpl();
			List<Suppliers> suppliersList = supplierBiz.list();
			request.setAttribute("suppliersList", suppliersList);

			request.setAttribute("ordersList", ordersList);
			request.setAttribute("pages", pages);
			// 9.转发到视图
			request.getRequestDispatcher("../security/billList.jsp").forward(
					request, response);

		} else if (action.equals("addView")) {
			System.out.println("------------->2");
			// 1.实例化业务对象
			GoodsBiz goodBiz = new GoodsBizImpl();
			// 2.调用方法
			List<GoodsView> goodsList = goodBiz.list();
			// 去除重复的名字
			List<String> gnameList = new ArrayList<String>();
			for (GoodsView good : goodsList) {
				gnameList.add(good.getGname());
			}
			Set<String> gnameSet = new HashSet<String>();
			gnameSet.addAll(gnameList);

			// 3.将数据存储在请求作用域
			request.setAttribute("goodsList", gnameSet);
			// 9.转发到视图
			request.getRequestDispatcher("../security/billAdd.jsp").forward(
					request, response);

		} else if (action.equals("add")) {
			System.out.println("------------->2");
			// 获得请求参数
			String billId = request.getParameter("billId");
			String billName = request.getParameter("billName");
			String billNum = request.getParameter("billNum");
			String supplier = request.getParameter("supplier");
			String zhifu = request.getParameter("zhifu");
			// 实例化业务对象
			OrdersBiz orderBiz = new OrdersBizImpl();
			// 调用方法
			// 根据商品名和供应商sid查找唯一的商品gid
			int gid = orderBiz.findGid(billName, Integer.parseInt(supplier));
			Date date = new Date();
			// 封装成对象
			Orders order = new Orders();
			order.setOid(Integer.parseInt(billId));
			order.setGid(gid);
			order.setOnum(Integer.parseInt(billNum));
			order.setDate(time(date).split(" ")[0]);
			order.setOpaid(Integer.parseInt(zhifu));

			System.out.println("--->" + order.getDate());

			// 调用方法
			int count = orderBiz.add(order);
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Orders.do");
			}
		} else if (action.equals("details")) {
			System.out.println("------------->3");
			// 获得请求 参数
			String id = request.getParameter("id");
			System.out.println(id);
			// 1.实例化业务对象
			OrdersBiz orderBiz = new OrdersBizImpl();
			// 2.调用方法
			OrdersDetail order = orderBiz.findById(Integer.parseInt(id));
			// 3.将对象保存在请求作用域
			request.setAttribute("order", order);
			System.out.println(order.getOid());
			// 4.转发到视图
			request.getRequestDispatcher("../security/billView.jsp").forward(
					request, response);

		} else if (action.equals("update")) {
			System.out.println("update------------->4");
			// 获得请求 参数
			String id = request.getParameter("id");
			// 1.实例化业务对象
			OrdersBiz orderBiz = new OrdersBizImpl();

			// 2.调用方法
			OrdersDetail order = orderBiz.findById(Integer.parseInt(id));

			List<Suppliers> suppliersList = orderBiz.findSupByGname(Integer
					.parseInt(id));

			System.out.println(suppliersList.size());
			// 3.将对象保存在请求作用域
			request.setAttribute("order", order);

			request.setAttribute("suppliersList", suppliersList);
			// 4.转发到视图
			request.getRequestDispatcher("../security/billUpdate.jsp").forward(
					request, response);
		} else if (action.equals("save")) {
			System.out.println("save------------->5");
			// 获得请求参数
			String billId = request.getParameter("billId");
			String billName = request.getParameter("billName");
			String onum = request.getParameter("onum");
			String sid = request.getParameter("supplier");
			String zhifu = request.getParameter("zhifu");
			// 实例化业务对象
			OrdersBiz orderBiz = new OrdersBizImpl();

			System.out.println(billName + "\n" + sid);

			int gid = orderBiz.findGid(billName, Integer.parseInt(sid));

			// 封装对象
			Orders order = new Orders();
			order.setOid(Integer.parseInt(billId));
			order.setGid(gid);
			order.setOnum(Integer.parseInt(onum));
			order.setOpaid(Integer.parseInt(zhifu));
			// 调用方法
			System.out.println("----->" + order.getGid());
			orderBiz.update(order);
			// 重定向
			response.sendRedirect("Orders.do");

		} else if (action.equals("del")) {
			System.out.println("------------->6");
			// 获得请求参数
			String oid = request.getParameter("oid");
			// 获得Cookie
			Cookie[] cookies = request.getCookies();
			if (cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("oid"))
						oid = cookie.getValue();
				}
			}
			System.out.println(oid);
			// 1.实例化业务对象
			OrdersBiz orderBiz = new OrdersBizImpl();
			// 2.调用方法
			int count = orderBiz.del(Integer.parseInt(oid));
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Orders.do");
			}
		} else if (action.equals("supplierajax")) {
			System.out.println("ajax------------->7");
			request.setCharacterEncoding("utf-8");

			OrdersBiz biz = new OrdersBizImpl();

			String gname = request.getParameter("gname");
			List<Suppliers> list = biz.findSupByGname(gname);
			// 将list封装成json数组
			JSONArray jsonArray = JSONArray.fromObject(list);
			// 处理中文乱码
			response.setHeader("content-type", "text/html;charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.println(jsonArray);

		} else if (action.equals("goodajax")) {
			System.out.println("ajax------------->8");
			request.setCharacterEncoding("utf-8");

			OrdersBiz biz = new OrdersBizImpl();

			String gname = request.getParameter("gname");
			String sid = request.getParameter("sid");
			// 根据商品名称和供应商sid找到gid
			int gid = biz.findGid(gname, Integer.parseInt(sid));
			GoodsBiz goodBiz = new GoodsBizImpl();
			// 根据id找到商品信息
			Goods good = goodBiz.findById(gid);
			System.out.println(good.getGprice());
			// 将list封装成json数组
			JSONArray jsonArray = JSONArray.fromObject(good);
			// 处理中文乱码
			response.setHeader("content-type", "text/html;charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.println(jsonArray);

		} else if (action.equals("search")) {
			System.out.println("search---------->");
			// 1获得请求参数
			String gname = request.getParameter("gname");
			String sname = request.getParameter("sname");
			String opaid = request.getParameter("opaid");
			// 2实例化业务对象
			OrdersBiz ordersBiz = new OrdersBizImpl();
			// 3调用方法
			List<OrdersView> ordersList=ordersBiz.search(gname, sname, opaid);
			System.out.println("ordersList---------->"+ordersList.size());
			//4保存数据到请求作用域
			SuppliersBiz supplierBiz = new SuppliersBizImpl();
			List<Suppliers> suppliersList = supplierBiz.list();
			request.setAttribute("suppliersList", suppliersList);
			
			request.setAttribute("ordersList", ordersList);
			// 5.转发到视图
			request.getRequestDispatcher("../security/billList.jsp").forward(
					request, response);

		}
	}

	// 格式化时间
	public String time(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}
}