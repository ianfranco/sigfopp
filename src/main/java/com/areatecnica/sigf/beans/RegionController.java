package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.Region;
import com.areatecnica.sigf.controllers.RegionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "regionController")
@ViewScoped
public class RegionController extends AbstractController<Region> {

    @Inject
    private RegionFacade ejbFacade;

    /**
     * Initialize the concrete Region controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public RegionController() {
        // Inform the Abstract parent controller of the concrete Region Entity
        super(Region.class);
    }

    /**
     * Sets the "items" attribute with a collection of Ciudad entities that are
     * retrieved from Region?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Ciudad page
     */
    public String navigateCiudadList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Ciudad_items", this.getSelected().getCiudadList());
        }
        return "/ciudad/index";
    }

    /**
     * Sets the "items" attribute with a collection of UnidadNegocio entities
     * that are retrieved from Region?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for UnidadNegocio page
     */
    public String navigateUnidadNegocioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("UnidadNegocio_items", this.getSelected().getUnidadNegocioList());
        }
        return "/unidadNegocio/index";
    }

}
