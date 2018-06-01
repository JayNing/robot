function showErrorDialogMsg(status){
	if(status == 500){
		showErrorDialog("服务器繁忙，请稍后重试！如果问题重复出现，请向管理员反馈。");
	}else if(status == 404){
		showErrorDialog("请求的页面不存在，无法找到指定位置的资源！");
	}else if(status == 403){
		showErrorDialog("您的权限不足， 资源不可用");
	}else if(status == 400){
		showErrorDialog("服务器繁忙，请稍后重试！如果问题一直存在，请向系统管理员反馈。");
	}else{
		showErrorDialog("未知错误，请联系管理员");
	}
}
function showNotificationErrors($state, result) {
    // spring-security login
    if (typeof result == 'string') {
    	var src = window.location.href; 
    	console.log("src 1 ===> " + src);
    	var agentLogin = src.indexOf("/public/agentindex.html");
    	var adminlogin = src.indexOf("/public/adminindex.html");
    	console.log("agentLogin ===> " + agentLogin);
    	console.log("adminlogin ===> " + adminlogin);
		if (result.indexOf('输入账号') >= 0 || result.indexOf('忘记密码') >= 0) {
	        if(agentLogin != -1){
	        	window.location.href = "http://" + window.location.host + "/public/agentlogin.html";
	        }else if(adminlogin != -1){
	        	window.location.href = "http://" + window.location.host + "/public/adminlogin.html";
	        }else{
		        var start = src.indexOf("?a=");
		        console.log("start ===> " + start);
		        var end = src.indexOf("#");
		        console.log("end ===> " + end);
		        console.log("start + 3 ===> " + src.substr(start + 3));
		        var host = src.substring(start + 3 , end);
		        console.log("host ===> " + host);
		        var decode_host = Decrypt(host,"");
			    window.location.href = "http://" + decode_host + "/public/Login.html";
	        }
		} else if (result.indexOf('<title>login_error</title>') >= 0) {
		    showErrorDialog('用户名或密码有误！');
		} else {
	        if(agentLogin != -1){
	        	window.location.href = "http://" + window.location.host + "/public/agentlogin.html";
	        }else if(adminlogin != -1){
	        	window.location.href = "http://" + window.location.host + "/public/adminlogin.html";
	        }else{
	        	showErrorDialog('服务器繁忙，请稍后重试！如果问题重复出现，请向系统管理员反馈。');
	        }
		}
    } else {
		if (result.errorMsg) {
		    if (result.errorMsg.length > 0) {
				if (result.errorMsg[0] == "请重新登录并选择公众号!") {
					var src = window.location.href; 
			        console.log("src 2 ===> " + src);
			        var agentLogin = src.indexOf("/public/agentlogin.html");
			        var adminlogin = src.indexOf("/public/adminlogin.html");
			        console.log("agentLogin ===> " + agentLogin);
			        console.log("adminlogin ===> " + adminlogin);
			        if(agentLogin != -1){
			        	window.location.href = "http://" + window.location.host + "/public/agentlogin.html";
			        }else if(adminlogin != -1){
			        	window.location.href = "http://" + window.location.host + "/public/adminlogin.html";
			        }else{
			        	var start = src.indexOf("?a=");
			        	console.log("start ===> " + start);
			        	var end = src.indexOf("#");
			        	console.log("end ===> " + end);
			        	console.log("start + 3 ===> " + src.substr(start + 3));
			        	var host = src.substring(start + 3 , end);
			        	console.log("host ===> " + host);
			        	var decode_host = Decrypt(host,"");
			        	window.location.href = "http://" + decode_host + "/public/Login.html";
			        }
				} else {
				    var error_msg = '';
				    for (var i = 0; i < result.errorMsg.length; i++) {
					error_msg += '\n';
					error_msg += result.errorMsg[i];
				    }
				    showErrorDialog(error_msg);
				}
		    }
		} else {
		    console.log(result);
		}	
    }
}
function getJsObject(data) {
    var result = data;
    if (typeof data == 'string') {
	result = eval('(' + data + ')');
    }
    return result;
}
Date.prototype.format = function(format) {
    var o = {
	"M+" : this.getMonth() + 1,
	"d+" : this.getDate(),
	"h+" : this.getHours(),
	"m+" : this.getMinutes(),
	"s+" : this.getSeconds(),
	"q+" : Math.floor((this.getMonth() + 3) / 3),
	"S" : this.getMilliseconds()
    }
    if (/(y+)/.test(format)) {
	format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for ( var k in o) {
	if (new RegExp("(" + k + ")").test(format)) {
	    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	}
    }
    return format;
}
/**
 * 转换日期对象为日期字符串
 * 
 * @param date
 *                日期对象
 * @param isFull
 *                是否为完整的日期数据, 为true时, 格式如"2000-03-05 01:05:04" 为false时, 格式如
 *                "2000-03-05"
 * @return 符合要求的日期字符串
 */
function getSmpFormatDate(date, isFull) {
    var pattern = "";
    if (isFull == true || isFull == undefined) {
	pattern = "yyyy-MM-dd hh:mm:ss";
    } else {
	pattern = "yyyy-MM-dd";
    }
    return getFormatDate(date, pattern);
}
/**
 * 转换当前日期对象为日期字符串
 * 
 * @param date
 *                日期对象
 * @param isFull
 *                是否为完整的日期数据, 为true时, 格式如"2000-03-05 01:05:04" 为false时, 格式如
 *                "2000-03-05"
 * @return 符合要求的日期字符串
 */

function getSmpFormatNowDate(isFull) {
    return getSmpFormatDate(new Date(), isFull);
}
/**
 * 转换long值为日期字符串
 * 
 * @param l
 *                long值
 * @param isFull
 *                是否为完整的日期数据, 为true时, 格式如"2000-03-05 01:05:04" 为false时, 格式如
 *                "2000-03-05"
 * @return 符合要求的日期字符串
 */

function getSmpFormatDateByLong(l, isFull) {
    return getSmpFormatDate(new Date(l), isFull);
}
/**
 * 转换long值为日期字符串
 * 
 * @param l
 *                long值
 * @param pattern
 *                格式字符串,例如：yyyy-MM-dd hh:mm:ss
 * @return 符合要求的日期字符串
 */

function getFormatDateByLong(l, pattern) {
    return getFormatDate(new Date(l), pattern);
}
/**
 * 转换日期对象为日期字符串
 * 
 * @param l
 *                long值
 * @param pattern
 *                格式字符串,例如：yyyy-MM-dd hh:mm:ss
 * @return 符合要求的日期字符串
 */
function getFormatDate(date, pattern) {
    if (date == undefined) {
	date = new Date();
    }
    if (pattern == undefined) {
	pattern = "yyyy-MM-dd hh:mm:ss";
    }
    return date.format(pattern);
}

// 弹出成功提示框
function showSuccessDialog(message) {
    // swal("提示", message, "success");
    layui.use('layer', function(){
        var layer = layui.layer;

        layer.msg(message);
    });
    // layer.open({content:message,time:1.5});
}

// 弹出error提示框
function showErrorDialog(message) {
    layui.use('layer', function(){
        var layer = layui.layer;

        layer.msg(message);
    });

    // layer.open({content:message,time:1.5});

    // swal("警告", message, "error");
}
// 弹出error提示框
function showLoad() {
    // layer.load();

    layui.use('layer', function(){
        var layer = layui.layer;

        layer.msg('注册中');
    });

    // swal("警告", message, "error");
}

function closeLayer() {
    layer.closeAll();

    // swal("警告", message, "error");
}

// 获取URL上的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
	return unescape(r[2]);
    return null;
}

