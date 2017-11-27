package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.ServicioController;
import com.areatecnica.sigf.entities.ControlServicio;
import com.areatecnica.sigf.controllers.ControlServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "controlServicioController")
@ViewScoped
public class ControlServicioController extends AbstractController<ControlServicio> {

    @Inject
    private ControlServicioFacade ejbFacade;
    @Inject
    private ControlController controlServicioIdControlController;
    @Inject
    private ServicioController controlServicioIdServicioController;

    /**
     * Initialize the concrete ControlServicio controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ControlServicioController() {
        // Inform the Abstract parent controller of the concrete ControlServicio Entity
        super(ControlServicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        controlServicioIdControlController.setSelected(null);
        controlServicioIdServicioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Control controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlServicioIdControl(ActionEvent event) {
        if (this.getSelected() != null && controlServicioIdControlController.getSelected() == null) {
            controlServicioIdControlController.setSelected(this.getSelected().getControlServicioIdControl());
        }
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlServicioIdServicio(ActionEvent event) {
        if (this.getSelected() != null && controlServicioIdServicioController.getSelected() == null) {
            controlServicioIdServicioController.setSelected(this.getSelected().getControlServicioIdServicio());
        }
    }

    /**
     * Sets the "items" attribute with a collection of ControlHorarioServicio
     * entities that are retrieved from ControlServicio?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for ControlHorarioServicio page
     */
    public String navigateControlHorarioServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ControlHorarioServicio_items", this.getSelected().getControlHorarioServicioList());
        }
        return "/controlHorarioServicio/index";
    }

}
