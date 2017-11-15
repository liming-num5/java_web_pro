<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻管理</title>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="../jquery/jquery-1.11.1.min.js"></script>
<script src="../js/date_util.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>

<!-- eEditor -->
<!-- 配置文件 -->
<script type="text/javascript" src="js/uEditer/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="js/uEditer/ueditor.all.js"></script>

<style>
#header nav {
	margin-bottom: 0;
}

#content .list-group a {
	border-radius: 0;
}

#content .right_box {
	padding: 0px;
}

#content .right_box .panel, #content .right_box .panel-heading {
	border-radius: 0;
}

table thead tr th {
	text-align: center;
}

.icon_row .row {
	margin: 0;
}

.icon_row .row span {
	font-size: 20px;
}

.icon_row .row span:hover {
	color: red;
}

.news_tr>td {
	vertical-align: middle !important;
}

.cop_row>div>.input-group {
	margin-top: 3px;
}

.cop_row>div>button {
	margin-top: 4.5px;
}

#addNewsModal {
	padding-bottom: 100px;
	overflow: scroll;
}

@media ( min-width :768px) {
	#addNewsModal .modal-dialog {
		width: 70%;
	}
}

.upload_btn {
	margin-top: 10px;
}

.edui-popup-content.edui-default {
	height: auto !important;
}

.news_tr td {
	max-width: 200px;
	text-over: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}

