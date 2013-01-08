/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import domain.Companies;
import domain.Contacts;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import services.CompaniesService;

/**
 *
 * @author KC
 */
public class ContactsView {

    public static String getContactsSelect(List <Contacts> contacts, String caption)
    {
        String result = "<div class=\"control-group\" >";
        result += "<label class=\"control-label\" for=\"inputEmail\">" + caption + "</label>";
        result += "<div class=\"controls\">";
        result += "<select class=\"input-block-level\" name=\"contactsSelect\">";
        
        Iterator<Contacts> iterator = contacts.iterator();
        
        while ( iterator.hasNext() )
        {
            Contacts contact = iterator.next();            
            result += "<option>" + contact.getContactsName() + "</option>";
        }
        
        result += "</select>";
        result += "</div>";
        result += "</div>";
        
        return result;
    }

    public static String getContactsTable(List <Contacts> contacts)
    {
        CompaniesService cs = new CompaniesService();
        
        String result = "<table class=\"table\">\n" +
                "<thead>\n" +
                "<tr>\n" +
                "<th>Контакт</th>\n" +
                "<th>Компания</th>\n" +
                "<th>Телефон</th>\n" +
                "<th>Email</th>\n" +
                "</tr>\n" +
                "</thead>";
        
        Iterator<Contacts> iterator = contacts.iterator();
        
        while ( iterator.hasNext() )
        {
            Contacts contact = iterator.next();
            
            result += "<tr>";
            result += "<td><p><a href=\"contacts.jsp?id=" + contact.getId() + "\">" + contact.getContactsName() + "</a></p></td>";
            
            if ( contact.getCompanyId() != null )
            {
                result += "<td><p><a href=\"companies.jsp?id=" + contact.getCompanyId().getId() + "\">" 
                        + contact.getCompanyId().getCompanyName() + "</a></p></td>";
            }
            else 
            {
                result += "<td><p><a href=\"\">-</a></p></td>";
            }
            
            //result += "<td><p><a href=\"companies.jsp?" + contact.getId() + "\">" + companyName + "</a></p></td>";
            result += "<td><span class=\"label label-important\">" + contact.getContactsTelephon() + "</span></td>";
            result += "<td><span class=\"badge badge-inverse\">" + contact.getContactsEmail() + "</span></td>";
            result += "</tr>";
        }
                
        result += "</tbody></table>";
        
        return result;
    }

    public static String getContactsTable(Set <Contacts> contacts)
    {
        CompaniesService cs = new CompaniesService();
        
        String result = "<table class=\"table\">\n" +
                "<thead>\n" +
                "<tr>\n" +
                "<th>Контакт</th>\n" +
                "<th>Компания</th>\n" +
                "<th>Телефон</th>\n" +
                "<th>Email</th>\n" +
                "</tr>\n" +
                "</thead>";
        
        Iterator<Contacts> iterator = contacts.iterator();
        
        while ( iterator.hasNext() )
        {
            Contacts contact = iterator.next();
            
            result += "<tr>";
            result += "<td><p><a href=\"contacts.jsp?id=" + contact.getId() + "\">" + contact.getContactsName() + "</a></p></td>";
            
            if ( contact.getCompanyId() != null )
            {
                result += "<td><p><a href=\"companies.jsp?id=" + contact.getCompanyId().getId() + "\">" 
                        + contact.getCompanyId().getCompanyName() + "</a></p></td>";
            }
            else 
            {
                result += "<td><p><a href=\"\">-</a></p></td>";
            }
            
            //result += "<td><p><a href=\"companies.jsp?" + contact.getId() + "\">" + companyName + "</a></p></td>";
            result += "<td><span class=\"label label-important\">" + contact.getContactsTelephon() + "</span></td>";
            result += "<td><span class=\"badge badge-inverse\">" + contact.getContactsEmail() + "</span></td>";
            result += "</tr>";
        }
                
        result += "</tbody></table>";
        
        return result;
    }
    
    public static String printContactInfo(Contacts contact)
    {        
        String result = "<h3>" + contact.getContactsName() + "</h3>"
                + "<br/><br/>";
        result += "<b>Адресс: </b> <span class=\"badge\">" + contact.getContactsAddress() + "</span><br/><br/>";
        if ( contact.getCompanyId() != null )
        {
            result += "<b>Компания: </b><a href=\"companies.jsp?id=" + contact.getCompanyId().getId() + "\">" 
                    + contact.getCompanyId().getCompanyName() + "</a><br/><br/>";
        }
        else 
        {
            result += "<b>Компания: </b><a href=\"\">-</a><br/><br/>";
        }

        result += "<b>Контактный номер: </b><span class=\"label label-important\">" + contact.getContactsTelephon() + "</span><br/><br/>";
        result += "<b>eMail: </b><span class=\"badge badge-inverse\">" + contact.getContactsEmail() + "</span><br/><br/>";        
        result += "<br/><br/>";        
        
        //now printing deal, atached to contact;
        if ( contact.getDealsId() != null )
        {
            result += "<b>Сделка, связанная с контактом: </b>";
            result += "<a href=\"deals.jsp?id=" + contact.getDealsId().getId() + "\">" 
                    + contact.getDealsId().getDealsName() + "</a>";
        }
        else
        {
            result += "<b>Сделок, связанных с контактом " + contact.getContactsName() + " нет</b><br/><br/>";
        }
        
        return result;
    }
}
