$(function () {

	var id = null;
	try {
		id = getQueryString("id");
	}catch (e) {}
	var ip;
	var port;

	getIp();
	var workspace = Blockly.inject('blockly_div',
		{
			toolbox: $("#toolbox")[0],
			trashcan: true,
			scrollbars: true
		});

	function myUpdateFunction(event) {
		var code = Blockly.JavaScript.workspaceToCode(workspace);
		$("#script_code_view").val(code);
		if (code.length <= 0){
			saveDisabled(true);
			runDisabled(true);
		}else{
			saveDisabled(false);
            runDisabled(false);
		}
	}
	workspace.addChangeListener(myUpdateFunction);

	function initWork(id) {
		if (id != null) {
			$.ajax({
				url: '/scriptedit/getScript',
				type: 'post',
				data: {"suid": id},
				dataType: 'json',
				success: function (datas) {
					if (datas.code == 200) {
						Blockly.mainWorkspace.clear();
						var xml = Blockly.Xml.textToDom(datas.data.workxml);
						Blockly.Xml.domToWorkspace(xml, workspace);
					} else {
						alert("服务器异常");
					}
				}
			})
		}
	}

	initWork(id);

	function runDisabled(bool){
		$("#operating_buts_run").css("color",bool == true ? "#525252" : "#09d296");
		$("#operating_buts_run").attr("disabled",bool == true ? true : false);
	}

	function saveDisabled(bool){
		$("#operating_buts_save").css("color",bool == true ? "#525252" : "#ff8c1f");
		$("#operating_buts_save").attr("disabled",bool == true ? true : false);
	}

	$(this).delegate('#operating_buts_run','click',function(){
		var code = Blockly.JavaScript.workspaceToCode(workspace);
		var data = {
			"code":code,
		};
		$.ajax({
			url:'/scriptedit/run',
			type:'post',
			data:data,
			dataType:'json',
			success:function(datas){
				if(datas.code == 200){
					alert("正在运行！");
				}else{
					alert(datas.message)
				}
			}
		});
	});

	$(this).delegate('#operating_buts_stop','click',function(){
		$.ajax({
			url:'/scriptedit/stop',
			type:'get',
			data:null,
			dataType:'json',
			success:function(datas){
				if(datas.code == 200){
					alert("停止运行！");
				}else{
					alert("服务器异常")
				}
			}
		});
	});

	$(this).delegate('#operating_buts_save','click',function(){
		var xml = Blockly.Xml.workspaceToDom(workspace);
		var xml_text = Blockly.Xml.domToText(xml);
		var code = Blockly.JavaScript.workspaceToCode(workspace);
		if (id != null){
			var data = {
				"suid": id,
				"code": code,
				"workxml": xml_text,
			};
			$.ajax({
				url: '/scriptedit/update',
				type: 'post',
				data: data,
				dataType: 'json',
				success: function (datas) {
					if (datas.code == 200) {
						alert("以保存!");
					} else {
						alert("执行失败!");
					}
				}, error: function () {
					alert("服务器异常");
				}
			})
		}else{
			var name=prompt("请输入脚本名称:");
			if (name!=null && name!=""){
				var data = {
					"code":code,
					"workxml":xml_text,
					"scriptName":name,
				};
				$.ajax({
					url:'/scriptedit/save',
					type:'post',
					data:data,
					dataType:'json',
					success:function(datas){
						if(datas.code == 200){
							alert("保存成功！");
						}else{
							alert("服务器异常")
						}
					}
				});
			}
		}
	});

	function socket() {
		var ws = new WebSocket("ws://"+ip+":"+port+"//socket/device/log/editDeviceUuid");
		ws.onmessage = function(evt) {
			if (ws.readyState != WebSocket.OPEN) {
				return;
			}
			var result = JSON.parse(evt.data);
			$('.log-cont').prepend(result.msg + "<br>");
		};
	};

	function getIp() {
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
});