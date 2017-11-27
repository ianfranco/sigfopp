package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.ImpuestoSegundaCategoria;
import com.areatecnica.sigf.controllers.ImpuestoSegundaCategoriaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "impuestoSegundaCategoriaController")
@ViewScoped
public class ImpuestoSegundaCategoriaController extends AbstractController<ImpuestoSegundaCategoria> {

    @Inject
    private ImpuestoSegundaCategoriaFacade ejbFacade;

    /**
     * Initialize the concrete ImpuestoSegundaCategoria controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ImpuestoSegundaCategoriaController() {
        // Inform the Abstract parent controller of the concrete ImpuestoSegundaCategoria Entity
        super(ImpuestoSegundaCategoria.class);
    }

}
