package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.DiaFestivo;
import com.areatecnica.sigf.controllers.DiaFestivoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "diaFestivoController")
@ViewScoped
public class DiaFestivoController extends AbstractController<DiaFestivo> {

    @Inject
    private DiaFestivoFacade ejbFacade;

    /**
     * Initialize the concrete DiaFestivo controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public DiaFestivoController() {
        // Inform the Abstract parent controller of the concrete DiaFestivo Entity
        super(DiaFestivo.class);
    }

}
