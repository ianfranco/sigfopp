package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.controllers.TrabajadorFacade;
import com.areatecnica.sigf.dao.ITrabajadorDao;
import com.areatecnica.sigf.dao.impl.ITrabajadorDaoImpl;
import com.ibm.icu.util.Calendar;
import java.util.Date;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.joda.time.DateTime;

@Named(value = "trabajadorController")
@ViewScoped
public class TrabajadorController extends AbstractController<Trabajador> {

    @Inject
    private TrabajadorFacade ejbFacade;
    @Inject
    private InstitucionApvController trabajadorIdInstitucionApvController;
    @Inject
    private AsignacionFamiliarController trabajadorIdAsignacionFamiliarController;
    @Inject
    private CentroCostoController trabajadorIdCentroCostoController;
    @Inject
    private ComunaController trabajadorIdComunaController;
    @Inject
    private TipoCotizacionTrabajadorController trabajadorIdTipoCotizacionTrabajadorController;
    @Inject
    private CuentaBancariaController trabajadorIdCuentaBancariaController;
    @Inject
    private EstadoCivilController trabajadorIdEstadoCivilController;
    @Inject
    private FormaPagoController trabajadorIdFormaPagoController;
    @Inject
    private InstitucionPrevisionController trabajadorIdInstitucionPrevisionController;
    @Inject
    private InstitucionSaludController trabajadorIdInstitucionSaludController;
    @Inject
    private SindicatoController trabajadorIdSindicatoController;
    @Inject
    private TerminalController trabajadorIdTerminalController;

    private ITrabajadorDao trabajadorDao;
    

    private Date minFechaNacimiento;
    private Date maxFechaNacimiento;
    private int regimenPrevisional;
    private boolean errorTrabajador;

    /**
     * Initialize the concrete Trabajador controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TrabajadorController() {
        // Inform the Abstract parent controller of the concrete Trabajador Entity
        super(Trabajador.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Trabajador.findAllByCuenta");
        Calendar calendar = Calendar.getInstance();
        DateTime dateTime = new DateTime(calendar.get(Calendar.YEAR) - 20, 1, 1, 0, 0);
        this.minFechaNacimiento = dateTime.toDate();
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        trabajadorIdInstitucionApvController.setSelected(null);
        trabajadorIdAsignacionFamiliarController.setSelected(null);
        trabajadorIdCentroCostoController.setSelected(null);
        trabajadorIdComunaController.setSelected(null);
        trabajadorIdTipoCotizacionTrabajadorController.setSelected(null);
        trabajadorIdCuentaBancariaController.setSelected(null);
        trabajadorIdEstadoCivilController.setSelected(null);
        trabajadorIdFormaPagoController.setSelected(null);
        trabajadorIdInstitucionPrevisionController.setSelected(null);
        trabajadorIdInstitucionSaludController.setSelected(null);
        trabajadorIdSindicatoController.setSelected(null);
        trabajadorIdTerminalController.setSelected(null);
    }

    /**
     * @return the regimenPrevisional
     */
    public int getRegimenPrevisional() {
        return regimenPrevisional;
    }

    /**
     * @param regimenPrevisional the regimenPrevisional to set
     */
    public void setRegimenPrevisional(int regimenPrevisional) {
        this.regimenPrevisional = regimenPrevisional;
    }

