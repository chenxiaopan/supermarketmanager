//根据用户选择的商品gname获得供应商的信息
function getsupplierAjax(gname) {
	$.ajax({
		url : "Orders.do?action=supplierajax",// 向服务器发送请求
		cache : false,
		type : "post",
		data : {
			"gname" : gname
		// 请求参数
		},
		success : function(data) {
			var tt = data;
			var jsonobj = eval('(' + tt + ')');// 将json字符串解析为数组
			$("[name=supplier]").empty();// 添加元素前先清空
			$("[name=supplier]").append('<option >--请选择相应的提供商--</option>');
			if (jsonobj.length > 0) {
				$.each(jsonobj, function(i) {// 循环处理json对象数组
					var info = '<OPTION value=' + jsonobj[i].sid + '>'
							+ jsonobj[i].sname + '</OPTION>';
					$("[name=supplier]").append(info);
				});
			}
		}
	});
}

// 根据用户选择的商品gid和供应商sid获得商品的单价和单位信息
function getgoodAjax(gname, sid) {
	$.ajax({
		url : "Orders.do?action=goodajax",// 向服务器发送请求
		cache : false,
		type : "post",
		data : {
			"gname" : gname,
			"sid" : sid
		// 请求参数
		},
		success : function(data) {
			var tt = data;
			var jsonobj = eval('(' + tt + ')');// 将json字符串解析为数组
			if (jsonobj.length > 0) {
				//toFixed(2)保留小数点后两位
				$("#billPrice").val(parseFloat(jsonobj[0].gprice).toFixed(2));
				$("#billCom").val(jsonobj[0].gunit);
			}
		}
	});
}
///////////////////
$(function() {
	// 当用户选择的商品名改变时，相应的提供商也会改变
	$("#billName").change(function() {
		// 再次点击时移掉单价和单位数量和金额
		$("#billPrice").val("");
		$("#billCom").val("");
		$("#billNum").val("");
		$("#money").val("");
		var gname = $(this).val();
		getsupplierAjax(gname);
	});

	// 当用户选择供应商时，根据选定的商品和供应商组合来提供商品的单价和单位信息
	$("[name=supplier]").change(function() {
		var sid = $(this).val();
		var gname = $("#billName").val();
		getgoodAjax(gname, sid);

	});

	$("#billNum").blur(function() {
		var price = $("#billPrice").val();
		var num = $(this).val();
		$("#money").val((price * num).toFixed(2));
	});

	$("[value=保存]").click(function() {
		if (checkinput()) {
			$("form").submit();
		}
	});

});