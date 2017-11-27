package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Mutual;
import com.areatecnica.sigf.controllers.MutualFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "mutualController")
@ViewScoped
public class MutualController extends AbstractController<Mutual> {

    @Inject
    private MutualFacade ejbFacade;

    /**
     * Initialize the concrete Mutual controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public MutualController() {
        // Inform the Abstract parent controller of the concrete Mutual Entity
        super(Mutual.class);
    }

    /**
     * Sets the "items" attribute with a collection of Empresa entities that are
     * retrieved from Mutual?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Empresa page
     */
    public String navigateEmpresaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empresa_items", this.getSelected().getEmpresaList());
        }
        return "/empresa/index";
    }

    @Override
    public Mutual prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setMutualIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

    public void resetParents(){
        
    }
    
}
