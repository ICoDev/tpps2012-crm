/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import domain.Tasks;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author KC
 */
public class TasksView {

    public static String getTasksSelect(List <Tasks> tasks , String caption)
    {
        String result = "<div class=\"control-group\">";
        result += "<label class=\"control-label\" for=\"inputEmail\">" + caption + "</label>";
        result += "<div class=\"controls\">";
        result += "<select class=\"input-block-level\" name=\"taskSelect\">";
        
        Iterator<Tasks> iterator = tasks.iterator();
        
        while ( iterator.hasNext() )
        {
            Tasks task = iterator.next();            
            result += "<option>" + task.getTasksText() + "</option>";
        }
        
        result += "</select>";
        result += "</div>";
        result += "</div>";
        
        return result;
    }

    public static String getTasksTable(List <Tasks> tasks)
    {
        String result = "<table class=\"table\">\n" +
"              <thead>\n" +
"                <tr>\n" +
"                  <th>Дата исполнения / Ответственный</th>\n" +
"                  <th>Объект задачи</th>\n" +
"                  <th>Текст задачи</th>\n" +
"                  <th></th>\n" +
"                </tr>\n" +
"              </thead><tbody>";
        
        Iterator<Tasks> iterator = tasks.iterator();
        
        while ( iterator.hasNext() )
        {
            Tasks task = iterator.next();
            String companyName = "-";
            String iconName = "";
            
            if ( task.getTasksType().equals("call") )
            {
                iconName += "<span class=\"badge badge-info\"><i class=\"icon-bell\"></i></span>";
            }
            else if ( task.getTasksType().equals("message"))
            {
                iconName += "<span class=\"badge badge-warning\"><i class=\"icon-envelope\"></i></span>";
            }
            if ( task.getTasksType().equals("meeting") )
            {
                iconName += "<span class=\"badge badge-success\"><i class=\"icon-glass\"></i></span>";
            }                
            
            result += "<tr>";
            result += "<td><span class=\"label\">" + task.getTasksDate().toString()
                            + " " + task.getTasksTime().toString() +"</span><p> " 
                            + task.getResponsible() + "</p></td>";
            result += "<td>";
            
            if ( null != task.getDealsId() )
            {
                result += "<span class=\"label label-info\">" + task.getDealsId().getDealsName() +"</span>"; 
            }
            
            String mainContact = "-";
            String mainContactHref = "";
            
            if ( null != task.getContactsId() )
            {
                mainContact = task.getContactsId().getContactsName();
                mainContactHref = "contacts.jsp?id=" + task.getContactsId().getId();
            }
            
            result += "<p><a href=\"" + mainContactHref + "\">" + mainContact + "</a></p></td>";            
            result += "<td>" + iconName + " " + task.getTasksText() + "</td>";
            result += "<td><a class=\"btn text-error\" href=\"\">Закрыть</a></td>";
            result += "</tr>";
        }
                
        result += "</tbody></table>";
        
        return result;
    }
    
    public static String getTasksTable(Set <Tasks> tasks)
    {
        String result = "<table class=\"table\">\n" +
"              <thead>\n" +
"                <tr>\n" +
"                  <th>Дата исполнения / Ответственный</th>\n" +
"                  <th>Объект задачи</th>\n" +
"                  <th>Текст задачи</th>\n" +
"                  <th></th>\n" +
"                </tr>\n" +
"              </thead><tbody>";
        
        Iterator<Tasks> iterator = tasks.iterator();
        
        while ( iterator.hasNext() )
        {
            Tasks task = iterator.next();
            String companyName = "-";
            String iconName = "";
            
            if ( task.getTasksType().equals("call") )
            {
                iconName += "<span class=\"badge badge-info\"><i class=\"icon-bell\"></i></span>";
            }
            else if ( task.getTasksType().equals("message"))
            {
                iconName += "<span class=\"badge badge-warning\"><i class=\"icon-envelope\"></i></span>";
            }
            if ( task.getTasksType().equals("meeting") )
            {
                iconName += "<span class=\"badge badge-success\"><i class=\"icon-glass\"></i></span>";
            }                
            
            result += "<tr>";
            result += "<td><span class=\"label\">" + task.getTasksDate().toString()
                            + " " + task.getTasksTime().toString() +"</span><p> " 
                            + task.getResponsible() + "</p></td>";
            result += "<td>";
            
            if ( null != task.getDealsId() )
            {
                result += "<span class=\"label label-info\">" + task.getDealsId().getDealsName() +"</span>"; 
            }
            
            String mainContact = "-";
            String mainContactHref = "";
            
            if ( null != task.getContactsId() )
            {
                mainContact = task.getContactsId().getContactsName();
                mainContactHref = "contacts.jsp?id=" + task.getContactsId().getId();
            }
            
            result += "<p><a href=\"" + mainContactHref + "\">" + mainContact + "</a></p></td>";            
            result += "<td>" + iconName + " " + task.getTasksText() + "</td>";
            result += "<td>";
            
            if ( 0 == task.getStatus().compareTo("not completed") )
            {
                result += "<a class=\"btn text-error\" href=\"/FilterTasks?id=" + task.getId() + "\">Закрыть</a>";
            }
            
            result += "<td>";
            result += "</tr>";
        }
                
        result += "</tbody></table>";
        
        return result;
    }
}
