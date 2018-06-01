/**
 * Created by Administrator on 2018/5/6.
 */
app.factory('loginService', [ '$state', 'serverInfo', function($state, serverInfo) {
    var initService = {};
    initService.login = function(username, password) {
        console.log("=====================================");
        var result = null;
        var url = serverInfo.ip + "/doLogin";
        $.ajax({
            url : url,
            async : false,
            type : 'POST',
            data : {
                username : username,
                password : password
            },
            success : function(data) {
                if (data.errorCode == 0) {
                    result = data.data;
                } else {
                    showNotificationErrors($state, data);
                }
            },
            error : function(err) {
                console.log(err);
                if(err){
                    showErrorDialogMsg(err.status);
                }
            }
        });
        return result;
    };
    initService.sendAuthCode = function(username) {
        var result = false;
        var url = serverInfo.ip + "/public/sendAuthCode";
        $.ajax({
            url : url,
            async : false,
            type : 'POST',
            data : {
                username : username
            },
            success : function(data) {
                if (data.error_code == 0) {
                    result = true;
                } else {
                    showNotificationErrors($state, data);
                }
            },
            error : function(err) {
                console.log(err);
                if(err){
                    showErrorDialogMsg(err.status);
                }
            }
        });
        return result;
    };
    initService.regist = function(username, password, authcode) {
        var result = false;
        var url = serverInfo.ip + "/public/regist";
        $.ajax({
            url : url,
            async : false,
            type : 'POST',
            data : {
                username : username,
                password : password,
                authcode : authcode,
            },
            success : function(data) {
                if (data.error_code == 0) {
                    result = true;
                } else {
                    showNotificationErrors($state, data);
                }
            },
            error : function(err) {
                console.log(err);
                if(err){
                    showErrorDialogMsg(err.status);
                }
            }
        });
        return result;
    };
    return initService;
} ]);