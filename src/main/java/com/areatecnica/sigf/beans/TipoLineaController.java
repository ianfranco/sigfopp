package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.TipoLinea;
import com.areatecnica.sigf.controllers.TipoLineaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tipoLineaController")
@ViewScoped
public class TipoLineaController extends AbstractController<TipoLinea> {

    @Inject
    private TipoLineaFacade ejbFacade;

    /**
     * Initialize the concrete TipoLinea controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoLineaController() {
        // Inform the Abstract parent controller of the concrete TipoLinea Entity
        super(TipoLinea.class);
    }

}
