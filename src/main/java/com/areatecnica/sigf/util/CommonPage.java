package com.areatecnica.sigf.util;


import com.areatecnica.sigf.entities.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Omer Faruk KURT
 * @email kurtomerfaruk@gmail.com
 * @blog http://kurtomerfaruk.wordpress.com
 */

public class CommonPage {

    private static Map<Usuario, UserForm> users = new HashMap<Usuario, UserForm>();
    private static List<Usuario> usuarioList = new ArrayList<>();

    public static Map<Usuario, UserForm> getUsers() {
        return users;
    }

    public static void setUsers(Map<Usuario, UserForm> users) {
        CommonPage.users = users;
    }

    public static List<Usuario> getUsuarioList() {
        return usuarioList;
    }
    
    

    public static void addStaff(UserForm userForm, String sessionId) {
        if (users.containsKey(userForm.getUsuario())) {
            UserForm oldUserSession = (UserForm) users.get(userForm.getUsuario());
            if (!oldUserSession.getSession().getId().equalsIgnoreCase(sessionId)) {
                try {
                    oldUserSession.getSession().invalidate();
                } catch (Exception localException) {
                }
                users.remove(userForm.getUsuario());
                usuarioList.remove(userForm.getUsuario());
                System.out.println("This user have another session,kill it");
            }
        }
        users.put(userForm.getUsuario(), userForm);
        usuarioList.add(userForm.getUsuario());
    }

    public static void removeStaff(Usuario usuario) {
        if (users.containsKey(usuario)) {
            users.remove(usuario);
            usuarioList.remove(usuario);
        }
    }

    public static int getUserSessionSize() {
        return users.size();
    }

}