function getWXUserSourceNameWithCode(code) {
    var name = "";

    switch (code) {
    case 0:
	name = "其他合计";
	break;
    case 1:
	name = "公众号搜索";
	break;
    case 17:
	name = "名片分享";
	break;
    case 30:
	name = "扫描二维码";
	break;
    case 43:
	name = "图文页右上角菜单";
	break;
    case 51:
	name = "支付后关注";
	break;
    case 57:
	name = "图文页内公众号名称 ";
	break;
    case 75:
	name = "公众号文章广告 ";
	break;
    case 78:
	name = "朋友圈广告 ";
	break;
    }

    return name;

}

//id : 文件控件的id，  fileMaxSize ： 允许上传的最大内存值  filetypesParam : 允许上传的文件的类型
function checkUploadFile(id, fileMaxSize, filetypesParam) {
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
    var target = document.getElementById(id);
    var fileSize = 0;
    // var filetypes
    // =[".jpg",".png",".rar",".txt",".zip",".doc",".ppt",".xls",".pdf",".docx",".xlsx"];
    // //默认格式
    var filetypes = [".bmp", ".jpg", ".png",".jpeg",".gif",".mp3",".wma",".wav",".mp4",".pdf",".ppt",".word",".excel"]; // 默认格式
    if (filetypesParam != null && filetypesParam != '' && filetypesParam.length > 0) {
    	filetypes = filetypesParam;
    }
    var filepath = target.value;
    var filemaxsize = 1024 * 10;// 默认10M
    if (fileMaxSize != null && fileMaxSize != '') {
	filemaxsize = fileMaxSize;
    }
    if (filepath) {
		var isnext = false; 
		var index = filepath.split(".");
		//不允许上传类似于文件名为123.pic.jpg的这类文件
		if(index && index != null && index.length == 2){
			var fileend = filepath.substring(filepath.indexOf(".")); 
			if(filetypes && filetypes.length>0){ 
				for(var i =0; i<filetypes.length;i++){ 
					if(filetypes[i]==fileend){ 
						isnext = true; 
						break; 
					} 
				} 
			} 
		}	
		if (!isnext) {
			showErrorDialog("不接受此文件类型！");
			target.value = "";
			return false;
		}
    } else {
		return false;
    }
    if (isIE && !target.files) {
	var filePath = target.value;
	var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
	if (!fileSystem.FileExists(filePath)) {
	    showErrorDialog("附件不存在，请重新输入！");
	    return false;
	}
	var file = fileSystem.GetFile(filePath);
	fileSize = file.Size;
    } else {
	fileSize = target.files[0].size;
    }

    var size = fileSize / 1024;
    if (size > filemaxsize) {
	showErrorDialog("附件大小不能大于" + filemaxsize / 1024 + "M！");
	target.value = "";
	return false;
    }
    if (size <= 0) {
	showErrorDialog("附件大小不能为0M！");
	target.value = "";
	return false;
    }
    return true;
}