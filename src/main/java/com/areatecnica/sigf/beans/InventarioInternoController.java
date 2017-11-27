package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.InventarioInterno;
import com.areatecnica.sigf.controllers.InventarioInternoFacade;
import com.areatecnica.sigf.dao.IInventarioInternoDao;
import com.areatecnica.sigf.dao.impl.IInventarioInternoDaoImpl;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "inventarioInternoController")
@ViewScoped
public class InventarioInternoController extends AbstractController<InventarioInterno> {

    @Inject
    private InventarioInternoFacade ejbFacade;
    @Inject
    private BoletoController inventarioInternoIdBoletoController;

    private List<InventarioInterno> items;
    
    private IInventarioInternoDao inventarioInternoDao;
    
    /**
     * Initialize the concrete InventarioInterno controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.inventarioInternoDao = new IInventarioInternoDaoImpl();
        this.items = this.inventarioInternoDao.findByEstado(Boolean.FALSE, this.getUserCount());
    }

    public InventarioInternoController() {
        // Inform the Abstract parent controller of the concrete InventarioInterno Entity
        super(InventarioInterno.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        inventarioInternoIdBoletoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Boleto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareInventarioInternoIdBoleto(ActionEvent event) {
        if (this.getSelected() != null && inventarioInternoIdBoletoController.getSelected() == null) {
            inventarioInternoIdBoletoController.setSelected(this.getSelected().getInventarioInternoIdBoleto());
        }
    }

    /**
     * Sets the "items" attribute with a collection of InventarioCaja entities
     * that are retrieved from InventarioInterno?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for InventarioCaja page
     */
    public String navigateInventarioCajaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InventarioCaja_items", this.getSelected().getInventarioCajaList());
        }
        return "/inventarioCaja/index";
    }

    @Override
    public List<InventarioInterno> getItems() {
        return items; //To change body of generated methods, choose Tools | Templates.
    }

}
