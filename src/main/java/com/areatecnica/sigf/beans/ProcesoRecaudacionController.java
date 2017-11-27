package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.controllers.ProcesoRecaudacionFacade;
import com.areatecnica.sigf.dao.IProcesoRecaudacionDao;
import com.areatecnica.sigf.dao.impl.IProcesoRecaudacionDaoImpl;
import com.areatecnica.sigf.models.ProcesoRecaudacionDataModel;
import java.util.Collection;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "procesoRecaudacionController")
@ViewScoped
public class ProcesoRecaudacionController extends AbstractController<ProcesoRecaudacion> {

    @Inject
    private ProcesoRecaudacionFacade ejbFacade;
    @Inject
    private CajaRecaudacionController procesoRecaudacionIdCajaTerminalController;
    @Inject
    private CuentaBancariaController procesoRecaudacionIdCuentaBancariaController;

    private List<ProcesoRecaudacion> items;

    private IProcesoRecaudacionDao dao;
    private ProcesoRecaudacionDataModel model;

    /**
     * Initialize the concrete ProcesoRecaudacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.dao = new IProcesoRecaudacionDaoImpl();
        this.items = this.dao.findAllByCuenta(this.getUserCount());
        this.setModel(new ProcesoRecaudacionDataModel(items));
    }

    /**
     * @return the model
     */
    public ProcesoRecaudacionDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(ProcesoRecaudacionDataModel model) {
        this.model = model;
    }

    public ProcesoRecaudacionController() {
        // Inform the Abstract parent controller of the concrete ProcesoRecaudacion Entity
        super(ProcesoRecaudacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        procesoRecaudacionIdCajaTerminalController.setSelected(null);
        procesoRecaudacionIdCuentaBancariaController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from ProcesoRecaudacion?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", this.getSelected().getBusList());
        }
        return "/bus/index";
    }

    @Override
    public ProcesoRecaudacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setProcesoRecaudacionIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

    @Override
    public Collection<ProcesoRecaudacion> getItems() {
        if (items == null) {
            this.dao = new IProcesoRecaudacionDaoImpl();
            this.items = this.dao.findAllByCuenta(this.getUserCount());
        }
        return items;
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (this.getSelected() != null) {
            super.saveNew(event); //To change body of generated methods, choose Tools | Templates.     
            this.items.add(this.getSelected());
        }
    }

}