    /**
     * Sets the "items" attribute with a collection of HaberTrabajador entities
     * that are retrieved from Trabajador?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for HaberTrabajador page
     */
    public String navigateHaberTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HaberTrabajador_items", this.getSelected().getHaberTrabajadorList());
        }
        return "/haberTrabajador/index";
    }

    /**
     * Sets the "items" attribute with a collection of CargaTrabajador entities
     * that are retrieved from Trabajador?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CargaTrabajador page
     */
    public String navigateCargaTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargaTrabajador_items", this.getSelected().getCargaTrabajadorList());
        }
        return "/cargaTrabajador/index";
    }

    /**
     * Sets the "items" attribute with a collection of TrabajadorAdicionalSalud
     * entities that are retrieved from Trabajador?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for TrabajadorAdicionalSalud page
     */
    public String navigateTrabajadorAdicionalSaludList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TrabajadorAdicionalSalud_items", this.getSelected().getTrabajadorAdicionalSaludList());
        }
        return "/trabajadorAdicionalSalud/index";
    }

    /**
     * Sets the "items" attribute with a collection of Despacho entities that
     * are retrieved from Trabajador?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Despacho page
     */
    public String navigateDespachoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Despacho_items", this.getSelected().getDespachoList());
        }
        return "/despacho/index";
    }

    /**
     * Sets the "items" attribute with a collection of LiquidacionSueldo
     * entities that are retrieved from Trabajador?cap_first and returns the
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
     * Sets the "selected" attribute of the InstitucionApv controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdInstitucionApv(ActionEvent event) {
        if (this.getSelected() != null && trabajadorIdInstitucionApvController.getSelected() == null) {
            trabajadorIdInstitucionApvController.setSelected(this.getSelected().getTrabajadorIdInstitucionApv());
        }
    }

    /**
     * Sets the "selected" attribute of the AsignacionFamiliar controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdAsignacionFamiliar(ActionEvent event) {
        if (this.getSelected() != null && trabajadorIdAsignacionFamiliarController.getSelected() == null) {
            trabajadorIdAsignacionFamiliarController.setSelected(this.getSelected().getTrabajadorIdAsignacionFamiliar());
        }
    }

    /**
     * Sets the "selected" attribute of the CentroCosto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdCentroCosto(ActionEvent event) {
        if (this.getSelected() != null && trabajadorIdCentroCostoController.getSelected() == null) {
            trabajadorIdCentroCostoController.setSelected(this.getSelected().getTrabajadorIdCentroCosto());
        }
    }

    /**
     * Sets the "selected" attribute of the Comuna controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdComuna(ActionEvent event) {
        if (this.getSelected() != null && trabajadorIdComunaController.getSelected() == null) {
            trabajadorIdComunaController.setSelected(this.getSelected().getTrabajadorIdComuna());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoCotizacionTrabajador controller
     * in order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdTipoCotizacionTrabajador(ActionEvent event) {
        if (this.getSelected() != null && trabajadorIdTipoCotizacionTrabajadorController.getSelected() == null) {
            trabajadorIdTipoCotizacionTrabajadorController.setSelected(this.getSelected().getTrabajadorIdTipoCotizacionTrabajador());
        }
    }

    /**
     * Sets the "selected" attribute of the EstadoCivil controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdEstadoCivil(ActionEvent event) {
        if (this.getSelected() != null && trabajadorIdEstadoCivilController.getSelected() == null) {
            trabajadorIdEstadoCivilController.setSelected(this.getSelected().getTrabajadorIdEstadoCivil());
        }
    }

    /**
     * Sets the "selected" attribute of the FormaPago controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdFormaPago(ActionEvent event) {
        if (this.getSelected() != null && trabajadorIdFormaPagoController.getSelected() == null) {
            trabajadorIdFormaPagoController.setSelected(this.getSelected().getTrabajadorIdFormaPago());
        }
    }

    /**
     * Sets the "selected" attribute of the InstitucionPrevision controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdInstitucionPrevision(ActionEvent event) {
        if (this.getSelected() != null && trabajadorIdInstitucionPrevisionController.getSelected() == null) {
            trabajadorIdInstitucionPrevisionController.setSelected(this.getSelected().getTrabajadorIdInstitucionPrevision());
        }
    }

    /**
     * Sets the "selected" attribute of the InstitucionSalud controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdInstitucionSalud(ActionEvent event) {
        if (this.getSelected() != null && trabajadorIdInstitucionSaludController.getSelected() == null) {
            trabajadorIdInstitucionSaludController.setSelected(this.getSelected().getTrabajadorIdInstitucionSalud());
        }
    }

    /**
     * Sets the "selected" attribute of the Sindicato controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdSindicato(ActionEvent event) {
        if (this.getSelected() != null && trabajadorIdSindicatoController.getSelected() == null) {
            trabajadorIdSindicatoController.setSelected(this.getSelected().getTrabajadorIdSindicato());
        }
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdTerminal(ActionEvent event) {
        if (this.getSelected() != null && trabajadorIdTerminalController.getSelected() == null) {
            trabajadorIdTerminalController.setSelected(this.getSelected().getTrabajadorIdTerminal());
        }
    }

    /**
     * Sets the "items" attribute with a collection of ReconocimientoDeuda
     * entities that are retrieved from Trabajador?cap_first and returns the
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
     * Sets the "items" attribute with a collection of RelacionLaboral entities
     * that are retrieved from Trabajador?cap_first and returns the navigation
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
     * Sets the "items" attribute with a collection of CargaRetroactiva entities
     * that are retrieved from Trabajador?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CargaRetroactiva page
     */
    public String navigateCargaRetroactivaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargaRetroactiva_items", this.getSelected().getCargaRetroactivaList());
        }
        return "/cargaRetroactiva/index";
    }

    /**
     * Sets the "items" attribute with a collection of LicenciaMedica entities
     * that are retrieved from Trabajador?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for LicenciaMedica page
     */
    public String navigateLicenciaMedicaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LicenciaMedica_items", this.getSelected().getLicenciaMedicaList());
        }
        return "/licenciaMedica/index";
    }

    /**
     * Sets the "items" attribute with a collection of HoraExtraTrabajador
     * entities that are retrieved from Trabajador?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for HoraExtraTrabajador page
     */
    public String navigateHoraExtraTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HoraExtraTrabajador_items", this.getSelected().getHoraExtraTrabajadorList());
        }
        return "/horaExtraTrabajador/index";
    }

    /**
     * Sets the "items" attribute with a collection of DescuentoTrabajador
     * entities that are retrieved from Trabajador?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DescuentoTrabajador page
     */
    public String navigateDescuentoTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DescuentoTrabajador_items", this.getSelected().getDescuentoTrabajadorList());
        }
        return "/descuentoTrabajador/index";
    }

    /**
     * Sets the "items" attribute with a collection of Guia entities that are
     * retrieved from Trabajador?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Guia page
     */
    public String navigateGuiaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Guia_items", this.getSelected().getGuiaList());
        }
        return "/guia/index";
    }

    /**
     * Sets the "items" attribute with a collection of FeriadoLegal entities
     * that are retrieved from Trabajador?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for FeriadoLegal page
     */
    public String navigateFeriadoLegalList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FeriadoLegal_items", this.getSelected().getFeriadoLegalList());
        }
        return "/feriadoLegal/index";
    }

    /**
     * Sets the "items" attribute with a collection of JornadaTrabajador
     * entities that are retrieved from Trabajador?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for JornadaTrabajador page
     */
    public String navigateJornadaTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("JornadaTrabajador_items", this.getSelected().getJornadaTrabajadorList());
        }
        return "/jornadaTrabajador/index";
    }

    /**
     * Sets the "items" attribute with a collection of ControlAsistencia
     * entities that are retrieved from Trabajador?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ControlAsistencia page
     */
    public String navigateControlAsistenciaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ControlAsistencia_items", this.getSelected().getControlAsistenciaList());
        }
        return "/controlAsistencia/index";
    }

    /**
     * Sets the "items" attribute with a collection of ObservacionTrabajador
     * entities that are retrieved from Trabajador?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ObservacionTrabajador page
     */
    public String navigateObservacionTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ObservacionTrabajador_items", this.getSelected().getObservacionTrabajadorList());
        }
        return "/observacionTrabajador/index";
    }

    @Override
    public Trabajador prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        this.trabajadorDao = new ITrabajadorDaoImpl();

        this.getSelected().setTrabajadorCodigo(this.trabajadorDao.findMaxCodigoCuenta(this.getUserCount()));        
        this.getSelected().setTrabajadorIdCuenta(this.getUserCount());
        this.getSelected().setTrabajadorNacionalidad(Boolean.TRUE);
        this.getSelected().setTrabajadorSexo(Boolean.TRUE);
        this.getSelected().setTrabajadorSubsidioJoven(Boolean.FALSE);
        this.getSelected().setTrabajadorIdEstadoCivil(this.trabajadorIdEstadoCivilController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdAsignacionFamiliar(this.trabajadorIdAsignacionFamiliarController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdCentroCosto(this.trabajadorIdCentroCostoController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdComuna(this.trabajadorIdComunaController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdSindicato(this.trabajadorIdSindicatoController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdTipoCotizacionTrabajador(this.trabajadorIdTipoCotizacionTrabajadorController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdInstitucionSalud(this.trabajadorIdInstitucionSaludController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorFechaNacimiento(this.minFechaNacimiento);
        this.getSelected().setTrabajadorIdInstitucionApv(this.trabajadorIdInstitucionApvController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdInstitucionPrevision(this.trabajadorIdInstitucionPrevisionController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorFormaPagoApv(Boolean.TRUE);
        this.getSelected().setTrabajadorMontoApv(0);
        this.getSelected().setTrabajadorFonasa(Boolean.TRUE);
        this.getSelected().setTrabajadorPoseeApv(Boolean.FALSE);
        this.getSelected().setTrabajadorPoseeCargas(Boolean.FALSE);
        this.getSelected().setTrabajadorPoseeCuentaBanco(Boolean.FALSE);
        this.getSelected().setTrabajadorJubilado(Boolean.FALSE);
        this.getSelected().setTrabajadorIps(Boolean.FALSE);
        this.getSelected().setTrabajadorContratado(Boolean.FALSE);
        this.setRegimenPrevisional(0);
        return this.getSelected();
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveNew(ActionEvent event) {

        if (!errorTrabajador) {
            switch (this.regimenPrevisional) {
                case 0:
                    break;
                case 1:
                    this.getSelected().setTrabajadorJubilado(Boolean.TRUE);
                    this.getSelected().setTrabajadorIps(Boolean.FALSE);
                    this.getSelected().setTrabajadorIdInstitucionPrevision(this.trabajadorIdInstitucionPrevisionController.getItems().stream().findFirst().get());
                    break;
                case 2:
                    this.getSelected().setTrabajadorIps(Boolean.TRUE);
                    this.getSelected().setTrabajadorJubilado(Boolean.FALSE);
                    this.getSelected().setTrabajadorIdInstitucionPrevision(this.trabajadorIdInstitucionPrevisionController.getItems().stream().findFirst().get());
                    break;
                default:
                    break;
            }
            super.saveNew(event);
        } else {
            JsfUtil.addErrorMessage("El rut se enncuentra registrado");
        }
    }

    public void findTrabajador() {
        if (!this.getSelected().getTrabajadorRut().equals("")) {
            FacesMessage msg = new FacesMessage("Rut ya registrado", "Error de validación");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            Trabajador auxTrabajador = this.trabajadorDao.findByTrabajadorRutAndCuenta(this.getSelected().getTrabajadorRut(), this.getUserCount());
            if (auxTrabajador != null) {
                this.errorTrabajador = Boolean.TRUE;
                JsfUtil.addErrorMessage("Rut ya registrado");
            }else{
                this.errorTrabajador = Boolean.FALSE;
            }
        }
    }
}
