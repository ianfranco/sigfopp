package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.ValorUtm;
import com.areatecnica.sigf.controllers.ValorUtmFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "valorUtmController")
@ViewScoped
public class ValorUtmController extends AbstractController<ValorUtm> {

    @Inject
    private ValorUtmFacade ejbFacade;

    /**
     * Initialize the concrete ValorUtm controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ValorUtmController() {
        // Inform the Abstract parent controller of the concrete ValorUtm Entity
        super(ValorUtm.class);
    }

}
