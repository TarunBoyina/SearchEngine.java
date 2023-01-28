package com.Accio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        System.out.println(keyword);
        try {
            // establish that connection to database
            Connection connection = DatabaseConnection.getConnection();
            // save keyword and link associated into history table
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into history values(?,?)");
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, "http:localhost:8080/SearchEngineJava/Search?keyword="+keyword);
            preparedStatement.executeUpdate();
            //executing the query related to keyword and get the result
            ResultSet resultSet = connection.createStatement().executeQuery("select pagetitle, page, (length(lower(pagetext))-length(replace(lower(pagetext),'" + keyword + "', '')))/length('" + keyword + "') as countoccurence from pages order by countoccurence desc limit 30;");
            ArrayList<SearcchResult> results = new ArrayList<>();
            //iterate through resultSet and save all elements in the results arraylist
            while (resultSet.next()) {
                SearcchResult searchResult = new SearcchResult();
                //get pageTitle
                searchResult.setPageTitle(resultSet.getString("pageTitle"));
                //get page link
                searchResult.setPageLink(resultSet.getString("page"));
                results.add(searchResult);
            }
            for (SearcchResult searchResult : results) {
                System.out.println(searchResult.getPageTitle() + " " + searchResult.getPageLink() + "\n");
            }
            request.setAttribute("results", results);
            request.getRequestDispatcher("/search.jsp").forward(request, response);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        }
        catch (SQLException | ServletException | IOException sqlException) {
            sqlException.printStackTrace();
        }
    }
}