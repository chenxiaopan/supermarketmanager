/**
 * Created by cxp on 2016/11/20.
 */

///验证表单里的所有输入框文本是否为空，有空值时表单不能提交

function checkinput(){
	var flag=true;
	$("input").each(function(){
		  //获得文本框的值
		var $val=$(this).val();
		//判断
		if($val==""){
          $(this).next("span").text("*不能为空");
			flag=false;
		}
		
	});
	return flag;
}

	
