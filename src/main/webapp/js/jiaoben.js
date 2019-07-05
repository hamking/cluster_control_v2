$(function () {
    var id = getQueryString("id");

    //主页
    $(this).delegate('#home','click',function(){
        var data = {
            'scope': -2,
            'uuid': id
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
            'uuid': id
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
    $(this).delegate('#open','click',function(){
        var data = {
            'state':0,
            'scope': -2,
            'uuid': id
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
            'uuid': id
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
            'uuid': id
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
    $(this).delegate('#off','click',function(){
        var data = {
            'state':1,
            'scope': -2,
            'uuid': id
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
            'uuid': id
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
            'uuid': id
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
            'uuid': id
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

    $(this).delegate('.script-do','click',function(){
        // 链接跳转高亮
        $('.script-do').each(function(){
            var $this = $(this);
            $this.removeClass('light-do');
        });
        $(this).addClass("light-do");
    });

    this.getScriptList = function () {
        $.ajax({
            url:'/script/getScriptList',
            type:'get',
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    var html = "";
                    var result = datas.data.scriptList;
                    for(var i= 0;i<datas.data.scriptList.length;i++){
                        html += "<li>\n" +
                            "<span class='script-do'>"+result[i].scriptName+"</span>\n" +
                            "</li>"
                    }

                }else{
                    alert("服务器异常");
                }
            }
        })
    };

    this.socket = function (id) {
        var ws = new WebSocket("ws://localhost:8080//socket/device/log/"+id);
        if (ws.readyState == WebSocket.OPEN) {
            ws.onmessage = function(evt) {
                //此处先做一个打印
                console.log( "打印信息: " + evt.data);
                $('.log-jian').append();
            };
        }
    };

    this.init = function(){
        this.getScriptList();
        this.socket(id);

    };

    this.init();


});