.news_tr>td>div {
	height: 30px;
}
</style>
<script>
	$(function() {
		getPageAllNews(1);
		getAllTypes();
		getPageCounts();
		$("#pageBtn li").click(function(){
			if($(this).index()==0){//前一页
				alert($(this).find("span").text());
			}else if($(this).index()==6){//后一页
				alert($(this).find("span").text());
			}else{//点击某一页
				$(this).siblings().removeClass("active");
				$(this).addClass("active");
			}
		});
	});
	
	function getPageCounts(){
		
	}
	function getPageAllNews(pagenum) {
		$('#myModal').modal('show');
		$.getJSON("/java_web_pro/NewsServlet", {
			action : "adminGetAllNewsByPage",
			pageNum:pagenum
		}, function(data) {
			showNewsData(data);
		});
	}

	function showNewsData(data) {
		if (data.retMsg) {
			$("#newsList_body").html("");
			var newslist = data.retData;
			for (var i = 0; i < newslist.length; i++) {
				var news = newslist[i];
				$("#newsList_body")
						.append(
								'<tr align="center" class="news_tr">' + '<td>'
										+ (i + 1)
										+ '</td>'
										+ '<td id="type'+i+'"></td>'
										+ '<td title="'+news.nTitle+'">'
										+ news.nTitle
										+ '</td>'
										+ '<td>'
										+ news.nSource
										+ '</td>'
										+ '<td>'
										+ news.nAuthor
										+ '</td>'
										+ '<td id="content'+i+'">'
										+ '<div>'
										+ news.nContent
										+ '</div>'
										+ '</td>'
										+ '<td>'
										+ getStrDate(news.nCreatetime.time)
										+ '</td>'
										+ '<td>'
										+ news.nCommcount
										+ '</td>'
										+ '<td>'
										+ news.nReadcount
										+ '</td>'
										+ '<td>'
										+ (news.nIfhot ? "是" : "否")
										+ '</td>'
										+ '<td>'
										+ (news.nIfreport ? "是" : "否")
										+ '</td>'
										+ '<td class="icon_row">'
										+ '<div class="row">'
										+ '<div class="col-md-6 text-center">'
										+ '<span class="glyphicon glyphicon-cog cofigBtn" id="configBtn'+i+'" ></span>'
										+ '</div>'
										+ '<div class="col-md-6 text-center">'
										+ '<span class="glyphicon glyphicon-trash" id="deleteBtn'
										+ i + '" onclick="deleteNew(' + news.id
										+ ')"></span>' + '</div>' + '</div>'
										+ '</td>' + '</tr>');
				$("#content" + i).attr("title", $("#content" + i).text());
				getTypeById(news.tTId, i);
			}
			$(".cofigBtn").each(function(index) {
				$("#configBtn" + index).click(function() {
					var news = newslist[index];
					//1.把模态框放出来
					$("#addNewsModal").modal('show');
					//2.将模态框里面显示的数据变成news对象里面的数据
					$("#myModalLabel").text("修改新闻");//修改模态框标题
					$("#addNewsModal #typeid").val(news.tTId);
					$("#addNewsModal #title").val(news.nTitle);
					$("#addNewsModal #source").val(news.nSource);
					$("#addNewsModal #author").val(news.nAuthor);
					$("#newsid").val(news.id);
					//3.修改确定按钮 事件
					var ue = UE.getEditor('editor',{ 
						initialFrameWidth : 700,
                		initialFrameHeight : 300
					});
					ue.setContent(news.nContent);
					console.log(news);
					if (news.nIfhot) {
						$("#yes").attr("checked", true);
						$("#no").removeAttr("checked");
					} else {
						$("#yes").removeAttr("checked");
						$("#no").attr("checked", true);
					}

				});
			});

			$('#myModal').modal('hide');
		}
	}
	/*根据id删除新闻*/
	function deleteNew(newsId) {
		$.ajax({
			url : "/java_web_pro/NewsServlet",//请求url地址
			data : { //请求参数对象
				action : "adminDeleteNewsById",
				id : newsId
			},
			timeout : 5000,//请求超时时间，以ms为单位
			type : "post",//请求方式
			beforesend : function() { //请求开始发送之前的回掉方法
				$('#myModal').modal('show');
			},
			success : function(data) {//请求成功回掉函数
				var objData = JSON.parse(data);
				if (objData.retCode == 0) {
					location.reload();
				}

			},
			error : function(e) {//请求失败回掉函数
				alert("类型获取异常" + e);
			},
			complete : function() {//请求完成时候的回调函数
				$('#myModal').modal('hide');
			}
		});
	}

	/*根据类型id获取类型*/
	function getTypeById(typeId, index) {
		$.ajax({
			url : "/java_web_pro/TypeServlet",//请求url地址
			data : { //请求参数对象
				action : "adminGetTypeById",
				id : typeId
			},
			timeout : 5000,//请求超时时间，以ms为单位
			type : "get",//请求方式
			beforesend : function() { //请求开始发送之前的回掉方法
				$('#myModal').modal('show');
			},
			success : function(data) {//请求成功回掉函数
				var objData = JSON.parse(data);//将字符串对象转化为js的Object对象
				if (objData.retCode == 0) {
					$("#type" + index).text(objData.retData.tName);
				}
			},
			error : function(e) {//请求失败回掉函数
				alert("类型获取异常" + e);
			},
			complete : function() {//请求完成时候的回调函数
				$('#myModal').modal('hide');
			}
		});
	}
	/**
	 * 获取所有类型
	 */
	function getAllTypes() {
		$.ajax({
			url : "/java_web_pro/TypeServlet",
			data : {
				action : "adminGetAllTypes"
			},
			type : "get",
			timeout : 5000,
			beforesend : function() {
				$('#myModal').modal('show');
			},
			success : function(data) {
				var jsData = JSON.parse(data);
				$("#typeid").html("");
				for (var i = 0; i < jsData.retData.length; i++) {
					var type = jsData.retData[i];
					$("#typeid").append(
							'<option value="'+type.id+'">' + type.tName
									+ '</option>');
				}
			},
			error : function(e) {
				alert("上传失败");
			},
			complete : function() {
				$('#myModal').modal('hide');
			}
		});
	}

	function addNewsBtn() {
		$("#addNewsModal").modal('show');
	}

	function searchNewsByLike() {
		var searchTv = $("#searchTv").val();
		if (searchTv == "") {
			getAllNews();
		} else {
			$.ajax({
				url : "/java_web_pro/NewsServlet",
				data : {
					action : "adminGetNewsByLike",
					likeStr : searchTv
				},
				type : "get",
				timeout : 5000,
				beforesend : function() {
					$('#myModal').modal('show');
				},
				success : function(data) {
					var datas = JSON.parse(data);
					showNewsData(datas);
				},
				error : function(e) {
					alert("上传失败");
				},
				complete : function() {
					$('#myModal').modal('hide');
				}
			});
		}

	}
