package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.TipoContrato;
import com.areatecnica.sigf.controllers.TipoContratoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoContratoController")
@ViewScoped
public class TipoContratoController extends AbstractController<TipoContrato> {

    @Inject
    private TipoContratoFacade ejbFacade;

    /**
     * Initialize the concrete TipoContrato controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoContratoController() {
        // Inform the Abstract parent controller of the concrete TipoContrato Entity
        super(TipoContrato.class);
    }

    /**
     * Sets the "items" attribute with a collection of LiquidacionSueldo
     * entities that are retrieved from TipoContrato?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for LiquidacionSueldo page
     */
    public String navigateLiquidacionSueldoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LiquidacionSueldo_items", this.getSelected().getLiquidacionSueldoList());
        }
        return "/liquidacionSueldo/index";
    }

    /**
     * Sets the "items" attribute with a collection of RelacionLaboral entities
     * that are retrieved from TipoContrato?cap_first and returns the navigation
     * outcome.
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
    public TipoContrato prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setTipoContratoIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

}
