package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.Ciudad;
import com.areatecnica.sigf.controllers.CiudadFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "ciudadController")
@ViewScoped
public class CiudadController extends AbstractController<Ciudad> {

    @Inject
    private CiudadFacade ejbFacade;
    @Inject
    private RegionController ciudadIdRegionController;

    /**
     * Initialize the concrete Ciudad controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CiudadController() {
        // Inform the Abstract parent controller of the concrete Ciudad Entity
        super(Ciudad.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ciudadIdRegionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Region controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCiudadIdRegion(ActionEvent event) {
        if (this.getSelected() != null && ciudadIdRegionController.getSelected() == null) {
            ciudadIdRegionController.setSelected(this.getSelected().getCiudadIdRegion());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Comuna entities that are
     * retrieved from Ciudad?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Comuna page
     */
    public String navigateComunaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Comuna_items", this.getSelected().getComunaList());
        }
        return "/comuna/index";
    }

}
