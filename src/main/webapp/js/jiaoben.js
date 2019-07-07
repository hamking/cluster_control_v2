$(function () {
    var id = getQueryString("id");
    var name = getQueryString("name");
    var group = getQueryString("group");
    var xing = getQueryString("xing");

    $("#name-shou").text(name);
    $("#group-shou").text(group);
    $("#xing-shou").text(xing);

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
            var use_da = $this.attr("data-id");
            $("#"+use_da).hide();
        });
        $(this).addClass("light-do");
        var use_id = $(this).attr("data-id");
        $("#"+use_id).show();
    });

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

    $(this).delegate('.script-zi','click',function(){
        var suid = $(this).attr("data-id");
        $.ajax({
            url:'/script/getScript?suid='+suid,
            type:'get',
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    $(".do-zhi").attr("data-id",datas.data.suid);
                    $(".do-ting").attr("data-id",datas.data.suid);

                    if(suid == "ComSmileGifmakerRaises"){
                        $("#sou_"+datas.data.directMessagesType).click();
                        if(datas.data.isGiveLike == 1){
                            $("#dianzan").attr("checked",true);
                        }
                        if(datas.data.isComment == 1){
                            $("#pinglun").attr("checked",true);
                        }
                        if(datas.data.isFocus == 1){
                            $("#guanzhuping").attr("checked",true);
                        }
                        $("#shicnt").val(datas.data.focusNum);
                        if(datas.data.isFocusAuthor == 1){
                            $("#guanzuozhe").attr("checked",true);
                        }
                        $("#meichang").val(datas.data.watchTimeInterval);
                        $("#jiange").val(datas.data.watchTime);
                        $("#allkan").val(datas.data.watchNum);
                        $("#nge").val(datas.data.numStart);
                        $("#pinghuashu").val(datas.data.commentStr);
                        $("#test").show();
                        $("#log_window").show();
                        $(".yang-hao").show();
                    }else if(suid == "ComSmileGifmakerPrivateMsg"){
                        $("#sizong").val(datas.data.watchNum);
                        $("#xindien").val(datas.data.numStart);
                        $("#xinmeitiao").val(datas.data.directMessagesNum);
                        $("#si_"+datas.data.isDirectMessagesOnAuthor).click();
                        $("#sixinhuashu").val(datas.data.directMessages);
                        $('#simg').attr('src',datas.data.directMessagesImage);
                        $(".si-xin").show();
                        $("#test").show();
                        $("#log_window").show();
                    }

                }else{
                    alert("服务器异常");
                }
            }
        })
    });

    $(this).delegate('.do-zhi','click',function(){
        var suid = $(this).attr("data-id");
        if(suid == "ComSmileGifmakerRaises"){
            var directMessagesType = $('.tongcheng input[type="radio"]:checked').val();
            if($("#dianzan").is(':checked') == true){
                var isGiveLike = 1;
            }else{
                var isGiveLike = 0;
            }
            if($("#pinglun").is(':checked') == true){
                var isComment = 1;
            }else{
                var isComment = 0;
            }
            if($("#guanzhuping").is(':checked') == true){
                var isFocus = 1;
            }else{
                var isFocus = 0;
            }
            var focusNum = $("#shicnt").val();

            if($("#guanzuozhe").is(':checked') == true){
                var isFocusAuthor = 1;
            }else{
                var isFocusAuthor = 0;
            }
            var watchTimeInterval = $("#meichang").val();
            var watchTime= $("#jiange").val();
            var watchNum = $("#allkan").val();
            var numStart = $("#nge").val();
            var commentStr = $("#pinghuashu").val();
            var directMessages = "";
            var feedingTime = 0;
            var isDirectMessages = 0;
            var isDirectMessagesOnAuthor = 0;
            var directMessagesNum = 0;
            var directMessagesImage = "";
        }else if(suid == "ComSmileGifmakerPrivateMsg"){
            var watchNum = $("#sizong").val();
            var numStart = $("#xindien").val();
            var directMessagesNum = $("#xinmeitiao").val();
            var isDirectMessagesOnAuthor = $('.sixinzi input[type="radio"]:checked').val();
            var isDirectMessages = 0;
            var directMessages = $("#sixinhuashu").val();
            var directMessagesImage = $('#simg').attr('src');
            var isFocus = 0;
            var isFocusAuthor = 0;
            var focusNum = 0;
            var watchTime = 0;
            var isComment = 0;
            var isGiveLike = 0;
            var commentStr = "";
            var feedingTime = 0;
            var watchTimeInterval = 0;
            var directMessagesType = 0;
        };
        var data = {
            "suid":suid,
            "isFocus":isFocus,
            "isFocusAuthor":isFocusAuthor,
            "watchNum":watchNum,
            "numStart":numStart,
            "focusNum":focusNum,
            "watchTime":watchTime,
            "isComment":isComment,
            "isGiveLike":isGiveLike,
            "commentStr":commentStr,
            "directMessages":directMessages,
            "feedingTime":feedingTime,
            "watchTimeMan":0,
            "watchTimeMin":0,
            "isDirectMessages":isDirectMessages,
            "watchTimeInterval":watchTimeInterval,
            "directMessagesNum":directMessagesNum,
            "directMessagesImage":directMessagesImage,
            "directMessagesType":directMessagesType,
            "isDirectMessagesOnAuthor":isDirectMessagesOnAuthor,
            "isOnlyDirectMessages":0,
            "individuationString":"",
            "individuationInt":0,
            "individuationVar1":0,
            "individuationVar2":0
        };
        $.ajax({
            url:'/script/saveScriptSetting',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    doExecute(suid);
                }else{
                    alert("执行失败!");
                }
            },error:function () {
                alert("服务器异常");
            }
        })
    });

    function doExecute(suid) {
        var data = {
            "scope":-1,
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
    };

    $(this).delegate('#test','click',function(){
        $(this).hide();
        $('#log_window').hide();
        $(".show-type").hide();
    });

    $(this).delegate('.do-ting','click',function(){
        var data = {
            "scope":-1,
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