<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="jquery/jquery-1.11.1.min.js"></script>
<script>
   $(function(){
	   //使用form表单
	   //使用FormData对象
	   $("#uploadBtn").click(function(){
		   var result = checkImg();
		   if(result){
			   //表单数据
			  var formData=new FormData(document.getElementById("headForm"));
			  $.ajax({
				  url:"/java_web_pro/FileUploadServlet?action=addUserPhoto",
				  type:"post",
				  data:formData,
				  processData:false,
				  contentType:false,
				  success:function(data){
					  console.log(data);
					  //将json字符串转化为js的对象
					 var result = JSON.parse(data);
					  if(result.retMsg){
						  $("#myhead").attr("src","/java_web_pro/img/"+result.imgName);
					  }else{
						  alert("上传失败");
					  }
					 
				  },
				  error:function(e){
					  console.log(data);
				  }
			  });
		   }
	   });
   });
   
   function checkImg() {
		if ($("#fileIpt").val() == "") {//文件选择器没有选择文件
			alert("请选择文件");
			return false;
		} else {
			return true;
		}

	}
</script>
</head>
<body>
	<div id="page">
		<div class="container_fluid">
			<div class="row">
				<div class="col-md-2 col-md-offset-5">
					<!-- 面板 -->
					<div class="panel panel-danger">
						<div class="panel-heading">
							<h3 class="panel-title">上传头像</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-12">
									<!-- 缩略图 -->

									<div class="thumbnail">
										<img src="img/avatar_def.jpg" id="myhead">

										<form id="headForm">
											<div class="caption">
												<p class="">
												    <input type="text" placeholder="请输入用户名" name="username">
													<input type="file" name="myfile" id="fileIpt">
													<button type="button" id="uploadBtn">上传头像</button>
												</p>
											</div>
										</form>
									</div>

									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>