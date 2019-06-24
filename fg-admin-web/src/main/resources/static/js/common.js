//通用ajax请求函数,需要用promise接收
function getAjaxPromise(url,data,type){

    return new Promise(function(res,rej){
        $.ajax({
            url: url,
            data: data,
            type: type,
            success: function(data) {
                res(data);
            }
        });
    });
}
//通用ajax请求函数,数组版本,需要用promise接收
function getAjaxPromiseForArray(url,data,type){

    return new Promise(function(res,rej){
        $.ajax({
            url: url,
            data: data,
            type: type,
            dataType: "json",
            traditional: true,
            success: function(data) {
                res(data);
            }
        });
    });
}
//通用ajax请求函数,文件上传版本,需要用promise接收
function getAjaxPromiseForForm(url,form,type){

    return new Promise(function(res,rej){
        $.ajax({
            url: url,
            data: form,
            type: type,
            dataType: "json",
            contentType : false,
            processData : false,
            success: function(data) {
                res(data);
            }
        });
    });
}

//gritter抽取
//显示自动隐藏的gritter
function showGritter(title,text){
    $.gritter.add({
        title: title,
        text: text,
        sticky: false,
        time: 3000
    });
    return false;
}
//不会隐藏的gritter
function showStikyGritter(title,text){
    $.gritter.add({
        title: title,
        text: text,
        sticky: false,
        time: 3000
    });
    return false;
}
//隐藏所有gritter
function removeAllGritters(){
    $.gritter.removeAll();
    return false;
}