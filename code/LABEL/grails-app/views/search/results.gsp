<%--
User: Janakiram Gollapudi
Date: 6/23/15
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Search</title>
    <meta name="layout" content="main"/>
    <g:javascript src="search.js"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12">

            <div class="center">
                <img src="../images/test.png" />
                <h3><g:message code="home.page.title"/></h3>
            </div>
            <br/>
            <br/>
            <g:form controller="search" method="GET">
                <div class="search">
                    <input type="text" name="term" placeholder="${message(code:"search.prompt.text")}" size="50" value="${params.term}"/>
                    <input type="submit" id="searchButton" value="${message(code:"search.button.label")}" class="btn btn-primary"/>
                    <br/>
                    <a href="#"><g:message code="upload.barcode.link.text"/></a>
                </div>
            </g:form>
            <input type="hidden" name="term" id="term" value="${term}"/>
            <br/>
            <br/>
            <br/>
            <table id="labelTable" class="table">

            </table>

        </div>
    </div>
</div>


</body>
</html>