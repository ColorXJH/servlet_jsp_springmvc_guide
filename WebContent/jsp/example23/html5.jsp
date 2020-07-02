<%@ page pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
<script>
    var totalFileLength, totalUploaded, fileCount, filesUploaded;

    function debug(s) {
        var debug = document.getElementById('debug');
        if (debug) {
            debug.innerHTML = debug.innerHTML + '<br/>' + s;
        }
    }

    function onUploadComplete(e) {
        totalUploaded += document.getElementById('files').
                files[filesUploaded].size;
        filesUploaded++;
        debug('complete ' + filesUploaded + " of " + fileCount);
        debug('totalUploaded: ' + totalUploaded);        
        if (filesUploaded < fileCount) {
            uploadNext();//加载下一个文件
        } else {
            var bar = document.getElementById('bar');
            bar.style.width = '100%';
            bar.innerHTML = '100% complete';
            alert('Finished uploading file(s)');
        }
    }
    
    function onFileSelect(e) {//选怎事件函数
        var files = e.target.files; // FileList object
        var output = [];
        fileCount = files.length;
        totalFileLength = 0;
        for (var i=0; i<fileCount; i++) {
            var file = files[i];
            output.push(file.name, ' (',
                  file.size, ' bytes, ',
                  file.lastModifiedDate.toLocaleDateString(), ')'
            );
            output.push('<br/>');
            debug('add ' + file.size);
            totalFileLength += file.size;
        }
        document.getElementById('selectedFiles').innerHTML = 
            output.join('');
        debug('totalFileLength:' + totalFileLength);
    }

    function onUploadProgress(e) {//上传过程函数
        if (e.lengthComputable) {
            var percentComplete = parseInt(
                    (e.loaded + totalUploaded) * 100 
                    / totalFileLength);
            var bar = document.getElementById('bar');
            bar.style.width = percentComplete + '%';
            bar.innerHTML = percentComplete + ' % complete';
        } else {
            debug('unable to compute');
        }
    }

    function onUploadFailed(e) {//上传出错函数
        alert("Error uploading file");
    }
    
    function uploadNext() {//上传文件事件
        var xhr = new XMLHttpRequest();
        var fd = new FormData();
        var file = document.getElementById('files').
                files[filesUploaded];
        fd.append("multipartFile", file);
        xhr.upload.addEventListener(//监听进度事件
                "progress", onUploadProgress, false);
        xhr.addEventListener("load", onUploadComplete, false);//一个文件上传完就触发这个事件
        xhr.addEventListener("error", onUploadFailed, false);
        xhr.open("POST", "save_file");
        debug('uploading ' + file.name);
        xhr.send(fd);
    }

    function startUpload() {
        totalUploaded = filesUploaded = 0;//初始化参数
        uploadNext();//还是上传
    }
    window.onload = function() {
        document.getElementById('files').addEventListener(//选择事件控制下方分显示
                'change', onFileSelect, false);
        document.getElementById('uploadButton').
                addEventListener('click', startUpload, false);//点击事件控制上传开始
    }
</script>
</head>
<body>
<h1>Multiple file uploads with progress bar</h1>
<div id='progressBar' style='height:20px;border:2px solid green'>
    <div id='bar' 
            style='height:100%;background:#33dd33;width:0%'>
    </div>
</div>
<form>
    <input type="file" id="files" multiple/>
    <br/>
    <output id="selectedFiles"></output>
    <input id="uploadButton" type="button" value="Upload"/>
</form>
<div id='debug' 
    style='height:100px;border:2px solid green;overflow:auto'>
</div>
</body>
</html>