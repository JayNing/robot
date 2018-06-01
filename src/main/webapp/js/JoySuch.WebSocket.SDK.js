var currentDomain = window.location.host;
var urlDomain = 'ws://192.168.0.180:8081';
var index = currentDomain.indexOf('joysuch.com');
if (index != -1) {
    urlDomain = 'wss://push.joysuch.com:8081';
}else{
    urlDomain = 'ws://192.168.0.180:8081';
}

/**
 * 使用示例
 */
function JoySuchWebSocketDemo(){
    //普通
    var jswebsocket = new JoySuchWebSocket(urlDomain+"/websocket/rail", onopen, onmsg);
    jswebsocket.openWebSocket();
    jswebsocket.sendMsg('123');
    jswebsocket.closeWebSocket();
    jswebsocket.isActive();

    //SDK
    var sdkwebsocket = new JoySuchSDKWebSocket(onopen, onmsg);
    sdkwebsocket.openWebSocket();
    sdkwebsocket.sendMsg('123');
    sdkwebsocket.closeWebSocket();
    sdkwebsocket.isActive();

}

function JoySuchSDKRailWebSocket(onopen, onmsg){
    var websocket = null;

    function openWebSocket(){
        websocket = new JoySuchWebSocket(urlDomain+"/websocket/sdkrail", onopen, onmsg);
        websocket.openWebSocket();
    }
    function closeWebSocket(){
        if(websocket != null){
            websocket.closeWebSocket();
            websocket = null;
        }
    }
    function sendMsg(msg){
        if(websocket != null){
            websocket.sendMsg(msg);
        }
    }
    function isActive(){
        if(websocket != null){
            return websocket.isActive();
        }
        return false;
    }
    this.isActive = isActive;
    this.openWebSocket = openWebSocket;
    this.closeWebSocket = closeWebSocket;
    this.sendMsg = sendMsg;
}

function JoySuchSDKHeatWebSocket(onopen, onmsg){
    var websocket = null;

    function openWebSocket(){
        websocket = new JoySuchWebSocket(urlDomain+"/websocket/sdkheat", onopen, onmsg);
        websocket.openWebSocket();
    }
    function closeWebSocket(){
        if(websocket != null){
            websocket.closeWebSocket();
            websocket = null;
        }
    }
    function sendMsg(msg){
        if(websocket != null){
            websocket.sendMsg(msg);
        }
    }
    function isActive(){
        if(websocket != null){
            return websocket.isActive();
        }
        return false;
    }
    this.isActive = isActive;
    this.openWebSocket = openWebSocket;
    this.closeWebSocket = closeWebSocket;
    this.sendMsg = sendMsg;
}

function JoySuchWebSocket(url, onopen, onmsg){
    var websocket = null;
    var webscoketShouldExists = false;//断线重连判断条件
    var latestCheckTaskTime = 0;//多个checkWebsocket()过滤条件，只留最新一个
    var websocketParams = {
        wsUrl : url,
        onOpenCallback : function(joysuchWebsocket){
            onopen(joysuchWebsocket);
        },
        onMessageCallback : function(joysuchWebsocket, dataObj){
            onmsg(joysuchWebsocket, dataObj);
        }
    };
    function openWebSocket(){
        websocket = new JSWebSocket();
        websocket.initWebSocket(websocketParams);
        webscoketShouldExists = true;
        latestCheckTaskTime = new Date().getTime();
        checkWebsocket(latestCheckTaskTime);
    }
    var checkLen = 0;
    //断线重连
    function checkWebsocket(startTaskTime){
        checkLen ++;
        if(startTaskTime == latestCheckTaskTime && checkLen <= 1){
            setTimeout(function(){
                // console.log('check websocket :' + startTaskTime);
                if((websocket == null || !websocket.isActive()) && webscoketShouldExists){
                    openWebSocket();
                }
                if(webscoketShouldExists){
                    checkWebsocket(startTaskTime);
                }
            }, 3000);
        }
    }
    function closeWebSocket(){
        webscoketShouldExists = false;
        if(websocket != null){
            websocket.close();
        }
    }
    function sendMsg(msg){
        if(websocket != null){
            websocket.sendMsg(msg);
        }
    }
    function isActive(){
        if(websocket != null){
            return websocket.isActive();
        }
        return false;
    }
    this.isActive = isActive;
    this.openWebSocket = openWebSocket;
    this.closeWebSocket = closeWebSocket;
    this.sendMsg = sendMsg;
}

