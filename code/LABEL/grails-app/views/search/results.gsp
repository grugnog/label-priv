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
    <g:javascript plugin="jquery-ui"/>
    <g:javascript src="search.js"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="center">
                <!--TODO: BEN Here we need LABEL logo. Once you put logo remove below h3 tag -->
                <img src="../images/test.png" />
                <h3><g:message code="home.page.title"/></h3>
            </div>
            <br/>
            <br/>
            <g:form controller="search" method="GET" id="searchForm">
                <div class="search">
                    <!--TODO:BEN This is search text box in home page, we have to add search icon inside textbox as shown in mockup  -->
                    <input type="text" name="term" id="searchInput" placeholder="${message(code:"search.prompt.text")}" size="50" value="${params.term}"/>
                    <input type="submit" id="searchButton" value="${message(code:"search.button.label")}" class="btn btn-primary"/>
                    <!--TODO:BEN This is help icon beside search button   -->
                    <img src="help.png" />
                    <br/>
                    <!--TODO:BEN As shown in mockiup we have to add '- OR -' between search box and upload image button. I am not sure if this is image or we need text  -->
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