function ajax(option){
	var xmlHttp=createXMLHttpRequest();
	
	if(option.method==undefined){
		option.method="GET";
	}
	if(option.asyn==undefined){
		option.asyn=true;
	}
	//alert(option.method);alert(option.params);
	xmlHttp.open(option.method, option.url,option.asyn);
	if("POST"==option.method){
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	}
	
	xmlHttp.send(option.params);
	
	xmlHttp.onreadystatechange=function(){
		var data;
		if(xmlHttp.readyState==4&&xmlHttp.status==200){
			if(!option.type){
				data=xmlHttp.responseText;
			}else if(option.type=="json"){
				var text=xmlHttp.responseText;
				data=eval(text);
			}
			option.callback(data);
		}
	}
}
function createXMLHttpRequest(){
	return new XMLHttpRequest();
}

function get(url,callback){
	var option={"type":"json","params":"null","url":url,"callback":callback};
	ajax(option);
}

function post(url,callback,params){
	var option={"type":"json","params":"null","url":url,"callback":callback,"params":params};
	ajax(option);
}