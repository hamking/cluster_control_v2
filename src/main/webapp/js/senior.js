$(function () {
        var workspace = Blockly.inject('blockly_div',
        {toolbox: $("#toolbox")[0],
        zoom:
           {controls: true,
            wheel: true},
        trashcan: true,
        scrollbars: true});
})