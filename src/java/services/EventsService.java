/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import domain.Events;
import hibernate.HibernateUtil;
import java.util.Calendar;
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
    
    public List<Events> getEventsForLastNDays(Integer numDays)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add( calendar.DAY_OF_YEAR, numDays*(-1) );
        
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        List list =  session.createCriteria(Events.class).add( Restrictions.ge("eventsDate", calendar.getTime()) ).
                addOrder(Order.desc("eventsDate")).addOrder(Order.desc("eventsTime"))
                .list();
        session.close();
        
        return list;
    }
}