</script>
</head>
<body>
	<div id="header">
		<%@include file="header.jsp"%>
	</div>
	<div class="container_fluid" id="content">
		<div class="row">
			<div class="col-md-2 left_box">
				<%@include file="left.jsp"%>
			</div>
			<div class="col-md-10 right_box">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row cop_row">
							<div class="col-md-2">
								<h4>新闻列表</h4>
							</div>
							<div class="col-md-offset-2 col-md-3">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="标题/内容/时间/来源" id="searchTv"> <span
										class="input-group-btn">
										<button class="btn btn-default" type="button"
											onclick="searchNewsByLike()">
											<span class="glyphicon glyphicon-search"></span>
										</button>
									</span>
								</div>
							</div>
							<div class="col-md-offset-3 col-md-2">
								<button type="button" class="btn btn-default btn-sm"
									onclick="addNewsBtn()">
									<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
									添加
								</button>
							</div>
						</div>


					</div>
					<div class="panel-body">
						<table class="table table-bordered table-stripted">
							<thead>
								<tr>
									<th>序号</th>
									<th>类型</th>
									<th>标题</th>
									<th>来源</th>
									<th>作者</th>
									<th>内容</th>
									<th>时间</th>
									<th>评论</th>
									<th>浏览</th>
									<th>是否热点</th>
									<th>是否被举报</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="newsList_body">


							</tbody>
						</table>
					</div>
					<div class="panel-footer text-center">
						<nav aria-label="Page navigation">
							<ul class="pagination" id="pageBtn">
								<li><a href="#" aria-label="Previous"> <span
										aria-hidden="true">&laquo;</span>
								</a></li>
								<li class="active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#" aria-label="Next"> <span
										aria-hidden="true">&raquo;</span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Modal -->
	<div class="modal fade" id="addNewsModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel">添加新闻</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="addNewsForm"
						action="/java_web_pro/NewsServlet?action=adminInsertNews"
						method="post">
						<div class="form-group">
						 
							<label class="col-sm-2 control-label">类型</label>
							<div class="col-sm-8">
								<select id="typeid" name="typeid" class="form-control"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">标题</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="title" name="title"
									placeholder="标题" value="马蓉首现身">
									<input type="hidden" id="newsid" value="0">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">来源</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="source"
									name="source" placeholder="来源" value="风行工作室">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">作者</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="author"
									name="author" placeholder="作者" value="小卓伟">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">内容</label>
							<div class="col-sm-9">
								<div>
									<script id="editor" type="text/plain"></script>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">是否为热点</label>
							<div class="col-sm-8">
								<label class="radio-inline"> <input type="radio"
									name="ifHot" id="yes" value="true" checked>是
								</label> <label class="radio-inline"> <input type="radio"
									name="ifHot" id="no" value="false"> 否
								</label>

							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="addNewsSure()">确定</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="progress.jsp"></jsp:include>

</body>


<script type="text/javascript">
	$("#myModal").modal('show');

	//实例化编辑器
	var ue = UE.getEditor('editor', {
		//focus时自动清空初始化时的内容
		autoClearinitialContent : true,
		//关闭字数统计
		wordCount : false,
		//关闭elementPath
		elementPathEnabled : false,
		//默认的编辑区域高度
		initialFrameWidth : 700,
		initialFrameHeight : 300
	//更多其他参数，请参考ueditor.config.js中的配置项
	});
	//添加新闻按钮点击事件
	function addNewsSure() {
		var title = $("#title").val();
		var source = $('#source').val();
		var author = $("#author").val();
		var content = ue.getContent();
		var type = $("#typeid").val();
		var modal_title = $("#myModalLabel").text();
		var ifHot = $("#yes").prop("checked") ? true : false;
		if (title == "" || source == "" || author == "" || content == "") {
			alert("请输入内容");
			return;
		}
		var data = {
				title : title,
				typeid : type,
				content : content,
				source : source,
				author : author,
				ifHot : ifHot
			}
		if (modal_title == "修改新闻") {
			data.newsid=$("#newsid").val();
			data.action="adminUpdateNews";
		} else {
			data.action ="adminInsertNews";
		}
		$.ajax({
			url : "/java_web_pro/NewsServlet",
			data : data,
			type : "post",
			timeout : 5000,
			beforesend : function() {
				$('#myModal').modal('show');
			},
			success : function(data) {
				console.log(data);
				var jsData = JSON.parse(data);
				if (jsData.retCode == 0) {
					location.reload();
				}
			},
			error : function(e) {
				alert("上传失败");
			},
			complete : function() {
				$('#myModal').modal('hide');
				$("#addNewsModal").modal('hide');
			}
		});
	}
</script>
</html>