package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TipoControlController;
import com.areatecnica.sigf.entities.Control;
import com.areatecnica.sigf.controllers.ControlFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "controlController")
@ViewScoped
public class ControlController extends AbstractController<Control> {

    @Inject
    private ControlFacade ejbFacade;
    @Inject
    private TipoControlController controlIdTipoControlController;

    /**
     * Initialize the concrete Control controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ControlController() {
        // Inform the Abstract parent controller of the concrete Control Entity
        super(Control.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        controlIdTipoControlController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of ControlServicio entities
     * that are retrieved from Control?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ControlServicio page
     */
    public String navigateControlServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ControlServicio_items", this.getSelected().getControlServicioList());
        }
        return "/controlServicio/index";
    }

    /**
     * Sets the "selected" attribute of the TipoControl controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlIdTipoControl(ActionEvent event) {
        if (this.getSelected() != null && controlIdTipoControlController.getSelected() == null) {
            controlIdTipoControlController.setSelected(this.getSelected().getControlIdTipoControl());
        }
    }
}
