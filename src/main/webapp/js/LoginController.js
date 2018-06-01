angular.module('app.loginController',[])
	.controller('LoginCtrl',function($scope,$state,serverInfo,$http,$timeout,loginService){

        $scope.isLoginMode = true;

        // 登录tab
        $scope.loginTabPress = function () {

            if($scope.isLoginMode){
                return;
            }
            loginAndSignUpTabChange(0);
        };

        //  注册tab
        $scope.signUpTabPress = function () {

            if(!$scope.isLoginMode){
                return;
            }
            loginAndSignUpTabChange(1);
        };

        // 登录,注册 切换
        function loginAndSignUpTabChange(flag){

            switch (flag){
                // 登录 tab
                case 0:
                    $scope.isLoginMode = true;
                    $("#signUpTab").removeClass("login2-dialog-selected");
                    $("#signUpTab").addClass("login2-dialog-unSelected");
                    $("#loginTab").removeClass("login2-dialog-unSelected");
                    $("#loginTab").addClass("login2-dialog-selected");
                    $('#loginDialog').css({
                        "height":"300px",
                        "margin-top":"-150px"
                    });
                    $("#loginContent").removeClass("hidden");
                    $("#signUpContent").addClass("hidden");
                    break;
                // 注册 tab
                case 1:
                    $scope.isLoginMode = false;
                    $("#signUpTab").removeClass("login2-dialog-unSelected");
                    $("#signUpTab").addClass("login2-dialog-selected");
                    $("#loginTab").removeClass("login2-dialog-selected");
                    $("#loginTab").addClass("login2-dialog-unSelected");
                    $('#loginDialog').css({
                        "height":"600px",
                        "margin-top":"-300px"
                    });
                    $("#loginContent").addClass("hidden");
                    $("#signUpContent").removeClass("hidden");
                    break;
            }

        }

        // 账号注册
        $scope.register = {
            username : "",
            authCode : "",
            password : "",
            repassword : ""
        }
        $scope.sendAuthCodeBtnDisabled = false;
        $scope.sendAuthCodeBtnText = "发送验证码";
        $scope.sendAuthCode = function(){
            if(validateMobile($scope.register.username)){
                var result = loginService.sendAuthCode($scope.register.username);
                if(result){
                    $scope.sendAuthCodeBtnDisabled = true;
                    $scope.sendAuthCodeBtnText = "重新发送(60)";
                    $scope.setBtnTime(60);
                }
            }else{
                showErrorDialog("请输入正确的手机号！");
            }
        }
        $scope.setBtnTime = function(i){
            if(i == 0){
                $scope.sendAuthCodeBtnDisabled = false;
                $scope.sendAuthCodeBtnText = "发送验证码";
            }else{
                $timeout(function(){
                    $scope.sendAuthCodeBtnText = "重新发送(" + (--i) +")";
                    $scope.setBtnTime(i);
                }, 1000);
            }
        }
        $scope.createAccountPress = function () {
            if(validateMobile($scope.register.username)){
                if($scope.register.password == $scope.register.repassword){
                    if(validatePassword($scope.register.password)){
                        var result = loginService.regist($scope.register.username, $scope.register.password, $scope.register.authCode);
                        if(result){
                            //返回登陆Tab
                            showSuccessDialog("注册成功！");
                            loginAndSignUpTabChange(0);
                        }

                    }else{
                        showErrorDialog("密码格式不正确(只能包含数字字母及部分特殊符号.!@#$%^&*_)！");
                    }
                }else{
                    showErrorDialog("两次输入密码不一致！");
                }
            }else{
                showErrorDialog("请输入正确的手机号！");
            }
        };
        $scope.loginuser = {
            username : "",
            password : ""
        };
        // 登录
        $scope.loginPress = function (){
            var eventKey = loginService.login($scope.loginuser.username,$scope.loginuser.password);
            console.log("eventKey : " + eventKey);
            alert("eventKey : " + eventKey);
        /*    if(eventKey){
                serverInfo.userName = $scope.loginuser.username;
                window.location.href = eventKey;
            }*/
        };
    })

;