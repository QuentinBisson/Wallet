<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<c:url value = "/img/favicon.ico" />" />
        <link rel="stylesheet" href="<c:url value = "/css/index.css" />" type="text/css" media="screen"/>
        <title>Bdthèque</title>
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header.jsp"/>

        <section class="content">
            <c:forEach items="${requestScope.listBd}" var="bd" varStatus="status">
                <c:set var="classValue">
                    <c:choose>
                    <c:when test="${(status.index % 2) == 0}" >
                        bd-left
                    </c:when>
                    <c:otherwise>
                        bd-right
                    </c:otherwise>
                    </c:choose>
                </c:set>
                
                <c:url value="/show/${bd.id}" var="show" />
                <a href="${show}" class="bd ${classValue}">
                    <section >
                        <div class="miniature">
                            <c:url value="${bd.image}" var="image" />
                            <img class="bd-image" src="${image}" alt="${bd.titre}">
                        </div>
                        <div class="info">
                            <h2><c:out value="${bd.titre}" /> </h2> <br/>
                            ISBN: <c:out value="${bd.isbn}" /> <br/>
                            Scénaristes:
                            <c:forEach items="${bd.scenaristes.scenariste}" var="scenariste">
                                <c:out value="${scenariste.prenom}" /> <c:out value="${scenariste.nom}" /> <br/>    
                            </c:forEach>
                            Tome: <c:out value="${bd.tome.numero}" />
                            <c:out value="${bd.tome.informations}" /> <br/>
                            Série: <c:out value="${bd.serie}" /> <br/>
                            <br/>
                            <div class="resume">
                                <c:out value="${bd.resume}" />
                            </div>...

                        </div>
                    </section>
                </a>
            </c:forEach>
            <c:if test="${nbResult != 1}">
                <div class="paginator">
                    <c:forEach var="i" begin="1" end="${nbResult}">
                        <c:if test="${i == page}">
                            <a class="paginator-number selected-page" href="<c:url value = "/${i}/"/>">${i}</a>
                        </c:if>
                        <c:if test="${i != page}">
                            <a class="paginator-number" href="<c:url value = "/${i}/"/>">${i}</a>
                        </c:if>
                        
                    </c:forEach>
                </div>
            </c:if>
        </section>

        <c:import url="/WEB-INF/jsp/footer.jsp"/>
    </body>
</html>
