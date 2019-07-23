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
		stepDisabled(true);
		if (code.length <= 0){
			saveDisabled(true);
		}else{
			saveDisabled(false);
		}
	}
	workspace.addChangeListener(myUpdateFunction);

	function runDisabled(bool){
		$("#operating_buts_run").css("color",bool == true ? "#525252" : "#09d296");
		$("#operating_buts_run").attr("disabled",bool == true ? true : false);
	}
	function stepDisabled(bool){
		$("#operating_buts_step").css("color",bool == true ? "#525252" : "#09d296");
		$("#operating_buts_step").attr("disabled",bool == true ? true : false);
	}
	function saveDisabled(bool){
		$("#operating_buts_save").css("color",bool == true ? "#525252" : "#ff8c1f");
		$("#operating_buts_save").attr("disabled",bool == true ? true : false);
	}

	$(this).delegate('#operating_buts_run','click',function(){
		run();
		stepDisabled(false);
		runDisabled(true);
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

	var myInterpreter = null;
	function run(){
		var code = Blockly.JavaScript.workspaceToCode(workspace);
		myInterpreter = new Interpreter(code);
		nextRun();
	}

	function nextStep() {
		alert("aaa");
		if (myInterpreter == null){
			var code = Blockly.JavaScript.workspaceToCode(workspace);
			myInterpreter = new Interpreter(code);
		}
		if (!myInterpreter.step()) {
			alert("执行完毕");
			myInterpreter = null;
		}
	}

	function nextRun() {
		if (myInterpreter.step()) {
			alert("code");
			window.setTimeout(nextRun, 10);
		}else {
			runDisabled(false);
			myInterpreter = null;
		}
	}
})