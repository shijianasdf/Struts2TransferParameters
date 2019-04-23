package utils;

import java.io.File;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateUtils {
	 private static final SessionFactory sessionFactory;  
	   
	    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();  
	    static {  
	        try {  
	             
	            sessionFactory = new Configuration().configure().buildSessionFactory();  
	        } catch (Throwable ex) {  
	            ex.printStackTrace();  
	            System.err.println("½¨Á¢SessionFactory´íÎó" + ex);  
	            throw new ExceptionInInitializerError(ex);  
	        }  
	    }     
	    public static SessionFactory getSessionFactory() {  
	        return sessionFactory;  
	    }     
	    public static Session getSession() throws HibernateException {  
	        Session session = (Session) threadLocal.get();  
	        if (session == null || !session.isOpen()) {  
	            session = (sessionFactory != null) ? sessionFactory.openSession(): null;  
	            threadLocal.set(session);  
	        }  
	        return session;  
	    }      
	    public static void closeSession() throws HibernateException {  
	        Session session = (Session) threadLocal.get();  
	        threadLocal.set(null);  
	  
	        if (session != null) {  
	            session.close();  
	        }  
	    }  
}
