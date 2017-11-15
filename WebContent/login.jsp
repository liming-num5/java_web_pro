<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="jquery/jquery-1.11.1.min.js"></script>
<style>
.my_panel {
	margin-top: 100px;
}

.my_login_f {
	margin-top: 30px;
	margin-bottom: 30px;
}

#validate_img {
	height: 32px;
	width: 85px;
}
#basic-addon3{
  padding:0;
}
#basic-code{
  width:40%;
}
#tip{
  color:red;
}

</style>
<script type="text/javascript">
   function checkValue(){
	   //fomr的onsubmit属性声明的方法返回false，表单不提交，返回true，表单继续提交
	   var username=$("#username").val();
	   var password=$("#pasword").val();
	   var code=$("#basic-code").val();
	   if(username==""||password==""||code==""){
		   $("#tip").text("用户名、密码、验证码不能为空");
		   return false;
	   }else{
		   $("#tip").text("");
		   return true;
	   }
	   
   }
</script>
</head>
<body>
	<div id="page">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<div class="panel panel-primary my_panel">
						<div class="panel-heading">
							<h3 class="panel-title">管理员登录</h3>
						</div>
						<div class="panel-body">
							<form action="/java_web_pro/AdminServlet"  method="post" onsubmit="return checkValue()"
								class="form-horizontal col-md-10 col-md-offset-1 my_login_f">
								<div class="form-group">
									<label for="username" class="col-sm-2 control-label">用户名</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="username"
										name="username"	placeholder="请输入用户名">
										<input type="hidden" name="action" value="adminLogin">
									</div>
								</div>
								<div class="form-group">
									<label for="password" class="col-sm-2 control-label">密码</label>
									<div class="col-sm-10">
										<input type="password" class="form-control" id="password"
											name="password"  placeholder="请输入密码" >
									</div>
								</div>
								<div class="form-group">
									<label for="password" class="col-sm-2 control-label">验证码</label>
									<div class="col-sm-10">
										<div class="input-group">
											<span class="input-group-addon" id="basic-addon3"> <img
												id="validate_img" alt="点击重试" src="validate.jsp"></span> 
												<input
												type="text" class="form-control" id="basic-code"
												name="code" aria-describedby="basic-addon3" >
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="checkbox-inline col-sm-offset-2"> <input
										type="checkbox" id="inlineCheckbox1" value="option1">
										记住密码
									</label> <label class="checkbox-inline"> <input type="checkbox"
										id="inlineCheckbox2" value="option2"> 自动登录
									</label>
								</div>
								<div class="form-group">
									<p id="tip" class="col-sm-offset-2">${tip}</p>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-3">
										<button type="submit" class="btn btn-primary ">登录</button>
									</div>
									<div class="col-sm-offset-1 col-sm-3">
										<button type="button" class="btn btn-default">注册</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>