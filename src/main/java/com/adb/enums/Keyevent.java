package com.adb.enums;

public enum Keyevent {
    UNKNOWN(0,"KEYCODE_UNKNOWN"),
    MENU(1,"KEYCODE_MENU"),
    SOFT_RIGHT(2,"KEYCODE_SOFT_RIGHT"),
    HOME(3,"KEYCODE_HOME"),
    BACK(4,"KEYCODE_BACK"),
    CALL(5,"KEYCODE_CALL"),
    ENDCALL(6,"KEYCODE_ENDCALL"),
    KEYCODE_0(7,"KEYCODE_0"),
    KEYCODE_1(8,"KEYCODE_1"),
    KEYCODE_2(9,"KEYCODE_2"),
    KEYCODE_3(10,"KEYCODE_3"),
    KEYCODE_4(11,"KEYCODE_4"),
    KEYCODE_5(12,"KEYCODE_5"),
    KEYCODE_6(13,"KEYCODE_6"),
    KEYCODE_7(14,"KEYCODE_7"),
    KEYCODE_8(15,"KEYCODE_8"),
    KEYCODE_9(16,"KEYCODE_9"),
    STAR(17,"KEYCODE_STAR"),
    POUND(18,"KEYCODE_POUND"),
    DPAD_UP(19,"KEYCODE_DPAD_UP"),
    DPAD_DOWN(20,"KEYCODE_DPAD_DOWN"),
    DPAD_LEFT(21,"KEYCODE_DPAD_LEFT"),
    DPAD_RIGHT(22,"KEYCODE_DPAD_RIGHT"),
    DPAD_CENTER(23,"KEYCODE_DPAD_CENTER"),
    VOLUME_UP(24,"KEYCODE_VOLUME_UP"),
    VOLUME_DOWN(25,"KEYCODE_VOLUME_DOWN"),
    VOLUME_MUTE(86,"KEYCODE_VOLUME_MUTE"),
    POWER(26,"KEYCODE_POWER"),
    CAMERA(27,"KEYCODE_CAMERA"),
    CLEAR(28,"KEYCODE_CLEAR"),
    COMMA(55,"KEYCODE_COMMA"),
    PERIOD(56,"KEYCODE_PERIOD"),
    ALT_LEFT(57,"KEYCODE_ALT_LEFT"),
    ALT_RIGHT(58,"KEYCODE_ALT_RIGHT"),
    SHIFT_LEFT(59,"KEYCODE_SHIFT_LEFT"),
    SHIFT_RIGHT(60,"KEYCODE_SHIFT_RIGHT"),
    TAB(61,"KEYCODE_TAB"),
    SPACE(62,"KEYCODE_SPACE"),
    SYM(63,"KEYCODE_SYM"),
    EXPLORER(64,"KEYCODE_EXPLORER"),
    ENVELOPE(65,"KEYCODE_ENVELOPE"),
    ENTER(66,"KEYCODE_ENTER"),
    DEL(67,"KEYCODE_DEL"),
    GRAVE(68,"KEYCODE_GRAVE"),
    MINUS(69,"KEYCODE_MINUS"),
    EQUALS(70,"KEYCODE_EQUALS"),
    LEFT_BRACKET(71,"KEYCODE_LEFT_BRACKET"),
    RIGHT_BRACKET(72,"KEYCODE_RIGHT_BRACKET"),
    BACKSLASH(73,"KEYCODE_BACKSLASH"),
    SEMICOLON(74,"KEYCODE_SEMICOLON"),
    APOSTROPHE(75,"KEYCODE_APOSTROPHE"),
    SLASH(76,"KEYCODE_SLASH"),
    AT(77,"KEYCODE_AT"),
    NUM(78,"KEYCODE_NUM"),
    HEADSETHOOK(79,"KEYCODE_HEADSETHOOK"),
    FOCUS(80,"KEYCODE_FOCUS"),
    PLUS(81,"KEYCODE_PLUS"),
    NOTIFICATION(83,"KEYCODE_NOTIFICATION"),
    SEARCH(84,"KEYCODE_SEARCH"),
    TAG_LAST(85,"TAG_LAST_KEYCODE");

    int start;
    String startInfo;

    public int getStart() {
        return start;
    }

    public String getStartInfo() {
        return startInfo;
    }

    Keyevent(int start, String startInfo) {
        this.start = start;
        this.startInfo = startInfo;
    }

    public static Keyevent startOf(int index){
        for (Keyevent keyevent: values()) {
            if(keyevent.getStart() == index){
                return keyevent;
            }
        }
        return null;
    }
}
