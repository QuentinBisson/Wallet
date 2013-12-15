<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="main-header">
    <section>
        <c:url value="/1/" var="homeLink" />
        <a class="home" href="${homeLink}"><h1>BDtheque</h1></a>
        <div class="menu-right">
            <c:url value="/search/1/" var="searchLink" />
            <form class="searchForm" method="post" action="${searchLink}">
                <input type="submit" value="">
                <input type="search" name="searchTitre" placeholder="Titre ..." value=""/>
            </form>
        </div>
        <div class="clear"></div>
    </section>
</header>
<section class="menu">
    <nav>
        <ul>
            <li class="menu-link"><a class=" home-link" href="${homeLink}"><img src="<c:url value = "/img/home.png" />" alt="Accueil"></i></a></li>
            <li class="sep"></li>
                <c:url value="/add" var="addLink" />
            <li class="menu-link"><a href="${addLink}"><p>Ajouter une bd</p></a></li>
            <li class="sep"></li>
                <c:url value="/search" var="searchLink" />
            <li class="menu-link"><a href="${searchLink}"><p>Recherche</p></a></li>

        </ul>
    </nav>
</section>