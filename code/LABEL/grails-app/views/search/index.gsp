<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
    <title><g:message code="home.page.title"/></title>
    <asset:stylesheet src="application.css" />
    <asset:stylesheet src="search.css"/>
    <meta name="layout" content="main"/>
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
                        <input type="file" value="${message(code:'upload.barcode.link.text')}" id="uploadBarCode" style="display: none"/>
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
</body>
</html>
