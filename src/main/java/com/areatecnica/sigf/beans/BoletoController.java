package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.controllers.BoletoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "boletoController")
@ViewScoped
public class BoletoController extends AbstractController<Boleto> {

    @Inject
    private BoletoFacade ejbFacade;

    /**
     * Initialize the concrete Boleto controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public BoletoController() {
        // Inform the Abstract parent controller of the concrete Boleto Entity
        super(Boleto.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Boleto.findAllByCuenta");
    }


    /**
     * Sets the "items" attribute with a collection of InventarioInterno
     * entities that are retrieved from Boleto?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for InventarioInterno page
     */
    public String navigateInventarioInternoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InventarioInterno_items", this.getSelected().getInventarioInternoList());
        }
        return "/inventarioInterno/index";
    }

    /**
     * Sets the "items" attribute with a collection of DetalleCompraBoleto
     * entities that are retrieved from Boleto?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DetalleCompraBoleto page
     */
    public String navigateDetalleCompraBoletoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleCompraBoleto_items", this.getSelected().getDetalleCompraBoletoList());
        }
        return "/detalleCompraBoleto/index";
    }

    /**
     * Sets the "items" attribute with a collection of TarifaServicio entities
     * that are retrieved from Boleto?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for TarifaServicio page
     */
    public String navigateTarifaServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TarifaServicio_items", this.getSelected().getTarifaGrupoServicioList());
        }
        return "/tarifaServicio/index";
    }
    
    
    public void resetParents() {
        
    }

    @Override
    public Boleto prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setBoletoIdCuenta(this.getUserCount());
        
        return getSelected();
    }
    
    

}
