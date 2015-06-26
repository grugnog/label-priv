<%--
  User: Janakiram Gollapudi
  Date: 6/23/15
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${details.title} - LABEL</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="container" id="mainmenu">
    <div class="row">
        <div class="col-sm-12">

            <div class="center">
                <img class="logo" src="${resource(dir: "images", file: "LABEL-logo.svg")}"/>
                <h3><g:message code="home.page.title"/></h3>
            </div>

            <br/>
            <a href="textSearchView?term=${params.term}&page=${params.page}"><g:message code="back.to.search"/></a>

            <h3>${details.title}</h3>
            <hr/>

            <div class="col-sm-3">
                <ul class="nav nav-stacked">
                    <li class="active"><a href="#abuseAndOverdose"><g:message code="abuse.and.over.dosage"/></a></li>
                    <li><a href="#adverseEffects"><g:message code="adverse.effects"/></a></li>
                    <li><a href="#clinicalPharmacology"><g:message code="clinical.pharmacology"/></a></li>
                    <li><a href="#indications"><g:message code="indications.usage"/></a></li>
                    <li><a href="#patientInformation"><g:message code="patient.information"/></a></li>
                    <li><a href="#specialPopulation"><g:message code="special.population"/></a></li>
                    <li><a href="#toxilogy"><g:message code="non.clinical.toxicology"/></a></li>
                    <li><a href="#reference"><g:message code="references"/></a></li>
                    <li><a href="#supplyStorage"><g:message code="supply.storage.handling"/></a></li>
                    <li><a href="#warnings"><g:message code="warnings.precautions"/></a></li>
                    <li><a href="#idVersion"><g:message code="id.version"/></a></li>
                    <li><a href="#other"><g:message code="other.fields"/></a></li>
                    <li><a href="#openFDA"><g:message code="open.fda.fields"/></a></li>
                </ul>
            </div>

            <div class="col-sm-9">
                <div id="abuseAndOverdose">
                    <h2 class="text-uppercase"><g:message code="abuse.and.over.dosage"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>
                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.ABUSEANDOVERDOSE}" var="drugs">
                        <g:if test="${details."${drugs}"}">
                            <g:render template="viewListDetails" model='[id: "${drugs}", code: "${drugs.replaceAll("_", ".")}", valueList: details."${drugs}" ]' />
                        </g:if>
                    </g:each>

                </div>
                <br/>


                <div id="adverseEffects">
                    <h2 class="text-uppercase"><g:message code="adverse.effects.interactions"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>

                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.ADVERSEEFFECTS}" var="itr">
                        <g:if test="${details."${itr}"}">
                            <g:render template="viewListDetails" model='[id: "${itr}", code: "${itr.replaceAll("_", ".")}", valueList: details."${itr}" ]' />
                        </g:if>
                    </g:each>

                </div>

                <div id="clinicalPharmacology">

                    <h2 class="text-uppercase"><g:message code="clinical.pharmacology"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>
                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.CLINICALPHAR}" var="itr">
                        <g:if test="${details."${itr}"}">
                            <g:render template="viewListDetails" model='[id: "${itr}", code: "${itr.replaceAll("_", ".")}", valueList: details."${itr}" ]' />
                        </g:if>
                    </g:each>

                </div>

                <div id="indications">

                    <h2 class="text-uppercase"><g:message code="indications.usage"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>

                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.INDICATIONS}" var="itr">
                        <g:if test="${details."${itr}"}">
                            <g:render template="viewListDetails" model='[id: "${itr}", code: "${itr.replaceAll("_", ".")}", valueList: details."${itr}" ]' />
                        </g:if>
                    </g:each>

                </div>

                <div id="patientInformation">

                    <h2 class="text-uppercase"><g:message code="patient.information"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>

                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.PATIENTINFO}" var="itr">
                        <g:if test="${details."${itr}"}">
                            <g:render template="viewListDetails" model='[id: "${itr}", code: "${itr.replaceAll("_", ".")}", valueList: details."${itr}" ]' />
                        </g:if>
                    </g:each>


                </div>

                <div id="specialPopulation">

                    <h2 class="text-uppercase"><g:message code="special.population"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>

                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.SPECIALPOPULATION}" var="itr">
                        <g:if test="${details."${itr}"}">
                            <g:render template="viewListDetails" model='[id: "${itr}", code: "${itr.replaceAll("_", ".")}", valueList: details."${itr}" ]' />
                        </g:if>
                    </g:each>


                </div>

                <div id="toxilogy">

                    <h2 class="text-uppercase"><g:message code="non.clinical.toxicology"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>

                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.TOXILOGY}" var="itr">
                        <g:if test="${details."${itr}"}">
                            <g:render template="viewListDetails" model='[id: "${itr}", code: "${itr.replaceAll("_", ".")}", valueList: details."${itr}" ]' />
                        </g:if>
                    </g:each>


                </div>

                <div id="reference">

                    <h2 class="text-uppercase"><g:message code="references"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>

                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.REFERENCE}" var="itr">
                        <g:if test="${details."${itr}"}">
                            <g:render template="viewListDetails" model='[id: "${itr}", code: "${itr.replaceAll("_", ".")}", valueList: details."${itr}" ]' />
                        </g:if>
                    </g:each>


                </div>


                <div id="supplyStorage">

                    <h2 class="text-uppercase"><g:message code="supply.storage.handling"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>

                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.SUPPLYSTORAGE}" var="itr">
                        <g:if test="${details."${itr}"}">
                            <g:render template="viewListDetails" model='[id: "${itr}", code: "${itr.replaceAll("_", ".")}", valueList: details."${itr}" ]' />
                        </g:if>
                    </g:each>


                </div>

                <div id="warnings">

                    <h2 class="text-uppercase"><g:message code="warnings.precautions"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>

                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.WARNINGS}" var="itr">
                        <g:if test="${details."${itr}"}">
                            <g:render template="viewListDetails" model='[id: "${itr}", code: "${itr.replaceAll("_", ".")}", valueList: details."${itr}" ]' />
                        </g:if>
                    </g:each>

                </div>


                <div id="idVersion">

                    <h2 class="text-uppercase"><g:message code="id.version"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>

                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.IDANDVERSION}" var="itr">
                        <g:if test="${details."${itr}"}">
                            <g:render template="viewStringDetails" model='[id: "${itr}", code: "${itr.replaceAll("_", ".")}", value: details."${itr}" ]' />
                        </g:if>
                    </g:each>

                </div>


                <div id="other">

                    <h2 class="text-uppercase"><g:message code="other.fields"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>

                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.OTHER}" var="itr">
                        <g:if test="${details."${itr}"}">
                            <g:render template="viewListDetails" model='[id: "${itr}", code: "${itr.replaceAll("_", ".")}", valueList: details."${itr}" ]' />
                        </g:if>
                    </g:each>

                </div>

                <div id="openFDA">

                    <h2 class="text-uppercase"><g:message code="open.fda.fields"/></h2> <a href="#mainmenu"><g:message code="back.to.top" /></a>

                    <hr/>

                    <g:each in="${grails.util.Holders.config.labels.map.OPENFDA}" var="itr">
                        <g:if test="${details."${itr}"}">
                            <g:render template="viewListDetails" model='[id: "${itr}", code: "${itr.replaceAll("_", ".")}", valueList: details."${itr}" ]' />
                        </g:if>
                    </g:each>

                </div>

                </div>

            <br/>
            <br/>

        </div>
    </div>
</div>

</body>
</html>