/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import domain.Events;
import hibernate.HibernateUtil;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author KC
 */
@WebService(serviceName = "EventsService")
public class EventsService extends AbstractService<Events> {

    /**
     * This is a sample web service operation
     */
    public EventsService(){
        super.class_ = Events.class;
    }
    
    @WebMethod(operationName = "findAllEvents")
    public List<Events> findAll()
    {
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        List list =  session.createCriteria(Events.class).addOrder(Order.desc("eventsDate")).addOrder(Order.desc("eventsTime"))
                .list();
        session.close();
        
        return list; 
    }
    
    public Events getEventById(Integer id)
    {
        return super.getById(id);
    }
}
