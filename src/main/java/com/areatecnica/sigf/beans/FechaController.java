package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Fecha;
import com.areatecnica.sigf.controllers.FechaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "fechaController")
@ViewScoped
public class FechaController extends AbstractController<Fecha> {

    @Inject
    private FechaFacade ejbFacade;

    /**
     * Initialize the concrete Fecha controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public FechaController() {
        // Inform the Abstract parent controller of the concrete Fecha Entity
        super(Fecha.class);
    }

}
