$(function () {

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
		var name=prompt("请输入脚本名称:");
		var code = Blockly.JavaScript.workspaceToCode(workspace);
		if (name!=null && name!=""){
			var xml = Blockly.Xml.workspaceToDom(workspace);
			var xml_text = Blockly.Xml.domToText(xml);
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
	});
});