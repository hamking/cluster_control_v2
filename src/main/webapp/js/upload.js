$(function(){

    this.creatDom = function(){
        this.fileObj = $('#fileMedia');
    };

    this.createEvent = function(){
        var self = this;
        self.fileObj.change(function(){
            self.upload()
        })
    };

    this.upload = function(){
        var self = this;
        var files = $('#fileMedia').prop("files");
        var formdata = new FormData();
        formdata.append("files", files[0]);
        formdata.append("uuid", "");
        formdata.append("scope", -2);
        $.ajax({
            type: "POST",
            url: "/convention/uploadFile",
            data: formdata,
            contentType: false,
            processData: false,
            dataType: "json",
            enctype: "multipart/form-data",
            success: function(data) {
              alert("上传成功！");
            },
            error: function(data) {
                alert("上传失败！");
            }
        });
    };

    this.init = function(){
        this.creatDom();
        this.createEvent();
    };

    this.init();

});