package Modelo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Modelo {

    public static void main (String [] args) {

        SessionFactory sessionFactory = null;

        try {
            //Solo se hace una vez, al iniciar la aplicacion
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();


            // Cualquier operacion debe hacerse dentro de una transaccion
            sessionFactory.getCurrentSession().beginTransaction();

            // leer todas las asignaturas
            Query query = sessionFactory.getCurrentSession().createQuery("FROM PorcentajesRangoedad");
            ArrayList<PorcentajesRangoedad> porcentajes = (ArrayList<PorcentajesRangoedad>) query.list();
                for(PorcentajesRangoedad lista: porcentajes) {
                    System.out.println(lista);
                    
                }


            sessionFactory.getCurrentSession().getTransaction().commit();
          
        } catch (Exception e) {
           
            e.printStackTrace();
        	sessionFactory.getCurrentSession().getTransaction().rollback();
			throw e;
			
        }finally {
        	//al cerrar la aplicacion
        	if (sessionFactory != null) {
				sessionFactory.close();
			}
        }
        


    }

}