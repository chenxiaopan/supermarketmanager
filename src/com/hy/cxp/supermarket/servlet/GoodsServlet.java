package com.hy.cxp.supermarket.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hy.cxp.supermarket.biz.GoodsBiz;
import com.hy.cxp.supermarket.biz.impl.GoodsBizImpl;
import com.hy.cxp.supermarket.entity.Goods;
import com.hy.cxp.supermarket.entity.GoodsView;

public class GoodsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public GoodsServlet() {
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
		// 获取动作参数，判断要执行的动作
		String action = request.getParameter("action");
		// ///////////////////执行动作/////////////////////////////
		if (action == null) {
			System.out.println("goods------------->1");
			// 1.实例化业务对象
			GoodsBiz goodBiz = new GoodsBizImpl();
			// 2.调用方法
			List<GoodsView> goodsList = goodBiz.list();
			// 3.将数据存储在请求作用域
			request.setAttribute("goodsList", goodsList);

			request.getRequestDispatcher("../security/productList.jsp")
					.forward(request, response);

		} else if (action.equals("add")) {
			System.out.println("goods------------->2");
			// 获得请求参数
			String productId = request.getParameter("productId");
			String productName = request.getParameter("productName");
			String price = request.getParameter("price");
			String unit = request.getParameter("unit");
			String sid = request.getParameter("sid");
			String gstock = request.getParameter("gstock");
			// 封装成对象
			Goods good = new Goods(Integer.parseInt(productId), productName,
					Float.parseFloat(price), unit, Integer.parseInt(sid),
					Integer.parseInt(gstock));
			System.out.println(good.getGid());
			// 实例化业务对象
			GoodsBiz goodBiz = new GoodsBizImpl();
			// 调用方法
			int count = goodBiz.add(good);
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Goods.do");

			}
		} else if (action.equals("details")) {
			System.out.println("goods------------->3");
			// 获得请求 参数
			String id = request.getParameter("id");
			System.out.println(id);
			// 1.实例化业务对象
			GoodsBiz goodBiz = new GoodsBizImpl();
			// 2.调用方法
			Goods good = goodBiz.findById(Integer.parseInt(id));
			// 3.将对象保存在请求作用域
			request.setAttribute("good", good);
			System.out.println(good.getGname());
			// 4.转发到视图
			request.getRequestDispatcher("../security/productView.jsp")
					.forward(request, response);
		} else if (action.equals("update")) {
			System.out.println("goods------------->4");
			// 获得请求 参数
			String id = request.getParameter("id");
			// 1.实例化业务对象
			GoodsBiz goodBiz = new GoodsBizImpl();
			// 2.调用方法
			Goods good = goodBiz.findById(Integer.parseInt(id));
			int[] sidList = goodBiz.findSid();
			
			// 3.将对象保存在请求作用域
			request.setAttribute("sidList", sidList);
			request.setAttribute("good", good);
			// 4.转发到视图
			request.getRequestDispatcher("../security/productUpdate.jsp")
					.forward(request, response);
		} else if (action.equals("save")) {
			System.out.println("goods------------->5");
			// 1.获得请求参数
			String productId = request.getParameter("productId");
			String productName = request.getParameter("productName");
			String price = request.getParameter("price");
			String unit = request.getParameter("unit");
			String sid = request.getParameter("sid");
			String gstock = request.getParameter("gstock");

			// 封装成对象
			Goods good = new Goods(Integer.parseInt(productId), productName,
					Float.parseFloat(price), unit, Integer.parseInt(sid),
					Integer.parseInt(gstock));
			// 2实例化业务对象
			GoodsBiz goodBiz = new GoodsBizImpl();
			// 3.调用方法
			goodBiz.update(good);
			// 4重定向
			response.sendRedirect("Goods.do");

		} else if (action.equals("del")) {
			System.out.println("goods------------->6");
			// 获得请求参数
			String gid = request.getParameter("gid");
			// 获得Cookie
			Cookie[] cookies = request.getCookies();
			if (cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("gid"))
						gid = cookie.getValue();
				}
			}
			System.out.println(gid);
			// 1.实例化业务对象
			GoodsBiz goodBiz = new GoodsBizImpl();
			// 2.调用方法
			int count = goodBiz.del(Integer.parseInt(gid));
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Goods.do");
			}
		} else if (action.equals("addView")) {
			System.out.println("------------->7");
			// 1.实例化业务对象
			GoodsBiz goodBiz = new GoodsBizImpl();
			// 2.调用方法
			int[] sidList = goodBiz.findSid();
			// 3.将对象保存在请求作用域
			request.setAttribute("sidList", sidList);
			System.out.println(sidList);
			// 4.转发到视图
			request.getRequestDispatcher("../security/productAdd.jsp").forward(
					request, response);
		}else if(action.equals("search")){
			String name=request.getParameter("name");
			System.out.println("search------------->"+name);
			// 1.实例化业务对象
			GoodsBiz biz = new GoodsBizImpl();
			// 2.调用方法
			List<GoodsView> list = biz.findByName(name);
			// 3.将数据存储在请求作用域
			request.setAttribute("goodsList", list);

			request.getRequestDispatcher("../security/productList.jsp")
					.forward(request, response);
			
			
		}
	}
}
