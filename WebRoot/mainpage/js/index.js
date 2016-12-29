/**
 * Created by Dead_Pool on 2016/12/8.
 */
var $title=$('.click');


$title.click(function () {
    var ul=this.nextSibling.nextSibling;
    if(ul.style.display=="block"){
        ul.style.display="none";
    }else{
    	$('ul').css("display","none");
        ul.style.display="block";
    }
});
