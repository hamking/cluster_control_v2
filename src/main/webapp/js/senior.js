$(function () {
    var blocklyDiv = $("#blocklyDiv")[0];
    var workspace = Blockly.inject(blocklyDiv,
        {toolbox: $("#toolbox")[0],
        zoom:
           {controls: true,
            wheel: true},
        trashcan: true,
        scrollbars: true});
    var onresize = function(e) {
        blocklyDiv.style.width = (window.innerWidth - 500) + 'px';
        blocklyDiv.style.height = (window.innerHeight - 82 - 28) + 'px';
        Blockly.svgResize(workspace);
    };
    window.addEventListener('resize', onresize, false);
    onresize();
    Blockly.svgResize(workspace);
});