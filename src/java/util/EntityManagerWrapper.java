package util;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.ejb.HibernatePersistence;

public class EntityManagerWrapper {

    public static EntityManager getEntityManager() {
        if(entityManager == null){
            HibernatePersistence hp = new HibernatePersistence();
            EntityManagerFactory emf = hp.createEntityManagerFactory("crmPU", new HashMap());
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    private static EntityManager entityManager;

}
