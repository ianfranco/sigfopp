package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.Banco;
import com.areatecnica.sigf.controllers.BancoFacade;
import java.util.Date;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "bancoController")
@ViewScoped
public class BancoController extends AbstractController<Banco> {

    @Inject
    private BancoFacade ejbFacade;

    /**
     * Initialize the concrete Banco controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public BancoController() {
        // Inform the Abstract parent controller of the concrete Banco Entity
        super(Banco.class);
    }

    /**
     * Sets the "items" attribute with a collection of CuentaBancaria entities
     * that are retrieved from Banco?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CuentaBancaria page
     */
    public String navigateCuentaBancariaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentaBancaria_items", this.getSelected().getCuentaBancariaList());
        }
        return "/cuentaBancaria/index";
    }

    
    public void resetParents(){
        
    }

    @Override
    public Banco prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setBancoIdCuenta(this.getUserCount());
        return this.getSelected();
    }
    
    
}
