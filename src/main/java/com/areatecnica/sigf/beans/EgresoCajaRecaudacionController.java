package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.controllers.EgresoCajaRecaudacionFacade;
import com.areatecnica.sigf.dao.IEgresoDao;
import com.areatecnica.sigf.dao.IEgresoCajaRecaudacionDao;
import com.areatecnica.sigf.dao.impl.IEgresoDaoImpl;
import com.areatecnica.sigf.dao.impl.IEgresoCajaRecaudacionDaoImpl;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.EgresoCajaRecaudacion;
import com.areatecnica.sigf.models.EgresoCajaRecaudacionDataModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.TransferEvent;

@Named(value = "egresoCajaRecaudacionController")
@ViewScoped
public class EgresoCajaRecaudacionController extends AbstractController<EgresoCajaRecaudacion> {

    @Inject
    private EgresoCajaRecaudacionFacade ejbFacade;
    @Inject
    private EgresoController egresoFlotaIdEgresoController;
    @Inject
    private CajaRecaudacionController egresoCajaRecaudacionIdCajaController;

    private List<EgresoCajaRecaudacion> selectedItems;
    private CajaRecaudacion cajaRecaudacion;
    private IEgresoDao egresoDao;
    private IEgresoCajaRecaudacionDao egresoCajaRecaudacionDao;
    private EgresoCajaRecaudacionDataModel model;

    
    public EgresoCajaRecaudacionController() {
        // Inform the Abstract parent controller of the concrete EgresoCajaRecaudacion Entity
        super(EgresoCajaRecaudacion.class);
    }
    
    /**
     * Initialize the concrete EgresoCajaRecaudacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    /**
     * @return the model
     */
    public EgresoCajaRecaudacionDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(EgresoCajaRecaudacionDataModel model) {
        this.model = model;
    }

    /**
     * @return the Caja Recaudación
     */
    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    /**
     * @param Caja Recaudacion the Caja Recaudación to set
     */
    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    

    /**
     * @return the selectedItems
     */
    public List<EgresoCajaRecaudacion> getSelectedItems() {
        return selectedItems;
    }

    /**
     * @param selectedItems the selectedItems to set
     */
    public void setSelectedItems(List<EgresoCajaRecaudacion> selectedItems) {
        this.selectedItems = selectedItems;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        egresoFlotaIdEgresoController.setSelected(null);
        egresoCajaRecaudacionIdCajaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Egreso controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoCajaRecaudacionIdEgreso(ActionEvent event) {
        if (this.getSelected() != null && egresoFlotaIdEgresoController.getSelected() == null) {
            egresoFlotaIdEgresoController.setSelected(this.getSelected().getEgresoCajaRecaudacionIdEgreso());
        }
    }

    public void find(ActionEvent event) {
        if (this.cajaRecaudacion != null) {
            this.egresoDao = new IEgresoDaoImpl();
            this.egresoCajaRecaudacionDao = new IEgresoCajaRecaudacionDaoImpl();
            this.egresoFlotaIdEgresoController.setItems(this.egresoDao.findAllByCuenta(this.getUserCount()));
            this.selectedItems = this.egresoCajaRecaudacionDao.findAllByCajaRecaudacion(cajaRecaudacion);
            this.setModel(new EgresoCajaRecaudacionDataModel(selectedItems));
        } else {
            JsfUtil.addExclamationMessage("Debe seleccionar la Caja Recaudación");
        }
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((EgresoCajaRecaudacion) item).getEgresoCajaRecaudacionIdEgreso().getEgresoNombre()).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Se ha transferido un Egreso");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowReorder(ReorderEvent event) {
        List<EgresoCajaRecaudacion> listAux = this.selectedItems;

        int fromIndex = event.getFromIndex();
        int toIndex = event.getToIndex();

        if (toIndex >= fromIndex) {
            Collections.rotate(listAux.subList(fromIndex, toIndex + 1), -1);
        } else {
            Collections.rotate(listAux.subList(toIndex, fromIndex + 1), 1);
        }
        this.selectedItems = new ArrayList<>(listAux);
    }

    @Override
    public void save(ActionEvent event) {
        int i = 0;
        for (EgresoCajaRecaudacion e : this.selectedItems) {
            e.setEgresoCajaRecaudacionNumeroOrden(i);
            this.ejbFacade.edit(e);
            i++;
        }
        JsfUtil.addSuccessMessage("Se han actualizado los egresos asociados a la Caja Recaudación");
        this.selectedItems = null;
        this.selectedItems = new ArrayList<>();
        this.model = null;
        this.model = new EgresoCajaRecaudacionDataModel(this.selectedItems);
    }

    @Override
    public EgresoCajaRecaudacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        List<Egreso> auxList = new ArrayList<>();

        for (EgresoCajaRecaudacion f : this.selectedItems) {
            auxList.add(f.getEgresoCajaRecaudacionIdEgreso());
        }

        this.egresoFlotaIdEgresoController.getItems().removeAll(auxList);

        this.getSelected().setEgresoCajaRecaudacionPorcentaje(BigDecimal.ZERO);
        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {
        this.getSelected().setEgresoCajaRecaudacionNumeroOrden(this.getSelectedItems().size() + 1);
        this.getSelected().setEgresoCajaRecaudacionActivo(Boolean.TRUE);
        
        this.getSelected().setEgresoCajaRecaudacionIdCaja(this.cajaRecaudacion);
        this.selectedItems.add(this.getSelected());

    }

    public void selectEgresoCajaRecaudacion() {

    }

    @Override
    public void delete(ActionEvent event) {
        this.selectedItems.remove(this.getSelected());
        super.delete(event); //To change body of generated methods, choose Tools | Templates.        
    }

}
