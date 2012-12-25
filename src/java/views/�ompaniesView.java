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
        String result = "<tbody>";
        
        Iterator<Companies> iterator = companies.iterator();
        
        while ( iterator.hasNext() )
        {
            Companies company = iterator.next(); 
            
            result += "<tr>";
            result += "<td><p><a href=\"\">" + company.getCompanyName() + "</a></p></td>";
            result += "<td><span class=\"label label-important\">" + company.getCompanyTelephone() + "</span></td>";
            result += "<td><span class=\"badge\">" + company.getCompanyAddress() + "</span></td>";
            result += "</tr>";
        }
        
        result += "</tbody>";
        
        return result;
    }
    
    public static String getCompaniesDataSource(List <Companies> companies)
    {
        //["JBS","Beatom","Litera Corp","Nork","Daota","Ohio","Rhodeands","Utaha INC","Vermont",]
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
