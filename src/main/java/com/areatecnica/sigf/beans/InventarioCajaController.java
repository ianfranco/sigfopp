package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.InventarioCaja;
import com.areatecnica.sigf.controllers.InventarioCajaFacade;
import com.areatecnica.sigf.dao.IInventarioCajaDao;
import com.areatecnica.sigf.dao.impl.IInventarioCajaDaoImpl;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "inventarioCajaController")
@ViewScoped
public class InventarioCajaController extends AbstractController<InventarioCaja> {

    @Inject
    private InventarioCajaFacade ejbFacade;
    @Inject
    private InventarioInternoController inventarioCajaIdInventarioInternoController;
    @Inject
    private CajaRecaudacionController inventarioCajaIdCajaController;
    
    private List<InventarioCaja> items;
    private IInventarioCajaDao inventarioCajaDao;

    /**
     * Initialize the concrete InventarioCaja controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.inventarioCajaDao = new IInventarioCajaDaoImpl();
        this.setItems((List<InventarioCaja>) this.inventarioCajaDao.findByEstado(Boolean.FALSE, this.getUserCount()));
    }

    /**
     * @return the items
     */
    @Override
    public List<InventarioCaja> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<InventarioCaja> items) {
        this.items = items;
    }

    public InventarioCajaController() {
        // Inform the Abstract parent controller of the concrete InventarioCaja Entity
        super(InventarioCaja.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        inventarioCajaIdInventarioInternoController.setSelected(null);
        inventarioCajaIdCajaController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of VentaBoleto entities that
     * are retrieved from InventarioCaja?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for VentaBoleto page
     */
    public String navigateVentaBoletoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("VentaBoleto_items", this.getSelected().getVentaBoletoList());
        }
        return "/ventaBoleto/index";
    }

    /**
     * Sets the "selected" attribute of the InventarioInterno controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareInventarioCajaIdInventarioInterno(ActionEvent event) {
        if (this.getSelected() != null && inventarioCajaIdInventarioInternoController.getSelected() == null) {
            inventarioCajaIdInventarioInternoController.setSelected(this.getSelected().getInventarioCajaIdInventarioInterno());
        }
    }

    /**
     * Sets the "selected" attribute of the CajaTerminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareInventarioCajaIdCaja(ActionEvent event) {
        if (this.getSelected() != null && inventarioCajaIdCajaController.getSelected() == null) {
            inventarioCajaIdCajaController.setSelected(this.getSelected().getInventarioCajaIdCaja());
        }
    }
}
