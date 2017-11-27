package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.UsuarioController;
import com.areatecnica.sigf.entities.Log;
import com.areatecnica.sigf.controllers.LogFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "logController")
@ViewScoped
public class LogController extends AbstractController<Log> {

    @Inject
    private LogFacade ejbFacade;
    @Inject
    private PrivilegioController logIdPrivilegioController;
    @Inject
    private UsuarioController logIdUsuarioController;

    /**
     * Initialize the concrete Log controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public LogController() {
        // Inform the Abstract parent controller of the concrete Log Entity
        super(Log.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        logIdPrivilegioController.setSelected(null);
        logIdUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Privilegio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLogIdPrivilegio(ActionEvent event) {
        if (this.getSelected() != null && logIdPrivilegioController.getSelected() == null) {
            logIdPrivilegioController.setSelected(this.getSelected().getLogIdPrivilegio());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLogIdUsuario(ActionEvent event) {
        if (this.getSelected() != null && logIdUsuarioController.getSelected() == null) {
            logIdUsuarioController.setSelected(this.getSelected().getLogIdUsuario());
        }
    }
}
