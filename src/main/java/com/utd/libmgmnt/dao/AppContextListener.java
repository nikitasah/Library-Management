package com.utd.libmgmnt.dao;

import java.sql.Connection;
import java.sql.SQLException;



import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.utd.libmgmnt.util.DbConnectionManager;

/**
 * 
 * Description : Listener that creates a common connection object and stores in context attribute for service classes to use.
 * 				 Responsible for destroying this connection object, when context is destroyed.
 * 
 * @author nikita
 *
 */

@WebListener
public class AppContextListener implements ServletContextListener {
	
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	ServletContext context = event.getServletContext();
   	    //Initialize DB Connection
        String dbURL = context.getInitParameter("dbURL");
        String user = context.getInitParameter("dbUser");
        String pwd = context.getInitParameter("dbPassword");        
        try {
            DbConnectionManager connectionManager = new DbConnectionManager(dbURL, user, pwd);
            context.setAttribute("DbConnection", connectionManager.getCon());
            System.out.println("Db Connection initialized successfully.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }      
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
    	Connection con = (Connection)event.getServletContext().getAttribute("DbConnection");
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }	
}



