
var tragleSelecturl;
var addurl;
var suburl;
var delurl;

var curallprice=document.getElementsByName("itemallprice");
var selectitem=document.getElementsByName("selectitem");
var ap=document.getElementById("jieAllPrice");
var sc=document.getElementById("select");
var bookids=document.getElementsByName("bookid");

function getCurIndex(cur){
	var curitem;
	var len=selectitem.length;
	for(var i=0;i<len;i++){
		if(selectitem[i]==cur){
			curitem=i;
		}
	}
	return curitem;
}
function addItemToSelect(cur,curitem,param){
	if(cur.checked){
		param.allprice+=parseInt(curallprice[curitem].innerHTML);
		param.select++;
	}
}
function targle(cur,option){
	var curitem=getCurIndex(cur);
	if(!tragleSelecturl){
		tragleSelecturl=option.url;
	}
	var isselect=cur.checked;
	option.url=tragleSelecturl+"&bookid="+bookids[curitem].value+"&select="+isselect;
	ajax(option);
}
function changeBottomAllPrice(allprice,select){
	ap.innerHTML="￥"+allprice;
	sc.innerHTML=select;
}
function checkAllToFluch(){
	var len=selectitem.length;
	var flag=true;
	for(var i=0;i<len;i++){
		if(!selectitem[i].checked){
			flag=false;
			break;
		}
	}
	if(flag&&len!=0){
		document.getElementById("gou").checked=true;
	}else{
		document.getElementById("gou").checked=false;
	}
	var param={"allprice":0,"select":0};
	var len=selectitem.length;
	for(var i=0;i<len;i++){
		addItemToSelect(selectitem[i],i,param);
	}
	changeBottomAllPrice(param.allprice,param.select);
}
function getcurbyclassname(cur,classname){
	var index=-1;
	var items=document.getElementsByClassName(classname);
	var len=items.length;
	for(var i=0;i<len;i++){
		if(items[i]==cur){
			index=i;
		}
	}
	return index;
}

function add(addoption,addparam,cur){
	var index=getcurbyclassname(cur,"control_right");
	var pritems=parseInt(document.getElementsByClassName("icount")[index].value)+1;
	document.getElementsByClassName("icount")[index].value=pritems;

	if(!addurl){
		addurl=addoption.url;
	}
	addoption.url=addurl+"&"+addparam;
	ajax(addoption);
}

function sub(addoption,subparam,cur,count){
	if(count==1)return ;
	
	var index=getcurbyclassname(cur,"control_left");
	var pritems=parseInt(document.getElementsByClassName("icount")[index].value)-1;
	document.getElementsByClassName("icount")[index].value=pritems;
	
	if(!suburl){
		suburl=addoption.url;
	}
	addoption.url=suburl+"&"+subparam;
	ajax(addoption);
}
function del(deloption,urlparam){
	if(!delurl){
		delurl=deloption.url;
	}
	deloption.url=delurl+urlparam;
	ajax(deloption);
}
function selectAll(cur,option){
	var len=selectitem.length;
	if(cur.checked){
		for(var i=0;i<len;i++){
			selectitem[i].checked=true;
			targle(selectitem[i],option)
		}
	}else{
		for(var i=0;i<len;i++){
			selectitem[i].checked=false;
			targle(selectitem[i],option)
		}
	}
}
function delAll(){
	var allbutton=document.getElementsByClassName("s_b2");
	var len=allbutton.length;
	for(var i=0;i<len;i++){
		if(selectitem[i].checked)
			allbutton[i].click();
	}
}