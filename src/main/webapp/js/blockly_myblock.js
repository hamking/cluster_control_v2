$(function () {
    Blockly.Blocks['start'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("开始程序");
            this.appendDummyInput()
                .appendField("请输入手机序号")
                .appendField(new Blockly.FieldTextInput("88MFDMD332YV"), "uuid");
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_xpath'] = {
        init: function() {
            this.appendValueInput("element_xpath")
                .appendField("根据xpath查找元素")
                .appendField("请输入xpath")
                .appendField(new Blockly.FieldTextInput("xpath"), "xpath")
                .setCheck("element");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_xpaths'] = {
        init: function() {
            this.appendValueInput("element_xpaths")
                .appendField("根据xpath查找元素数组")
                .appendField("请输入xpath")
                .appendField(new Blockly.FieldTextInput("xpath"), "xpath")
                .setCheck("element");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_text_xpaths'] = {
        init: function() {
            this.appendValueInput("element_text_xpaths")
                .appendField("根据text查找元素数组")
                .appendField("请输入text")
                .appendField(new Blockly.FieldTextInput("text"), "text")
                .setCheck("element");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_text_xpath'] = {
        init: function() {
            this.appendValueInput("element_text_xpath")
                .appendField("根据text查找元素")
                .appendField("请输入text")
                .appendField(new Blockly.FieldTextInput("text"), "text")
                .setCheck("element");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_sup_xpath'] = {
        init: function() {
            this.appendValueInput("element_sup_xpath")
                .appendField("根据xpath查找控件的父级")
                .appendField("请输入xpath")
                .appendField(new Blockly.FieldTextInput("xpath"), "xpath")
                .setCheck("element");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_isExist_xpath'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("根据text查找控件是否存在")
                .appendField(new Blockly.FieldTextInput("请输入text"), "text");
            this.setOutput(true, "Boolean");
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_none'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("无模拟操作");
            this.setOutput(true, "element");
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_click'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("点击");
            this.setOutput(true, "element");
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_click_offsetx'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("x轴偏移")
                .appendField(new Blockly.FieldTextInput("offset_X"), "offsetx")
                .appendField("像素 点击")
            this.setOutput(true, "element");
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_click_offsety'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("y轴偏移")
                .appendField(new Blockly.FieldTextInput("offset_Y"), "offsety")
                .appendField("像素 点击");
            this.setOutput(true, "element");
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_click_offsetx_offsety'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("x轴偏移")
                .appendField(new Blockly.FieldTextInput("offset_X"), "offsetx")
                .appendField("像素")
                .appendField("，y轴偏移")
                .appendField(new Blockly.FieldTextInput("offset_Y"), "offsety")
                .appendField("像素 点击");
            this.setOutput(true, "element");
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_send_text'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("发送文字")
                .appendField(new Blockly.FieldTextInput("send_text"), "sendText")
            this.setOutput(true, "element");
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_wait'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("延时")
                .appendField(new Blockly.FieldTextInput("0"), "wait")
                .appendField("毫秒");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['device_back'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("返回");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['device_home'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("首页");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['device_kill'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("清空全部进程");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['device_swipeup'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("上滑操作");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['device_swipedown'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("下滑操作");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['device_swipedown'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("下滑操作");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['device_switchkey'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("切换键盘")
                .appendField(new Blockly.FieldDropdown([["系统键盘","system_key"], ["ADB键盘","system_adb"]]), "type");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['device_lock'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("锁屏");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['device_unlock'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("解锁");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };






    Blockly.Lua['start'] = function(block) {
        var text_uuid = block.getFieldValue('uuid');
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        return code;
    };

    Blockly.Lua['element_xpath'] = function(block) {
        var text_xpath = block.getFieldValue('xpath');
        var value_element_xpath = Blockly.Lua.valueToCode(block, 'element_xpath', Blockly.Lua.ORDER_ATOMIC);
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        return code;
    };

    Blockly.Lua['element_xpaths'] = function(block) {
        var text_xpath = block.getFieldValue('xpath');
        var value_element_xpath = Blockly.Lua.valueToCode(block, 'element_xpaths', Blockly.Lua.ORDER_ATOMIC);

        // TODO: Assemble JavaScript into code variable.
        var code = '...\n';
        // TODO: Change ORDER_NONE to the correct strength.
        return code;
    };

    Blockly.Lua['element_text_xpaths'] = function(block) {
        var text_text = block.getFieldValue('text');
        var value_element_xpath = Blockly.Lua.valueToCode(block, 'element_xpath', Blockly.Lua.ORDER_ATOMIC);
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        // TODO: Change ORDER_NONE to the correct strength.
        return code;
    };

    Blockly.Lua['element_text_xpath'] = function(block) {
        var text_text = block.getFieldValue('text');
        var value_element_text_xpath = Blockly.Lua.valueToCode(block, 'element_text_xpath', Blockly.Lua.ORDER_ATOMIC);
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        // TODO: Change ORDER_NONE to the correct strength.
        return code;
    };

    Blockly.Lua['element_sup_xpath'] = function(block) {
        var text_xpath = block.getFieldValue('xpath');
        var value_element_sup_xpath = Blockly.Lua.valueToCode(block, 'element_sup_xpath', Blockly.Lua.ORDER_ATOMIC);
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        // TODO: Change ORDER_NONE to the correct strength.
        return code;
    };

    Blockly.Lua['element_isExist_xpath'] = function(block) {
        var text_text = block.getFieldValue('text');
        var value_element_isexist_xpath = Blockly.Lua.valueToCode(block, 'element_isExist_xpath', Blockly.Lua.ORDER_ATOMIC);
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        // TODO: Change ORDER_NONE to the correct strength.
        return code;
    };

    Blockly.Lua['element_click'] = function() {
        // TODO: Assemble JavaScript into code variable.
        var code = '...\n';
        // TODO: Change ORDER_NONE to the correct strength.
        return [code, Blockly.Lua.ORDER_NONE];
    };

    Blockly.Lua['element_click_offsety'] = function(block) {
        var text_offsety = block.getFieldValue('offsety');
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        // TODO: Change ORDER_NONE to the correct strength.
        return [code, Blockly.Lua.ORDER_NONE];
    };

    Blockly.Lua['element_click_offsetx'] = function(block) {
        var text_offsetx = block.getFieldValue('offsetx');
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        // TODO: Change ORDER_NONE to the correct strength.
        return [code, Blockly.Lua.ORDER_NONE];
    };

    Blockly.Lua['element_click_offsetx_offsety'] = function(block) {
        var text_offsetx = block.getFieldValue('offsetx');
        var text_offsety = block.getFieldValue('offsety');
        // TODO: Assemble Lua into code variable.
        var code = '...';
        // TODO: Change ORDER_NONE to the correct strength.
        return [code, Blockly.Lua.ORDER_NONE];
    };

    Blockly.Lua['element_send_text'] = function(block) {
        var text_sendtext = block.getFieldValue('sendText');
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        // TODO: Change ORDER_NONE to the correct strength.
        return [code, Blockly.Lua.ORDER_NONE];
    };

    Blockly.Lua['element_wait'] = function(block) {
        var text_wait = block.getFieldValue('wait');
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        return code;
    };

    Blockly.Lua['device_home'] = function(block) {
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        return code;
    };

    Blockly.Lua['device_kill'] = function(block) {
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        return code;
    };

    Blockly.Lua['device_swipeup'] = function(block) {
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        return code;
    };

    Blockly.Lua['device_swipedown'] = function(block) {
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        return code;
    };

    Blockly.Lua['device_switchkey'] = function(block) {
        var dropdown_type = block.getFieldValue('type');
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        return code;
    };

    Blockly.Lua['device_lock'] = function(block) {
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        return code;
    };

    Blockly.Lua['device_unlock'] = function(block) {
        // TODO: Assemble Lua into code variable.
        var code = '...\n';
        return code;
    };

    Blockly.Lua['element_none'] = function(block) {
        // TODO: Assemble Lua into code variable.
        var code = '...';
        // TODO: Change ORDER_NONE to the correct strength.
        return [code, Blockly.Lua.ORDER_NONE];
    };
});