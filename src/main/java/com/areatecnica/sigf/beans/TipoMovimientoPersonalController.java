package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.TipoMovimientoPersonal;
import com.areatecnica.sigf.controllers.TipoMovimientoPersonalFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tipoMovimientoPersonalController")
@ViewScoped
public class TipoMovimientoPersonalController extends AbstractController<TipoMovimientoPersonal> {

    @Inject
    private TipoMovimientoPersonalFacade ejbFacade;

    /**
     * Initialize the concrete TipoMovimientoPersonal controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoMovimientoPersonalController() {
        // Inform the Abstract parent controller of the concrete TipoMovimientoPersonal Entity
        super(TipoMovimientoPersonal.class);
    }

}
