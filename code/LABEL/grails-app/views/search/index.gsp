<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
    <title><g:message code="home.page.title"/></title>
    <asset:stylesheet src="application.css" />
    <asset:stylesheet src="search.css"/>
    <meta name="layout" content="main"/>
    <g:javascript src="file-upload/vendor/jquery.ui.widget.js" />
    <g:javascript src='file-upload/vendor/jquery.ui.widget.js' />
    <g:javascript src='file-upload/jquery.iframe-transport.js' />
    <g:javascript src='file-upload/jquery.fileupload.js' />
    <g:javascript src='file-upload/jquery.fileupload-process.js' />
    <g:javascript src='file-upload/jquery.fileupload-audio.js' />
    <g:javascript src='file-upload/jquery.fileupload-video.js' />
    <g:javascript src='file-upload/jquery.fileupload-validate.js' />
</head>
<body>
<div class="container centerSearch">


            <div class="row">
                <div class="center">
                    <!--TODO: BEN Here we need LABEL logo. Once you put logo remove below h3 tag -->
                    <img src="../images/test.png" />
                    <h3><g:message code="home.page.title"/></h3>
                </div>
                <br/>
                <br/>
                <g:form controller="search" action="index" method="GET">
                    <div>
                        <!--TODO:BEN This is search text box in home page, we have to add search icon inside textbox as shown in mockup  -->
                        <input type="text" name="term" placeholder="${message(code:"search.prompt.text")}" id="termText" size="50"/>
                        <!--TODO:BEN As shown in mockiup we have to add '- OR -' between search box and upload image button. I am not sure if this is image or we need text  -->
                        %{--<input type="file" value="${message(code:'upload.barcode.link.text')}" id="uploadBarCode" name="uploadBarCode" style="display: none"/>--}%
                        <span id="uploadBarCode" style="display: none">
                            &nbsp;
                            <span class="btn btn-primary btn-sm fileinput-button" id="overrideFile">
                                <span>Upload</span>
                                <!-- The file input field used as target for the file upload widget -->
                                <input id="fileupload" type="file" name="files[]" multiple>
                                <!-- The container for the uploaded files -->

                            </span>
                            <span id="filesUpdate" class="files"></span>
                            <div id="progress" class="progress" style="display: none">
                                <div class="progress-bar"></div>
                            </div>
                        </span>
                        <input type="submit" id="searchButton" value="${message(code:"search.button.label")}" class="btn btn-primary"/>
                        <!--TODO:BEN This is help icon beside search button   -->
                        <img src="help.png" />
                        <br/>

                        <button class="btn btn-primary" id="uploadBarCodeButton" ><g:message code="upload.barcode.link.text" /></button>
                        <button class="btn btn-primary" id="searchText" style="display: none"><g:message code="search.term.link.text" /></button>

                    </div>
                </g:form>
            </div>

        <div class="masthead">
            <ul class="nav nav-pills">
                <li class="active"><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">Help</a></li>
            </ul>
        </div>

</div>

<script type="text/javascript">
    $(function () {
        'use strict';
        // Change this to the location of your server-side upload handler:
        var url = 'search/processBarCodeImage';
        var jqXHR = null

        $('#fileupload').fileupload({
            url: url,
            dataType: 'json',
            acceptFileTypes: /(\.|\/)(jpg|jpeg|gif|png)$/i,
            done: function (e, data) {
                $('#overrideFile').hide();
                $.each(data.result, function (index, file) {
                    $("#filesUpdate").html("");
                    $('#progress').hide();
                    $('#termText').val("#openfda.upc:" + data.result.code);
                    $('#termText').show();
                    $('#searchButton').click();
                });
            },
            progressall: function (e, data) {
                $('#progress').show();
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#progress .progress-bar').css(
                        'width',
                        progress + '%'
                );
            }
        }).on('fileuploadprocessalways', function (e, data) {
                    var index = data.index,
                            file = data.files[index]
                    if (file.error) {
                        if(file.error == "TYPE_NOT_ALLOWED") {
                            file.error = "File type not supported. Please select a [jpg|jpeg|gif|png] file.";
                        }
                        $("#filesUpdate").html("");
                        $("#filesUpdate")
                                .append("<br>")
                                .append("<br>")
                                .append($('<span class="text-danger"/>').text(file.error));
                    }
        })
    });

</script>

</body>
</html>
