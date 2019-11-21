$(function () {
    Blockly.Blocks['start'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("开始程序");
            this.appendDummyInput()
                .appendField("手机序号")
                .appendField(new Blockly.FieldTextInput("88MFDMD332YV"), "uuid");
            this.appendDummyInput()
                .appendField("启动包名")
                .appendField(new Blockly.FieldTextInput("com.x.x.x/action"), "action");
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
            this.setPreviousStatement(true, "element");
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_xpaths'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("根据xpath查找元素数组并循环操作");
            this.appendDummyInput()
                .appendField("请输入xpath")
                .appendField(new Blockly.FieldTextInput("xpath"), "xpath");
            this.appendDummyInput()
                .appendField("设置变量名")
                .appendField(new Blockly.FieldTextInput("请输入变量名"), "variable");
            this.appendStatementInput("element_xpaths")
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("请注意变量名必须唯一");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_local'] = {
        init: function() {
            this.appendValueInput("element_local")
                .setCheck("element")
                .appendField("xpath元素操作");
            this.setPreviousStatement(true, "element");
            this.setNextStatement(true, "element");
            this.setColour(230);
            this.setTooltip("该模块不可以单独使用");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_text_xpaths'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("根据text查找元素数组并循环操作");
            this.appendDummyInput()
                .appendField("请输入text")
                .appendField(new Blockly.FieldTextInput("text"), "text");
            this.appendDummyInput()
                .appendField("设置变量名")
                .appendField(new Blockly.FieldTextInput("请输入变量名"), "variable");
            this.appendStatementInput("element_text_xpaths")
                .setCheck(null);
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("请注意变量名必须唯一");
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
                .appendField("请输入text")
                .appendField(new Blockly.FieldTextInput("text"), "text")
                .appendField("请输入父级节点xpath")
                .appendField(new Blockly.FieldTextInput("xpath"), "xpath");
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
                .appendField(new Blockly.FieldNumber(0), "offsetx")
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
                .appendField(new Blockly.FieldNumber(0), "offsety")
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
                .appendField(new Blockly.FieldNumber(0), "offsetx")
                .appendField("像素")
                .appendField("，y轴偏移")
                .appendField(new Blockly.FieldNumber(0), "offsety")
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
                .appendField(new Blockly.FieldNumber(0), "wait")
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
                .appendField(new Blockly.FieldDropdown([["系统键盘","1"], ["ADB键盘","0"]]), "type");
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

    Blockly.Blocks['device_log'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("日志输出")
                .appendField(new Blockly.FieldTextInput("请输入相关描述"), "logstr");
            this.setPreviousStatement(true, null);
            this.setNextStatement(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };

    Blockly.Blocks['element_random_send_text'] = {
        init: function() {
            this.appendDummyInput()
                .appendField("发送个数")
                .appendField(new Blockly.FieldTextInput("1"), "number")
                .appendField(new Blockly.FieldTextInput("每段已英文符号$分割"), "texts");
            this.setOutput(true, null);
            this.setColour(230);
            this.setTooltip("");
            this.setHelpUrl("");
        }
    };






    Blockly.JavaScript['start'] = function(block) {
        var text_uuid = block.getFieldValue('uuid');
        var text_action = block.getFieldValue('action');
        var code = 'var uuid = ' + '"' + text_uuid + '";' + '\n' +
                   'var action = ' + '"' + text_action + '";' + '\n' +
                    "var AndroidStart;\n";
        return code;
    };

    Blockly.JavaScript['element_xpath'] = function(block) {
        var text_xpath = block.getFieldValue('xpath');
        var value_element_xpath = Blockly.JavaScript.valueToCode(block, 'element_xpath', Blockly.JavaScript.ORDER_NONE);
        var code = 'Auto.findByXpatch('+ '"' + text_xpath + '"' +')' + value_element_xpath + '\n';
        return code;
    };

    Blockly.JavaScript['element_xpaths'] = function(block) {
        var text_xpath = block.getFieldValue('xpath');
        var text_variable = block.getFieldValue('variable');
        var statements_element_xpaths = Blockly.JavaScript.statementToCode(block, 'element_xpaths');
        var code =
            'var '+ text_variable + ' = Auto.findByXpatchs('+ '"' + text_xpath + '"' +');' + '\n' +
            text_variable +'.forEach(function(v){\n' +
            '' + statements_element_xpaths+
            '})\n';
        return code;
    };

    Blockly.JavaScript['element_text_xpaths'] = function(block) {
        var text_text = block.getFieldValue('text');
        var text_variable = block.getFieldValue('variable');
        var statements_element_text_xpaths = Blockly.JavaScript.statementToCode(block, 'element_text_xpaths');
        // TODO: Assemble JavaScript into code variable.
        var code =
            'local '+ text_variable + ' = Auto.findByTexts('+ '"' + text_text + '"' +')' + '\n' +
            text_variable +'.forEach(function(v){\n' +
            '' + statements_element_text_xpaths+
            '})\n';
        return code;
    };

    Blockly.JavaScript['element_local'] = function(block) {
        var value_element_local = Blockly.JavaScript.valueToCode(block, 'element_local', Blockly.JavaScript.ORDER_NONE);
        // TODO: Assemble JavaScript into code variable.
        var code = 'v' + value_element_local + ';\n';
        return code;
    };

    Blockly.JavaScript['element_text_xpath'] = function(block) {
        var text_text = block.getFieldValue('text');
        var value_element_text_xpath = Blockly.JavaScript.valueToCode(block, 'element_text_xpath', Blockly.JavaScript.ORDER_NONE);
        // TODO: Assemble JavaScript into code variable.
        var code = 'Auto.findByText('+ '"' + text_text + '"' + ')' + value_element_text_xpath + '\n';
        // TODO: Change ORDER_NONE to the correct strength.
        return code;
    };

    Blockly.JavaScript['element_sup_xpath'] = function(block) {
        var text_xpath = block.getFieldValue('xpath');
        var value_element_sup_xpath = Blockly.JavaScript.valueToCode(block, 'element_sup_xpath', Blockly.JavaScript.ORDER_NONE);
        // TODO: Assemble JavaScript into code variable.
        var code = 'Auto.findByXpatchParent('+ '"' + text_xpath + '"' + ')' + value_element_sup_xpath + '\n';
        // TODO: Change ORDER_NONE to the correct strength.
        return code;
    };

    Blockly.JavaScript['element_isExist_xpath'] = function(block) {
        var text_text = block.getFieldValue('text');
        var text_xpath = block.getFieldValue('xpath');
        var code = 'Auto.isFindNoteForText('+ '"' + text_xpath + '"' + ',' + '"' + text_text + '"' +')' + '\n';
        return [code, Blockly.JavaScript.ORDER_NONE];
    };

    Blockly.JavaScript['element_click'] = function() {
        // TODO: Assemble JavaScript into code variable.
        var code = '.click()';
        // TODO: Change ORDER_NONE to the correct strength.
        return [code, Blockly.JavaScript.ORDER_NONE];
    };

    Blockly.JavaScript['element_click_offsety'] = function(block) {
        var text_offsety = block.getFieldValue('offsety');
        // TODO: Assemble JavaScript into code variable.
        var code = '.clickOffsetY('+ text_offsety +')';
        // TODO: Change ORDER_NONE to the correct strength.
        return [code, Blockly.JavaScript.ORDER_NONE];
    };

    Blockly.JavaScript['element_click_offsetx'] = function(block) {
        var text_offsetx = block.getFieldValue('offsetx');
        // TODO: Assemble JavaScript into code variable.
        var code = '.clickOffsetX('+ text_offsetx +')';
        // TODO: Change ORDER_NONE to the correct strength.
        return [code, Blockly.JavaScript.ORDER_NONE];
    };

    Blockly.JavaScript['element_click_offsetx_offsety'] = function(block) {
        var text_offsetx = block.getFieldValue('offsetx');
        var text_offsety = block.getFieldValue('offsety');
        // TODO: Assemble JavaScript into code variable.
        var code = '.click('+ text_offsetx + ',' + text_offsety +')';
        // TODO: Change ORDER_NONE to the correct strength.
        return [code, Blockly.JavaScript.ORDER_NONE];
    };

    Blockly.JavaScript['element_wait'] = function(block) {
        var text_wait = block.getFieldValue('wait');
        // TODO: Assemble JavaScript into code variable.
        var code = 'sleep('+text_wait+');\n';
        return code;
    };

    Blockly.JavaScript['device_home'] = function(block) {
        // TODO: Assemble JavaScript into code variable.
        var code = 'Auto.home();' + '\n';
        return code;
    };

    Blockly.JavaScript['device_back'] = function(block) {
        // TODO: Assemble JavaScript into code variable.
        var code = 'Auto.back();' + '\n';
        return code;
    };

    Blockly.JavaScript['device_swipeup'] = function(block) {
        // TODO: Assemble JavaScript into code variable.
        var code = 'Auto.swipeUp();' + '\n';
        return code;
    };

    Blockly.JavaScript['device_swipedown'] = function(block) {
        // TODO: Assemble JavaScript into code variable.
        var code = 'Auto.swipeDown();' + '\n';
        return code;
    };

    Blockly.JavaScript['device_switchkey'] = function(block) {
        var dropdown_type = block.getFieldValue('type');
        // TODO: Assemble JavaScript into code variable.
        var code = 'Auto.switchKey(' + dropdown_type + ');' + '\n';
        return code;
    };

    Blockly.JavaScript['device_lock'] = function(block) {
        // TODO: Assemble JavaScript into code variable.
        var code = 'Auto.lock();' + '\n';
        return code;
    };

    Blockly.JavaScript['device_unlock'] = function(block) {
        // TODO: Assemble JavaScript into code variable.
        var code = 'Auto.unlock();' + '\n';
        return code;
    };

    Blockly.JavaScript['device_log'] = function(block) {
        var text_logstr = block.getFieldValue('logstr');
        // TODO: Assemble JavaScript into code variable.
        var code = 'Auto.push('+ '"editDeviceUuid",' +'"' + text_logstr + '"' +');' + '\n';
        return code;
    };

    Blockly.JavaScript['element_none'] = function(block) {
        // TODO: Assemble JavaScript into code variable.
        var code = '';
        // TODO: Change ORDER_NONE to the correct strength.
        return [code, Blockly.JavaScript.ORDER_NONE];
    };

    Blockly.JavaScript['element_send_text'] = function(block) {
        var text_sendtext = block.getFieldValue('sendText');
        // TODO: Assemble JavaScript into code variable.
        var code = '.sendKeys('+ text_sendtext + ')';
        // TODO: Change ORDER_NONE to the correct strength.
        return [code, Blockly.JavaScript.ORDER_NONE];
    };

    Blockly.JavaScript['element_random_send_text'] = function(block) {
        var text_number = block.getFieldValue('number');
        var text_texts = block.getFieldValue('texts');
        var code = '.randomSendKeys('+ text_number + ',' + text_texts + ')';
        return [code, Blockly.JavaScript.ORDER_NONE];
    };
});