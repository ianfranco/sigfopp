package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.entities.EgresoFlota;
import com.areatecnica.sigf.controllers.EgresoFlotaFacade;
import com.areatecnica.sigf.dao.IEgresoDao;
import com.areatecnica.sigf.dao.IEgresoFlotaDao;
import com.areatecnica.sigf.dao.impl.IEgresoDaoImpl;
import com.areatecnica.sigf.dao.impl.IEgresoFlotaDaoImpl;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.models.EgresoFlotaDataModel;
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

@Named(value = "egresoFlotaController")
@ViewScoped
public class EgresoFlotaController extends AbstractController<EgresoFlota> {

    @Inject
    private EgresoFlotaFacade ejbFacade;
    @Inject
    private EgresoController egresoFlotaIdEgresoController;
    @Inject
    private FlotaController egresoFlotaIdFlotaController;

    private List<EgresoFlota> selectedItems;
    private Flota flota;
    private IEgresoDao egresoDao;
    private IEgresoFlotaDao egresoFlotaDao;
    private EgresoFlotaDataModel model;

    public EgresoFlotaController() {
        // Inform the Abstract parent controller of the concrete EgresoFlota Entity
        super(EgresoFlota.class);
    }
    
    /**
     * Initialize the concrete EgresoFlota controller bean. The
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
    public EgresoFlotaDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(EgresoFlotaDataModel model) {
        this.model = model;
    }

    /**
     * @return the flota
     */
    public Flota getFlota() {
        return flota;
    }

    /**
     * @param flota the flota to set
     */
    public void setFlota(Flota flota) {
        this.flota = flota;
    }

    

    /**
     * @return the selectedItems
     */
    public List<EgresoFlota> getSelectedItems() {
        return selectedItems;
    }

    /**
     * @param selectedItems the selectedItems to set
     */
    public void setSelectedItems(List<EgresoFlota> selectedItems) {
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
    public void prepareEgresoFlotaIdEgreso(ActionEvent event) {
        if (this.getSelected() != null && egresoFlotaIdEgresoController.getSelected() == null) {
            egresoFlotaIdEgresoController.setSelected(this.getSelected().getEgresoFlotaIdEgreso());
        }
    }

    /**
     * Sets the "selected" attribute of the Flota controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoFlotaIdFlota(ActionEvent event) {
        if (this.getSelected() != null && egresoFlotaIdFlotaController.getSelected() == null) {
            egresoFlotaIdFlotaController.setSelected(this.getSelected().getEgresoFlotaIdFlota());
        }
    }

    public void find(ActionEvent event) {
        if (this.flota != null) {
            this.egresoDao = new IEgresoDaoImpl();
            this.egresoFlotaDao = new IEgresoFlotaDaoImpl();
            this.egresoFlotaIdEgresoController.setItems(this.egresoDao.findAllByCuenta(this.getUserCount()));
            this.selectedItems = this.egresoFlotaDao.findAllByFlota(flota);
            this.setModel(new EgresoFlotaDataModel(selectedItems));
        } else {
            JsfUtil.addExclamationMessage("Debe seleccionar la flota");
        }
    }

    public void onRowReorder(ReorderEvent event) {        
        List<EgresoFlota> listAux = this.selectedItems;

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
        for (EgresoFlota e : this.selectedItems) {
            e.setEgresoFlotaNumeroOrden(i);
            this.ejbFacade.edit(e);
            i++;
        }
        JsfUtil.addSuccessMessage("Se han actualizado los egresos asociados a la flota");
        this.selectedItems = null;
        this.selectedItems = new ArrayList<>();
        this.model = null;
        this.model = new EgresoFlotaDataModel(this.selectedItems);
    }

    @Override
    public EgresoFlota prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        List<Egreso> auxList = new ArrayList<>();

        for (EgresoFlota f : this.selectedItems) {
            auxList.add(f.getEgresoFlotaIdEgreso());
        }

        this.egresoFlotaIdEgresoController.getItems().removeAll(auxList);

        this.getSelected().setEgresoFlotaPorcentaje(BigDecimal.ZERO);
        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {
        this.getSelected().setEgresoFlotaNumeroOrden(this.getSelectedItems().size() + 1);
        this.getSelected().setEgresoFlotaActivo(Boolean.TRUE);
        
        this.getSelected().setEgresoFlotaIdFlota(this.flota);

        this.selectedItems.add(this.getSelected());

    }

    public void selectEgresoFlota() {

    }

    @Override
    public void delete(ActionEvent event) {
        this.selectedItems.remove(this.getSelected());
        super.delete(event); //To change body of generated methods, choose Tools | Templates.        
    }

}
