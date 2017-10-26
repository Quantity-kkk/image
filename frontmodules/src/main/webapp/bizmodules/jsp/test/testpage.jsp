<%--
  Created by IntelliJ IDEA.
  User: kyq1024
  Date: 2017/10/25
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../../css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../../css/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>

</head>
<body>

    <p>test</p>
    <div class="row">
        <input id="input-b1" name="input-b1" multiple type="file" class="file" style="margin-left: 20px">
    </div>

        <button type="button" class="btn btn-success " id="test_btn"><i class="fa fa-fw fa-file-text-o"></i>test
    </button>

    <script type="text/javascript" src="../../../bizmodules/js/jquery.min.js"></script>
    <script type="text/javascript" src="../../../bizmodules/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="../../../bizmodules/js/fileinput.min.js"></script>
    <script type="text/javascript" src="../../../bizmodules/js/locales/zh.js"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            $("#test_btn").click(function(){
                debugger;
//                var fileObj = document.getElementById("input-b1").files[0];
                var formData = new FormData();
                var files = document.getElementById("input-b1").files;
                for(var i=0; i< files.length; i++){
                    formData.append("uploadFiles" , files[i] );
                }

                $.ajax({
                    url: "/laneimage/postLaneImageFile.do",
                    type: "POST",
                    data: formData,
                    /**
                     *必须false才会自动加上正确的Content-Type
                     */
                    contentType: false,
                    /**
                     * 必须false才会避开jQuery对 formdata 的默认处理
                     * XMLHttpRequest会对 formdata 进行正确的处理
                     */
                    processData: false,
                    success: function (data) {
                        if (data.status == "true") {
                            alert("上传成功！");
                        }
                        if (data.status == "error") {
                            alert(data.msg);
                        }
                        $("#imgWait").hide();
                    },
                    error: function () {
                        alert("上传失败！");
                        $("#imgWait").hide();
                    }
                });

                /*
                //此处发起ajax请求.
                $.ajax({
                    type: 'GET',
                    url: "/laneimage/getBatchLaneImageFileInfo.do",
                    data: {
                        stationNo:1
                    },
                    success: function (data) {
                        console.info(data);
                    },
                    error:function (ret) {
                        
                    }
                });*/
//                alert("test");
            });
        });

        $("#input-b1").fileinput({
            language: 'zh', //设置语言
        });
    </script>
</body>
</html>
