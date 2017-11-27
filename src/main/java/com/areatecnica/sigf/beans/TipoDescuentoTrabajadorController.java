package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.TipoDescuentoTrabajador;
import com.areatecnica.sigf.controllers.TipoDescuentoTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoDescuentoTrabajadorController")
@ViewScoped
public class TipoDescuentoTrabajadorController extends AbstractController<TipoDescuentoTrabajador> {

    @Inject
    private TipoDescuentoTrabajadorFacade ejbFacade;

    /**
     * Initialize the concrete TipoDescuentoTrabajador controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoDescuentoTrabajadorController() {
        // Inform the Abstract parent controller of the concrete TipoDescuentoTrabajador Entity
        super(TipoDescuentoTrabajador.class);
    }

    /**
     * Sets the "items" attribute with a collection of DescuentoTrabajador
     * entities that are retrieved from TipoDescuentoTrabajador?cap_first and
     * returns the navigation outcome.
     *
     * @return navigation outcome for DescuentoTrabajador page
     */
    public String navigateDescuentoTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DescuentoTrabajador_items", this.getSelected().getDescuentoTrabajadorList());
        }
        return "/descuentoTrabajador/index";
    }

    @Override
    public TipoDescuentoTrabajador prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setTipoDescuentoTrabajadorIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

}
