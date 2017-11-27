package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.TipoCombustible;
import com.areatecnica.sigf.controllers.TipoCombustibleFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoCombustibleController")
@ViewScoped
public class TipoCombustibleController extends AbstractController<TipoCombustible> {

    @Inject
    private TipoCombustibleFacade ejbFacade;

    /**
     * Initialize the concrete TipoCombustible controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoCombustibleController() {
        // Inform the Abstract parent controller of the concrete TipoCombustible Entity
        super(TipoCombustible.class);
    }

    /**
     * Sets the "items" attribute with a collection of PrecioCombustible
     * entities that are retrieved from TipoCombustible?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for PrecioCombustible page
     */
    public String navigatePrecioCombustibleList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PrecioCombustible_items", this.getSelected().getPrecioCombustibleList());
        }
        return "/precioCombustible/index";
    }

    /**
     * Sets the "items" attribute with a collection of CompraCombustible
     * entities that are retrieved from TipoCombustible?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for CompraCombustible page
     */
    public String navigateCompraCombustibleList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CompraCombustible_items", this.getSelected().getCompraCombustibleList());
        }
        return "/compraCombustible/index";
    }

    @Override
    public TipoCombustible prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        
        return this.getSelected();
    }

}
