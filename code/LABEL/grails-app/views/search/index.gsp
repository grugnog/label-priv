<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
    <title><g:message code="home.page.title"/></title>
    <asset:stylesheet src="application.css" />
    <asset:javascript src="application.js" />
    <g:external plugin="jquery-ui" dir="/jquery-ui/themes/ui-lightness/" file="jquery-ui-1.10.4.custom.min.css"/>
    <g:javascript plugin="jquery-ui" src="../jquery-ui/js/jquery-ui-1.10.4.custom.min.js"/>
    <g:javascript src="search.js"/>
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
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="center">
                <a class="logoLink" href="${createLink(uri: '/')}"><img class="logo" src="${resource(dir: "images", file: "LABEL-logo.svg")}"/></a>
            </div>
            <br/>
            <br/>
            <g:form controller="search" action="textSearchView" method="GET">
                <div>
                    <input type="text" class="searchBox" name="term" placeholder="${message(code:"search.prompt.text")}" id="termText" size="50" autocomplete="off"/>
                    <span id="uploadBarCode" style="display: none">
                        &nbsp;
                        <span class="btn btn-primary btn-sm fileinput-button" id="overrideFile">
                            <span>Upload</span>
                            <!-- The file input field used as target for the file upload widget -->
                            <input id="fileupload" type="file" name="files[]" accept="image/*;capture=camera" multiple>
                            <!-- The container for the uploaded files -->

                        </span>
                        <span id="filesUpdate" class="files"></span>
                        <div id="progress" class="progress" style="display: none">
                            <div class="progress-bar"></div>
                        </div>
                    </span>
                    <input type="submit" id="searchButton" value="${message(code:"search.button.label")}" class="btn btn-primary"/>
                    <img class="help" title="${message(code: 'advancedSearch.help.text')}" src="${resource(dir: 'images', file: 'help.png')}"/>
                    <br/>
                    <img class="optionOR" src="${resource(dir: 'images', file: 'OR.png')}" title='OR'/>
                    <br/>
                    <button class="btn btn-primary" id="uploadBarCodeButton" ><g:message code="upload.barcode.link.text" /></button>
                    <button class="btn btn-primary" id="searchText" style="display: none"><g:message code="search.term.link.text" /></button>

                </div>
            </g:form>
        </div>
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

    $(document).ready( function () {

        $("#uploadBarCodeButton").on('click', function(event) {
            $("#uploadBarCode, #searchText").show();
            $("#uploadBarCodeButton, #searchButton, #termText").hide();
            event.preventDefault();
        });

        $("#searchText").on('click', function(event) {
            $("#termText, #uploadBarCodeButton, #searchButton").show();
            $("#uploadBarCode, #searchText").hide();
            event.preventDefault();
        });
    });

</script>

</body>
</html>
