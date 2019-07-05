$(function () {
    // 链接跳转高亮
    $('.nav-a').each(function(){
        var $this = $(this);
        var href = $this[0].href.split('?')[0];
        if(String(window.location.href).indexOf(href)>-1){
            $this.parent('li').addClass('on');
        }else{
            $this.parent('li').removeClass('on');
        }

    });

    $(".gongju").mouseover(function(event) {
        $(".gongju-style").show();
    }).mouseout(function() {
        $(".gongju-style").hide();
    })
});
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

