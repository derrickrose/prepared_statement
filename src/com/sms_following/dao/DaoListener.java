package com.sms_following.dao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class DaoListener
 *
 */
@WebListener
public class DaoListener implements ServletContextListener {

	private static final String DAO_FACTORY_ATTRIBUTE = "daoFactory";
    /**
     * Default constructor. 
     */
    public DaoListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	ServletContext servletContext = arg0.getServletContext();
    	servletContext.setAttribute(DAO_FACTORY_ATTRIBUTE, daoFactory);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
