$(function () {
    var id = getQueryString("id");
    var name = getQueryString("name");
    var group = getQueryString("group");
    var xing = getQueryString("xing");

    var ip = "";
    var port = "";

    var scope = "-2";

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
                    }else if(suid == "ComUgcAwemeRaises"){
                        if(datas.data.isGiveLike == 1){
                            $("#dou-zan").attr("checked",true);
                        }
                        if(datas.data.isComment == 1){
                            $("#dou-lun").attr("checked",true);
                        }
                        if(datas.data.isFocus == 1){
                            $("#dou-zhuping").attr("checked",true);
                        }
                        $("#shi-cnt").val(datas.data.focusNum);
                        if(datas.data.isFocusAuthor == 1){
                            $("#guan-zuo").attr("checked",true);
                        }
                        $("#mei-chang").val(datas.data.watchTimeInterval);
                        $("#jian-ge").val(datas.data.watchTime);
                        $("#all-kan").val(datas.data.watchNum);
                        $("#n-ge").val(datas.data.numStart);
                        $("#ping-hua").val(datas.data.commentStr);
                        $("#test").show();
                        $("#log_window").show();
                        $(".dou-hao").show();
                    }else if(suid == "ComUgcAwemePrivateMsg"){
                        $("#guan_"+datas.data.directMessagesType).click();
                        $("#xin-hua").val(datas.data.directMessages);
                        $('#si-mg').attr('src',datas.data.directMessagesImage);
                        $(".dou-xin").show();
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
        }else if(suid == "ComUgcAwemeRaises"){
            var directMessagesType = 0;
            if($("#dou-zan").is(':checked') == true){
                var isGiveLike = 1;
            }else{
                var isGiveLike = 0;
            }
            if($("#dou-lun").is(':checked') == true){
                var isComment = 1;
            }else{
                var isComment = 0;
            }
            if($("#dou-zhuping").is(':checked') == true){
                var isFocus = 1;
            }else{
                var isFocus = 0;
            }
            var focusNum = $("#shi-cnt").val();

            if($("#guan-zuo").is(':checked') == true){
                var isFocusAuthor = 1;
            }else{
                var isFocusAuthor = 0;
            }
            var watchTimeInterval = $("#mei-chang").val();
            var watchTime= $("#jian-ge").val();
            var watchNum = $("#all-kan").val();
            var numStart = $("#n-ge").val();
            var commentStr = $("#ping-hua").val();
            var directMessages = "";
            var feedingTime = 0;
            var isDirectMessages = 0;
            var isDirectMessagesOnAuthor = 0;
            var directMessagesNum = 0;
            var directMessagesImage = "";
        }else if(suid == "ComUgcAwemePrivateMsg"){
            var watchNum = 0;
            var numStart = 0
            var directMessagesNum = 0;
            var isDirectMessagesOnAuthor = $('.guan-zi input[type="radio"]:checked').val();
            var isDirectMessages = 0;
            var directMessages = $("#xin-hua").val();
            var directMessagesImage = $('#si-mg').attr('src');
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
            'scope': scope,
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
        scope = "-2";
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
        })
        scope = "-2";
    });

    function socket(id) {
        var ws = new WebSocket("ws://"+ip+":"+port+"//socket/device/log/"+id);
        ws.onmessage = function(evt) {
            if (ws.readyState != WebSocket.OPEN) {
                return;
            }
            //此处先做一个打印
            console.log( "打印信息: " + evt.data);
            $('.log-cont').append(evt.data + "<br>");
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
            if(type == "当前设备"){
                scope = "-1";
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