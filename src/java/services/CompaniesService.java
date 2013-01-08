/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.xml.ws.api.tx.at.Transactional;
import domain.Companies;
import domain.Contacts;
import hibernate.HibernateUtil;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author KC
 */
@WebService(serviceName = "CompaniesService")
public class CompaniesService extends AbstractService<Companies> {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello", action="hello" )
    public String hello()
    {   
        return "Hello, ass!";
    }
    
    public CompaniesService(){
        super.class_ = Companies.class;
    }
    
    @WebMethod(operationName = "findAllCompanies")
    public List<Companies> findAll()
    {
        return super.findAll();
    }
    
    @WebMethod(operationName = "getCompanyByName")
    public Companies getCompanyByName(String name)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Companies company = (Companies) session.createCriteria(Companies.class)
                .add(Restrictions.eq("companyName", name))
                .setMaxResults(1).uniqueResult();
        session.close();
        return company;
    }
    
    @WebMethod(operationName = "getCompaniesWhereNameLike")
    public List<Companies> getCompaniesWhereNameLike(String name)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List <Companies> list = session.createCriteria(Companies.class)
                .add(Restrictions.like("companyName", name, MatchMode.ANYWHERE ).ignoreCase())
                .list();
        session.close();
        return list;
    }
        
    @WebMethod(operationName = "getCompanyById")
    public Companies getCompanyById(int id)
    {
        return super.getById(id);
    }
    
    @Transactional
    public void remove(Companies company)
    {
        super.remove(company);
    }
    
    @WebMethod(operationName="createCompany")
    public void create(Companies company)
    {
        super.create(company);
    }
    
    @Transactional
    @WebMethod(operationName = "ChangeContactInfo")
    public void changeContactInfo(Companies company, String newAdress, Integer newPhone)
    {
        company.setCompanyAddress(newAdress);
        company.setCompanyTelephone(newPhone);
        
        super.update(company);
    }
}
