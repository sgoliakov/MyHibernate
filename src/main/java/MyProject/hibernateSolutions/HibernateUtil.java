package MyProject.hibernateSolutions;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
   private static SessionFactory sessionFactory;

  private HibernateUtil () {

   }

    public synchronized static SessionFactory getFactory() {
       if (sessionFactory == null){
           try {
               sessionFactory = new Configuration().configure("hib.xml").buildSessionFactory();
           }catch (HibernateException e){
               e.printStackTrace();
           }

       }
       return sessionFactory;
    }

}
