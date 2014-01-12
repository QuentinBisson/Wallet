<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fieldset>
    <legend>Connection</legend>
    <form action="<c:url value="/login" />" method="POST">
        <label for="username" >Identifiant : </label>
        <input type="text" name="username" id="username"/> <br/>
        <label for="password">Mot de passe : </label>
        <input type="password" name="password" id="password"/> <br/>
        <input type="submit" value="Connection"/>
    </form>
</fieldset>