<%--
User: Janakiram Gollapudi
Date: 6/23/15
--%>
<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
    <title><g:message code="results.page.title"/></title>
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
            <g:form controller="search" method="GET" id="searchForm">
                <div class="search">
                    <input type="text" name="term" id="searchInput" placeholder="${message(code:"search.prompt.text")}" size="50" value="${params.term}"/>
                    <input type="submit" id="searchButton" value="${message(code:"search.button.label")}" class="btn btn-primary"/>
                    <img id="helpImage" src="help.png" />
                    <br/>
                    <input type="file" value="${message(code:'upload.barcode.link.text')}"/>
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