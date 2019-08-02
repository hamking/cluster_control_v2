var jianben;
$(function () {
    var id = getQueryString("id");
    var name = getQueryString("name");
    var group = getQueryString("group");
    var xing = getQueryString("xing");

    var ip = "";
    var port = "";

    var scope = "-1";

    $("#name-shou").text(name);
    $("#group-shou").text(group);
    $("#xing-shou").text(xing);

    //主页
    $(this).delegate('#home','click',function(){
        var data = {
            'scope': scope,
            'uuid': id
        };
        $.ajax({
            url:'/convention/home',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    // alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });
    //返回
    $(this).delegate('#return','click',function(){
        var data = {
            'scope': scope,
            'uuid': id
        };
        $.ajax({
            url:'/convention/back',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    // alert("操作成功");
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
            'scope': scope,
            'uuid': id
        };
        $.ajax({
            url:'/convention/lock',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    // alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });
    //停止运行
    $(this).delegate('#zanting','click',function(){
        var data = {
            'scope': scope,
            'uuid': id
        };
        $.ajax({
            url:'/script/stop',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    // alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });
    //清空进程
    $(this).delegate('#delete','click',function(){
        var data = {
            'scope': scope,
            'uuid': id
        };
        $.ajax({
            url:'/convention/killProgress',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    // alert("操作成功");
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
            'scope': scope,
            'uuid': id
        };
        $.ajax({
            url:'/convention/lock',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    // alert("操作成功");
                }else{
                    alert("服务器异常");
                }
            }
        })
    });
    //重启设备
    $(this).delegate('#restart','click',function(){
        var data = {
            'scope': scope,
            'uuid': id
        };
        $.ajax({
            url:'/convention/reboot',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    // alert("操作成功");
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
            'scope': scope,
            'uuid': id
        };
        $.ajax({
            url:'/convention/volume',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    // alert("操作成功");
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
            var use_da = $this.attr("data-id");
            $("#"+use_da).hide();
        });
        $(this).addClass("light-do");
        var use_id = $(this).attr("data-id");
        $("#"+use_id).show();
    });

    (function () {
        jianben ={
            doExecute:function (suid,code) {
                var data = {
                    "scope": scope,
                    "suid":suid,
                    "uuid":id
                };
                $.ajax({
                    url:'/script/run',
                    type:'post',
                    data:data,
                    dataType:'json',
                    success:function(datas){
                        if(datas.code == 200){
                            alert("正在执行！");
                            $("#test").hide();
                            $('#log_window').hide();
                            $(".show-type").hide();
                        }else{
                            alert("执行失败!");
                        }
                    },error:function () {
                        alert("服务器异常");
                    }
                })
            }
        }
    })();

    this.getScriptList = function () {
        $.ajax({
            url:'/script/getScriptList',
            type:'get',
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    var html = "";
                    var cao = "";
                    var result = datas.data.scriptList;
                    for(var i= 0;i<datas.data.scriptList.length;i++) {
                        html += "<li>\n" +
                            "<span class='script-do' data-id=" + result[i].scriptName + ">" + result[i].scriptName + "</span>\n" +
                            "</li>";
                        var scriptDetails = result[i].scriptDetails;
                        cao += "<div class='script-use' id = " + result[i].scriptName + "  data-id=" + result[i].scriptName + " style='display: none;'>";
                        for (var j = 0; j < scriptDetails.length; j++){

                            if (j ==0){
                                cao +=  "<span class='script-zi' data-id=" + scriptDetails[j].suid + ">" + scriptDetails[j].scriptName + "</span>\n";
                            }else{
                                cao +=  "<span>|</span><span class='script-zi' data-id=" + scriptDetails[j].suid + ">" + scriptDetails[j].scriptName + "</span>\n"
                            }
                        }
                        cao +=  "</div>";
                    }

                    $(".script").append(html);
                    $(".cao-zuo").append(cao);

                }else{
                    alert("服务器异常");
                }
            }, error : function() {
           alert("数据异常！");
               }
    })
    };

    $(this).delegate('#test','click',function(){
        $(this).hide();
        $('#log_window').hide();
        $(".show-type").hide();
    });

    $(this).delegate('.do-ting','click',function(){
        var data = {
            'scope': scope,
            "uuid":id
        };
        $.ajax({
            url:'/script/stop',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("停止成功！");
                    $("#test").hide();
                    $('#log_window').hide();
                    $(".show-type").hide();
                }else{
                    alert("停止失败！");
                }
            },error:function () {
                alert("服务器异常");
            }
        })
    });

    $(this).delegate('.dang-she','click',function () {
        $(this).removeClass("qie-hui");
        $(this).addClass("qie-se");
        $(".kong-she").removeClass("qie-se");
        $(".kong-she").addClass("qie-hui");
        $(".group-cont").hide();
        scope = "-1";
    });

    $(this).delegate('.kong-she','click',function () {
        $(this).removeClass("qie-hui");
        $(this).addClass("qie-se");
        $(".dang-she").removeClass("qie-se");
        $(".dang-she").addClass("qie-hui");
        $("#select-group").removeAttr();
        var html = "";
        $.ajax({
            url:'/device/getGroudList',
            type:'get',
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    for (var i = 0; i < datas.data.deviceList.length; i++) {
                        html += "<option value="+datas.data.deviceList[i]+">"+datas.data.deviceList[i]+"</option>";
                    }
                    $("#select-group").append(html);
                    $(".group-cont").show();
                }else{
                    alert("获取分组失败！");
                }
            },error:function () {
                alert("服务器异常");
            }
        });
        scope = "-2";
    });

    function socket(id) {
        var ws = new WebSocket("ws://"+ip+":"+port+"//socket/device/log/"+id);
        ws.onmessage = function(evt) {
            if (ws.readyState != WebSocket.OPEN) {
                return;
            }
            var result = JSON.parse(evt.data);
            //此处先做一个打印
            $('.log-cont').prepend(result.msg + "<br>");
        };
    };

    this.getIp = function () {
        $.ajax({
            url:'/tools/getAddrPort',
            type:'get',
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    ip = datas.data.addr;
                    port = datas.data.port;
                }else{
                    alert("获取IP失败！");
                }
                socket(id);
            },error:function () {
                alert("服务器异常");
            }
        })
    };
    this.creatDom = function(){
        this.seleObj = $('#select-group');
    };

    this.createEvent = function(){
        var self = this;
        self.seleObj.change(function(){
            var type = $("#select-group").find("option:selected").text();
            $(".kong-she").text(type);
            if(type == "当前设备") {
                scope = "-1";
            }else if (type == "全部设备"){
                scope = "-2";
            }else{
                scope = type;
            }
        });
    };

    this.init = function(){
        this.creatDom();
        this.createEvent();
        this.getIp();
        this.getScriptList();
    };

    this.init();


});