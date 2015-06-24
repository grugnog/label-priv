<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="home.page.title"/></title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="search.css"/>
</head>
<body>
<div class="container centerSearch">
    <div class="row vertical-center-row">
        <div class="col-sm-12">
            <div class="row">
                <div class="center">
                    <img src="../images/test.png" />
                    <h3><g:message code="home.page.title"/></h3>
                </div>
                <br/>
                <br/>
                <g:form controller="search" method="GET">
                    <div>
                        <input type="text" name="term" placeholder="${message(code:"search.prompt.text")}" size="50"/>
                        <input type="submit" id="searchButton" value="${message(code:"search.button.label")}" class="btn btn-primary"/>
                        <br/>
                        <a href="#"><g:message code="upload.barcode.link.text"/></a>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
