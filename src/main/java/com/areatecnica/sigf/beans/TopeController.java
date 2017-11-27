package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.Tope;
import com.areatecnica.sigf.controllers.TopeFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "topeController")
@ViewScoped
public class TopeController extends AbstractController<Tope> {

    @Inject
    private TopeFacade ejbFacade;

    /**
     * Initialize the concrete Tope controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TopeController() {
        // Inform the Abstract parent controller of the concrete Tope Entity
        super(Tope.class);
    }

}
