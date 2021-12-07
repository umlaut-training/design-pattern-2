<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Hallo, Hello, Hola, Bonjour, Ciao.</title>
        <style>
            .error {
                color: #ff0000;
            }
        </style>
</head>
<body>
<h1>Hallo, Hello, Hola, Bonjour, Ciao.</h1>
    <form:form modelAttribute="greetingsmodel">
        <table>
            <tr>
                <td>
                   Name:
                </td>
                <td>
                    <form:input path="name" />
                </td>
                <td>
                    <form:errors path="name" cssClass="error" />
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input type="submit" value="Gru&szlig; an den Computer.">
                </td>
            </tr>
        </table>
    </form:form>
    <h2>${greetingsmodel.greetingsFromComputer}</h2>
</body>
</html>