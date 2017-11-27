package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.ParametroInterpolacion;
import com.areatecnica.sigf.controllers.ParametroInterpolacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "parametroInterpolacionController")
@ViewScoped
public class ParametroInterpolacionController extends AbstractController<ParametroInterpolacion> {

    @Inject
    private ParametroInterpolacionFacade ejbFacade;

    /**
     * Initialize the concrete ParametroInterpolacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ParametroInterpolacionController() {
        // Inform the Abstract parent controller of the concrete ParametroInterpolacion Entity
        super(ParametroInterpolacion.class);
    }

}
