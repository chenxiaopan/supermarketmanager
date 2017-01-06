<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta charset="UTF-8">
<title>超市账单管理系统</title>
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/password.js"></script>
<script type="text/javascript">
//验证旧密码!!!必须放在相应的jsp页面里，否则得不到session的值
	function checkoldpwd() {
		var oldPwd = $("#oldPassword").val();
		//获得session域保存的密码
		var pwd = '${sessionScope.pwd}';
		if (oldPwd != pwd) {
			$("#oldPassword").next("span").html("*原密码输入错误");
			return false;
		} else {
			$("#oldPassword").next("span").empty();
			return true;
		}
	}
$(function(){

	//判断原密码输入是否正确
		$("#oldPassword").blur(function() {
            checkoldpwd();
		});

});



</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

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
			<li><a href="Orders.do">账单管理</a></li>
			<li><a href="Suppliers.do">供应商管理</a></li>
			<li><a href="Goods.do">商品管理</a></li>
			<li><a href="Users.do">用户管理</a></li>
			<li id="active"><a href="security/password.jsp">密码修改</a></li>
			<li><a href="loginOut.do">退出系统</a></li>
		</ul>
		</nav>
	</div>
	<div class="right">
		<div class="location">
			<strong>你现在所在的位置是:</strong> <span>密码修改页面</span>
		</div>
		<div class="providerAdd">
			<form action="modifyPassword.do" method="post">
				<!--div的class 为error是验证错误，ok是验证成功-->
				<div class="">
					<label for="oldPassword">旧密码：</label> <input type="password"
						name="oldPassword" id="oldPassword" required /> <span>*请输入原密码</span>
				</div>
				<div>
					<label for="newPassword">新密码：</label> <input type="password"
						name="newPassword" id="newPassword" required /> <span>*请输入新密码</span>
				</div>
				<div>
					<label for="reNewPassword">确认新密码：</label> <input type="password"
						name="reNewPassword" id="reNewPassword" required /> <span>*请输入新确认密码，保证和新密码一致</span>
				</div>
				<div class="providerAddBtn">
					<!--<a href="#">保存</a>-->
					<input type="button" value="保存" />
				</div>
			</form>
		</div>
	</div>
	</section>
	<footer class="footer"> 版权归北大青鸟 </footer>
	<script src="js/time.js"></script>

</body>
</html>