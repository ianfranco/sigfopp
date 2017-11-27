package com.areatecnica.sigf.beans;


import com.areatecnica.sigf.controllers.CuentaFacade;
import com.areatecnica.sigf.entities.Cuenta;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "cuentaController")
@ViewScoped
public class CuentaController extends AbstractController<Cuenta> {

    @Inject
    private CuentaFacade ejbFacade;
    @Inject
    private TipoCuentaController cuentaIdTipoController;


    /**
     * Initialize the concrete Cuenta controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CuentaController() {
        // Inform the Abstract parent controller of the concrete Cuenta Entity
        super(Cuenta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cuentaIdTipoController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of HorarioJornada entities
     * that are retrieved from Cuenta?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for HorarioJornada page
     */
    public String navigateHorarioJornadaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HorarioJornada_items", this.getSelected().getHorarioJornadaList());
        }
        return "//horarioJornada/index";
    }

    /**
     * Sets the "items" attribute with a collection of Boleto entities that are
     * retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Boleto page
     */
    public String navigateBoletoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Boleto_items", this.getSelected().getBoletoList());
        }
        return "//boleto/index";
    }

    /**
     * Sets the "items" attribute with a collection of ValorMinuto entities that
     * are retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for ValorMinuto page
     */
    public String navigateValorMinutoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ValorMinuto_items", this.getSelected().getValorMinutoList());
        }
        return "//valorMinuto/index";
    }

    /**
     * Sets the "items" attribute with a collection of RepresentanteLegal
     * entities that are retrieved from Cuenta?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RepresentanteLegal page
     */
    public String navigateRepresentanteLegalList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RepresentanteLegal_items", this.getSelected().getRepresentanteLegalList());
        }
        return "//representanteLegal/index";
    }

    /**
     * Sets the "items" attribute with a collection of Banco entities that are
     * retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Banco page
     */
    public String navigateBancoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Banco_items", this.getSelected().getBancoList());
        }
        return "//banco/index";
    }

    /**
     * Sets the "items" attribute with a collection of TipoControl entities that
     * are retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for TipoControl page
     */
    public String navigateTipoControlList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoControl_items", this.getSelected().getTipoControlList());
        }
        return "//tipoControl/index";
    }

    /**
     * Sets the "items" attribute with a collection of CentroCosto entities that
     * are retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for CentroCosto page
     */
    public String navigateCentroCostoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CentroCosto_items", this.getSelected().getCentroCostoList());
        }
        return "//centroCosto/index";
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", this.getSelected().getUsuarioList());
        }
        return "//usuario/index";
    }

    /**
     * Sets the "items" attribute with a collection of AsignacionFamiliar
     * entities that are retrieved from Cuenta?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for AsignacionFamiliar page
     */
    public String navigateAsignacionFamiliarList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AsignacionFamiliar_items", this.getSelected().getAsignacionFamiliarList());
        }
        return "//asignacionFamiliar/index";
    }

    /**
     * Sets the "items" attribute with a collection of InstitucionSalud entities
     * that are retrieved from Cuenta?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for InstitucionSalud page
     */
    public String navigateInstitucionSaludList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InstitucionSalud_items", this.getSelected().getInstitucionSaludList());
        }
        return "//institucionSalud/index";
    }

    /**
     * Sets the "items" attribute with a collection of Flota entities that are
     * retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Flota page
     */
    public String navigateFlotaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Flota_items", this.getSelected().getFlotaList());
        }
        return "//flota/index";
    }

    /**
     * Sets the "items" attribute with a collection of Egreso entities that are
     * retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Egreso page
     */
    public String navigateEgresoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Egreso_items", this.getSelected().getEgresoList());
        }
        return "//egreso/index";
    }

    /**
     * Sets the "items" attribute with a collection of TipoObservacion entities
     * that are retrieved from Cuenta?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for TipoObservacion page
     */
    public String navigateTipoObservacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoObservacion_items", this.getSelected().getTipoObservacionList());
        }
        return "//tipoObservacion/index";
    }

    /**
     * Sets the "items" attribute with a collection of AdministracionMensual
     * entities that are retrieved from Cuenta?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for AdministracionMensual page
     */
    public String navigateAdministracionMensualList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AdministracionMensual_items", this.getSelected().getAdministracionMensualList());
        }
        return "//administracionMensual/index";
    }

    /**
     * Sets the "items" attribute with a collection of IntervalosAdministracion
     * entities that are retrieved from Cuenta?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for IntervalosAdministracion page
     */
    public String navigateIntervalosAdministracionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("IntervalosAdministracion_items", this.getSelected().getIntervalosAdministracionList());
        }
        return "//intervalosAdministracion/index";
    }

    /**
     * Sets the "items" attribute with a collection of TipoHaberTrabajador
     * entities that are retrieved from Cuenta?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for TipoHaberTrabajador page
     */
    public String navigateTipoHaberTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoHaberTrabajador_items", this.getSelected().getTipoHaberTrabajadorList());
        }
        return "//tipoHaberTrabajador/index";
    }

    /**
     * Sets the "items" attribute with a collection of PrecioCombustible
     * entities that are retrieved from Cuenta?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for PrecioCombustible page
     */
    public String navigatePrecioCombustibleList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PrecioCombustible_items", this.getSelected().getPrecioCombustibleList());
        }
        return "//precioCombustible/index";
    }

    /**
     * Sets the "items" attribute with a collection of InstitucionApv entities
     * that are retrieved from Cuenta?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for InstitucionApv page
     */
    public String navigateInstitucionApvList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InstitucionApv_items", this.getSelected().getInstitucionApvList());
        }
        return "//institucionApv/index";
    }

    /**
     * Sets the "items" attribute with a collection of TipoContrato entities
     * that are retrieved from Cuenta?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for TipoContrato page
     */
    public String navigateTipoContratoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoContrato_items", this.getSelected().getTipoContratoList());
        }
        return "//tipoContrato/index";
    }

    /**
     * Sets the "items" attribute with a collection of Sindicato entities that
     * are retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Sindicato page
     */
    public String navigateSindicatoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Sindicato_items", this.getSelected().getSindicatoList());
        }
        return "//sindicato/index";
    }

    /**
     * Sets the "items" attribute with a collection of CompraBoleto entities
     * that are retrieved from Cuenta?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CompraBoleto page
     */
    public String navigateCompraBoletoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CompraBoleto_items", this.getSelected().getCompraBoletoList());
        }
        return "//compraBoleto/index";
    }

    /**
     * Sets the "items" attribute with a collection of TipoCargo entities that
     * are retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for TipoCargo page
     */
    public String navigateTipoCargoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoCargo_items", this.getSelected().getTipoCargoList());
        }
        return "//tipoCargo/index";
    }

    /**
     * Sets the "items" attribute with a collection of TipoDescuentoTrabajador
     * entities that are retrieved from Cuenta?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for TipoDescuentoTrabajador page
     */
    public String navigateTipoDescuentoTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoDescuentoTrabajador_items", this.getSelected().getTipoDescuentoTrabajadorList());
        }
        return "//tipoDescuentoTrabajador/index";
    }

    /**
     * Sets the "items" attribute with a collection of InstitucionPrevision
     * entities that are retrieved from Cuenta?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for InstitucionPrevision page
     */
    public String navigateInstitucionPrevisionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InstitucionPrevision_items", this.getSelected().getInstitucionPrevisionList());
        }
        return "//institucionPrevision/index";
    }

    /**
     * Sets the "items" attribute with a collection of GrupoServicio entities
     * that are retrieved from Cuenta?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for GrupoServicio page
     */
    public String navigateGrupoServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("GrupoServicio_items", this.getSelected().getGrupoServicioList());
        }
        return "//grupoServicio/index";
    }

    /**
     * Sets the "items" attribute with a collection of CompraCombustible
     * entities that are retrieved from Cuenta?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for CompraCombustible page
     */
    public String navigateCompraCombustibleList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CompraCombustible_items", this.getSelected().getCompraCombustibleList());
        }
        return "//compraCombustible/index";
    }

    /**
     * Sets the "items" attribute with a collection of Mutual entities that are
     * retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Mutual page
     */
    public String navigateMutualList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Mutual_items", this.getSelected().getMutualList());
        }
        return "//mutual/index";
    }

    /**
     * Sets the "items" attribute with a collection of TipoAbono entities that
     * are retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for TipoAbono page
     */
    public String navigateTipoAbonoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoAbono_items", this.getSelected().getTipoAbonoList());
        }
        return "//tipoAbono/index";
    }

    /**
     * Sets the "items" attribute with a collection of Empresa entities that are
     * retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Empresa page
     */
    public String navigateEmpresaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empresa_items", this.getSelected().getEmpresaList());
        }
        return "//empresa/index";
    }

    /**
     * Sets the "items" attribute with a collection of UnidadNegocio entities
     * that are retrieved from Cuenta?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for UnidadNegocio page
     */
    public String navigateUnidadNegocioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("UnidadNegocio_items", this.getSelected().getUnidadNegocioList());
        }
        return "//unidadNegocio/index";
    }

    /**
     * Sets the "items" attribute with a collection of ProcesoRecaudacion
     * entities that are retrieved from Cuenta?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ProcesoRecaudacion page
     */
    public String navigateProcesoRecaudacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ProcesoRecaudacion_items", this.getSelected().getProcesoRecaudacionList());
        }
        return "//procesoRecaudacion/index";
    }

    /**
     * Sets the "selected" attribute of the TipoCuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCuentaIdTipo(ActionEvent event) {
        if (this.getSelected() != null && cuentaIdTipoController.getSelected() == null) {
            cuentaIdTipoController.setSelected(this.getSelected().getCuentaIdTipo());
        }
    }

    /**
     * Sets the "items" attribute with a collection of TipoTrabajador entities
     * that are retrieved from Cuenta?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for TipoTrabajador page
     */
    public String navigateTipoTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoTrabajador_items", this.getSelected().getTipoTrabajadorList());
        }
        return "//tipoTrabajador/index";
    }

    /**
     * Sets the "items" attribute with a collection of CajaCompensacion entities
     * that are retrieved from Cuenta?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CajaCompensacion page
     */
    public String navigateCajaCompensacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CajaCompensacion_items", this.getSelected().getCajaCompensacionList());
        }
        return "//cajaCompensacion/index";
    }

    /**
     * Sets the "items" attribute with a collection of Control entities that are
     * retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Control page
     */
    public String navigateControlList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Control_items", this.getSelected().getControlList());
        }
        return "//control/index";
    }

    /**
     * Sets the "items" attribute with a collection of Terminal entities that
     * are retrieved from Cuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Terminal page
     */
    public String navigateTerminalList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Terminal_items", this.getSelected().getTerminalList());
        }
        return "//terminal/index";
    }
    
    /**
     * Sets the "items" attribute with a collection of Departamento entities
     * that are retrieved from Cuenta?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Departamento page
     */
    public String navigateDepartamentoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Departamento_items", this.getSelected().getDepartamentoList());
        }
        return "//departamento/index";
    }

    
}
