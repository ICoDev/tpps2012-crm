/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.xml.ws.api.tx.at.Transactional;
import domain.Tasks;
import hibernate.HibernateUtil;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.EntityManagerWrapper;

/**
 *
 * @author KC
 */
@WebService(serviceName = "TasksService")
public class TasksService extends AbstractService<Tasks> {

    /**
     * This is a sample web service operation
     */
    
    public TasksService(){
        super.class_ = Tasks.class;
    }
    
    @WebMethod(operationName = "findAllTasks")
    public List<Tasks> findAll()
    {
        return super.findAll();
    }
    
    @WebMethod(operationName = "getTaskByID")
    public Tasks getTasksById(int id)
    {
        return  super.getById(id);
    }
        
    @WebMethod(operationName = "getTaskByAutor")
    public List<Tasks> getTasksByAutor(String autor)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List list = session.createCriteria(Tasks.class)
                .add(Restrictions.eq("autor", autor))
                .list();
        session.close();
        return list;
    }
        
    @WebMethod(operationName = "getTaskByResponsible")
    public List<Tasks> getTasksByResponsible(String responsible)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List list = session.createCriteria(Tasks.class)
                .add(Restrictions.eq("responsible", responsible))
                .list();
        session.close();
        return list;
    }
            
    @WebMethod(operationName = "getTaskByDate")
    public List<Tasks> getTasksByDate(Date date)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List list = session.createCriteria(Tasks.class)
                .add(Restrictions.eq("tasksDate", date))
                .list();
        session.close();
        return list;
    }
                    
    @WebMethod(operationName = "getTasksByType")
    public List<Tasks> getTasksByType(String type)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List list = session.createCriteria(Tasks.class)
                .add(Restrictions.eq("tasksType", type))
                .list();
        session.close();
        return list;
    }
    
    @WebMethod(operationName = "getTasksByStatus")
    public List<Tasks> getTasksByStatus(String status)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List list = session.createCriteria(Tasks.class)
                .add(Restrictions.eq("status", status))
                .list();
        session.close();
        return list;
    }
    
    @WebMethod(operationName = "getTasksByName")
    public Tasks getTaskByName(String name)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Tasks task = (Tasks) session.createCriteria(Tasks.class)
                .add(Restrictions.eq("tasksName", name))
                .setMaxResults(1).uniqueResult();
        session.close();
        return task;
    }
        
    @Transactional
    public void remove(Tasks task)
    {
        super.remove(task);
    }
    
    @WebMethod(operationName="createTask")
    public void create(Tasks task)
    {
        super.create(task);
    }
    
    @Transactional
    @WebMethod(operationName = "ChangeTaskInfo")
    public void changeTaskInfo(Tasks task, String newResponsible, String newType, 
                                  String newText, String newStatus)
    {
        task.setResponsible(newResponsible);
        task.setTasksType(newType);
        task.setTasksText(newText);
        task.setStatus(newStatus);
        
        super.update(task);
    }
}
