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
<script type="text/javascript" src="js/checkform.js"></script>
<script type="text/javascript" src="js/billadd.js"></script>
</head>

<body>
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
			<li id="active"><a href="Orders.do">账单管理</a></li>
			<li><a href="Suppliers.do">供应商管理</a></li>
			<li><a href="Goods.do">商品管理</a></li>
			<li><a href="Users.do">用户管理</a></li>
			<li><a href="security/password.jsp">密码修改</a></li>
			<li><a href="loginOut.do">退出系统</a></li>
		</ul>
		</nav>
	</div>
	<div class="right">
		<div class="location">
			<strong>你现在所在的位置是:</strong> <span>账单管理页面 >> 订单添加页面</span>
		</div>
		<div class="providerAdd">
			<form action="Orders.do?action=add" method="post">
				<!--div的class 为error是验证错误，ok是验证成功-->
				<div class="">
					<label for="billId">订单编码：</label> <input type="text" name="billId"
						id="billId" required /> <span>*请输入订单编码</span>
				</div>
				<div>
					<label for="billName">商品名称：</label>

					<%-- !!!!!!!!!!!!!!items="${goodsList}记得加${}!!!!!!!!!!!!!!!! --%>
					<select name="billName" id="billName" required>
						<option value="">--请选择商品名称--</option>
						<c:forEach var="gname" items="${goodsList}">
							<option value="${gname}">${gname}</option>
						</c:forEach>
					</select> <span>*请选择商品名称，选择后再选择供应商</span>
				</div>
				<div>
					<label for="billCom">商品单价：</label> <input type="text"
						name="billPrice" id="billPrice" required readonly="readonly" /> <span>*商品单价，无需填写</span>

				</div>
				<div>
					<label for="billCom">商品单位：</label> <input type="text"
						name="billCom" id="billCom" required readonly="readonly" /> <span>*商品单位，无需填写</span>

				</div>
				<div>
					<label for="billNum">商品数量：</label> <input type="text"
						name="billNum" id="billNum" required /> <span>*请输入大于0的正自然数</span>
				</div>
				<div>
					<label for="money">总金额：</label> <input type="text" name="money"
						id="money" required /> <span>*无需填写</span>
				</div>
				<div>
					<label>供应商：</label> <select name="supplier">
						<option value="">--请选择相应的提供商--</option>
					</select> <span>*请选择供应商</span>
				</div>
				<div>
					<label>是否付款：</label> <input type="radio" name="zhifu" value="0"
						checked />未付款 <input type="radio" name="zhifu" value="1" />已付款
				</div>
				<div class="providerAddBtn">
					<!--<a href="#">保存</a>-->
					<!--<a href="billList.html">返回</a>-->
					<input type="button" value="保存" /> <input type="button" value="返回"
						onclick="history.back(-1)" />
				</div>
			</form>
		</div>

	</div>
	</section>
	<footer class="footer"> 版权归北大青鸟 </footer>
	<script src="js/time.js"></script>

</body>
</html>