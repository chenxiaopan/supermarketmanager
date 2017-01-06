<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript">
	$(function() {
		$("[value=保存]").click(function() {
			$("form").submit();
		});
	});
</script>
</head>

<body>
	=
	<!--头部-->
	<header class="publicHeader">
	<h1>超市账单管理系统</h1>

	<div class="publicHeaderR">
		<p>
			<span>下午好！</span><span style="color: #fff21b">${sessionScope.name}</span>
			, 欢迎你！
		</p>
		<a href="login.html">退出</a>
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
			<li><a href="Orders.do">账单管理</a></li>
			<li><a href="Suppliers.do">供应商管理</a></li>
			<li id="active"><a href="Goods.do">商品管理</a></li>
			<li><a href="Users.do">用户管理</a></li>
			<li><a href="security/password.jsps">密码修改</a></li>
			<li><a href="loginOut.do">退出系统</a></li>
		</ul>
		</nav>
	</div>
	<div class="right">
		<div class="location">
			<strong>你现在所在的位置是:</strong> <span>商品管理页面 >商品修改页</span>
		</div>
		<div class="providerAdd">
			<form action="Goods.do?action=save" method="post">
				<!--div的class 为error是验证错误，ok是验证成功-->
				<div class="">
					<label for="providerId">商品编号：</label> <input type="text"
						name="productId" id="providerId" value="${good.gid }"
						readonly="readonly" /> <span>*商品编号不可修改</span>
				</div>
				<div>
					<label for="providerName">商品名称：</label> <input type="text"
						name="productName" id="providerName" value="${good.gname }"
						readonly="readonly" /> <span>*商品名称不可修改</span>
				</div>
				<div>
					<label for="price">单价：</label> <input type="text" name="price"
						id="price" value="${good.gprice }" /> <span></span>

				</div>
				<div>
					<label for="unit">单位：</label> <input type="text" name="unit"
						id="unit" value="${good.gunit }" /> <span></span>
				</div>
				<div>
					<label for="sid">供应商编号：</label> <select name="sid">
						<c:forEach var="sid" items="${sidList}">
							<c:if test="${sid!=0}">
								<option value="${sid}">${sid}</option>
							</c:if>
							<!-- 默认选中的编号 -->
							<c:if test="${sid==good.sid}">
								<option value="${sid}" selected="selected">${sid}</option>
							</c:if>
						</c:forEach>
					</select> <span></span>
				</div>
				<div>
					<label for="gstock">库存：</label> <input type="text" name="gstock"
						id="gstock" value="${good.gstock }" /> <span></span>
				</div>
				<div class="providerAddBtn">
					<!--<a href="#">保存</a>-->
					<!--<a href="providerList.html">返回</a>-->
					<input type="button" value="保存" " /> <input type="button"
						value="返回" onclick="history.back(-1)" />
				</div>
			</form>
		</div>

	</div>
	</section>
	<footer class="footer"> 版权归北大青鸟 </footer>
	<script src="js/time.js"></script>

</body>
</html>