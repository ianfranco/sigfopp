package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.ValorMinuto;
import com.areatecnica.sigf.controllers.ValorMinutoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "valorMinutoController")
@ViewScoped
public class ValorMinutoController extends AbstractController<ValorMinuto> {

    @Inject
    private ValorMinutoFacade ejbFacade;

    /**
     * Initialize the concrete ValorMinuto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ValorMinutoController() {
        // Inform the Abstract parent controller of the concrete ValorMinuto Entity
        super(ValorMinuto.class);
    }

    @Override
    public ValorMinuto prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setValorMinutoIdCuenta(this.getUserCount());        
        return this.getSelected();
    }

}
