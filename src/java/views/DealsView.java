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
        String result = "<table class=\"table\">\n" +
"              <thead>\n" +
"                <tr>\n" +
"                  <th>Название сделки</th>\n" +
"                  <th>Основной контакт</th>\n" +
"                  <th>Компания</th>\n" +
"                  <th>Статус сделки</th>\n" +
"                  <th>Бюджет</th>\n" +
"                </tr>\n" +
"              </thead><tbody>";
        
        Iterator<Deals> iterator = deals.iterator();
        
        while ( iterator.hasNext() )
        {
            result += "<tr>";
            
            Deals deal = iterator.next();
            String companyName = "-";
            String companyHref = "";
            
            String mainContact = "-";
            String mainContactHref = "";
                                    
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
                        mainContactHref = "contacts.jsp?id=" + contact.getId();
                        
                        if( contact.getCompanyId() != null )
                        {
                            companyName = contact.getCompanyId().getCompanyName();
                            companyHref = "companies.jsp?id=" + contact.getCompanyId().getId();
                        }
                    }
                }
            }
            
            result += "<td><p><a href=\"deals.jsp?id=" + deal.getId() + "\">"
                                    + deal.getDealsName() + "</a></p></td>";
            result += "<td><p><a href=\"" + mainContactHref + "\">"
                        + mainContact + "</a></p></td>";
            result += "<td><p><a href=\"" + companyHref + "\">" + companyName + "</a></p></td>";
            result += "<td><span class=\"label label-important\">" + deal.getDealsType() + "</span></td>";
            result += "<td><span class=\"badge\">" + deal.getMoney() + " грн</span></td>";
            result += "</tr>";
        }
                
        result += "</tbody></table>";
        
        return result;
    }
    
    public static String getTags( List<Deals> deals )
    {
        String result = "<p>";
        
        Iterator<Deals> iterator = deals.iterator();
        
        while ( iterator.hasNext() )
        {
            Deals deal = iterator.next();
            
            result += "<a href=\"deals.jsp?tag=" + deal.getDealsTag() + "\">"
                    + "<span class=\"label\">" + deal.getDealsTag() + "</span>"
                    + "</a>\n";
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
    
    public static String printDealInfo(Deals deal)
    {                          
        String result = "<h3>" + deal.getDealsName() + "</h3>"
                + "<br/><br/>";

        String companyName = "-";
        String companyHref = "";

        String mainContact = "-";
        String mainContactHref = "";

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
                    mainContactHref = "contacts.jsp?id=" + contact.getId();

                    if( contact.getCompanyId() != null )
                    {
                        companyName = contact.getCompanyId().getCompanyName();
                        companyHref = "companies.jsp?id=" + contact.getCompanyId().getId();
                    }
                }
            }
        }
            
        result += "<b>Главный контакт: </b><a href=\"" + mainContactHref + "\">"
                    + mainContact + "</a><br/><br/>";
        result += "<b>Компания, с которой осуществляется сделка: </b><a href=\"" + companyHref + "\">" 
                + companyName + "</a><br/><br/>";
        result += "<b>Тип сделки: </b><span class=\"label label-important\">" + deal.getDealsType() + "</span><br/><br/>";
        result += "<b>Бюджет сделки: </b><span class=\"badge\">" + deal.getMoney() + " грн</span>"
                + "<br/><br/><br/><br/>";        
        
        //now printing companies contacts;
        if ( deal.getContactsSet().isEmpty()  )
        {
            result += "<b>Контактов, связанных с данной сделкой нет</b><br/><br/>";
        }
        else
        {
            result += "<b>Контакты, связанные с компанией:</b><br/><br/>";
            result += ContactsView.getContactsTable( deal.getContactsSet() );
        }
        
        result += "<br/><br/><br/><br/>";   
        
        //and finally printing tasks, atached to the deal
        if ( deal.getTasksSet().isEmpty()  )
        {
            result += "<b>Задач, связанных с данной сделкой нет</b><br/><br/>";
        }
        else
        {
            result += "<b>Задачи, связанные с данной сделкой:</b><br/><br/>";
            result += TasksView.getTasksTable( deal.getTasksSet() );
        }
                
        return result;
    }
}
