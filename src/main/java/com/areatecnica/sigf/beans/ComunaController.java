package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.CiudadController;
import com.areatecnica.sigf.entities.Comuna;
import com.areatecnica.sigf.controllers.ComunaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "comunaController")
@ViewScoped
public class ComunaController extends AbstractController<Comuna> {

    @Inject
    private ComunaFacade ejbFacade;
    @Inject
    private CiudadController comunaIdCiudadController;

    /**
     * Initialize the concrete Comuna controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ComunaController() {
        // Inform the Abstract parent controller of the concrete Comuna Entity
        super(Comuna.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        comunaIdCiudadController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Calle entities that are
     * retrieved from Comuna?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Calle page
     */
    public String navigateCalleList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Calle_items", this.getSelected().getCalleList());
        }
        return "/calle/index";
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from Comuna?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", this.getSelected().getTrabajadorList());
        }
        return "/trabajador/index";
    }

    /**
     * Sets the "items" attribute with a collection of Terminal entities that
     * are retrieved from Comuna?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Terminal page
     */
    public String navigateTerminalList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Terminal_items", this.getSelected().getTerminalList());
        }
        return "/terminal/index";
    }

    /**
     * Sets the "selected" attribute of the Ciudad controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareComunaIdCiudad(ActionEvent event) {
        if (this.getSelected() != null && comunaIdCiudadController.getSelected() == null) {
            comunaIdCiudadController.setSelected(this.getSelected().getComunaIdCiudad());
        }
    }
}
