package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.ParametroCesantia;
import com.areatecnica.sigf.controllers.ParametroCesantiaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "parametroCesantiaController")
@ViewScoped
public class ParametroCesantiaController extends AbstractController<ParametroCesantia> {

    @Inject
    private ParametroCesantiaFacade ejbFacade;

    /**
     * Initialize the concrete ParametroCesantia controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ParametroCesantiaController() {
        // Inform the Abstract parent controller of the concrete ParametroCesantia Entity
        super(ParametroCesantia.class);
    }

}
