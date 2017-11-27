package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.controllers.UsuarioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {

    @Inject
    private UsuarioFacade ejbFacade;
    @Inject
    private RolController usuarioIdRolController;
    @Inject
    private TerminalController usuarioIdTerminalController;

    /**
     * Initialize the concrete Usuario controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public UsuarioController() {
        // Inform the Abstract parent controller of the concrete Usuario Entity
        super(Usuario.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Usuario.findAllByCuenta");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        usuarioIdRolController.setSelected(null);
        usuarioIdTerminalController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of CajaTerminal entities
     * that are retrieved from Usuario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CajaTerminal page
     */
    public String navigateCajaTerminalList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CajaTerminal_items", this.getSelected().getCajaRecaudacionList());
        }
        return "/cajaTerminal/index";
    }

    /**
     * Sets the "items" attribute with a collection of Despacho entities that
     * are retrieved from Usuario?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Despacho page
     */
    public String navigateDespachoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Despacho_items", this.getSelected().getDespachoList());
        }
        return "/despacho/index";
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuarioIdRol(ActionEvent event) {
        if (this.getSelected() != null && usuarioIdRolController.getSelected() == null) {
            usuarioIdRolController.setSelected(this.getSelected().getUsuarioIdRol());
        }
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuarioIdTerminal(ActionEvent event) {
        if (this.getSelected() != null && usuarioIdTerminalController.getSelected() == null) {
            usuarioIdTerminalController.setSelected(this.getSelected().getUsuarioIdTerminal());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Log entities that are
     * retrieved from Usuario?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Log page
     */
    public String navigateLogList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Log_items", this.getSelected().getLogList());
        }
        return "/log/index";
    }

    @Override
    public Usuario prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setUsuarioIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

}
