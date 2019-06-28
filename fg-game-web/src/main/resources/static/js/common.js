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
/*
封装上传函数
1.请求地址
2.file表单对象
3.需要回显的标签,要span/div之类的
4.需要回显的img标签
5.需要设置value的input标签
 */
function handleUploadUrl(reqUrl,fileObj,reShowNameObj,reShowPicObj,reSetValueObj){
    var obj = $(fileObj);
    var fileName = obj.val();		                                           //上传的本地文件绝对路径
    if (fileName == null || fileName == "") return;
    return new Promise(function(proRes,rej){
        //设置文件名的回显
        try {
            $(reShowNameObj).text(fileName.substring(fileName.lastIndexOf("\\")+1, fileName.length));
        }catch (e) {
            console.log(e);
        }
        var suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length);//后缀名
        var file = obj.get(0).files[0];	                                           //上传的文件
        var size = file.size > 1024 ? file.size / 1024 > 1024 ? file.size / (1024 * 1024) > 1024 ? (file.size / (1024 * 1024 * 1024)).toFixed(2) + 'GB' : (file.size
            / (1024 * 1024)).toFixed(2) + 'MB' : (file.size
            / 1024).toFixed(2) + 'KB' : (file.size).toFixed(2) + 'B';		   //文件上传大小
        //七牛云上传
        $.ajax({
            type: 'post',
            url: reqUrl,
            data: {"suffix": suffix},
            dataType: 'json',
            success: function (result) {
                if (result.success == 1) {
                    var observer = {                         //设置上传过程的监听函数
                        next(result) {                        //上传中(result参数带有total字段的 object，包含loaded、total、percent三个属性)
                            var process = Math.floor(result.total.percent);//查看进度[loaded:已上传大小(字节);total:本次上传总大小;percent:当前上传进度(0-100)]
                            //console.log("上传进度:" + process);
                            $.AMUI.progress.set(process/100);
                        },
                        error(err) {                          //失败后
                            showGritter("消息", "上传失败:" + err.message);
                        },
                        complete(res) {                       //成功后
                            // ?imageView2/2/h/100：展示缩略图，不加显示原图
                            // ?vframe/jpg/offset/0/w/480/h/360：用于获取视频截图的后缀，0：秒，w：宽，h：高
                            //$("#image").attr("src", result.domain + result.imgUrl + "?imageView2/2/w/400/h/400/q/100");
                            console.log("上传成功的结果:")
                            //返回了hash和文件地址后半部分
                            //{hash: "FgO9AXuUxlXPRxvYIDheZKeXYvHM", key: "house/90b20d91-fcbd-4a7a-96e4-e63fcaa5a60d.png"}
                            console.log(res);
                            var returnUrl = result.domain + result.imgUrl + "?imageslim";
                            showGritter("消息", "上传成功!");
                            try {
                                $(reShowPicObj).attr("src",returnUrl);
                                $(reSetValueObj).val(returnUrl);
                            }catch (e) {
                                console.log(e);
                            }
                            proRes(returnUrl);
                        }
                    };
                    var putExtra = {
                        fname: "",                          //原文件名
                        //fname: fileName,
                        params: {},                         //用来放置自定义变量
                        //params: {"username" : "1"},
                        mimeType: null
                        //mimeType: null                      //限制上传文件类型
                    };
                    var config = {
                        region: qiniu.region.z0,             //存储区域(z0:代表华东;z2:代表华南,不写默认自动识别)
                        concurrentRequestLimit: 3            //分片上传的并发请求量
                    };
                    var observable = qiniu.upload(file, result.imgUrl, result.token, putExtra, config);
                    var subscription = observable.subscribe(observer);          // 上传开始
                    // 取消上传
                    // subscription.unsubscribe();
                } else {
                    alert(result.message);                  //获取凭证失败
                    rej("获取凭证失败")
                }
            }, error: function () {                             //服务器响应失败处理函数
                rej("服务器繁忙")
            }
        });
    });

}