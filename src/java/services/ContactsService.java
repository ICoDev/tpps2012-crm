/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.xml.ws.api.tx.at.Transactional;
import domain.Contacts;
import hibernate.HibernateUtil;
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
@WebService(serviceName = "ContactsService")
public class ContactsService extends AbstractService<Contacts> {

    /**
     * This is a sample web service operation
     */
    public ContactsService(){
        super.class_ = Contacts.class;
    }
        
    @WebMethod(operationName = "findAllContacts")
    public List<Contacts> findAll()
    {        
        return super.findAll();
    }
    
    @WebMethod(operationName = "getContactByName")
    public Contacts getContactByName(String name)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Contacts contact = (Contacts) session.createCriteria(Contacts.class)
                .add(Restrictions.eq("contactsName", name))
                .setMaxResults(1).uniqueResult();
        session.close();
        return contact;
    } 
    
    @WebMethod(operationName = "getContactById")
    public Contacts getContactById(int id)
    {
        return super.getById(id);
    }
    
    @Transactional
    public void remove(Contacts contact)
    {
        super.remove(contact);
    }
    
    @WebMethod(operationName="createCompany")
    public void create(Contacts contact)
    {
        super.create(contact);
    }
    
    @Transactional
    @WebMethod(operationName = "ChangeResponsible")
    public void changeResponsible(Contacts contact, String newResponsible)
    {
        contact.setResponsible(newResponsible);
        super.update(contact);
    }
    
    @Transactional
    @WebMethod(operationName = "ChangeInfo")
    public void changeInfo(Contacts contact, String newAdress, Integer newPhone, 
                                  String newMail, String newName, String newNote, String newTag)
    {
        contact.setContactsAddress(newAdress);
        contact.setContactsTelephon(newPhone);
        contact.setContactsEmail(newMail);
        contact.setContactsName(newName);
        contact.setContactsNote(newNote);
        contact.setContactsTags(newTag);
        
        super.update(contact);
    }
}
