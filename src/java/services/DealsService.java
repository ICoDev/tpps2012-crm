/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.xml.ws.api.tx.at.Transactional;
import domain.Companies;
import domain.Contacts;
import domain.Deals;
import hibernate.HibernateUtil;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author KC
 */
@WebService(serviceName = "DealsService")
public class DealsService extends AbstractService<Deals> {

    /**
     * This is a sample web service operation
     */
    public DealsService(){
        super.class_ = Deals.class;
    }
    
    @WebMethod(operationName = "findAllDeals")
    public List<Deals> findAll()
    {
        return super.findAll(); 
    }
    
    @WebMethod(operationName = "getDealsByType")
    public List<Deals> getDealsByType(String type)
    {
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        List list =  session.createCriteria(Deals.class)
                .add(Restrictions.eq("dealsType", type))
                .list();
        session.close();
        
        return list; 
    }
    
    @WebMethod(operationName = "getDealsByResponsible")
    public List<Deals> getByResponsible(String responsible)
    {
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        List list =  session.createCriteria(Deals.class)
                .add(Restrictions.eq("responsible", responsible))
                .list();
        session.close();
        
        return list; 
    }
    
//    @WebMethod(operationName = "getDealsByTag")
//    public List<Deals> getByTag(String tag)
//    {
//        return  EntityManagerWrapper.getEntityManager()
//                .createNativeQuery("SELECT * FROM \"DEALS\" WHERE \"deals_tag\" = '"
//                        + tag + "'", Deals.class)
//                    .getResultList();
//    }
    
    @WebMethod(operationName = "getDealByName")
    public Deals getDealByName(String name)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Deals deal = (Deals) session.createCriteria(Deals.class)
                .add(Restrictions.eq("dealsName", name))
                .setMaxResults(1).uniqueResult();
        session.close();
        return deal;
    }
        
    @WebMethod(operationName = "getDealsByID")
    public Deals getDealById(int id)
    {
        return super.getById(id);
    }
    
    @Transactional
    public void remove(Deals deal)
    {
        super.remove(deal);
    }
    
    @WebMethod(operationName="createDeal")
    public void create(Deals deal)
    {
        super.create(deal);
    }
    
    @Transactional
    @WebMethod(operationName = "ChangeDealInfo")
    public void changeDealInfo(Deals deal, String newResponsible, Integer newMoney, 
                                  String newType, String newTag)
    {
        deal.setResponsible(newResponsible);
        deal.setMoney(newMoney);
        deal.setDealsType(newType);
        deal.setDealsTag(newTag);
        
        super.update(deal);
    }
}

