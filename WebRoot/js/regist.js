/**
 * Created by Dead_Pool on 2016/12/6.
 */

window.onload=function () {
    var veritifyimg=document.getElementById('vertifyImg');
    veritifyimg.onclick=function () {
        veritifyimg.src='VertifyCodeServlet?vertifyCode='+new Date().getTime();
    }
}
