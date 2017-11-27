package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.entities.EgresoProcesoRecaudacion;
import com.areatecnica.sigf.controllers.EgresoProcesoRecaudacionFacade;
import com.areatecnica.sigf.dao.IEgresoDao;
import com.areatecnica.sigf.dao.IEgresoProcesoRecaudacionDao;
import com.areatecnica.sigf.dao.impl.IEgresoDaoImpl;
import com.areatecnica.sigf.dao.impl.IEgresoProcesoRecaudacionDaoImpl;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.models.EgresoProcesoRecaudacionDataModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.event.ReorderEvent;

@Named(value = "egresoProcesoRecaudacionController")
@ViewScoped
public class EgresoProcesoRecaudacionController extends AbstractController<EgresoProcesoRecaudacion> {

    @Inject
    private EgresoProcesoRecaudacionFacade ejbFacade;
    @Inject
    private EgresoController egresoFlotaIdEgresoController;
    @Inject
    private FlotaController egresoFlotaIdFlotaController;

    private List<EgresoProcesoRecaudacion> selectedItems;
    private ProcesoRecaudacion procesoRecaudacion;
    private IEgresoDao egresoDao;
    private IEgresoProcesoRecaudacionDao egresoFlotaDao;
    private EgresoProcesoRecaudacionDataModel model;

    public EgresoProcesoRecaudacionController() {
        // Inform the Abstract parent controller of the concrete EgresoProcesoRecaudacion Entity
        super(EgresoProcesoRecaudacion.class);
    }

    /**
     * Initialize the concrete EgresoProcesoRecaudacion controller bean. The
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
    public EgresoProcesoRecaudacionDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(EgresoProcesoRecaudacionDataModel model) {
        this.model = model;
    }

    /**
     * @return the procesoRecaudacion
     */
    public ProcesoRecaudacion getProcesoRecaudacion() {
        return procesoRecaudacion;
    }

    /**
     * @param procesoRecaudacion the procesoRecaudacion to set
     */
    public void setProcesoRecaudacion(ProcesoRecaudacion procesoRecaudacion) {
        this.procesoRecaudacion = procesoRecaudacion;
    }

    /**
     * @return the selectedItems
     */
    public List<EgresoProcesoRecaudacion> getSelectedItems() {
        return selectedItems;
    }

    /**
     * @param selectedItems the selectedItems to set
     */
    public void setSelectedItems(List<EgresoProcesoRecaudacion> selectedItems) {
        this.selectedItems = selectedItems;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        egresoFlotaIdEgresoController.setSelected(null);
        egresoFlotaIdFlotaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Egreso controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoProcesoRecaudacionIdEgreso(ActionEvent event) {
        if (this.getSelected() != null && egresoFlotaIdEgresoController.getSelected() == null) {
            egresoFlotaIdEgresoController.setSelected(this.getSelected().getEgresoProcesoRecaudacionIdEgreso());
        }
    }

    public void find(ActionEvent event) {
        if (this.procesoRecaudacion != null) {
            this.egresoDao = new IEgresoDaoImpl();
            this.egresoFlotaDao = new IEgresoProcesoRecaudacionDaoImpl();
            this.egresoFlotaIdEgresoController.setItems(this.egresoDao.findAllByCuenta(this.getUserCount()));
            this.selectedItems = this.egresoFlotaDao.findAllByProceso(procesoRecaudacion);
            this.setModel(new EgresoProcesoRecaudacionDataModel(selectedItems));
        } else {
            JsfUtil.addExclamationMessage("Debe seleccionar el procesoRecaudacion");
        }
    }

    public void onRowReorder(ReorderEvent event) {
        List<EgresoProcesoRecaudacion> listAux = this.selectedItems;

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
        for (EgresoProcesoRecaudacion e : this.selectedItems) {
            e.setEgresoProcesoRecaudacionNumeroOrden(i);
            this.ejbFacade.edit(e);
            i++;
        }
        JsfUtil.addSuccessMessage("Se han actualizado los egresos asociados al procesoRecaudacion");
        this.selectedItems = null;
        this.selectedItems = new ArrayList<>();
        this.model = null;
        this.model = new EgresoProcesoRecaudacionDataModel(this.selectedItems);
    }

    @Override
    public EgresoProcesoRecaudacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        List<Egreso> auxList = new ArrayList<>();

        for (EgresoProcesoRecaudacion f : this.selectedItems) {
            auxList.add(f.getEgresoProcesoRecaudacionIdEgreso());
        }

        this.egresoFlotaIdEgresoController.getItems().removeAll(auxList);

        this.getSelected().setEgresoProcesoRecaudacionPorcentaje(BigDecimal.ZERO);
        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {
        this.getSelected().setEgresoProcesoRecaudacionNumeroOrden(this.getSelectedItems().size() + 1);
        this.getSelected().setEgresoProcesoRecaudacionActivo(Boolean.TRUE);
        
        this.getSelected().setEgresoProcesoRecaudacionIdProceso(this.procesoRecaudacion);

        this.selectedItems.add(this.getSelected());

    }

    public void selectEgresoProcesoRecaudacion() {

    }

    @Override
    public void delete(ActionEvent event) {
        this.selectedItems.remove(this.getSelected());
        super.delete(event); //To change body of generated methods, choose Tools | Templates.        
    }

}
