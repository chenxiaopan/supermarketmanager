package com.hy.cxp.supermarket.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hy.cxp.supermarket.biz.UsersBiz;
import com.hy.cxp.supermarket.biz.impl.UsersBizImpl;
import com.hy.cxp.supermarket.entity.Users;
import com.hy.cxp.supermarket.entity.UsersView;
import com.hy.cxp.supermarket.util.Page;

public class UsersServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UsersServlet() {
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
			System.out.println("------------->1");
			// 1.实例化业务对象
			UsersBiz userBiz = new UsersBizImpl();
			// 2.调用方法
			List<UsersView> list = userBiz.list();

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
			List<UsersView> usersList = userBiz.getPageInfo(pages);
			// 得到总页数
			pages.getTotalPageCount();
			// 8.保存数据在请求域
			request.setAttribute("usersList", usersList);
			request.setAttribute("pages", pages);
			request.getRequestDispatcher("../security/userList.jsp").forward(
					request, response);
		} else if (action.equals("details")) {
			System.out.println("------------->2");
			// 获得请求 参数
			String id = request.getParameter("id");
			System.out.println(id);
			// 1.实例化业务对象
			UsersBiz userBiz = new UsersBizImpl();
			// 2.调用方法
			Users user = userBiz.findById(Integer.parseInt(id));
			// 3.将对象保存在请求作用域
			request.setAttribute("user", user);
			System.out.println(user.getUname());
			// 4.转发到视图
			request.getRequestDispatcher("../security/userView.jsp").forward(
					request, response);
		} else if (action.equals("add")) {
			System.out.println("------------->3");
			// 获得请求参数
			String userId = request.getParameter("userId");
			String userName = request.getParameter("userName");
			String userpassword = request.getParameter("userpassword");
			String sex = request.getParameter("sex");
			String houseDate = request.getParameter("houseDate");
			String userphone = request.getParameter("userphone");
			String userAddress = request.getParameter("userAddress");
			String userlei = request.getParameter("userlei");
			// 封装成对象
			Users user = new Users(Integer.parseInt(userId), userName,
					userpassword, sex, houseDate, userphone, userAddress,
					Integer.parseInt(userlei));
			System.out.println(houseDate);
			// 实例化业务对象
			UsersBiz userBiz = new UsersBizImpl();
			// 调用方法
			int count = userBiz.add(user);
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Users.do");
			}
		} else if (action.equals("update")) {
			System.out.println("------------->4");
			// 获得请求 参数
			String id = request.getParameter("id");
			// 1.实例化业务对象
			UsersBiz userBiz = new UsersBizImpl();
			// 2.调用方法
			Users user = userBiz.findById(Integer.parseInt(id));
			System.out.println(user.getUname());
			// 3.将对象保存在请求作用域
			request.setAttribute("user", user);
			// 4.转发到视图
			request.getRequestDispatcher("../security/userUpdate.jsp").forward(
					request, response);
		} else if (action.equals("save")) {
			System.out.println("------------->5");
			// 获得请求 参数
			String id = request.getParameter("id");
			String sex = request.getParameter("sex");
			String houseDate = request.getParameter("houseDate");
			String userphone = request.getParameter("userphone");
			String userAddress = request.getParameter("userAddress");
			String userlei = request.getParameter("userlei");
			// 封装对象
			Users user = new Users();
			user.setUserid(Integer.parseInt(id));
			user.setUsex(sex);
			user.setUbirth(houseDate);
			user.setUtel(userphone);
			user.setUadd(userAddress);
			user.setUtype(Integer.parseInt(userlei));
			// 1.实例化业务对象
			UsersBiz userBiz = new UsersBizImpl();
			// 2.调用方法
			int count = userBiz.update(user);
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Users.do");
			}
		} else if (action.equals("del")) {
			System.out.println("------------->6");
			// 获得请求参数
			String uid = request.getParameter("uid");
			// 获得Cookie
			Cookie[] cookies = request.getCookies();
			if (cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("uid"))
						uid = cookie.getValue();
				}
			}
			System.out.println(uid);
			// 1.实例化业务对象
			UsersBiz userBiz = new UsersBizImpl();
			// 2.调用方法
			int count = userBiz.del(Integer.parseInt(uid));
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Users.do");
			}
		}else if(action.equals("search")){
			String name=request.getParameter("name");
			System.out.println("search------------->"+name);
			// 1.实例化业务对象
			UsersBiz biz = new UsersBizImpl();
			// 2.调用方法
			List<UsersView> list = biz.findByName(name);
			// 3.将数据存储在请求作用域
			request.setAttribute("usersList", list);

			request.getRequestDispatcher("../security/userList.jsp")
					.forward(request, response);
			
		}

	}
}