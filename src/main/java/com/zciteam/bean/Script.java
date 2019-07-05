package com.zciteam.bean;

public class Script {
    private int id;
    private String scriptName;
    private String commentStr;
    private String directMessages;
    private String suid;
    private String directMessagesImage;
    private String type;
    private String packageName;
    private int feedingTime;
    private int watchTimeMin;
    private int watchTimeMan;
    private int isGiveLike;
    private int isComment;
    private int isFocusAuthor;
    private int isFocus;
    private int isDirectMessages;
    private int watchTime;
    private int focusNum;
    private int watchTimeInterval;
    private int directMessagesType;
    private int directMessagesNum;
    private int isOnlyDirectMessages;
    private int watchNum;
    private int isDirectMessagesOnAuthor;
    private int numStart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getCommentStr() {
        return commentStr;
    }

    public void setCommentStr(String commentStr) {
        this.commentStr = commentStr;
    }

    public String getDirectMessages() {
        return directMessages;
    }

    public void setDirectMessages(String directMessages) {
        this.directMessages = directMessages;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getDirectMessagesImage() {
        return directMessagesImage;
    }

    public void setDirectMessagesImage(String directMessagesImage) {
        this.directMessagesImage = directMessagesImage;
    }

    public int getFeedingTime() {
        return feedingTime;
    }

    public void setFeedingTime(int feedingTime) {
        this.feedingTime = feedingTime;
    }

    public int getWatchTimeMin() {
        return watchTimeMin;
    }

    public void setWatchTimeMin(int watchTimeMin) {
        this.watchTimeMin = watchTimeMin;
    }

    public int getWatchTimeMan() {
        return watchTimeMan;
    }

    public void setWatchTimeMan(int watchTimeMan) {
        this.watchTimeMan = watchTimeMan;
    }

    public int getIsGiveLike() {
        return isGiveLike;
    }

    public void setIsGiveLike(int isGiveLike) {
        this.isGiveLike = isGiveLike;
    }

    public int getIsComment() {
        return isComment;
    }

    public void setIsComment(int isComment) {
        this.isComment = isComment;
    }

    public int getIsFocusAuthor() {
        return isFocusAuthor;
    }

    public void setIsFocusAuthor(int isFocusAuthor) {
        this.isFocusAuthor = isFocusAuthor;
    }

    public int getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(int isFocus) {
        this.isFocus = isFocus;
    }

    public int getIsDirectMessages() {
        return isDirectMessages;
    }

    public void setIsDirectMessages(int isDirectMessages) {
        this.isDirectMessages = isDirectMessages;
    }

    public int getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(int watchTime) {
        this.watchTime = watchTime;
    }

    public int getFocusNum() {
        return focusNum;
    }

    public void setFocusNum(int focusNum) {
        this.focusNum = focusNum;
    }

    public int getWatchTimeInterval() {
        return watchTimeInterval;
    }

    public void setWatchTimeInterval(int watchTimeInterval) {
        this.watchTimeInterval = watchTimeInterval;
    }

    public int getDirectMessagesType() {
        return directMessagesType;
    }

    public void setDirectMessagesType(int directMessagesType) {
        this.directMessagesType = directMessagesType;
    }

    public int getDirectMessagesNum() {
        return directMessagesNum;
    }

    public void setDirectMessagesNum(int directMessagesNum) {
        this.directMessagesNum = directMessagesNum;
    }

    public int getIsOnlyDirectMessages() {
        return isOnlyDirectMessages;
    }

    public void setIsOnlyDirectMessages(int isOnlyDirectMessages) {
        this.isOnlyDirectMessages = isOnlyDirectMessages;
    }

    public int getWatchNum() {
        return watchNum;
    }

    public void setWatchNum(int watchNum) {
        this.watchNum = watchNum;
    }

    public int getIsDirectMessagesOnAuthor() {
        return isDirectMessagesOnAuthor;
    }

    public void setIsDirectMessagesOnAuthor(int isDirectMessagesOnAuthor) {
        this.isDirectMessagesOnAuthor = isDirectMessagesOnAuthor;
    }

    public int getNumStart() {
        return numStart;
    }

    public void setNumStart(int numStart) {
        this.numStart = numStart;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "Script{" +
                "id=" + id +
                ", scriptName='" + scriptName + '\'' +
                ", commentStr='" + commentStr + '\'' +
                ", directMessages='" + directMessages + '\'' +
                ", suid='" + suid + '\'' +
                ", directMessagesImage='" + directMessagesImage + '\'' +
                ", type='" + type + '\'' +
                ", packageName='" + packageName + '\'' +
                ", feedingTime=" + feedingTime +
                ", watchTimeMin=" + watchTimeMin +
                ", watchTimeMan=" + watchTimeMan +
                ", isGiveLike=" + isGiveLike +
                ", isComment=" + isComment +
                ", isFocusAuthor=" + isFocusAuthor +
                ", isFocus=" + isFocus +
                ", isDirectMessages=" + isDirectMessages +
                ", watchTime=" + watchTime +
                ", focusNum=" + focusNum +
                ", watchTimeInterval=" + watchTimeInterval +
                ", directMessagesType=" + directMessagesType +
                ", directMessagesNum=" + directMessagesNum +
                ", isOnlyDirectMessages=" + isOnlyDirectMessages +
                ", watchNum=" + watchNum +
                ", isDirectMessagesOnAuthor=" + isDirectMessagesOnAuthor +
                ", numStart=" + numStart +
                '}';
    }
}
