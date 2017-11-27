package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.MonedaPactadaInstitucionSalud;
import com.areatecnica.sigf.controllers.MonedaPactadaInstitucionSaludFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "monedaPactadaInstitucionSaludController")
@ViewScoped
public class MonedaPactadaInstitucionSaludController extends AbstractController<MonedaPactadaInstitucionSalud> {

    @Inject
    private MonedaPactadaInstitucionSaludFacade ejbFacade;

    /**
     * Initialize the concrete MonedaPactadaInstitucionSalud controller bean.
     * The AbstractController requires the EJB Facade object for most
     * operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public MonedaPactadaInstitucionSaludController() {
        // Inform the Abstract parent controller of the concrete MonedaPactadaInstitucionSalud Entity
        super(MonedaPactadaInstitucionSalud.class);
    }

    /**
     * Sets the "items" attribute with a collection of TrabajadorAdicionalSalud
     * entities that are retrieved from MonedaPactadaInstitucionSalud?cap_first
     * and returns the navigation outcome.
     *
     * @return navigation outcome for TrabajadorAdicionalSalud page
     */
    public String navigateTrabajadorAdicionalSaludList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TrabajadorAdicionalSalud_items", this.getSelected().getTrabajadorAdicionalSaludList());
        }
        return "/trabajadorAdicionalSalud/index";
    }

}
