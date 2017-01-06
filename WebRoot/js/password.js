/**
 * Created by cxp on 2016/11/20.
 */

// 验证旧密码!!!必须放在相应的jsp页面里，否则得不到session的值
function checkoldpwd() {
	var oldPwd = $("#oldPassword").val();
	// 获得session域保存的密码
	var pwd = '${sessionScope.pwd}';
	alert(pwd);
	if (oldPwd != pwd) {
		$("#oldPassword").next("span").html("*原密码输入错误");
		return false;
	} else {
		$("#oldPassword").next("span").empty();
		return true;
	}
}

// 验证新密码
function checknewpwd() {
	// 获得文本框的值
	var $val = $("#newPassword").val();
	// 正则表达式
	// 密码由英文字母和数字组成的4-10位字符
	var reg = /^[A-Z0-9a-z]{4,10}$/;
	// 判断
	if ($val == "") {
		$("#newPassword").next("span").text("*密码不能为空");
		return false;
	} else if (!reg.test($val)) {
		$("#newPassword").next("span").text("*密码由英文字母和数字组成的4-10位字符");
		return false;
	}
	// 验证通过，清除错误提示
	$("#newPassword").next("span").text("");
	return true;
}

// 判断两次输入密码是否一致
function checkrepwd() {

	var $pwd = $("#newPassword").val();
	var $val = $("#reNewPassword").val();
	if ($val == "") {

		$("#reNewPassword").next("span").text("*密码不能为空");
		
		return false;

	} else if ($val != $pwd) {

		$("#reNewPassword").next("span").text("*两次输入密码不一致");
		
		return false;

	}
	$("#reNewPassword").next("span").text("");
	return true;
}

$(function() {
	// 提交表单
	$("[value=保存]").click(function() {
		if (checkoldpwd() && checknewpwd() && checkrepwd()) {
			$("form").submit();
		}
	});

	// 判断原密码输入是否正确
	$("#oldPassword").blur(function() {
		checkoldpwd();
	});

	// 判断新密码格式是否正确
	$("#newPassword").blur(function() {
		checknewpwd();
	});

	// 判断两次输入密码是否一致
	$("#reNewPassword").blur(function() {
		checkrepwd();
	});
});
