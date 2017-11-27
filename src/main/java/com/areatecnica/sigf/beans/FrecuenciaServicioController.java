package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.ServicioController;
import com.areatecnica.sigf.entities.FrecuenciaServicio;
import com.areatecnica.sigf.controllers.FrecuenciaServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "frecuenciaServicioController")
@ViewScoped
public class FrecuenciaServicioController extends AbstractController<FrecuenciaServicio> {

    @Inject
    private FrecuenciaServicioFacade ejbFacade;
    @Inject
    private PeriodoFrecuenciaController frecuenciaServicioIdPeriodoController;
    @Inject
    private ServicioController frecuenciaServicioIdServicioController;
    @Inject
    private TipoDemandaController frecuenciaServicioIdTipoDemandaController;
    @Inject
    private TipoDiaFrecuenciaController frecuenciaServicioIdTipoDiaController;

    /**
     * Initialize the concrete FrecuenciaServicio controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public FrecuenciaServicioController() {
        // Inform the Abstract parent controller of the concrete FrecuenciaServicio Entity
        super(FrecuenciaServicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        frecuenciaServicioIdPeriodoController.setSelected(null);
        frecuenciaServicioIdServicioController.setSelected(null);
        frecuenciaServicioIdTipoDemandaController.setSelected(null);
        frecuenciaServicioIdTipoDiaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the PeriodoFrecuencia controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFrecuenciaServicioIdPeriodo(ActionEvent event) {
        if (this.getSelected() != null && frecuenciaServicioIdPeriodoController.getSelected() == null) {
            frecuenciaServicioIdPeriodoController.setSelected(this.getSelected().getFrecuenciaServicioIdPeriodo());
        }
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFrecuenciaServicioIdServicio(ActionEvent event) {
        if (this.getSelected() != null && frecuenciaServicioIdServicioController.getSelected() == null) {
            frecuenciaServicioIdServicioController.setSelected(this.getSelected().getFrecuenciaServicioIdServicio());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoDemanda controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFrecuenciaServicioIdTipoDemanda(ActionEvent event) {
        if (this.getSelected() != null && frecuenciaServicioIdTipoDemandaController.getSelected() == null) {
            frecuenciaServicioIdTipoDemandaController.setSelected(this.getSelected().getFrecuenciaServicioIdTipoDemanda());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoDiaFrecuencia controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFrecuenciaServicioIdTipoDia(ActionEvent event) {
        if (this.getSelected() != null && frecuenciaServicioIdTipoDiaController.getSelected() == null) {
            frecuenciaServicioIdTipoDiaController.setSelected(this.getSelected().getFrecuenciaServicioIdTipoDia());
        }
    }
}
