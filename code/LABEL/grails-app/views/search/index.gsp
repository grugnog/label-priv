<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title><g:message code="home.page.title"/></title>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'search.css')}" type="text/css">
</head>
<body>
<br/>
<div class="center">
    <img src="../images/test.png" />
    <h3><g:message code="home.page.title"/></h3>
</div>
<br/>
<br/>
<g:form controller="search" method="GET">
    <div class="search">
        <input type="text" name="term" placeholder="${message(code:"search.prompt.text")}" size="50"/>
        <input type="submit" id="searchButton" value="${message(code:"search.button.label")}"/>
        <br/>
        <br/>
        <a href="#"><g:message code="upload.barcode.link.text"/></a>
    </div>
</g:form>
</body>
</html>
