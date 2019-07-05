// 所有页面公共js
$(function(){
    this.getPhone = function(){
        $.ajax({
            url:'/device/getDeviceDetails',
            type:'get',
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    $("#xu-cnt").val(datas.data.authorization);
                    $("#zai-cnt").val(datas.data.online);
                    $("#li-cnt").val(datas.data.offline);
                }else{
                    alert("服务器异常")
                }
            }
        })
    };

    //主页
    $(this).delegate('#home','click',function(){
        var data = {
            'scope': -2,
            'uuid': ""
        };
        $.ajax({
            url:'/convention/home',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });
    //返回
    $(this).delegate('#return','click',function(){
        var data = {
            'scope': -2,
            'uuid': ""
        };
        $.ajax({
            url:'/convention/back',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });
    //开屏
    $(this).delegate('#off','click',function(){
        var data = {
            'state':1,
            'scope': -2,
            'uuid': ""
        };
        $.ajax({
            url:'/convention/lock',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });
    //停止运行
    $(this).delegate('#zanting','click',function(){
        var data = {
            'scope': -2,
            'uuid': ""
        };
        $.ajax({
            url:'/script/stop',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });
    //清空进程
    $(this).delegate('#delete','click',function(){
        var data = {
            'scope': -2,
            'uuid': ""
        };
        $.ajax({
            url:'/convention/killProgress',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });
    //锁屏
    $(this).delegate('#open','click',function(){
        var data = {
            'state':0,
            'scope': -2,
            'uuid': ""
        };
        $.ajax({
            url:'/convention/lock',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });
    //重启设备
    $(this).delegate('#restart','click',function(){
        var data = {
            'scope': -2,
            'uuid': ""
        };
        $.ajax({
            url:'/convention/reboot',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });
    //安装软件
    $(this).delegate('#do-ruan','click',function(){
        var data = {
            'scope': -2,
            'uuid': ""
        };
        $.ajax({
            url:'/convention/installApp',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });
    //音量加/减/静音
    $(this).delegate('.volume','click',function(){
        var state = $(this).attr("data-volume");
        var data = {
            'state':state,
            'scope': -2,
            'uuid': ""
        };
        $.ajax({
            url:'/convention/volume',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });

    $(this).delegate('.mobile-chu','click',function(){
        var state = $(this).attr("data-volume");
        var data = {
            'scope': -2,
            'uuid': ""
        };
        $.ajax({
            url:'/tools/initPhone',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });



    this.getDeviceList = function () {
        $.ajax({
            url:'/device/getDeviceList',
            type:'get',
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    var html = "<li class=\"white\">\n" +
                        "                    <span>机械名</span>\n" +
                        "                    <span>分组</span>\n" +
                        "                    <span>状态</span>\n" +
                        "                    <span>设备型号</span>\n" +
                        "                </li>";
                    var html_list = "";
                    if(datas.data.deviceList.length > 0){
                        var result = datas.data.deviceList;
                        var class_type = "";
                        var state_name = "";
                        var yuan_type = "";
                        for(var i = 0;i<result.length;i++){
                            if(result[i].state == 0){
                                class_type = "hui";
                                state_name = "未上线";
                                yuan_type = "yuanhui";
                            }else if(result[i].state == 2){
                                class_type = "hui";
                                state_name = "未知";
                                yuan_type = "yuanhui";
                            }else{
                                class_type = "green";
                                state_name = "已上线";
                                yuan_type = "yuan";
                            }
                            html += "<li class="+class_type+ " list-use"+" data-id="+result[i].id+" data-xing="+result[i].id+">\n" +
                                "                    <span class='left-nick'>"+result[i].nickname+"</span>\n" +
                                "                    <span class='left-group'>组1</span>\n" +
                                "                    <span>"+state_name+"</span>\n" +
                                "                    <span class='left-she'>小米1</span>\n" +
                                "                </li>";

                            html_list += "<div class='mobile clearfix' data-id="+result[i].id+" data-xing="+result[i].id+">\n" +
                                "                <div class='ri-zhi'>\n" +
                                "                    <p>第一版日志</p>\n" +
                                "                </div>\n" +
                                "                <div class='she-name' style='margin: 10px;'>\n" +
                                "                    <span class='white'>设备名称</span>\n" +
                                "                    <span class='white she-nick'>"+result[i].nickname+"</span>\n" +
                                "                </div>\n" +
                                "                <div class='group-all' style='margin: 10px;'>\n" +
                                "                    <span class='white'>组&nbsp;&nbsp;&nbsp;&nbsp;名</span>\n" +
                                "                    <span class='white she-group'>组1-抖音刷评论组</span>\n" +
                                "                </div>\n" +
                                "                <div class="+yuan_type+"></div>\n" +
                                "            </div>"
                        }

                    }
                    $(".she-bei").append(html);
                    $(".content-box").append(html_list);
                }else{
                    alert("服务器异常")
                }
            }
        })
    };

    $(this).delegate('.mobile','click',function(){
        var name = $(this).children(".she-name").children(".she-nick").text();
        var group = $(this).children(".group-all").children(".she-group").text();
        var xing = $(this).attr("data-xing");
        var id = $(this).attr("data-id");
        window.location.href = "jiaoben.html?id="+id+"&name="+name+"&group="+group+"&xing="+xing;
    });

    $(this).delegate('.list-use','click',function(){
        var name = $(this).children(".left-nick").text();
        var group = $(this).children(".left-group").text();
        var xing = $(this).attr("data-xing");
        var id = $(this).attr("data-id");
        window.location.href = "jiaoben.html?id="+id+"&name="+name+"&group="+group+"&xing="+xing;
    });

    this.socket = function () {
        var ws = new WebSocket("ws://localhost:8080//socket/device/list");
        if (ws.readyState == WebSocket.OPEN) {
            ws.onmessage = function(evt) {
                //此处先做一个打印
                console.log( "打印信息: " + evt.data);
                $('.log-jian').append();
            };
        }
    };

    this.init = function(){
        this.getPhone();
        this.getDeviceList();
        this.socket();
    };

    this.init();
});