package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.SueldoBase;
import com.areatecnica.sigf.controllers.SueldoBaseFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "sueldoBaseController")
@ViewScoped
public class SueldoBaseController extends AbstractController<SueldoBase> {

    @Inject
    private SueldoBaseFacade ejbFacade;

    /**
     * Initialize the concrete SueldoBase controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public SueldoBaseController() {
        // Inform the Abstract parent controller of the concrete SueldoBase Entity
        super(SueldoBase.class);
    }

}
