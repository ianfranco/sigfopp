package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.controllers.CajaProcesoFacade;
import com.areatecnica.sigf.dao.ICajaProcesoDao;
import com.areatecnica.sigf.dao.impl.ICajaProcesoDaoImpl;
import com.areatecnica.sigf.entities.CajaProceso;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.models.ProcesoCajaRecaudacionDataModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@Named(value = "cajaProcesoController")
@ViewScoped
public class CajaProcesoController extends AbstractController<CajaProceso> {

    @Inject
    private CajaProcesoFacade ejbFacade;
    @Inject
    private CajaRecaudacionController cajaRecaudacionController;
    @Inject
    private ProcesoRecaudacionController procesoRecaudacionController;

    private CajaRecaudacion cajaRecaudacion;
    private ICajaProcesoDao cajaProcesoDao;
    private List<CajaRecaudacion> selectedItems;
    private ProcesoCajaRecaudacionDataModel model;

    /**
     * Initialize the concrete CajaProceso controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setItems(null);
    }

    public CajaProcesoController() {
        // Inform the Abstract parent controller of the concrete CajaProceso Entity
        super(CajaProceso.class);
    }

    /**
     * @return the selectedItems
     */
    public List<CajaRecaudacion> getSelectedItems() {
        return selectedItems;
    }

    /**
     * @param selectedItems the selectedItems to set
     */
    public void setSelectedItems(List<CajaRecaudacion> selectedItems) {
        this.selectedItems = selectedItems;
    }

    /**
     * @return the model
     */
    public ProcesoCajaRecaudacionDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(ProcesoCajaRecaudacionDataModel model) {
        this.model = model;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cajaRecaudacionController.setSelected(null);
        procesoRecaudacionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CajaRecaudacion controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCajaProcesoIdCaja(ActionEvent event) {
        if (this.getSelected() != null && cajaRecaudacionController.getSelected() == null) {
            cajaRecaudacionController.setSelected(this.getSelected().getCajaProcesoIdCaja());
        }
    }

    /**
     * Sets the "selected" attribute of the ProcesoRecaudacion controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCajaProcesoIdProceso(ActionEvent event) {
        if (this.getSelected() != null && procesoRecaudacionController.getSelected() == null) {
            procesoRecaudacionController.setSelected(this.getSelected().getCajaProcesoIdProceso());
        }
    }

    /**
     * @return the cajaRecaudacion
     */
    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    /**
     * @param cajaRecaudacion the cajaRecaudacion to set
     */
    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    public void find(ActionEvent event) {
        if (this.cajaRecaudacion != null) {
            this.cajaProcesoDao = new ICajaProcesoDaoImpl();

            this.setItems(new ArrayList<CajaProceso>());
            //BUSCAR LOS PROCESOS QUE ESTÉN ASOCIADOS A LA CUENTA 
            for (ProcesoRecaudacion proceso : this.procesoRecaudacionController.getItems()) {
                try {
                    this.getItems().add(this.cajaProcesoDao.findByCajaProceso(cajaRecaudacion, proceso));
                } catch (NoResultException e) {
                    CajaProceso cajaAux = new CajaProceso();
                    
                    cajaAux.setCajaProcesoIdProceso(proceso);
                    cajaAux.setCajaProcesoActivo(Boolean.FALSE);
                    this.getItems().add(cajaAux);
                }
            }
            this.model = (new ProcesoCajaRecaudacionDataModel((List<CajaProceso>) this.getItems()));
        } else {
            JsfUtil.addExclamationMessage("Debe seleccionar la Caja Recaudación");
        }
    }

    public void selectCajasProceso() {

    }

    @Override
    public void save(ActionEvent event) {
        for (CajaProceso cp : this.getItems()) {
            if (cp.getCajaProcesoId() == null) {
                if (cp.getCajaProcesoActivo()) {
                    this.setSelected(cp);
                    cp.setCajaProcesoIdCaja(cajaRecaudacion);
                    this.ejbFacade.create(cp);
                }else{
                    JsfUtil.addExclamationMessage("NO ESTA ACTIVO EL PROCESO: "+cp.getCajaProcesoIdProceso().getProcesoRecaudacionNombre());
                }
            } else {
                JsfUtil.addExclamationMessage("ACTUALIZANDO EL PROCESO: "+cp.getCajaProcesoIdProceso().getProcesoRecaudacionNombre());
                
                this.ejbFacade.edit(cp);
            }
        }
        this.setItems(new ArrayList<CajaProceso>());
        this.model = new ProcesoCajaRecaudacionDataModel((List<CajaProceso>) this.getItems());
    }

}
