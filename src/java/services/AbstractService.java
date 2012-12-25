/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import hibernate.HibernateUtil;
import java.util.List;
import javax.jws.WebService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author KC
 */


@WebService(serviceName = "AbstractService")
public class AbstractService <T> {
    
    Class<T> class_;
            
    public void create( T entity)
    {
        if (entity == null ) 
        {
            return;
        }
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.getTransaction().begin();
        try
        {
            session.persist(entity);
            session.flush();
            session.getTransaction().commit();
        }
        catch(HibernateException ex)
        {
            session.getTransaction().rollback();
        }
        
        session.close();
    }
    
    public void remove(T entity){
        if (entity == null ) 
        {
            return;
        }
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.getTransaction().begin();
        try
        {
            session.delete(entity);
            session.flush();
            session.getTransaction().commit();
        }
        catch(HibernateException ex)
        {
            session.getTransaction().rollback();
        }
        
        session.close();
    }
    
    public void update(T entity){
        if (entity == null ) 
        {
            return;
        }
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.getTransaction().begin();
        try
        {            
            session.merge(entity);
            session.flush();
            session.getTransaction().commit();
        }
        catch(HibernateException ex)
        {
            session.getTransaction().rollback();
        }
        
        session.close();
    }
    
    public List<T> findAll()
    {
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        List list =  session.createCriteria(class_).list();
        session.close();
        
        return list;        
    }
    
    public T getById(Integer id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        T result =  (T) session.createCriteria(class_)
                .add(Restrictions.eq("id", id))
                .setMaxResults(1).uniqueResult();
        session.close();
        
        return result;        
    }
}
