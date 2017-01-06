package com.hy.cxp.supermarket.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hy.cxp.supermarket.biz.SuppliersBiz;
import com.hy.cxp.supermarket.biz.impl.SuppliersBizImpl;
import com.hy.cxp.supermarket.entity.Suppliers;
import com.hy.cxp.supermarket.util.Page;

public class SuppliersServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SuppliersServlet() {
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
			System.out.println("provider------------->1");
			// 1.实例化业务对象
			SuppliersBiz suppliersBiz = new SuppliersBizImpl();
			// 2.调用方法
			List<Suppliers> list = suppliersBiz.list();

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
			List<Suppliers> providerList = suppliersBiz.getPageInfo(pages);
			// 得到总页数
			pages.getTotalPageCount();
			// 8.保存数据在请求域
			request.setAttribute("providerList", providerList);
			request.setAttribute("pages", pages);

			request.getRequestDispatcher("../security/providerList.jsp")
					.forward(request, response);
		} else if (action.equals("add")) {
			System.out.println("------------->3");
			// 获得请求参数
			String providerId = request.getParameter("providerId");
			String providerName = request.getParameter("providerName");
			String people = request.getParameter("people");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String fax = request.getParameter("fax");
			String describe = request.getParameter("describe");
			
			Date date=new Date();
			System.out.println(date);
			// 封装成对象
			Suppliers supplier = new Suppliers(Integer.parseInt(providerId), providerName,
					people, phone, address, fax, describe,time(date));
			// 实例化业务对象
			SuppliersBiz supplierBiz = new SuppliersBizImpl();
			// 调用方法
			int count = supplierBiz.add(supplier);
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Suppliers.do");
			}
		} else if (action.equals("details")) {
			System.out.println("------------->3");
			// 获得请求 参数
			String id = request.getParameter("id");
			System.out.println(id);
			// 1.实例化业务对象
			SuppliersBiz suppliersBiz = new SuppliersBizImpl();
			// 2.调用方法
			Suppliers supplier = suppliersBiz.findById(Integer.parseInt(id));
			// 3.将对象保存在请求作用域
			request.setAttribute("supplier", supplier);
			System.out.println(supplier.getSname());
			// 4.转发到视图
			request.getRequestDispatcher("../security/providerView.jsp")
					.forward(request, response);

		} else if (action.equals("update")) {
			System.out.println("------------->4");
			// 获得请求 参数
			String id = request.getParameter("id");
			// 1.实例化业务对象
			SuppliersBiz suppliersBiz = new SuppliersBizImpl();
			// 2.调用方法
			Suppliers supplier = suppliersBiz.findByIdForUpdate(Integer
					.parseInt(id));
			System.out.println(supplier.getSname());
			// 3.将对象保存在请求作用域
			request.setAttribute("supplier", supplier);
			// 4.转发到视图
			request.getRequestDispatcher("../security/providerUpdate.jsp")
					.forward(request, response);

		} else if (action.equals("save")) {
			System.out.println("------------->5");
			// 获得请求 参数
			String id = request.getParameter("id");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String fax = request.getParameter("fax");
			String describe = request.getParameter("describe");

			// 封装对象
			Suppliers supplier = new Suppliers();
			supplier.setSid(Integer.parseInt(id));
			supplier.setStel(phone);
			supplier.setSadd(address);
			supplier.setSfax(fax);
			supplier.setSdesc(describe);
			// 1.实例化业务对象
			SuppliersBiz suppliersBiz = new SuppliersBizImpl();
			System.out.println("------------->5");
			// 2.调用方法
			int count = suppliersBiz.update(supplier);
			System.out.println(count);
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Suppliers.do");
			}

		} else if (action.equals("del")) {
			System.out.println("------------->6");
			// 获得请求参数
			String sid = request.getParameter("sid");
			// 获得Cookie
			Cookie[] cookies = request.getCookies();
			if (cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("sid"))
						sid = cookie.getValue();
				}
			}
			System.out.println(sid);
			// 1.实例化业务对象
			SuppliersBiz supplierBiz = new SuppliersBizImpl();
			// 2.调用方法
			int count = supplierBiz.del(Integer.parseInt(sid));
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Suppliers.do");

			}
		}else if(action.equals("search")){
			
			String name=request.getParameter("name");
			System.out.println("search------------->"+name);
			// 1.实例化业务对象
			SuppliersBiz biz = new SuppliersBizImpl();
			// 2.调用方法
			List<Suppliers> list = biz.findByName(name);
			// 3.将数据存储在请求作用域
			request.setAttribute("providerList", list);

			request.getRequestDispatcher("../security/providerList.jsp")
					.forward(request, response);
			
			
			
		}
	}
	
	
	// 格式化时间
	public String time(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}
}