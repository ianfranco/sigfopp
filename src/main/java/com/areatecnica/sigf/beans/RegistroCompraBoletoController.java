package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.entities.CompraBoleto;
import com.areatecnica.sigf.controllers.CompraBoletoFacade;
import com.areatecnica.sigf.controllers.InventarioInternoFacade;
import com.areatecnica.sigf.dao.ICompraBoletoDao;
import com.areatecnica.sigf.dao.impl.ICompraBoletoDaoImpl;
import com.areatecnica.sigf.entities.DetalleCompraBoleto;
import com.areatecnica.sigf.entities.InventarioInterno;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Named(value = "registroCompraBoletoController")
@ViewScoped
public class RegistroCompraBoletoController extends AbstractController<CompraBoleto> {

    @Inject
    private CompraBoletoFacade ejbFacade;
    @Inject
    private InventarioInternoFacade inventarioInternoFacade;

    private DetalleCompraBoleto detalleCompraBoleto;
    private List<DetalleCompraBoleto> itemsDetalleCompraBoleto;
    private ICompraBoletoDao compraBoletoDao;

    private enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }

    /**
     * Initialize the concrete CompraBoleto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        if (this.getSelected() == null) {
            this.setSelected(new CompraBoleto());
            this.getSelected().setCompraBoletoFecha(new Date());
        }

        if (this.detalleCompraBoleto == null) {
            this.detalleCompraBoleto = new DetalleCompraBoleto();
        }
    }

    public RegistroCompraBoletoController() {
        // Inform the Abstract parent controller of the concrete CompraBoleto Entity
        super(CompraBoleto.class);
        this.setSelected(super.prepareCreate(null));
        this.getSelected().setCompraBoletoIdCuenta(this.getUserCount());
        super.setFacade(ejbFacade);

        this.detalleCompraBoleto = new DetalleCompraBoleto();
        this.detalleCompraBoleto.setDetalleCompraBoletoIdCompraBoleto(this.getSelected());
        this.getSelected().setCompraBoletoFecha(new Date());
        this.getSelected().setCompraBoletoTotal(0);
        this.getSelected().setDetalleCompraBoletoList(new ArrayList<DetalleCompraBoleto>());
    }

    /**
     * @return the detalleCompraBoleto
     */
    public DetalleCompraBoleto getDetalleCompraBoleto() {
        return detalleCompraBoleto;
    }

    /**
     * @param detalleCompraBoleto the detalleCompraBoleto to set
     */
    public void setDetalleCompraBoleto(DetalleCompraBoleto detalleCompraBoleto) {
        this.detalleCompraBoleto = detalleCompraBoleto;
    }

    /**
     * @return the itemsDetalleCompraBoleto
     */
    public List<DetalleCompraBoleto> getItemsDetalleCompraBoleto() {
        return itemsDetalleCompraBoleto;
    }

    /**
     * @param itemsDetalleCompraBoleto the itemsDetalleCompraBoleto to set
     */
    public void setItemsDetalleCompraBoleto(List<DetalleCompraBoleto> itemsDetalleCompraBoleto) {
        this.itemsDetalleCompraBoleto = itemsDetalleCompraBoleto;
    }

    /**
     * Sets the "items" attribute with a collection of DetalleCompraBoleto
     * entities that are retrieved from CompraBoleto?cap_first and returns the
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

    public void addDetalleCompra(ActionEvent event) {
        int valorRollo = this.detalleCompraBoleto.getDetalleCompraBoletoTotal();
        int cantidad = this.detalleCompraBoleto.getDetalleCompraBoletoCantidadRollos();
        int total = valorRollo * cantidad;
        this.detalleCompraBoleto.setDetalleCompraBoletoTotal(total);
        this.detalleCompraBoleto.setDetalleCompraBoletoIdCompraBoleto(this.getSelected());

        this.getSelected().setCompraBoletoTotal(this.getSelected().getCompraBoletoTotal() + total);

        this.getSelected().getDetalleCompraBoletoList().add(detalleCompraBoleto);

        this.detalleCompraBoleto = null;
        this.detalleCompraBoleto = new DetalleCompraBoleto();
        this.detalleCompraBoleto.setDetalleCompraBoletoIdBoleto(null);
        this.detalleCompraBoleto.setDetalleCompraBoletoSerie("0");
        this.detalleCompraBoleto.setDetalleCompraBoletoTotal(valorRollo);
        this.detalleCompraBoleto.setDetalleCompraBoletoCantidadRollos(cantidad);
    }

    @Override
    public void saveNew(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/Bundle").getString(CompraBoleto.class.getSimpleName() + "Created");
        persist(PersistAction.CREATE, msg);

        this.setSelected(super.prepareCreate(null));
        this.getSelected().setCompraBoletoIdCuenta(this.getUserCount());
        this.detalleCompraBoleto = new DetalleCompraBoleto();
        this.detalleCompraBoleto.setDetalleCompraBoletoIdCompraBoleto(this.getSelected());
        this.getSelected().setCompraBoletoFecha(new Date());
        this.getSelected().setCompraBoletoTotal(0);
        this.getSelected().setDetalleCompraBoletoList(new ArrayList<DetalleCompraBoleto>());

    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (this.getSelected() != null) {
            this.setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    this.ejbFacade.edit(this.getSelected());
                    List<InventarioInterno> inventarioInterno = new ArrayList<>();
                    for (DetalleCompraBoleto d : this.getSelected().getDetalleCompraBoletoList()) {

                        int serieInicial = Integer.parseInt(d.getDetalleCompraBoletoSerie());

                        for (int i = 0; i < d.getDetalleCompraBoletoCantidadRollos(); i++) {

                            InventarioInterno ii = new InventarioInterno();
                            ii.setInventarioInternoEstado(Boolean.FALSE);
                            
                            ii.setInventarioInternoIdBoleto(d.getDetalleCompraBoletoIdBoleto());
                            ii.setInventarioInternoIdentificador(d.getDetalleCompraBoletoIdentificador());
                            ii.setInventarioInternoSerie(serieInicial);
                            serieInicial += 1000;

                            this.inventarioInternoFacade.create(ii);

                            inventarioInterno.add(ii);
                        }

                    }

                    this.getSelected().setDetalleCompraBoletoList(null);
                    this.setSelected(null);

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

    public void findFolio() {
        this.compraBoletoDao = new ICompraBoletoDaoImpl();
        CompraBoleto compraBoleto = this.compraBoletoDao.findByFolioFactura(this.getSelected().getCompraBoletoFolioFactura(), this.getUserCount());
        if (compraBoleto != null) {
            JsfUtil.addErrorMessage("La factura N° " + this.getSelected().getCompraBoletoFolioFactura() + " ya se encuentra ingresada");
        }
    }

}
