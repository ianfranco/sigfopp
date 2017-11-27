package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.controllers.EmpresaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "empresaController")
@ViewScoped
public class EmpresaController extends AbstractController<Empresa> {

    @Inject
    private EmpresaFacade ejbFacade;
    @Inject
    private CajaCompensacionController empresaIdCajaCompensacionController;
    @Inject
    private CuentaBancariaController empresaIdCuentaBancariaController;
    @Inject
    private MutualController empresaIdMutualController;

    /**
     * Initialize the concrete Empresa controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public EmpresaController() {
        // Inform the Abstract parent controller of the concrete Empresa Entity
        super(Empresa.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Empresa.findAllByCuenta");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empresaIdCajaCompensacionController.setSelected(null);
        empresaIdCuentaBancariaController.setSelected(null);
        empresaIdMutualController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of LiquidacionEmpresa
     * entities that are retrieved from Empresa?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for LiquidacionEmpresa page
     */
    public String navigateLiquidacionEmpresaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LiquidacionEmpresa_items", this.getSelected().getLiquidacionEmpresaList());
        }
        return "/liquidacionEmpresa/index";
    }

    /**
     * Sets the "items" attribute with a collection of RepresentanteEmpresa
     * entities that are retrieved from Empresa?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RepresentanteEmpresa page
     */
    public String navigateRepresentanteEmpresaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RepresentanteEmpresa_items", this.getSelected().getRepresentanteEmpresaList());
        }
        return "/representanteEmpresa/index";
    }

    /**
     * Sets the "items" attribute with a collection of LiquidacionSueldo
     * entities that are retrieved from Empresa?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for LiquidacionSueldo page
     */
    public String navigateLiquidacionSueldoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LiquidacionSueldo_items", this.getSelected().getLiquidacionSueldoList());
        }
        return "/liquidacionSueldo/index";
    }

    /**
     * Sets the "items" attribute with a collection of ReconocimientoDeuda
     * entities that are retrieved from Empresa?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ReconocimientoDeuda page
     */
    public String navigateReconocimientoDeudaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ReconocimientoDeuda_items", this.getSelected().getReconocimientoDeudaList());
        }
        return "/reconocimientoDeuda/index";
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from Empresa?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", this.getSelected().getBusList());
        }
        return "/bus/index";
    }

    /**
     * Sets the "items" attribute with a collection of RelacionLaboral entities
     * that are retrieved from Empresa?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RelacionLaboral page
     */
    public String navigateRelacionLaboralList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RelacionLaboral_items", this.getSelected().getRelacionLaboralList());
        }
        return "/relacionLaboral/index";
    }

    /**
     * Sets the "selected" attribute of the CajaCompensacion controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpresaIdCajaCompensacion(ActionEvent event) {
        if (this.getSelected() != null && empresaIdCajaCompensacionController.getSelected() == null) {
            empresaIdCajaCompensacionController.setSelected(this.getSelected().getEmpresaIdCajaCompensacion());
        }
    }

    /**
     * Sets the "selected" attribute of the Mutual controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpresaIdMutual(ActionEvent event) {
        if (this.getSelected() != null && empresaIdMutualController.getSelected() == null) {
            empresaIdMutualController.setSelected(this.getSelected().getEmpresaIdMutual());
        }
    }

    @Override
    public Empresa prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setEmpresaIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }
    
}
