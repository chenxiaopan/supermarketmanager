<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>超市账单管理系统</title>
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/style.css" />

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery.js"></script>
</head>

<body>
	x
	<!--头部-->
	<header class="publicHeader">
	<h1>超市账单管理系统</h1>

	<div class="publicHeaderR">
		<p>
			<span>下午好！</span><span style="color: #fff21b">
				${sessionScope.name}</span> , 欢迎你！
		</p>
		<a href="loginOut.do">退出</a>
	</div>
	</header>
	<!--时间-->
	<section class="publicTime"> <span id="time">2015年1月1日
		11:11 星期一</span> <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a> </section>
	<!--主体内容-->
	<section class="publicMian ">
	<div class="left">
		<h2 class="leftH2">
			<span class="span1"></span>功能列表 <span></span>
		</h2>
		<nav>
		<ul class="list">
			<li><a href="billList.html">账单管理</a>
			</li>
			<li><a href="providerList.html">供应商管理</a>
			</li>
			<li><a href="productList.html">商品管理</a>
			</li>
			<li id="active"><a href="userList.html">用户管理</a>
			</li>
			<li><a href="password.html">密码修改</a>
			</li>
			<li><a href="loginOut.do">退出系统</a>
			</li>
		</ul>
		</nav>
	</div>
	<div class="right">
		<div class="location">
			<strong>你现在所在的位置是:</strong> <span>用户管理页面 >> 用户信息查看页面</span>
		</div>
		<div class="providerView">
			<p>
				<strong>用户编号：</strong><span>${user.userid}</span>
			</p>
			<p>
				<strong>用户名称：</strong><span>${user.uname}</span>
			</p>
			<p>
				<strong>用户性别：</strong><span>${user.usex}</span>
			</p>
			<p>
				<strong>出生日期：</strong><span>${user.ubirth}</span>
			</p>
			<p>
				<strong>用户电话：</strong><span>${user.utel}</span>
			</p>
			<p>
				<strong>用户地址：</strong><span>${user.uadd}</span>
			</p>
			<p>
				<strong>用户类别：</strong><span> <c:choose>
						<c:when test="${user.utype==1}">
                                    管理员
            </c:when>
						<c:when test="${user.utype==2}">
                                        经理
            </c:when>
						<c:when test="${user.utype==3}">
                                       普通用户
            </c:when>
					</c:choose> </span>
			</p>

			<a href="javascript:void(0);" onclick="history.back(-1)">返回</a>
		</div>
	</div>
	</section>
	<footer class="footer"> 版权归北大青鸟 </footer>
	<script src="js/time.js"></script>

</body>
</html>