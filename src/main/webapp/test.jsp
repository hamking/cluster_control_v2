<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>test</title>
</head>
<body>

<h1><%=("service info")%></h1>
<h1>服务器ip地址: <%=request.getLocalAddr()%></h1>
<h1>客户端ip地址: <%=request.getRemoteAddr()%></h1>


<h5>/home接口测试</h5>
<form id="homeform">
    <input id="uuid" type="text" name="uuid">
    <input id="scope" type="text" name="scope">
    <button type=button id="homeButton">导入</button>
</form>

<script type="application/javascript">
    $("#homeButton").click(function () {

        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",
            url: "http://<%=request.getLocalAddr()%>:<%=request.getLocalPort()%>/zciteam/convention/home",
            data: $('#homeform').serialize(),
            success: function (result) {
                console.log(result);
                alert(result.message);
            },
            error : function(meg) {
                console.log(meg);
                alert(meg.message);
            }
        });
    })
</script>


<h5>上传文件</h5>
<form enctype="multipart/form-data">
    <input type="file" id="uploadFile" name="files"/>
    <button type=button id="import">导入</button>
</form>

<script type="application/javascript">
    $("#import").click(function () {
        var files = $('#uploadFile').prop('files');
        var data = new FormData();
        data.append('files', files[0]);

        $.ajax({
            type: 'POST',
            url: "http://<%=request.getLocalAddr()%>:<%=request.getLocalPort()%>/zciteam/convention/uploadFile",
            data: data,
            dataType:"json",
            cache: false,
            processData: false,
            contentType: false,
            enctype: "multipart/form-data",
            success: function (ret) {
                console.log(ret);
                alert(result.message);
            },
            error: function (msg) {
                console.log(msg);
                alert(msg.message);
            }
        });
    });
</script>


<h5>socket测试</h5>
<input id="message" type="text">
<button id="btn">发送消息</button>
<div id="show">

</div>
<script>
    var btn = document.getElementById("btn");
    var message = document.getElementById("message");
    var show = document.getElementById("show");
    var ws = new WebSocket("ws://<%=request.getLocalAddr()%>:<%=request.getLocalPort()%>/socket/device/list");
    ws.onmessage = function (evt) {
        var node = document.createElement("div");
        node.innerHTML = "<h5>" + evt.data + "</h5>";
        show.appendChild(node);
    };
    btn.addEventListener("click", function () {
        var data = message.value;
        console.log(data);
        if (data) {
            ws.send(encodeURI(data));
        } else {
            alert("请输入消息后发送");
        }
        message.value = "";
    });
    // 关闭页面时候关闭ws
    window.addEventListener("beforeunload", function(event) {
        ws.close();
    });
</script>

</body>
</html>
