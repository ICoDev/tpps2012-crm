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
        String result = "<tbody>";
        
        
        Iterator<Events> iterator = events.iterator();
        
        while ( iterator.hasNext() )
        {
            Events event = iterator.next();
            
            result += "<tr>";
            result += "<td><span class=\"label\">" + event.getEventsDate().toString() + " "
                            + event.getEventsTime().toString()
                            + "</span>";
            
            String eventObjectName = "";
            String icon = "";
            
            if (  null != event.getContaktsId() ) //Event happened with Contacts
            {
                eventObjectName = event.getContaktsId().getContactsName();
                //iconName = "icon-user";
                icon = "<span class=\"badge badge-success\"><i class=\"icon-user\"></i></span>";
            }
            else if (  null != event.getDealsId() ) //Event happened with Contacts
            {
                eventObjectName = event.getDealsId().getDealsName();
                icon = "<span class=\"badge badge-warning\"><i class=\"icon-fire\"></i></span>";
            }
            else if (  null != event.getTasksId() ) //Event happened with Contacts
            {
                eventObjectName = event.getTasksId().getTasksName();
                icon = "<span class=\"badge badge-inverse\"><i class=\"icon-cog icon-white\"></i></span>";
            }
            
            result += "<td>" + icon + " <a href = \"\">" + eventObjectName + "</a></td>";
            //"<a href=\"\" ><span class=\"label label-info\">Информационный портал</span></a>";
            //"<p><a href=\"\">Игорь Петрович</a></p>"
            //"<i class=\"icon-pencil\"></i></span>"
            result += "<td>" + event.getEventsText() + "</td>";
            result += "</tr>";
        }
        
        result += "</tbody>";
        return result;
//        <tbody>
//                <tr>
//                  <td><span class="label">30.09.2012 08:02</span>
//                    <p>Иван Иванов</p>
//                  </td>
//                  <td>
//                      <a href="" ><span class="label label-info">Информационный портал</span></a>
//                      <p><a href="">Игорь Петрович</a></p>
//                  </td>
//                  <td>
//                    <span class="badge"><i class="icon-pencil"></i></span>
//                    Оказывается, требуется поддержка 24х7, хотя ранее это нигде не упоминалось
//                  </td>
//                </tr>
//                <tr>
//                  <td><span class="label">28.09.2012 15:47</span>
//                    <p>Иван Иванов</p>
//                  </td>
//                  <td>
//                    <a href="#s" ><span class="label label-info">Техническая поддержка </span></a>
//                    <p><a href="">Игорь Петрович</a></p>
//                  </td>
//                  <td>
//                    <span class="badge badge-warning"><i class="icon-fire"></i></span>
//                    Добавлена новая сделка
//                  </td>
//                </tr>
//                <tr>
//                  <td><span class="label">27.09.2012 08:02</span><p>Иван Иванов</p></td>
//                  <td><p><a href="">Василий Сергеев</a></p></td>
//                  <td>
//                    <span class="badge badge-success"><i class="icon-user"></i></span>
//                      Добавлен новый контакт
//                  </td>
//                </tr>
//                <tr>
//                  <td><span class="label">23.09.2012 10:45</span><p>Иван Иванов</p></td>
//                  <td>
//                    <a href="#s" ><span class="label label-info">Разработка корпоративного сайта</span></a>
//                    <p><a href="">Михаил Александрович</a></p>
//                  </td>
//                  <td>
//                    <span class="badge badge-inverse"><i class="icon-cog icon-white"></i></span>
//                     <span class="label label-info">Первичный контакт</span> на <span class="label label-warning">Переговоры</span>
//                  </td>
//                </tr>
//
//              </tbody>
    }
}
