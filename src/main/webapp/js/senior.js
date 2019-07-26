$(function () {

	var workspace = Blockly.inject('blockly_div',
		{
			toolbox: $("#toolbox")[0],
			zoom:{controls: true,wheel: true},
			trashcan: true,
			scrollbars: true
		});

	function myUpdateFunction(event) {
		var code = Blockly.Lua.workspaceToCode(workspace);
		$("#script_code_view").html(code);
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
		run();
	});

	$(this).delegate('#operating_buts_stop','click',function(){
		alert("sdfa");
	});

	$(this).delegate('#operating_buts_save','click',function(){
		alert("sdfa");
	});

	function run(){
		var code = Blockly.Lua.workspaceToCode(workspace);

		alert(code);
	}
});