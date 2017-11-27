package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.ValorUf;
import com.areatecnica.sigf.controllers.ValorUfFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "valorUfController")
@ViewScoped
public class ValorUfController extends AbstractController<ValorUf> {

    @Inject
    private ValorUfFacade ejbFacade;

    /**
     * Initialize the concrete ValorUf controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ValorUfController() {
        // Inform the Abstract parent controller of the concrete ValorUf Entity
        super(ValorUf.class);
    }

}
