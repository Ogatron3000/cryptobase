<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Crypto Base</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 50%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
    <div style="text-align: center">
        <h1>Crypto Base</h1>
        <h2>
            <a href="/new">Add New Coin</a>
        </h2>
    </div>
    <div style="display: flex; justify-content: center">
        <table>
            <caption><h2>Coins</h2></caption>
            <tr>
                <th>Name</th>
                <th>Abbreviation</th>
                <th>Price</th>
                <th>Market Cap</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="coin" items="${coins}">
                <tr>
                    <td><c:out value="${coin.name}"></c:out></td>
                    <td><c:out value="${coin.abbreviation}"></c:out></td>
                    <td><c:out value="$${coin.price}"></c:out></td>
                    <td><c:out value="$${coin.marketCap}"></c:out></td>
                    <td>
                        <a href="/edit?id=<c:out value='${coin.id}' />">Edit</a>
                        <a href="/delete?id=<c:out value='${coin.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>