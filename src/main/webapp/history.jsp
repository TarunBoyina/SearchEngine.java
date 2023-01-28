<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Accio.HistoryResult"%>
<html>
    <head>
        <link rel = "stylesheet" type="text/css" href = "styles.css">
    </head>
<body>
        <form action = "Search">
            <input type ="text" name = "keyword">
            <button type = "submit">Search</button>
        </form>
        <div class="resultTable">
    <table border = 2>
        <tr>
            <td>Keyword</td>
            <td>Link</td>
        </tr>
        <%
            // get result from search servlet
            ArrayList<HistoryResult> results = (ArrayList<HistoryResult>) request.getAttribute("results");
            // iteration of every data present in result array
            for(HistoryResult result: results){
        %>
            <tr>
                <td><%out.println(result.getKeyword());%></td>
                <td><a href ="<%out.println(result.getLink());%>"><%out.println(result.getLink());%></a></td>
            </tr>
        <%
            }
        %>
    </table>
    </div>
</body>
</html>