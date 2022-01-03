<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Crypto Base</title>
    <style>
        table {
            border: 1px solid #dddddd;
        }

        form {
            display: flex;
            justify-content: center;
        }

        td, th {
            text-align: left;
            padding: 8px;
        }
    </style>
</head>
<body>
    <div style="text-align: center">
        <h1>Crypto Base</h1>
        <h2>
            <a href="/">Home</a>
        </h2>
    </div>
    <c:if test="${coin != null}">
        <form action="update" method="post">
    </c:if>
    <c:if test="${coin == null}">
        <form action="insert" method="post">
    </c:if>

        <table>
            <caption>
                <h2>
                    <c:if test="${coin != null}">
                        Edit Coin
                    </c:if>
                    <c:if test="${coin == null}">
                        Add New Coin
                    </c:if>
                </h2>
            </caption>
            <c:if test="${coin != null}">
                <input type="hidden" name="id" value="<c:out value='${coin.id}' />" />
            </c:if>
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" value="<c:out value='${coin.name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Abbreviation: </th>
                <td>
                    <input type="text" name="abbreviation" value="<c:out value='${coin.abbreviation}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Price: </th>
                <td>
                    <input type="text" name="price" value="<c:out value='${coin.price}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Market cap: </th>
                <td>
                    <input type="text" name="marketCap" value="<c:out value='${coin.marketCap}' />"
                    />
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
</body>
</html>
