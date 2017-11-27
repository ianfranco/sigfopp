package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.TipoTrabajador;
import com.areatecnica.sigf.controllers.TipoTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoTrabajadorController")
@ViewScoped
public class TipoTrabajadorController extends AbstractController<TipoTrabajador> {

    @Inject
    private TipoTrabajadorFacade ejbFacade;

    /**
     * Initialize the concrete TipoTrabajador controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoTrabajadorController() {
        // Inform the Abstract parent controller of the concrete TipoTrabajador Entity
        super(TipoTrabajador.class);
    }

    /**
     * Sets the "items" attribute with a collection of RelacionLaboral entities
     * that are retrieved from TipoTrabajador?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RelacionLaboral page
     */
    public String navigateRelacionLaboralList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RelacionLaboral_items", this.getSelected().getRelacionLaboralList());
        }
        return "/relacionLaboral/index";
    }

    @Override
    public TipoTrabajador prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setTipoTrabajadorIdCuenta(this.getUserCount());        
        return this.getSelected();
    }

}
