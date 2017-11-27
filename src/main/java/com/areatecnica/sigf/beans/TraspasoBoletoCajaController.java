package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.entities.InventarioCaja;
import com.areatecnica.sigf.controllers.InventarioCajaFacade;
import com.areatecnica.sigf.controllers.InventarioInternoFacade;
import com.areatecnica.sigf.dao.IInventarioInternoDao;
import com.areatecnica.sigf.dao.impl.IInventarioInternoDaoImpl;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.InventarioInterno;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Named(value = "traspasoBoletoCajaController")
@ViewScoped
public class TraspasoBoletoCajaController extends AbstractController<InventarioCaja> {

    @Inject
    private InventarioCajaFacade ejbFacade;
    @Inject
    private InventarioInternoFacade inventarioInternoFacade;
    @Inject
    private InventarioInternoController inventarioCajaIdInventarioInternoController;
    @Inject
    private CajaRecaudacionController inventarioCajaIdCajaController;

    private Boleto boletoItem;
    private InventarioInterno inventarioInterno;
    private List<InventarioInterno> inventarioInternoList;
    private List<InventarioCaja> inventarioCajaList;
    private List<InventarioInterno> inventarioCajaSelectedItems;
    private IInventarioInternoDao inventarioInternoDao;

    private enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }

    /**
     * Initialize the concrete InventarioCaja controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.inventarioInternoFacade = new InventarioInternoFacade();
        this.setSelected(new InventarioCaja());        
        this.inventarioInternoList = new ArrayList<>();
        this.inventarioCajaList = new ArrayList<>();
        this.inventarioInternoDao = new IInventarioInternoDaoImpl();
    }

    public TraspasoBoletoCajaController() {
        // Inform the Abstract parent controller of the concrete InventarioCaja Entity
        super(InventarioCaja.class);
    }

    /**
     * @return the inventarioCajaList
     */
    public List<InventarioCaja> getInventarioCajaList() {
        return inventarioCajaList;
    }

    /**
     * @param inventarioCajaList the inventarioCajaList to set
     */
    public void setInventarioCajaList(List<InventarioCaja> inventarioCajaList) {
        this.inventarioCajaList = inventarioCajaList;
    }

    /**
     * @return the inventarioInterno
     */
    public InventarioInterno getInventarioInterno() {
        return inventarioInterno;
    }

    /**
     * @param inventarioInterno the inventarioInterno to set
     */
    public void setInventarioInterno(InventarioInterno inventarioInterno) {
        this.inventarioInterno = inventarioInterno;
    }

    /**
     * @return the inventarioInternoList
     */
    public List<InventarioInterno> getInventarioInternoList() {
        return inventarioInternoList;
    }

    /**
     * @param inventarioInternoList the inventarioInternoList to set
     */
    public void setInventarioInternoList(List<InventarioInterno> inventarioInternoList) {
        this.inventarioInternoList = inventarioInternoList;
    }

    /**
     * @return the boletoItem
     */
    public Boleto getBoletoItem() {
        return boletoItem;
    }

    /**
     * @param boletoItem the boletoItem to set
     */
    public void setBoletoItem(Boleto boletoItem) {
        this.boletoItem = boletoItem;
    }

    /**
     * @return the inventarioCajaSelectedItems
     */
    public List<InventarioInterno> getInventarioCajaSelectedItems() {
        return inventarioCajaSelectedItems;
    }

    /**
     * @param inventarioCajaSelectedItems the inventarioCajaSelectedItems to set
     */
    public void setInventarioCajaSelectedItems(List<InventarioInterno> inventarioCajaSelectedItems) {
        this.inventarioCajaSelectedItems = inventarioCajaSelectedItems;
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

    public void handleBoletoChange(ActionEvent event) {
        if (this.boletoItem != null) {
            this.inventarioInternoList = this.inventarioInternoDao.findByBoletoEstado(this.boletoItem, Boolean.FALSE);
            this.inventarioCajaList.stream().filter((c) -> (this.inventarioInternoList.contains(c.getInventarioCajaIdInventarioInterno()))).forEachOrdered((c) -> {
                this.inventarioInternoList.remove(c.getInventarioCajaIdInventarioInterno());
            });
        }
    }

    public void addInventarioCaja(ActionEvent event) {

        CajaRecaudacion caja = this.getSelected().getInventarioCajaIdCaja();

        this.inventarioCajaSelectedItems.forEach((ic) -> {
            InventarioCaja i = new InventarioCaja();            
            i.setInventarioCajaEstado(Boolean.FALSE);
            i.setInventarioCajaIdCaja(caja);
            i.setInventarioCajaIdInventarioInterno(ic);
            i.setInventarioCajaIdentificador(ic.getInventarioInternoIdentificador());
            i.setInventarioCajaSerie(ic.getInventarioInternoSerie());
            ic.setInventarioInternoEstado(Boolean.TRUE);

            this.inventarioInternoList.remove(ic);

            this.inventarioCajaList.add(i);
        });

        this.setSelected(null);
        this.setSelected(new InventarioCaja());
        //this.getSelected().setInventarioCajaIdCaja(caja);

    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (this.getSelected() != null) {
            this.setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {

                    for (InventarioCaja i : this.inventarioCajaList) {
                        this.ejbFacade.edit(i);
                    }

                    this.inventarioCajaList = null;

                } else {
                    this.ejbFacade.remove(this.getSelected());
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    if (cause instanceof ConstraintViolationException) {
                        ConstraintViolationException excp = (ConstraintViolationException) cause;
                        for (ConstraintViolation s : excp.getConstraintViolations()) {
                            JsfUtil.addErrorMessage(s.getMessage());
                        }
                    } else {
                        String msg = cause.getLocalizedMessage();
                        if (msg.length() > 0) {
                            JsfUtil.addErrorMessage(msg);
                        } else {
                            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    @Override
    public void saveNew(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/Bundle").getString(InventarioCaja.class.getSimpleName() + "Created");
        persist(PersistAction.CREATE, msg);
    }

}
