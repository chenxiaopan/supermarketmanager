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
		$(".removeBill").click(function() {
			var oid = $(this).parent("td").siblings(".oid").text();
			//将要删除的订单oid保存到cookie
			document.cookie = "oid=" + oid;
		});

		$("[value=查询]").click(
				function() {
					var txt = $("[value=查询]").prevAll("input").val();
					var sname = $("[name=tigong]").val();
					var opaid = $("[name=fukuan]").val();
					document.location.href = 'Orders.do?action=search&gname='
							+ txt + '&sname=' + sname + '&opaid=' + opaid;
				});

	});
</script>
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
			<strong>你现在所在的位置是:</strong> <span>账单管理页面</span>
		</div>
		<div class="search">
			<span>商品名称：</span> <input type="text" placeholder="请输入商品的名称" /> <span>供应商：</span>
			<select name="tigong">
				<option value="">--请选择--</option>
				<c:forEach var="supplier" items="${suppliersList}">
					<option value="${supplier.sname}">${supplier.sname}</option>
				</c:forEach>
			</select> <span>是否付款：</span> <select name="fukuan">
				<option value="">--请选择--</option>
				<option value="已付款">已付款</option>
				<option value="未付款">未付款</option>
			</select> <input type="button" value="查询" /> <a
				href="Orders.do?action=addView">添加订单</a>
		</div>
		<!--账单表格 样式和供应商公用-->
		<table class="providerTable" cellpadding="0" cellspacing="0">
			<tr class="firstTr">
				<th width="10%">账单编码</th>
				<th width="20%">商品名称</th>
				<th width="10%">供应商</th>
				<th width="10%">账单金额</th>
				<th width="10%">是否付款</th>
				<th width="10%">创建时间</th>
				<th width="30%">操作</th>
			</tr>
			<%--  !!!items里一定要记得写${ } --%>
			<c:choose>
				<c:when test="${ordersList.size()==0}">
					<td colspan="7" ><span id="null">没有匹配的数据</span></td>
				</c:when>
				<c:when test="${ordersList.size()!=0}">
					<c:forEach var="order" items="${ordersList}">
						<tr>
							<td class="oid">${order.oid }</td>
							<td>${order.gname}</td>
							<td>${order.sname }</td>
							<td>${order.cost}</td>
							<td>${order.opaid}</td>
							<td>${order.date}</td>
							<td><a href="Orders.do?action=details&id=${order.oid}"><img
									src="img/read.png" alt="查看" title="查看" /> </a> <a
								href="Orders.do?action=update&id=${order.oid}"><img
									src="img/xiugai.png" alt="修改" title="修改" /> </a> <c:choose>
									<c:when test="${param.pageIndex==null}">
										<a href="Orders.do#" class="removeBill"><img
											src="img/schu.png" alt="删除" title="删除" /> </a>
									</c:when>
									<c:when test="${param.pageIndex!=null}">
										<a href="Orders.do?pageIndex=${pages.currPageNo}#"
											class="removeBill"><img src="img/schu.png" alt="删除"
											title="删除" /> </a>
									</c:when>
								</c:choose>
							</td>

						</tr>
					</c:forEach>
				</c:when>
			</c:choose>

		</table>
		<table width="800">
			<tr>
				<c:if test="${pages.currPageNo>1}">
					<td><a href="Orders.do?pageIndex=1">首页</a>
					</td>
					<td><a href="Orders.do?pageIndex=${pages.currPageNo - 1}">上一页</a>
					</td>
				</c:if>
				<c:if test="${pages.currPageNo<pages.totalPageCount}">
					<td><a href="Orders.do?pageIndex=${pages.currPageNo + 1}">下一页</a>
					</td>
					<td><a href="Orders.do?pageIndex=${pages.totalPageCount}">末页</a>
					</td>
				</c:if>
				<c:if test="${param.action!='search'}">
					<td class="total" align="right" ><span class=total style="text-align:right">${pages.currPageNo }/${pages.totalPageCount}页</span>
					</td>
					</c:if>
			</tr>
		</table>
	</div>
	</section>

	<!--点击删除按钮后弹出的页面-->
	<div class="zhezhao"></div>
	<div class="remove" id="removeBi">
		<div class="removerChid">
			<h2>提示</h2>
			<div class="removeMain">
				<p>你确定要删除该订单吗？</p>
				<a href="Orders.do?action=del" id="yes">确定</a>
				<c:choose>
					<c:when test="${param.pageIndex==null}">
						<a href="Orders.do#" id="no">取消</a>
					</c:when>
					<c:when test="${param.pageIndex!=null}">
						<a href="Orders.do?pageIndex=${pages.currPageNo}#" id="no">取消</a>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>

	<footer class="footer"> 版权归北大青鸟 </footer>

	<script src="js/jquery.js"></script>
	<script src="js/js.js"></script>
	<script src="js/time.js"></script>

</body>

</html>
