package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Departamento;
import com.areatecnica.sigf.controllers.DepartamentoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "departamentoController")
@ViewScoped
public class DepartamentoController extends AbstractController<Departamento> {

    @Inject
    private DepartamentoFacade ejbFacade;

    /**
     * Initialize the concrete Departamento controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public DepartamentoController() {
        // Inform the Abstract parent controller of the concrete Departamento Entity
        super(Departamento.class);
    }

    /**
     * Sets the "items" attribute with a collection of
     * GastoAdministracionMensual entities that are retrieved from
     * Departamento?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for GastoAdministracionMensual page
     */
    public String navigateGastoAdministracionMensualList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("GastoAdministracionMensual_items", this.getSelected().getGastoAdministracionMensualList());
        }
        return "/gastoAdministracionMensual/index";
    }

    @Override
    public Departamento prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setDepartamentoIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

}
