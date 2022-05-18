package com.ecommerce.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;

/**
 * Servlet implementation class ListProduct
 */
@WebServlet("/ListProduct")
public class ListProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//List Products
		try {
			
			PrintWriter out = response.getWriter();
			
			//Load properties
			Properties properties = new Properties();
			properties.load(getServletContext().getResourceAsStream("/config.properties"));
			
			//create connection
			
			DBConnection conn = new DBConnection(properties.getProperty("url"), properties.getProperty("username"), 
					properties.getProperty("password"));
			
			//Create statement
			
			Statement stm = conn.getConnection().createStatement();
			
			//execute query
			String query = "select * from eproduct";
			ResultSet resultset = stm.executeQuery(query);
			
			//print response
			
			out.println("<html><body>");
			while(resultset.next()) {
				out.println( "<p>" +resultset.getInt("ID")+ "  |  "+resultset.getString("name")+ "  |  "+resultset.getDouble("price")
				+"  |  "+resultset.getTimestamp("date_added"));
			}
			out.println("</body></html>");
			
			//close connection
			conn.closeConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
