package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.AdministracionMensual;
import com.areatecnica.sigf.controllers.AdministracionMensualFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "administracionMensualController")
@ViewScoped
public class AdministracionMensualController extends AbstractController<AdministracionMensual> {

    @Inject
    private AdministracionMensualFacade ejbFacade;

    /**
     * Initialize the concrete AdministracionMensual controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public AdministracionMensualController() {
        // Inform the Abstract parent controller of the concrete AdministracionMensual Entity
        super(AdministracionMensual.class);
    }

    /**
     * Sets the "items" attribute with a collection of DetalleIntervalosMensual
     * entities that are retrieved from AdministracionMensual?cap_first and
     * returns the navigation outcome.
     *
     * @return navigation outcome for DetalleIntervalosMensual page
     */
    public String navigateDetalleIntervalosMensualList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleIntervalosMensual_items", this.getSelected().getDetalleIntervalosMensualList());
        }
        return "/detalleIntervalosMensual/index";
    }

}
