package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.controllers.EgresoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "egresoController")
@ViewScoped
public class EgresoController extends AbstractController<Egreso> {

    @Inject
    private EgresoFacade ejbFacade;

    /**
     * Initialize the concrete Egreso controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public EgresoController() {
        // Inform the Abstract parent controller of the concrete Egreso Entity
        super(Egreso.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Egreso.findAllByCuenta");
    }

    /**
     * Sets the "items" attribute with a collection of EgresoBus entities that
     * are retrieved from Egreso?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for EgresoBus page
     */
    public String navigateEgresoBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoBus_items", this.getSelected().getEgresoBusList());
        }
        return "/egresoBus/index";
    }

    /**
     * Sets the "items" attribute with a collection of EgresoCajaTerminal
     * entities that are retrieved from Egreso?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for EgresoCajaTerminal page
     */
    public String navigateEgresoCajaTerminalList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoCajaTerminal_items", this.getSelected().getEgresoCajaRecaudacionList());
        }
        return "/egresoCajaTerminal/index";
    }

    /**
     * Sets the "items" attribute with a collection of EgresoFlota entities that
     * are retrieved from Egreso?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for EgresoFlota page
     */
    public String navigateEgresoFlotaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoFlota_items", this.getSelected().getEgresoFlotaList());
        }
        return "/egresoFlota/index";
    }

    public void resetParents(){
        
    }

    @Override
    public Egreso prepareCreate(ActionEvent event) {
        
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setEgresoIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }
    
    
    
}
