var app = angular.module("app",[
        'ui.router',
       'app.loginController'
       ])
       .factory('serverInfo',function(){
    	   var info = {
    	   		ip:'http://ningjj.mppstore.com',
			   domain:'',
			   username:''
    	   };

    	   return info;
       })
       .config(function($stateProvider,$urlRouterProvider){
    	   $stateProvider.
    	   		state('home', {
                    url: '/home',
                    templateUrl: './templates/home.html',
                    controller: 'LoginCtrl'
                });
    	   $urlRouterProvider.otherwise('/home');
       });
