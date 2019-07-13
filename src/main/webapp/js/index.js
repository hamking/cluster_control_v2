// 所有页面公共js
$(function(){

    var she_arr = [];
    var ip = "";
    var port = "";

    function getPhone(){
        $.ajax({
            url:'/device/getDeviceDetails',
            type:'get',
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    $("#xu-cnt").text(datas.data.authorization);
                    $("#zai-cnt").text(datas.data.online);
                    $("#li-cnt").text(datas.data.offline);
                }else{
                    alert("服务器异常")
                }
            }
        })
    };

    //主页
    $(this).delegate('#home','click',function(){
        var data = {
            'scope': "-2",
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
            'scope': "-2",
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
            'scope': "-2",
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
            'scope': "-2",
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
            'scope': "-2",
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
            'scope': "-2",
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
            'scope': "-2",
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

    //音量加/减/静音
    $(this).delegate('.volume','click',function(){
        var state = $(this).attr("data-volume");
        var data = {
            'state':state,
            'scope': "-2",
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
            'scope': "-2",
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



    function getDeviceList () {
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
                            she_arr.push(result[i].uuid);
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
                            html += "<li class="+class_type+ " list-use"+" data-id="+result[i].uuid+" data-xing="+result[i].id+" id=clas_" + result[i].uuid + ">\n" +
                                "                    <span class='left-nick' id=nick_"+result[i].uuid+">"+result[i].nickname+"</span>\n" +
                                "                    <span class='left-group' id=group_"+result[i].uuid+">"+result[i].groupId+"</span>\n" +
                                "                    <span id=state_"+result[i].uuid+">"+state_name+"</span>\n" +
                                "                    <span class='left-she' id=she_"+result[i].uuid+">"+result[i].id+"</span>\n" +
                                "                </li>";

                            html_list += "<div class='mobile clearfix' data-id="+result[i].uuid+" data-xing="+result[i].id+">\n" +
                                "                <div class='ri-zhi'>\n" +
                                "                    <p>未监控</p>\n" +
                                "                </div>\n" +
                                "                <div class='she-name' style='margin: 10px;'>\n" +
                                "                    <span class='white'>设备名称</span>\n" +
                                "                    <span class='white she-nick' id=anick_"+result[i].uuid+">"+result[i].nickname+"</span>\n" +
                                "                </div>\n" +
                                "                <div class='group-all' style='margin: 10px;'>\n" +
                                "                    <span class='white'>组&nbsp;&nbsp;&nbsp;&nbsp;名</span>\n" +
                                "                    <span class='white she-group' id=agroup_"+result[i].uuid+">"+result[i].groupId+"</span>\n" +
                                "                </div>\n" +
                                "                <div class="+yuan_type+" id=ashe_"+result[i].uuid+"></div>\n" +
                                "            </div>";

                            socket_add(result[i].uuid);
                            socket_log(result[i].uuid);
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

    $(this).delegate('.left-nick','click',function () {
        var left_click = $(this);
        var nick_old = left_click.text();
        var html = "<div class='nick-show'>" +
            "<p>重名手机</p>" +
            "<input type='text' name='nick-name' value="+nick_old+" id='nick-chang' class='spaceCnt'>" +
            "<span class='rename'>确定</span></div>";
        left_click.after(html);
    });

    $(this).delegate('.left-group','click',function () {
        var left_click = $(this);
        var html = "<div class='group-cont'>\n" +
            "                    <div class='qie-bug'><span class='new-add'>选择组</span><span class='xiu-zu'>修改组</span></div>\n" +
            "                    <input style='display: none;' type='text' name='group-name' value='' placeholder='请输入组名' style='margin-bottom: 15px' id='add-group' class='spaceCnt'>\n";
        $.ajax({
            url:'/device/getGroudList',
            type:'get',
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    html += "<select class='get-group'>";
                    for (var i = 0; i<datas.data.deviceList.length; i++){
                        html += "<option value="+datas.data.deviceList[i]+">"+datas.data.deviceList[i]+"</option>"
                    }
                    html +="</select><div class='button-group'><span class='group-add'>新增组</span><span class='group-xiu' style='display: none;'>修改组</span></div>\n" +
                        "                </div>";
                    left_click.after(html);
                }else{
                    alert("获取新增组数据失败");
                    html +="<div class='button-group'><span class='group-add'>新增组</span><span class='group-xiu' style='display: none;'>修改组</span></div>\n" +
                        "                </div>";
                    left_click.after(html);
                }
            },error:function(){
                html += "<select class='get-group'>";
                for (var i = 0; i<4; i++){
                    html += "<option value='抖音组'>抖音组</option>"
                }
                html +="</select><div class='button-group'><span class='group-add'>新增组</span><span class='group-xiu' style='display: none;'>修改组</span></div>\n" +
                    "                </div>";
                left_click.after(html);
            }
        });

    });

    $(this).delegate('.new-add','click',function (){
        $(".group-xiu").hide();
        $("#add-group").hide();
        $(".get-group").show();
        $(".group-add").show();

    });

    $(this).delegate('.xiu-zu','click',function (){
        $(".group-add").hide();
        $(".get-group").hide();
        $("#add-group").show();
        $(".group-xiu").show();
    });

    $(this).delegate('.group-add','click',function () {
        var uuid = $(this).parent().parent().parent().attr("data-id");
        var group = $(".get-group option:selected").text();
        var data = {
            "uuid":uuid,
            "groupId":group
        };
        $.ajax({
            url:'/device/addGroud',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("新建组成功！");
                    $(".group-cont").remove();
                    window.location.reload();
                }else{
                    alert("服务器异常")
                }
            }
        })
    });

    $(this).delegate('.group-xiu','click',function () {
        var uuid = $(this).parent().parent().parent().attr("data-id");
        var group = $("#add-group").val();
        var data = {
            "uuid":uuid,
            "groupId":group
        };
        $.ajax({
            url:'/device/groudRename',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("重命名成功！");
                    $(".group-cont").remove();
                    window.location.reload();
                }else{
                    alert("服务器异常")
                }
            }
        })
    });

    $(this).delegate('.rename','click',function () {
        var uuid = $(this).parent().parent().attr("data-id");
        var nick = $("#nick-chang").val();
        var data = {
            "uuid":uuid,
            "nickName":nick
        };
        $.ajax({
            url:'/device/nickNameRename',
            type:'post',
            data:data,
            dataType:'json',
            success:function(datas){
                if(datas.code == 200){
                    alert("重命名成功！");
                    $(".nick-show").remove();
                    window.location.reload();
                }else{
                    alert("服务器异常")
                }
            }
        })
    });

    $(this).delegate('.mobile','click',function(){
        var name = $(this).children(".she-name").children(".she-nick").text();
        var group = $(this).children(".group-all").children(".she-group").text();
        var xing = $(this).attr("data-xing");
        var id = $(this).attr("data-id");
        window.location.href = "jiaoben.html?id="+id+"&name="+name+"&group="+group+"&xing="+xing;
    });

    function socket () {
        var ws = new WebSocket("ws://"+ip+":"+port+"//socket/device/list");
        ws.onmessage = function(evt) {
            if (ws.readyState != WebSocket.OPEN) {
                return;
            }
                //此处先做一个打印
            console.log("打印信息: " + evt.data);
            var result = JSON.parse(evt.data);
            if (she_arr.indexOf(result.uuid) == -1) {
                getPhone();
                if (result.state == 0) {
                    class_type = "hui";
                    state_name = "未上线";
                    yuan_type = "yuanhui";
                } else if (result.state == 2) {
                    class_type = "hui";
                    state_name = "未知";
                    yuan_type = "yuanhui";
                } else {
                    class_type = "green";
                    state_name = "已上线";
                    yuan_type = "yuan";
                }

                var html = "<li class=" + class_type + " list-use" + " data-id=" + result.uuid + " data-xing=" + result.id + " id=clas_" + result.uuid + ">\n" +
                    "                    <span class='left-nick' id=nick_" + result.uuid + ">" + result.nickname + "</span>\n" +
                    "                    <span class='left-group' id=group_" + result.uuid + ">" + result.groupId + "</span>\n" +
                    "                    <span id=state_" + result.uuid + ">" + state_name + "</span>\n" +
                    "                    <span class='left-she' id=she_" + result.uuid + ">" + result.id + "</span>\n" +
                    "                </li>";

                var html_list = "<div class='mobile clearfix' data-id=" + result.uuid + " data-xing=" + result.id + ">\n" +
                    "                <div class='ri-zhi'>\n" +
                    "                    <p>未监控</p>\n" +
                    "                </div>\n" +
                    "                <div class='she-name' style='margin: 10px;'>\n" +
                    "                    <span class='white'>设备名称</span>\n" +
                    "                    <span class='white she-nick' id=anick_" + result.uuid + ">" + result.nickname + "</span>\n" +
                    "                </div>\n" +
                    "                <div class='group-all' style='margin: 10px;'>\n" +
                    "                    <span class='white'>组&nbsp;&nbsp;&nbsp;&nbsp;名</span>\n" +
                    "                    <span class='white she-group' id=agroup_" + result.uuid + ">" + result.groupId + "</span>\n" +
                    "                </div>\n" +
                    "                <div class=" + yuan_type + " id=ashe_" + result.uuid + "></div>\n" +
                    "            </div>";

                $(".she-bei").append(html);
                $(".content-box").append(html_list);
                socket_add(result.uuid);
                socket_log(result.uuid);
                she_arr.push(result.uuid);
            }
        };
    };

    function socket_add(id) {
        var ws = new WebSocket("ws://"+ip+":"+port+"//socket/device/uuid/"+id);
        ws.onmessage = function(evt) {
            if (ws.readyState != WebSocket.OPEN) {
                return;
            }
            console.log( "打印信息: " + evt.data);
            getPhone();
            var result = JSON.parse(evt.data);
            var zai_cnt = $("#zai-cnt").text();
            var li_cnt = $("#li-cnt").text();
            if(result.state == 0){
                $("#clas_"+result.uuid).removeClass();
                $("#clas_"+result.uuid).addClass("hui");
                $("#clas_"+result.uuid).addClass("list-use");
                $("#ashe_"+result.uuid).removeClass();
                $("#ashe_"+result.uuid).addClass("yuanhui");
                $("#state_"+result.uuid).text("未上线");
            }else if(result.state == 2){
                $("#clas_"+result.uuid).removeClass();
                $("#clas_"+result.uuid).addClass("hui");
                $("#clas_"+result.uuid).addClass("list-use");
                $("#ashe_"+result.uuid).removeClass();
                $("#ashe_"+result.uuid).addClass("yuanhui");
                $("#state_"+result.uuid).text("未知");
            }else{
                $("#clas_"+result.uuid).removeClass();
                $("#clas_"+result.uuid).addClass("green");
                $("#clas_"+result.uuid).addClass("list-use");
                $("#ashe_"+result.uuid).removeClass();
                $("#ashe_"+result.uuid).addClass("yuan");
                $("#state_"+result.uuid).text("已上线");
            }

            $("#nick_"+result.uuid).text(result.nickname);
            $("#group_"+result.uuid).text(result.groupId);
            $("#she_"+result.uuid).text(result.id);

            $("#anick_"+result.uuid).text(result.nickname);
            $("#agroup_"+result.uuid).text(result.groupId);

        };
    };
    function socket_log(id) {
        var ws = new WebSocket("ws://"+ip+":"+port+"//socket/device/log/"+id);
        ws.onmessage = function(evt) {
            if (ws.readyState != WebSocket.OPEN) {
                return;
            }
            console.log( "打印信息: " + evt.data);

            var result = JSON.parse(evt.data);
            var html = "<p>"+result.msg+"</p>";
            $("#anick_"+result.uuid).parent().prev().append(html);
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
                    socket();
                    getDeviceList();
                }else{
                    alert("获取IP失败！");
                }
            },error:function () {
                alert("服务器异常");
            }
        })
    };

    this.init = function(){
        this.getIp();
        getPhone();
    };

    this.init();
});