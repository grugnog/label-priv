<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
    <title><g:message code="home.page.title"/></title>
    <asset:stylesheet src="application.css" />
    <asset:stylesheet src="search.css"/>
    <asset:javascript src="application.js" />
    <g:external plugin="jquery-ui" dir="/jquery-ui/themes/ui-lightness/" file="jquery-ui-1.10.4.custom.min.css"/>
    <g:javascript plugin="jquery-ui" src="../jquery-ui/js/jquery-ui-1.10.4.custom.min.js"/>
    <g:javascript src="search.js"/>
</head>
<body>
<div class="container centerSearch">
    <div class="row">
        <div class="center">
            <img src="../images/test.png" />
            <h3><g:message code="home.page.title"/></h3>
        </div>
        <br/>
        <br/>
        <g:form controller="search" action="index" method="GET" id="searchForm">
            <div>
                <input type="text" name="term" id="searchInput" placeholder="${message(code:"search.prompt.text")}" size="50"/>
                <input type="submit" id="searchButton" value="${message(code:"search.button.label")}" class="btn btn-primary"/>
                <img id="helpImage" src="help.png" />
                <br/>
                <img id="orImage" src="OR.png"/>
                <input type="file" value="${message(code:'upload.barcode.link.text')}"/>
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
