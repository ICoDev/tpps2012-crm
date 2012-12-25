/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import domain.Companies;
import domain.Contacts;
import java.util.Iterator;
import java.util.List;
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
        
        String result = "<tbody>";
        
        Iterator<Contacts> iterator = contacts.iterator();
        
        while ( iterator.hasNext() )
        {
            Contacts contact = iterator.next();
            String companyName = "-";
            
            result += "<tr>";
            result += "<td><p><a href=\"\">" + contact.getContactsName() + "</a></p></td>";
            
            if ( contact.getCompanyId() != null )
            {
                companyName = contact.getCompanyId().getCompanyName();
            }
            else 
            {
                companyName = "-";
            }
            
            result += "<td><p><a href=\"\">" + companyName + "</a></p></td>";
            result += "<td><span class=\"label label-important\">" + contact.getContactsTelephon() + "</span></td>";
            result += "<td><span class=\"badge badge-inverse\">" + contact.getContactsEmail() + "</span></td>";
            result += "</tr>";
        }
                
        result += "</tbody>";
        
        return result;
    }
}
