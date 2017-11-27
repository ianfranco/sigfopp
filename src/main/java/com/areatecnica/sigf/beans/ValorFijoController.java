package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.ValorFijo;
import com.areatecnica.sigf.controllers.ValorFijoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "valorFijoController")
@ViewScoped
public class ValorFijoController extends AbstractController<ValorFijo> {

    @Inject
    private ValorFijoFacade ejbFacade;

    /**
     * Initialize the concrete ValorFijo controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ValorFijoController() {
        // Inform the Abstract parent controller of the concrete ValorFijo Entity
        super(ValorFijo.class);
    }

}
