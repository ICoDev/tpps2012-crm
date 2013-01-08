/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import domain.Companies;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author KC
 */
public class СompaniesView {

    public static String getCompaniesSelect(List <Companies> companies, String caption)
    {
        String result = "<div class=\"control-group\">";
        result += "<label class=\"control-label\" for=\"inputEmail\">" + caption + "</label>";
        result += "<div class=\"controls\">";
        result += "<select class=\"input-block-level\" name=\"companiesSelect\">";
        
        Iterator<Companies> iterator = companies.iterator();
        
        while ( iterator.hasNext() )
        {
            Companies company = iterator.next();            
            result += "<option>" + company.getCompanyName() + "</option>";
        }
        
        result += "</select>";
        result += "</div>";
        result += "</div>";
        
        return result;
    }
    
    public static String getCompaniesTable(List <Companies> companies)
    {
        String result = "<table class=\"table\">"
            + "<thead>"
            + "<tr>"
            + "<th>Название компании</th><th>Телефон</th><th>Адрес</th>"
            + "</tr>"
            + "</thead>";
        
        Iterator<Companies> iterator = companies.iterator();
        
        while ( iterator.hasNext() )
        {
            Companies company = iterator.next(); 
            
            result += "<tr>";
            result += "<td><p><a href=\"companies.jsp?id=" + company.getId() + "\">" + company.getCompanyName() + "</a></p></td>";
            result += "<td><span class=\"label label-important\">" + company.getCompanyTelephone() + "</span></td>";
            result += "<td><span class=\"badge\">" + company.getCompanyAddress() + "</span></td>";
            result += "</tr>";
        }
        
        result += "</tbody>"
                + "</table>";
        
        return result;
    }
    
    public static String printCompanyInfo(Companies company)
    {                          
        String result = "<h3>" + company.getCompanyName() + "</h3>"
                + "<br/><br/>";
        result += "<b>Адресс компании: </b> <span class=\"badge\">" + company.getCompanyAddress() + "</span><br/><br/>";
        result += "<b>Контактный номер: </b><span class=\"label label-important\">" + company.getCompanyTelephone() + "</span>"
                + "<br/><br/><br/><br/>";        
        
        //now printing companies contacts;
        if ( company.getContactsSet().isEmpty()  )
        {
            result += "<b>Контактов, связанных с компанией " + company.getCompanyName() + " нет</b><br/><br/>";
        }
        else
        {
            result += "<b>Контакты, связанные с компанией:</b><br/><br/>";
            result += ContactsView.getContactsTable( company.getContactsSet() );
        }
                
        return result;
    }
    
    public static String getCompaniesDataSource(List <Companies> companies)
    {
        String result = "[";
        
        Iterator<Companies> iterator = companies.iterator();
        
        while ( iterator.hasNext() )
        {
            Companies company = iterator.next();
            result += "\"" + company.getCompanyName() + "\"";
            
            if ( iterator.hasNext() )
            {
                result += ",";
            }
        }
        
        result += "]";
        return result;
    }
}
