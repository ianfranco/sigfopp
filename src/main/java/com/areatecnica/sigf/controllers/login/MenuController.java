package com.areatecnica.sigf.controllers.login;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.dao.IRolMenuDao;
import com.areatecnica.sigf.dao.impl.IRolMenuDaoImpl;
import com.areatecnica.sigf.entities.Privilegio;
import com.areatecnica.sigf.entities.RolMenu;
import com.areatecnica.sigf.entities.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Omer Faruk KURT
 * @e-mail kurtomerfaruk@gmail.com
 * @blog http://kurtomerfaruk.com
 */
@ManagedBean
@ViewScoped
public class MenuController implements java.io.Serializable {

    private static final long serialVersionUID = 7481372634818437093L;

    private String pageLink;
    private String pageName;
    private List<Menu> menuList;
    private String searhText;
    private Usuario usuario;
    private IRolMenuDao dao;

    /**
     *
     */
    public MenuController() {
        this.pageLink = "blankPage";
        this.pageName = "Dashboard";
        this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     */
    public String getPageLink() {
        return pageLink;
    }

    /**
     *
     * @param pageLink
     */
    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }

    /**
     *
     * @return
     */
    public String getPageName() {
        return pageName;
    }

    /**
     *
     * @param pageName
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public List<Menu> getMenuList() {

        dao = new IRolMenuDaoImpl();

        if (menuList == null) {
            menuList = new ArrayList<Menu>();

            int auxId = 0;
            if (!this.getUsuario().getUsuarioIdRol().getRolMenuList().isEmpty()) {

                List<RolMenu> aux = dao.findAllByRol(this.getUsuario().getUsuarioIdRol());

                for (RolMenu m : aux) {

                    List<Privilegio> privilegios = m.getRolMenuIdMenu().getPrivilegioList();
                    menuList.add(new Menu(m.getRolMenuIdMenu().getMenuId(),
                            m.getRolMenuIdMenu().getMenuNombre(),
                            null,
                            1,
                            null,
                            "fa fa-bars",
                            "white",
                            1));

                    for (Privilegio p : privilegios) {
                        menuList.add(new Menu(p.getPrivilegioId(),
                                p.getPrivilegioNombre(),
                                m.getRolMenuIdMenu().getMenuId(),
                                2,
                                p.getPrivilegioMenuLink(),
                                p.getPrivilegioIcon(),
                                p.getPrivilegioColor(),
                                p.getPrivilegioId()));
                    }
                }

            } else {
                JsfUtil.addExclamationMessage("No tiene privilegios asignados a su cuenta");
            }

        } else {

        }

        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

//    /**
//     *
//     * @return
//     */
//    public List<Menu> menuList() {
//        return menuDao.findAll();
//    }
    public String getSearhText() {
        return searhText;
    }

    public void setSearhText(String searhText) {
        this.searhText = searhText;
    }

    /**
     *
     * @param link
     * @param name
     */
    public void setPage(String link, String name) {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> map = context.getViewRoot().getViewMap();
        List<String> list = new ArrayList<>();

        for (String key : map.keySet()) {
            if (!key.equals("menuController")) {
                list.add(key);
            }
        }

        if (list != null && !list.isEmpty()) {
            for (String get : list) {
                map.remove(get);
            }
        }

        setPageLink(link);
        setPageName(name);
    }

    public void menuSearchValueChange(ValueChangeEvent event) {
        System.out.println("Menu Search Value Change");
        if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {

            System.out.println("Event getOldValue");
            System.out.println("menuListSize:" + menuList.size());
        }

        for (int i = 0; i < menuList.size(); i++) {
            Menu get = menuList.get(i);
            if (get.getTopMenuId() != null) {
                Menu topMenu = new Menu();//menuDao.getTopMenu(get.getTopMenuId());

                if (topMenu != null) {
                    if (!menuList.contains(topMenu)) {
                        menuList.add(topMenu);
                    }
                }
            }
        }
    }

}
