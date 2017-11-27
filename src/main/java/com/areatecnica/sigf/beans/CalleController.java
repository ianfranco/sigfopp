package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.Calle;
import com.areatecnica.sigf.controllers.CalleFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "calleController")
@ViewScoped
public class CalleController extends AbstractController<Calle> {

    @Inject
    private CalleFacade ejbFacade;
    @Inject
    private ComunaController calleIdComunaController;

    /**
     * Initialize the concrete Calle controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CalleController() {
        // Inform the Abstract parent controller of the concrete Calle Entity
        super(Calle.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        calleIdComunaController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of CalleServicio entities
     * that are retrieved from Calle?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CalleServicio page
     */
    public String navigateCalleServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CalleServicio_items", this.getSelected().getCalleServicioList());
        }
        return "/calleServicio/index";
    }

    /**
     * Sets the "selected" attribute of the Comuna controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCalleIdComuna(ActionEvent event) {
        if (this.getSelected() != null && calleIdComunaController.getSelected() == null) {
            calleIdComunaController.setSelected(this.getSelected().getCalleIdComuna());
        }
    }
}
