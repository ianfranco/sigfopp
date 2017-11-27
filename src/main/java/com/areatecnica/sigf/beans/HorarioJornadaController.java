package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.HorarioJornada;
import com.areatecnica.sigf.controllers.HorarioJornadaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "horarioJornadaController")
@ViewScoped
public class HorarioJornadaController extends AbstractController<HorarioJornada> {

    @Inject
    private HorarioJornadaFacade ejbFacade;

    /**
     * Initialize the concrete HorarioJornada controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public HorarioJornadaController() {
        // Inform the Abstract parent controller of the concrete HorarioJornada Entity
        super(HorarioJornada.class);
    }

    /**
     * Sets the "items" attribute with a collection of JornadaLaboral entities
     * that are retrieved from HorarioJornada?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for JornadaLaboral page
     */
    public String navigateJornadaLaboralList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("JornadaLaboral_items", this.getSelected().getJornadaLaboralList());
        }
        return "/jornadaLaboral/index";
    }

    @Override
    public HorarioJornada prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setHorarioJornadaIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

}
