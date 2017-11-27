package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.controllers.EgresoBusFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.IEgresoDao;
import com.areatecnica.sigf.dao.IEgresoBusDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IEgresoDaoImpl;
import com.areatecnica.sigf.dao.impl.IEgresoBusDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.EgresoBus;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.models.EgresoBusDataModel;
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

@Named(value = "egresoBusController")
@ViewScoped
public class EgresoBusController extends AbstractController<EgresoBus> {

    @Inject
    private EgresoBusFacade ejbFacade;
    @Inject
    private EgresoController egresoFlotaIdEgresoController;
    @Inject
    private BusController busController;

    private List<EgresoBus> selectedItems;
    private Bus bus;
    private UnidadNegocio unidadNegocio;
    private IEgresoDao egresoDao;
    private IEgresoBusDao egresoBusDao;
    private IBusDao busDao;
    private EgresoBusDataModel model;

    
    public EgresoBusController() {
        // Inform the Abstract parent controller of the concrete EgresoBus Entity
        super(EgresoBus.class);
    }
    
    /**
     * Initialize the concrete EgresoBus controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.busController.setItems(new ArrayList<Bus>());
    }

    /**
     * @return the model
     */
    public EgresoBusDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(EgresoBusDataModel model) {
        this.model = model;
    }

    /**
     * @return the Caja Recaudación
     */
    public Bus getBus() {
        return bus;
    }

    /**
     * @param Caja Recaudacion the Caja Recaudación to set
     */
    public void setBus(Bus bus) {
        this.bus = bus;
    }

    /**
     * @return the unidadNegocio
     */
    public UnidadNegocio getUnidadNegocio() {
        return unidadNegocio;
    }

    /**
     * @param unidadNegocio the unidadNegocio to set
     */
    public void setUnidadNegocio(UnidadNegocio unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    /**
     * @return the selectedItems
     */
    public List<EgresoBus> getSelectedItems() {
        return selectedItems;
    }

    /**
     * @param selectedItems the selectedItems to set
     */
    public void setSelectedItems(List<EgresoBus> selectedItems) {
        this.selectedItems = selectedItems;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        egresoFlotaIdEgresoController.setSelected(null);
        busController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Egreso controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoBusIdEgreso(ActionEvent event) {
        if (this.getSelected() != null && egresoFlotaIdEgresoController.getSelected() == null) {
            egresoFlotaIdEgresoController.setSelected(this.getSelected().getEgresoBusIdEgreso());
        }
    }

    public void find(ActionEvent event) {
        if (this.bus != null) {
            this.egresoDao = new IEgresoDaoImpl();
            this.egresoBusDao = new IEgresoBusDaoImpl();
            this.egresoFlotaIdEgresoController.setItems(this.egresoDao.findAllByCuenta(this.getUserCount()));
            this.selectedItems = this.egresoBusDao.findAllByBus(bus);
            this.setModel(new EgresoBusDataModel(selectedItems));
        } else {
            JsfUtil.addExclamationMessage("Debe seleccionar la Caja Recaudación");
        }
    }

    public void onRowReorder(ReorderEvent event) {

        List<EgresoBus> listAux = this.selectedItems;

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

        for (EgresoBus e : this.selectedItems) {
            e.setEgresoBusNumeroOrden(i);
            this.egresoBusDao.update(e);
            i++;
        }

        JsfUtil.addSuccessMessage("Se han actualizado los egresos asociados a la Caja Recaudación");
        this.selectedItems = null;
        this.selectedItems = new ArrayList<>();
        this.model = null;
        this.model = new EgresoBusDataModel(this.selectedItems);
    }

    @Override
    public EgresoBus prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        List<Egreso> auxList = new ArrayList<>();

        for (EgresoBus f : this.selectedItems) {
            auxList.add(f.getEgresoBusIdEgreso());
        }

        this.egresoFlotaIdEgresoController.getItems().removeAll(auxList);

        this.getSelected().setEgresoBusPorcentaje(BigDecimal.ZERO);
        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {
        this.getSelected().setEgresoBusNumeroOrden(this.getSelectedItems().size() + 1);
        this.getSelected().setEgresoBusActivo(Boolean.TRUE);
        
        this.getSelected().setEgresoBusIdBus(this.bus);

        this.selectedItems.add(this.getSelected());

    }

    public void selectEgresoBus() {

    }

    public void findBuses() {
        this.busDao = new IBusDaoImpl();
        this.busController.setItems(this.busDao.findByUnidad(this.getUnidadNegocio()));
    }

    @Override
    public void delete(ActionEvent event) {
        this.selectedItems.remove(this.getSelected());
        super.delete(event); //To change body of generated methods, choose Tools | Templates.        
    }

}
