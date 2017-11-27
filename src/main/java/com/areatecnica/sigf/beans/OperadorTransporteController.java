package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.OperadorTransporte;
import com.areatecnica.sigf.controllers.OperadorTransporteFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "operadorTransporteController")
@ViewScoped
public class OperadorTransporteController extends AbstractController<OperadorTransporte> {

    @Inject
    private OperadorTransporteFacade ejbFacade;

    /**
     * Initialize the concrete OperadorTransporte controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public OperadorTransporteController() {
        // Inform the Abstract parent controller of the concrete OperadorTransporte Entity
        super(OperadorTransporte.class);
    }

    /**
     * Sets the "items" attribute with a collection of RelacionLaboral entities
     * that are retrieved from OperadorTransporte?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RelacionLaboral page
     */
    public String navigateRelacionLaboralList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RelacionLaboral_items", this.getSelected().getRelacionLaboralList());
        }
        return "/relacionLaboral/index";
    }

    /**
     * Sets the "items" attribute with a collection of UnidadNegocio entities
     * that are retrieved from OperadorTransporte?cap_first and returns the
     * navigation outcome.
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
