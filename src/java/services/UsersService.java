/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.xml.ws.api.tx.at.Transactional;
import domain.Users;
import hibernate.HibernateUtil;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author KC
 */
@WebService(serviceName = "UsersService")
public class UsersService extends AbstractService<Users> {

    /**
     * This is a sample web service operation
     */
    public UsersService(){
        super.class_ = Users.class;
    }
    
    @WebMethod(operationName="createUser")
    public void create(Users user)
    {
        super.create(user);
    }
    
    @WebMethod(operationName = "findAllUsers")
    public List<Users> findAll()
    {
        return super.findAll();
    }
    
    @WebMethod(operationName = "findUserByLoginAndPass")
    public Users getUserByLoginAndPassword(String login, String password)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Users user = (Users) session.createCriteria(Users.class)
                .add(Restrictions.eq("login", login))
                .add(Restrictions.eq("password", password))
                .setMaxResults(1).uniqueResult();
        session.close();
        return user;
    }
    
    @WebMethod(operationName = "findUsersByPrivilegies")
    public List<Users> getUsersByPrivilegies(int privilegies)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List list = session.createCriteria(Users.class)
                .add(Restrictions.eq("privilege", privilegies)).list();               
        session.close();
        return list;
    }
    
    @WebMethod(operationName = "findUserByID")
    public Users getUserById(int id)
    {
        return super.getById(id);
    }
    
    @Transactional
    @WebMethod(operationName = "ChangePassword")
    public void changePassword(Users user, String newPassword)
    {
        user.setPassword(newPassword);
        super.update(user);
    }
    
    @Transactional
    @WebMethod(operationName = "ChangePrivilegie")
    public void ChangePrivilegie(Users user, Integer privilegie)
    {
        user.setPrivilege(privilegie);
        super.update(user);
    }
    
    @Transactional
    public void remove(Users user)
    {
        super.remove(user);
    }
}