function JSWebSocket(){

    var connectStatus = false;
    var websocket = null;
    var wsUrl = null;
    var onOpenCallback = null;
    var onMessageCallback = null;
    var joysuchWebsocket = this;
    var sendedMsg = [];

    function initWebSocket(initParams) {
        //clear
        connectStatus = false;
        if(websocket != null){
            websocket.close();
        }
        websocket = null;
        wsUrl = null;
        onOpenCallback = null;
        onMessageCallback = null;

        //params
        wsUrl = initParams.wsUrl;
        onOpenCallback = initParams.onOpenCallback;
        onMessageCallback = initParams.onMessageCallback;

        //socket
        websocket = new WebSocket(wsUrl);
        websocket.onopen = function () {
            console.log('websocket: ' + wsUrl + ' open...');
            onOpenCallback(joysuchWebsocket);
            connectStatus = true;

            if(document.getElementById("websocketSign")){
                document.getElementById("websocketSign").className = "websocketSign";
            }
        };
        websocket.onmessage = function (event) {
            var dataObj = event.data;

            if (typeof event.data == 'string') {
                dataObj = eval('(' + event.data + ')');
            }

            // TODO: dataObj 会导致内存泄漏？？

            // console.log('websocket: ' + wsUrl + ' msg...');
            // console.log(dataObj);
            // console.log('---------------------');
            onMessageCallback(joysuchWebsocket, dataObj);
        };
        websocket.onclose = function (event) {
            console.log('========== websocket 关闭了 ===========');
            console.log(moment().format('YYYY/MM/DD HH:mm:ss'));
            console.log(event);
            console.log('websocket: ' + wsUrl + ' connection closed.');
            connectStatus = false;

            if(document.getElementById("websocketSign")){
                document.getElementById("websocketSign").className = "websocketSign websocketSign-no-sign-animation";
            }
            
            setTimeout(function () {
                reConnect();
            },5000);
        };
    }

    function reConnect() {
        
        if(sendedMsg.length == 0){
            return;
        }
        //socket
        websocket = new WebSocket(wsUrl);
        websocket.onopen = function () {
            console.log('websocket: ' + wsUrl + ' open...');
            onOpenCallback(joysuchWebsocket);
            connectStatus = true;

            // 断线重连后，发送msg
            for(var i = 0; i < sendedMsg.length; i++){
                var msg = sendedMsg[i];
                websocket.send(msg);
            }

        };
        websocket.onmessage = function (event) {
            var dataObj = event.data;

            if (typeof event.data == 'string') {
                dataObj = eval('(' + event.data + ')');
            }
            // console.log('websocket: ' + wsUrl + ' msg...');
            // console.log(dataObj);
            // console.log('---------------------');
            onMessageCallback(joysuchWebsocket, dataObj);
        };
        websocket.onclose = function (event) {
            console.log('========== websocket 关闭了 ===========');
            console.log(moment().format('YYYY/MM/DD HH:mm:ss'));
            console.log(event);
            console.log('websocket: ' + wsUrl + ' connection closed.');
            connectStatus = false;

            setTimeout(function () {
                reConnect();
            },5000);
        };
    }

    function sendMsg(msg){
        if(websocket != null){
            console.log('websocket: ' + wsUrl + ' send msg: ' + msg);
            websocket.send(msg);
            sendedMsg.push(msg);
        }
    }

    function close(){
        if(websocket != null){
            sendedMsg = [];
            websocket.close();
        }
    }

    function isActive(){
        return connectStatus;
    }

    this.initWebSocket = initWebSocket;
    this.sendMsg = sendMsg;
    this.close = close;
    this.isActive = isActive;
}