/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import domain.Contacts;
import domain.Deals;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author KC
 */
public class DealsView {

    public static String getContactsSelect(List <Deals> deals , String caption)
    {
        String result = "<div class=\"control-group\">";
        result += "<label class=\"control-label\" for=\"inputEmail\">" + caption + "</label>";
        result += "<div class=\"controls\">";
        result += "<select class=\"input-block-level\" name=\"dealsSelect\">";
        
        Iterator<Deals> iterator = deals.iterator();
        
        while ( iterator.hasNext() )
        {
            Deals deal = iterator.next();            
            result += "<option>" + deal.getDealsTag() + "</option>";
        }
        
        result += "</select>";
        result += "</div>";
        result += "</div>";
        
        return result;
    }

    public static String getDealsTable(List <Deals> deals)
    {
        String result = "<tbody>";
        
        Iterator<Deals> iterator = deals.iterator();
        
        while ( iterator.hasNext() )
        {
            Deals deal = iterator.next();
            String companyName = "-";
            String mainContact = "-";
            
            //Trying to get Deals contact, if exists
            if ( deal.getContactsSet() != null )
            {
                Iterator i = deal.getContactsSet().iterator();
                
                if ( i.hasNext() )
                {
                    Contacts contact = (Contacts) i.next();
                    if ( contact != null )
                    {
                        mainContact = contact.getContactsName();
                        
                        if( contact.getCompanyId() != null )
                        {
                            companyName = contact.getCompanyId().getCompanyName();
                        }
                        else
                        {
                            companyName = "-";
                        }
                    }
                }
                else
                {
                    companyName = "-";
                    mainContact = "-";
                }
            }
            else 
            {
                companyName = "-";
                mainContact = "-";
            }
            
            result += "<tr>";
            result += "<td><p><a href=\"\">" + deal.getDealsName() + "</a></p></td>";
            result += "<td><p><a href=\"\">" + mainContact + "</a></p></td>";
            result += "<td><p><a href=\"\">" + companyName + "</a></p></td>";
            result += "<td><span class=\"label label-important\">" + deal.getDealsType() + "</span></td>";
            result += "<td><span class=\"badge\">" + deal.getMoney() + " грн</span></td>";
            result += "</tr>";
        }
                
        result += "</tbody>";
        
        return result;
    }
    
    public static String getTags( List<Deals> deals )
    {
        String result = "<p>";
        
        Iterator<Deals> iterator = deals.iterator();
        
        while ( iterator.hasNext() )
        {
            Deals deal = iterator.next();
            
            result += "<a href=\"\"><span class=\"label\">" + deal.getDealsTag() + "</span></a>\n";
        }
        
        result += "</p>";
        
        return result;
    }
    
        public static String getDealsDataSource(List <Deals> deals)
    {
        //["JBS","Beatom","Litera Corp","Nork","Daota","Ohio","Rhodeands","Utaha INC","Vermont",]
        String result = "[";
        
        Iterator<Deals> iterator = deals.iterator();
        
        while ( iterator.hasNext() )
        {
            Deals deal = iterator.next();
            result += "\"" + deal.getDealsName() + "\"";
            
            if ( iterator.hasNext() )
            {
                result += ",";
            }
        }
        
        result += "]";
        return result;
    }
}
