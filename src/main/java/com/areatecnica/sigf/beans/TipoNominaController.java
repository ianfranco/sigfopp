package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.TipoNomina;
import com.areatecnica.sigf.controllers.TipoNominaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tipoNominaController")
@ViewScoped
public class TipoNominaController extends AbstractController<TipoNomina> {

    @Inject
    private TipoNominaFacade ejbFacade;

    /**
     * Initialize the concrete TipoNomina controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoNominaController() {
        // Inform the Abstract parent controller of the concrete TipoNomina Entity
        super(TipoNomina.class);
    }

}
