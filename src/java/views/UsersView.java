/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import domain.Users;
import java.util.Iterator;
import java.util.List;
import javassist.bytecode.Descriptor;

/**
 *
 * @author KC
 */
public class UsersView {

    public static String getUsersSelect(List <Users> users, String caption)
    {
        String result = "<div class=\"control-group\">";
        result += "<label class=\"control-label\" for=\"inputEmail\">" + caption + "</label>";
        result += "<div class=\"controls\">";
        result += "<select class=\"input-block-level\" name=\"usersSelect\">";
        result += "<option></option>";
        
        Iterator<Users> iterator = users.iterator();
        
        while ( iterator.hasNext() )
        {
            Users user = iterator.next();            
            result += "<option>" + user.getLogin() + "</option>";
        }
        
        result += "</select>";
        result += "</div>";
        result += "</div>";
        
        return result;
    }
}
