/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import domain.Tasks;
import java.util.Iterator;
import java.util.List;

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
        String result = "<tbody>";
        
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
            if ( null != task.getContactsId() )
            {
                mainContact = task.getContactsId().getContactsName();
            }
            
            result += "<p><a href=\"\">" + mainContact + "</a></p></td>";            
            result += "<td>" + iconName + " " + task.getTasksText() + "</td>";
            result += "<td><a class=\"btn text-error\" href=\"\">Закрыть</a></td>";
            result += "</tr>";
        }
                
        result += "</tbody>";
        
        return result;
    }
}
