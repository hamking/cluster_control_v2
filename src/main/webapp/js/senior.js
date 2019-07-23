$(function () {

	var workspace = Blockly.inject('blockly_div',
		{
			toolbox: $("#toolbox")[0],
			zoom:{controls: true,wheel: true},
			trashcan: true,
			scrollbars: true
		});

	function myUpdateFunction(event) {
		var code = Blockly.JavaScript.workspaceToCode(workspace);
		$("#script_code_view").html(code.replace(/\n/g,"<br>"));
		$("#operating_buts_step").css("color","#525252");
		$("#operating_buts_step").attr("disabled",true);
	}
	workspace.addChangeListener(myUpdateFunction);

	$(this).delegate('#operating_buts_run','click',function(){
		run();
		$("#operating_buts_step").css("color","#09d296");
		$("#operating_buts_step").attr("disabled",false);
		$("#operating_buts_run").css("color","#525252");
		$("#operating_buts_run").attr("disabled",true);
	})

	$(this).delegate('#operating_buts_step','click',function(){
		nextStep();
	})

	$(this).delegate('#operating_buts_stop','click',function(){
		alert("sdfa");
	})

	$(this).delegate('#operating_buts_save','click',function(){
		alert("sdfa");
	})

	var myInterpreter;
	function run(){
		var code = Blockly.JavaScript.workspaceToCode(workspace);
		myInterpreter = new Interpreter(code);
		nextRun();
	}

	function nextStep() {
		alert("aaa");
		if (!myInterpreter.step()) {
			alert("执行完毕");
		}
	}

	function nextRun() {
		if (myInterpreter.step()) {
			alert("code");
			window.setTimeout(nextRun, 10);
		}else {
			$("#operating_buts_run").css("color","#09d296");
			$("#operating_buts_run").attr("disabled",false);
		}
	}
})