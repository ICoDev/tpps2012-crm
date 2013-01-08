/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import domain.Events;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author KC
 */
public class EventsView {
    
    public static String getEventsTable( List<Events> events)
    {
        String result = "<table class=\"table\">\n" +
"              <thead>\n" +
"                <tr>\n" +
"                  <th>Дата / Время</th>\n" +
"                  <th>Объект</th>\n" +
"                  <th>Событие</th>\n" +
"                </tr>\n" +
"              </thead>"
                + "<tbody>";
        
        
        Iterator<Events> iterator = events.iterator();
        
        while ( iterator.hasNext() )
        {
            Events event = iterator.next();
            
            result += "<tr>";
            result += "<td><span class=\"label\">" + event.getEventsDate().toString() + " "
                            + event.getEventsTime().toString()
                            + "</span>";
            
            String eventObjectName = "";
            String evenObjectHref = "";
            String icon = "";
            
            if (  null != event.getContaktsId() ) //Event happened with Contacts
            {
                eventObjectName = event.getContaktsId().getContactsName();
                evenObjectHref = "contacts.jsp?id=" + event.getContaktsId().getId();
                icon = "<span class=\"badge badge-success\"><i class=\"icon-user\"></i></span>";
            }
            else if (  null != event.getDealsId() ) //Event happened with Deals
            {
                eventObjectName = event.getDealsId().getDealsName();
                evenObjectHref = "deals.jsp?id=" + event.getDealsId().getId();
                icon = "<span class=\"badge badge-warning\"><i class=\"icon-fire\"></i></span>";
            }
            else if (  null != event.getTasksId() ) //Event happened with Tasks
            {
                eventObjectName = event.getTasksId().getTasksName();
                evenObjectHref = "tasks.jsp?id=" + event.getTasksId().getId();
                icon = "<span class=\"badge badge-inverse\"><i class=\"icon-cog icon-white\"></i></span>";
            }
            
            result += "<td>" + icon + " <a href = \"" + evenObjectHref + "\">" + eventObjectName + "</a></td>";
            result += "<td>" + event.getEventsText() + "</td>";
            result += "</tr>";
        }
        
        result += "</tbody></table>";
        return result;
    }
}
