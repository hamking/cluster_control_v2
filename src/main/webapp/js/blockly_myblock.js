$(function () {
    Blockly.Blocks['start'] = {
        init: function() {
            this.jsonInit({
                "message0": "程序开始",
                "nextStatement": null,
                "colour": "#48cf4f",
                "tooltip": "",
                "helpUrl": ""
            })
        }
    };


    Blockly.JavaScript['start'] = function(block) {
        return 'start\n';
    };
})