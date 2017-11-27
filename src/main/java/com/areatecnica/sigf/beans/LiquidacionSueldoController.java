package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.beans.TerminalController;
import com.areatecnica.sigf.entities.LiquidacionSueldo;
import com.areatecnica.sigf.controllers.LiquidacionSueldoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "liquidacionSueldoController")
@ViewScoped
public class LiquidacionSueldoController extends AbstractController<LiquidacionSueldo> {

    @Inject
    private LiquidacionSueldoFacade ejbFacade;
    @Inject
    private EmpresaController liquidacionSueldoIdEmpresaController;
    @Inject
    private TerminalController liquidacionSueldoIdTerminalController;
    @Inject
    private TipoContratoController liquidacionSueldoIdTipoContratoController;
    @Inject
    private TrabajadorController liquidacionSueldoIdTrabajadorController;

    /**
     * Initialize the concrete LiquidacionSueldo controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public LiquidacionSueldoController() {
        // Inform the Abstract parent controller of the concrete LiquidacionSueldo Entity
        super(LiquidacionSueldo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        liquidacionSueldoIdEmpresaController.setSelected(null);
        liquidacionSueldoIdTerminalController.setSelected(null);
        liquidacionSueldoIdTipoContratoController.setSelected(null);
        liquidacionSueldoIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLiquidacionSueldoIdEmpresa(ActionEvent event) {
        if (this.getSelected() != null && liquidacionSueldoIdEmpresaController.getSelected() == null) {
            liquidacionSueldoIdEmpresaController.setSelected(this.getSelected().getLiquidacionSueldoIdEmpresa());
        }
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLiquidacionSueldoIdTerminal(ActionEvent event) {
        if (this.getSelected() != null && liquidacionSueldoIdTerminalController.getSelected() == null) {
            liquidacionSueldoIdTerminalController.setSelected(this.getSelected().getLiquidacionSueldoIdTerminal());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoContrato controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLiquidacionSueldoIdTipoContrato(ActionEvent event) {
        if (this.getSelected() != null && liquidacionSueldoIdTipoContratoController.getSelected() == null) {
            liquidacionSueldoIdTipoContratoController.setSelected(this.getSelected().getLiquidacionSueldoIdTipoContrato());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLiquidacionSueldoIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && liquidacionSueldoIdTrabajadorController.getSelected() == null) {
            liquidacionSueldoIdTrabajadorController.setSelected(this.getSelected().getLiquidacionSueldoIdTrabajador());
        }
    }

    /**
     * Sets the "items" attribute with a collection of HaberLiquidacion entities
     * that are retrieved from LiquidacionSueldo?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for HaberLiquidacion page
     */
    public String navigateHaberLiquidacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HaberLiquidacion_items", this.getSelected().getHaberLiquidacionList());
        }
        return "/haberLiquidacion/index";
    }

    /**
     * Sets the "items" attribute with a collection of DescuentoLiquidacion
     * entities that are retrieved from LiquidacionSueldo?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for DescuentoLiquidacion page
     */
    public String navigateDescuentoLiquidacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DescuentoLiquidacion_items", this.getSelected().getDescuentoLiquidacionList());
        }
        return "/descuentoLiquidacion/index";
    }

}
