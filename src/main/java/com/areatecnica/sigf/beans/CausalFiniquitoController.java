package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.CausalFiniquito;
import com.areatecnica.sigf.controllers.CausalFiniquitoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "causalFiniquitoController")
@ViewScoped
public class CausalFiniquitoController extends AbstractController<CausalFiniquito> {

    @Inject
    private CausalFiniquitoFacade ejbFacade;

    /**
     * Initialize the concrete CausalFiniquito controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CausalFiniquitoController() {
        // Inform the Abstract parent controller of the concrete CausalFiniquito Entity
        super(CausalFiniquito.class);
    }

    /**
     * Sets the "items" attribute with a collection of FiniquitoRelacionLaboral
     * entities that are retrieved from CausalFiniquito?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for FiniquitoRelacionLaboral page
     */
    public String navigateFiniquitoRelacionLaboralList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FiniquitoRelacionLaboral_items", this.getSelected().getFiniquitoRelacionLaboralList());
        }
        return "/finiquitoRelacionLaboral/index";
    }

}
