package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Privilegio;
import com.areatecnica.sigf.entities.RolMenu;
import com.areatecnica.sigf.entities.Usuario;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Omer Faruk KURT
 * @e-mail kurtomerfaruk@gmail.com
 * @blog http://kurtomerfaruk.com
 */
@ManagedBean
@ViewScoped
public final class CountPage implements java.io.Serializable {

    private static final long serialVersionUID = 7466250594126927225L;
    private Usuario usuario;
    private int abonoBusCount;
    private int abonoLiquidacionCount;
    private int administracionMensualCount;
    private int asignacionFamiliarCount;
    private int bancoCount;
    private int boletoCount;
    private int busCount;
    private int busServicioCount;
    private int cargandoCount;
    private int cajaCompensacionCount;
    private int cajaRecaudacionCount;
    private int cajaProcesoCount;
    private int calleCount;
    private int calleServicioCount;
    private int cargaRetroactivaCount;
    private int cargaTrabajadorCount;
    private int cargoBusCount;
    private int cargoLiquidacionCount;
    private int causalFiniquitoCount;
    private int centroCostoCount;
    private int ciudadCount;
    private int grupoServicioCount;
    private int compraBoletoCount;
    private int compraCombustibleCount;
    private int comunaCount;
    private int contratoUnidadCount;
    private int controlCount;
    private int controlAsistenciaCount;
    private int controlHorarioServicioCount;
    private int controlServicioCount;
    private int ctvResumenCount;
    private int cuentaBancariaCount;
    private int cuentaBancoEmpresaCount;
    private int cuentaBancoProcesoCount;
    private int cuentaBancoTrabajadorCount;
    private int departamentoCount;
    private int descuentoLiquidacionCount;
    private int descuentoTrabajadorCount;
    private int despachoCount;
    private int detalleBilletesCtvCount;
    private int detalleCompraBoletoCount;
    private int detalleIntervalosMensualCount;
    private int detalleMetalicoCtvCount;
    private int detalleResumenRecaudacionCount;
    private int diaFestivoCount;
    private int efectivoCajaCount;
    private int count;
    private int egresoCount;
    private int egresoBusCount;
    private int egresoCajaRecaudacionCount;
    private int egresoFlotaCount;
    private int egresoGuiaCount;
    private int egresoProcesoRecaudacionCount;
    private int egresoRecaudacionCount;
    private int empresaCount;
    private int estadoBusCount;
    private int estadoCivilCount;
    private int estadoGuiaCount;
    private int fechaCount;
    private int feriadoLegalCount;
    private int finiquitoRelacionLaboralCount;
    private int flotaCount;
    private int formaPagoCount;
    private int frecuenciaServicioCount;
    private int gastoAdministracionMensualCount;
    private int guiaCount;
    private int haberLiquidacionCount;
    private int haberTrabajadorCount;
    private int horaExtraTrabajadorCount;
    private int horarioJornadaCount;
    private int horarioServicioCount;
    private int impuestoSegundaCategoriaCount;
    private int institucionApvCount;
    private int institucionPrevisionCount;
    private int institucionSaludCount;
    private int intervalosAdministracionCount;
    private int inventarioCajaCount;
    private int inventarioInternoCount;
    private int jornadaLaboralCount;
    private int jornadaTrabajadorCount;
    private int licenciaMedicaCount;
    private int liquidacionEmpresaCount;
    private int liquidacionSueldoCount;
    private int logCount;
    private int marcaBusCount;
    private int metodoPagoCount;
    private int modeloMarcaBusCount;
    private int monedaPactadaInstitucionSaludCount;
    private int mutualCount;
    private int numeralSurtidorCount;
    private int observacionTrabajadorCount;
    private int operadorTransporteCount;
    private int parametroCesantiaCount;
    private int parametroInterpolacionCount;
    private int parentescoCargaCount;
    private int periodoFrecuenciaCount;
    private int precioCombustibleCount;
    private int privilegioCount;
    private int procesoRecaudacionCount;
    private int reconocimientoDeudaCount;
    private int regionCount;
    private int registroMinutoCount;
    private int relacionLaboralCount;
    private int representanteEmpresaCount;
    private int representanteLegalCount;
    private int resumenRecaudacionCount;
    private int rolCount;
    private int rolPrivilegioCount;
    private int serieBoletoGuiaCount;
    private int servicioCount;
    private int sindicatoCount;
    private int sueldoBaseCount;
    private int surtidorCount;
    private int tarifaGrupoServicioCount;
    private int terminalCount;
    private int tipoAbonoCount;
    private int tipoCargaFamiliarCount;
    private int tipoCargoCount;
    private int tipoCombustibleCount;
    private int tipoContratoCount;
    private int tipoControlCount;
    private int tipoCotizacionTrabajadorCount;
    private int tipoCuentaBancoCount;
    private int tipoDemandaCount;
    private int tipoDescuentoTrabajadorCount;
    private int tipoDiaFrecuenciaCount;
    private int tipoEstacionalidadCount;
    private int tipoHaberTrabajadorCount;
    private int tipoInstitucionApvCount;
    private int tipoLineaCount;
    private int tipoMovimientoPersonalCount;
    private int tipoNominaCount;
    private int tipoObservacionCount;
    private int tipoTrabajadorCount;
    private int topeCount;
    private int trabajadorCount;
    private int trabajadorAdicionalSaludCount;
    private int unidadNegocioCount;
    private int usuarioCount;
    private int valorFijoCount;
    private int valorMinutoCount;
    private int valorRolloUnidadCount;
    private int valorUfCount;
    private int valorUtmCount;
    private int ventaBoletoCount;
    private int ventaCombustibleCount;

    private final static List<String> NOT_COLUMN_NAME_LIST = Arrays.asList("BaseEntity",
            "ColumnModel",
            "ColumnSettings",
            "Menu",
            "chart",
            "Language",
            "lang",
            "audit");

    private List<Integer> listCount;

    /**
     *
     */
    protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.areatecnica_sigf-admin_war_2PU");

    /**
     *
     */
    protected EntityManager entityManager;

    /**
     *
     */
    public CountPage() {

    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");

        this.busCount = this.usuario.getUsuarioIdTerminal().getBusList().size();
        this.servicioCount = this.usuario.getUsuarioIdTerminal().getServicioList().size();
        this.trabajadorCount = this.usuario.getUsuarioIdTerminal().getTrabajadorList().size();
        this.usuarioCount = this.usuario.getUsuarioIdTerminal().getUsuarioList().size();
        this.cajaRecaudacionCount = this.usuario.getUsuarioIdCuenta().getCajaRecaudacionList().size();
        this.terminalCount = this.usuario.getUsuarioIdCuenta().getTerminalList().size();
        this.empresaCount = this.usuario.getUsuarioIdCuenta().getEmpresaList().size();
        this.flotaCount = this.usuario.getUsuarioIdCuenta().getFlotaList().size();
        
        
        /*entityManager = emf.createEntityManager();

        for (RolMenu r : this.usuario.getUsuarioIdRol().getRolMenuList()) {
            for (Privilegio p : r.getRolMenuIdMenu().getPrivilegioList()) {
                String tabla = p.getPrivilegioTabla();
                if (tabla != null) {

                    try {
                        Field field = getClass().getDeclaredField(tabla + "Count");
                        if (field.getName().contains("Count") && !field.getName().equals("cargando")) {
                            field.setInt(this, entityManager.createQuery("SELECT c FROM " + tabla.substring(0, 1).toUpperCase() + tabla.substring(1) + " c").getResultList().size());
                        }
                    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(CountPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }*/

    }

    /**
     *
     * @param menuLink
     * @return
     */
    public int fieldGetValue(String menuLink) {
        String[] parts = menuLink.split("/");
        int result = 0;
        Field field = null;
        if (parts.length > 1) {
            if (!parts[1].equals("menu")) {
                if (!NOT_COLUMN_NAME_LIST.contains(parts[2])) {
                    try {

                        CharSequence cs = "-";

                        if (!parts[2].contains(cs)) {
                            field = getClass().getDeclaredField(parts[2] + "Count");
                            result = (int) field.get(this);
                        }

                    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | NullPointerException ex) {
                        Logger.getLogger(CountPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return result;
    }

    /*private List<String> getClassesInPackage() {
        List<String> classes = new ArrayList<String>();
        String packageNameSlashed = "/" + "com.areatecnica.sigf.entities".replace(".", "/");
        URL directoryURL = Thread.currentThread().getContextClassLoader().getResource(packageNameSlashed);
        if (directoryURL == null) {
            return classes;
        }

        String directoryString = directoryURL.getFile();
        if (directoryString == null) {
            return classes;
        }

        File directory = new File(directoryString);
        if (directory.exists()) {
            String[] files = directory.list();
            for (String fileName : files) {
                if (fileName.endsWith(".class")) {
                    fileName = fileName.substring(0, fileName.length() - 6);
                    try {
                        if (!fileName.contains("_")) {
                            classes.add(fileName);
                        }

                    } catch (Exception e) {
                    }
                }
            }
        } else {
        }
        return classes;
    }*/
    /**
     *
     * @return
     */
    public List<Integer> getListCount() {
        return listCount;
    }

    /**
     *
     * @param listCount
     */
    public void setListCount(List<Integer> listCount) {
        this.listCount = listCount;
    }

    /**
     * @return the abonoBusCount
     */
    public int getAbonoBusCount() {
        return abonoBusCount;
    }

    /**
     * @param abonoBusCount the abonoBusCount to set
     */
    public void setAbonoBusCount(int abonoBusCount) {
        this.abonoBusCount = abonoBusCount;
    }

    /**
     * @return the abonoLiquidacionCount
     */
    public int getAbonoLiquidacionCount() {
        return abonoLiquidacionCount;
    }

    /**
     * @param abonoLiquidacionCount the abonoLiquidacionCount to set
     */
    public void setAbonoLiquidacionCount(int abonoLiquidacionCount) {
        this.abonoLiquidacionCount = abonoLiquidacionCount;
    }

    /**
     * @return the administracionMensualCount
     */
    public int getAdministracionMensualCount() {
        return administracionMensualCount;
    }

    /**
     * @param administracionMensualCount the administracionMensualCount to set
     */
    public void setAdministracionMensualCount(int administracionMensualCount) {
        this.administracionMensualCount = administracionMensualCount;
    }

    /**
     * @return the asignacionFamiliarCount
     */
    public int getAsignacionFamiliarCount() {
        return asignacionFamiliarCount;
    }

    /**
     * @param asignacionFamiliarCount the asignacionFamiliarCount to set
     */
    public void setAsignacionFamiliarCount(int asignacionFamiliarCount) {
        this.asignacionFamiliarCount = asignacionFamiliarCount;
    }

    /**
     * @return the bancoCount
     */
    public int getBancoCount() {
        return bancoCount;
    }

    /**
     * @param bancoCount the bancoCount to set
     */
    public void setBancoCount(int bancoCount) {
        this.bancoCount = bancoCount;
    }

    /**
     * @return the boletoCount
     */
    public int getBoletoCount() {
        return boletoCount;
    }

    /**
     * @param boletoCount the boletoCount to set
     */
    public void setBoletoCount(int boletoCount) {
        this.boletoCount = boletoCount;
    }

    /**
     * @return the busCount
     */
    public int getBusCount() {
        return busCount;
    }

    /**
     * @param busCount the busCount to set
     */
    public void setBusCount(int busCount) {
        this.busCount = busCount;
    }

    /**
     * @return the busServicioCount
     */
    public int getBusServicioCount() {
        return busServicioCount;
    }

    /**
     * @param busServicioCount the busServicioCount to set
     */
    public void setBusServicioCount(int busServicioCount) {
        this.busServicioCount = busServicioCount;
    }

    /**
     * @return the cajaCompensacionCount
     */
    public int getCajaCompensacionCount() {
        return cajaCompensacionCount;
    }

    /**
     * @param cajaCompensacionCount the cajaCompensacionCount to set
     */
    public void setCajaCompensacionCount(int cajaCompensacionCount) {
        this.cajaCompensacionCount = cajaCompensacionCount;
    }

    /**
     * @return the cajaProcesoCount
     */
    public int getCajaProcesoCount() {
        return cajaProcesoCount;
    }

    /**
     * @param cajaProcesoCount the cajaProcesoCount to set
     */
    public void setCajaProcesoCount(int cajaProcesoCount) {
        this.cajaProcesoCount = cajaProcesoCount;
    }

    /**
     * @return the cajaTerminalCount
     */
    public int getCajaRecaudacionCount() {
        return cajaRecaudacionCount;
    }

    /**
     * @param cajaTerminalCount the cajaTerminalCount to set
     */
    public void setCajaRecaudacionCount(int cajaRecaudacionCount) {
        this.cajaRecaudacionCount = cajaRecaudacionCount;
    }

    /**
     * @return the calleCount
     */
    public int getCalleCount() {
        return calleCount;
    }

    /**
     * @param calleCount the calleCount to set
     */
    public void setCalleCount(int calleCount) {
        this.calleCount = calleCount;
    }

    /**
     * @return the calleServicioCount
     */
    public int getCalleServicioCount() {
        return calleServicioCount;
    }

    /**
     * @param calleServicioCount the calleServicioCount to set
     */
    public void setCalleServicioCount(int calleServicioCount) {
        this.calleServicioCount = calleServicioCount;
    }

    /**
     * @return the cargandoCount
     */
    public int getCargandoCount() {
        return cargandoCount;
    }

    /**
     * @param cargandoCount the cargandoCount to set
     */
    public void setCargandoCount(int cargandoCount) {
        this.cargandoCount = cargandoCount;
    }

    /**
     * @return the cargaRetroactivaCount
     */
    public int getCargaRetroactivaCount() {
        return cargaRetroactivaCount;
    }

    /**
     * @param cargaRetroactivaCount the cargaRetroactivaCount to set
     */
    public void setCargaRetroactivaCount(int cargaRetroactivaCount) {
        this.cargaRetroactivaCount = cargaRetroactivaCount;
    }

    /**
     * @return the cargaTrabajadorCount
     */
    public int getCargaTrabajadorCount() {
        return cargaTrabajadorCount;
    }

    /**
     * @param cargaTrabajadorCount the cargaTrabajadorCount to set
     */
    public void setCargaTrabajadorCount(int cargaTrabajadorCount) {
        this.cargaTrabajadorCount = cargaTrabajadorCount;
    }

    /**
     * @return the cargoBusCount
     */
    public int getCargoBusCount() {
        return cargoBusCount;
    }

    /**
     * @param cargoBusCount the cargoBusCount to set
     */
    public void setCargoBusCount(int cargoBusCount) {
        this.cargoBusCount = cargoBusCount;
    }

    /**
     * @return the cargoLiquidacionCount
     */
    public int getCargoLiquidacionCount() {
        return cargoLiquidacionCount;
    }

    /**
     * @param cargoLiquidacionCount the cargoLiquidacionCount to set
     */
    public void setCargoLiquidacionCount(int cargoLiquidacionCount) {
        this.cargoLiquidacionCount = cargoLiquidacionCount;
    }

    /**
     * @return the causalFiniquitoCount
     */
    public int getCausalFiniquitoCount() {
        return causalFiniquitoCount;
    }

    /**
     * @param causalFiniquitoCount the causalFiniquitoCount to set
     */
    public void setCausalFiniquitoCount(int causalFiniquitoCount) {
        this.causalFiniquitoCount = causalFiniquitoCount;
    }

    /**
     * @return the centroCostoCount
     */
    public int getCentroCostoCount() {
        return centroCostoCount;
    }

    /**
     * @param centroCostoCount the centroCostoCount to set
     */
    public void setCentroCostoCount(int centroCostoCount) {
        this.centroCostoCount = centroCostoCount;
    }

    /**
     * @return the ciudadCount
     */
    public int getCiudadCount() {
        return ciudadCount;
    }

    /**
     * @param ciudadCount the ciudadCount to set
     */
    public void setCiudadCount(int ciudadCount) {
        this.ciudadCount = ciudadCount;
    }

    /**
     * @return the compraBoletoCount
     */
    public int getCompraBoletoCount() {
        return compraBoletoCount;
    }

    /**
     * @param compraBoletoCount the compraBoletoCount to set
     */
    public void setCompraBoletoCount(int compraBoletoCount) {
        this.compraBoletoCount = compraBoletoCount;
    }

    /**
     * @return the compraCombustibleCount
     */
    public int getCompraCombustibleCount() {
        return compraCombustibleCount;
    }

    /**
     * @param compraCombustibleCount the compraCombustibleCount to set
     */
    public void setCompraCombustibleCount(int compraCombustibleCount) {
        this.compraCombustibleCount = compraCombustibleCount;
    }

    /**
     * @return the comunaCount
     */
    public int getComunaCount() {
        return comunaCount;
    }

    /**
     * @param comunaCount the comunaCount to set
     */
    public void setComunaCount(int comunaCount) {
        this.comunaCount = comunaCount;
    }

    /**
     * @return the contratoUnidadCount
     */
    public int getContratoUnidadCount() {
        return contratoUnidadCount;
    }

    /**
     * @param contratoUnidadCount the contratoUnidadCount to set
     */
    public void setContratoUnidadCount(int contratoUnidadCount) {
        this.contratoUnidadCount = contratoUnidadCount;
    }

    /**
     * @return the controlCount
     */
    public int getControlCount() {
        return controlCount;
    }

    /**
     * @param controlCount the controlCount to set
     */
    public void setControlCount(int controlCount) {
        this.controlCount = controlCount;
    }

    /**
     * @return the controlAsistenciaCount
     */
    public int getControlAsistenciaCount() {
        return controlAsistenciaCount;
    }

    /**
     * @param controlAsistenciaCount the controlAsistenciaCount to set
     */
    public void setControlAsistenciaCount(int controlAsistenciaCount) {
        this.controlAsistenciaCount = controlAsistenciaCount;
    }

    /**
     * @return the controlHorarioServicioCount
     */
    public int getControlHorarioServicioCount() {
        return controlHorarioServicioCount;
    }

    /**
     * @param controlHorarioServicioCount the controlHorarioServicioCount to set
     */
    public void setControlHorarioServicioCount(int controlHorarioServicioCount) {
        this.controlHorarioServicioCount = controlHorarioServicioCount;
    }

    /**
     * @return the controlServicioCount
     */
    public int getControlServicioCount() {
        return controlServicioCount;
    }

    /**
     * @param controlServicioCount the controlServicioCount to set
     */
    public void setControlServicioCount(int controlServicioCount) {
        this.controlServicioCount = controlServicioCount;
    }

    /**
     * @return the ctvResumenCount
     */
    public int getCtvResumenCount() {
        return ctvResumenCount;
    }

    /**
     * @param ctvResumenCount the ctvResumenCount to set
     */
    public void setCtvResumenCount(int ctvResumenCount) {
        this.ctvResumenCount = ctvResumenCount;
    }

    /**
     * @return the cuentaBancariaCount
     */
    public int getCuentaBancariaCount() {
        return cuentaBancariaCount;
    }

    /**
     * @param cuentaBancariaCount the cuentaBancariaCount to set
     */
    public void setCuentaBancariaCount(int cuentaBancariaCount) {
        this.cuentaBancariaCount = cuentaBancariaCount;
    }

    /**
     * @return the cuentaBancoEmpresaCount
     */
    public int getCuentaBancoEmpresaCount() {
        return cuentaBancoEmpresaCount;
    }

    /**
     * @param cuentaBancoEmpresaCount the cuentaBancoEmpresaCount to set
     */
    public void setCuentaBancoEmpresaCount(int cuentaBancoEmpresaCount) {
        this.cuentaBancoEmpresaCount = cuentaBancoEmpresaCount;
    }

    /**
     * @return the cuentaBancoProcesoCount
     */
    public int getCuentaBancoProcesoCount() {
        return cuentaBancoProcesoCount;
    }

    /**
     * @param cuentaBancoProcesoCount the cuentaBancoProcesoCount to set
     */
    public void setCuentaBancoProcesoCount(int cuentaBancoProcesoCount) {
        this.cuentaBancoProcesoCount = cuentaBancoProcesoCount;
    }

    /**
     * @return the cuentaBancoTrabajadorCount
     */
    public int getCuentaBancoTrabajadorCount() {
        return cuentaBancoTrabajadorCount;
    }

    /**
     * @param cuentaBancoTrabajadorCount the cuentaBancoTrabajadorCount to set
     */
    public void setCuentaBancoTrabajadorCount(int cuentaBancoTrabajadorCount) {
        this.cuentaBancoTrabajadorCount = cuentaBancoTrabajadorCount;
    }

    /**
     * @return the departamentoCount
     */
    public int getDepartamentoCount() {
        return departamentoCount;
    }

    /**
     * @param departamentoCount the departamentoCount to set
     */
    public void setDepartamentoCount(int departamentoCount) {
        this.departamentoCount = departamentoCount;
    }

    /**
     * @return the descuentoLiquidacionCount
     */
    public int getDescuentoLiquidacionCount() {
        return descuentoLiquidacionCount;
    }

    /**
     * @param descuentoLiquidacionCount the descuentoLiquidacionCount to set
     */
    public void setDescuentoLiquidacionCount(int descuentoLiquidacionCount) {
        this.descuentoLiquidacionCount = descuentoLiquidacionCount;
    }

    /**
     * @return the descuentoTrabajadorCount
     */
    public int getDescuentoTrabajadorCount() {
        return descuentoTrabajadorCount;
    }

    /**
     * @param descuentoTrabajadorCount the descuentoTrabajadorCount to set
     */
    public void setDescuentoTrabajadorCount(int descuentoTrabajadorCount) {
        this.descuentoTrabajadorCount = descuentoTrabajadorCount;
    }

    /**
     * @return the despachoCount
     */
    public int getDespachoCount() {
        return despachoCount;
    }

    /**
     * @param despachoCount the despachoCount to set
     */
    public void setDespachoCount(int despachoCount) {
        this.despachoCount = despachoCount;
    }

    /**
     * @return the detalleBilletesCtvCount
     */
    public int getDetalleBilletesCtvCount() {
        return detalleBilletesCtvCount;
    }

    /**
     * @param detalleBilletesCtvCount the detalleBilletesCtvCount to set
     */
    public void setDetalleBilletesCtvCount(int detalleBilletesCtvCount) {
        this.detalleBilletesCtvCount = detalleBilletesCtvCount;
    }

    /**
     * @return the detalleCompraBoletoCount
     */
    public int getDetalleCompraBoletoCount() {
        return detalleCompraBoletoCount;
    }

    /**
     * @param detalleCompraBoletoCount the detalleCompraBoletoCount to set
     */
    public void setDetalleCompraBoletoCount(int detalleCompraBoletoCount) {
        this.detalleCompraBoletoCount = detalleCompraBoletoCount;
    }

    /**
     * @return the detalleIntervalosMensualCount
     */
    public int getDetalleIntervalosMensualCount() {
        return detalleIntervalosMensualCount;
    }

    /**
     * @param detalleIntervalosMensualCount the detalleIntervalosMensualCount to
     * set
     */
    public void setDetalleIntervalosMensualCount(int detalleIntervalosMensualCount) {
        this.detalleIntervalosMensualCount = detalleIntervalosMensualCount;
    }

    /**
     * @return the detalleMetalicoCtvCount
     */
    public int getDetalleMetalicoCtvCount() {
        return detalleMetalicoCtvCount;
    }

    /**
     * @param detalleMetalicoCtvCount the detalleMetalicoCtvCount to set
     */
    public void setDetalleMetalicoCtvCount(int detalleMetalicoCtvCount) {
        this.detalleMetalicoCtvCount = detalleMetalicoCtvCount;
    }

    /**
     * @return the detalleResumenRecaudacionCount
     */
    public int getDetalleResumenRecaudacionCount() {
        return detalleResumenRecaudacionCount;
    }

    /**
     * @param detalleResumenRecaudacionCount the detalleResumenRecaudacionCount
     * to set
     */
    public void setDetalleResumenRecaudacionCount(int detalleResumenRecaudacionCount) {
        this.detalleResumenRecaudacionCount = detalleResumenRecaudacionCount;
    }

    /**
     * @return the diaFestivoCount
     */
    public int getDiaFestivoCount() {
        return diaFestivoCount;
    }

    /**
     * @param diaFestivoCount the diaFestivoCount to set
     */
    public void setDiaFestivoCount(int diaFestivoCount) {
        this.diaFestivoCount = diaFestivoCount;
    }

    /**
     * @return the efectivoCajaCount
     */
    public int getEfectivoCajaCount() {
        return efectivoCajaCount;
    }

    /**
     * @param efectivoCajaCount the efectivoCajaCount to set
     */
    public void setEfectivoCajaCount(int efectivoCajaCount) {
        this.efectivoCajaCount = efectivoCajaCount;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the egresoCount
     */
    public int getEgresoCount() {
        return egresoCount;
    }

    /**
     * @param egresoCount the egresoCount to set
     */
    public void setEgresoCount(int egresoCount) {
        this.egresoCount = egresoCount;
    }

    /**
     * @return the egresoBusCount
     */
    public int getEgresoBusCount() {
        return egresoBusCount;
    }

    /**
     * @param egresoBusCount the egresoBusCount to set
     */
    public void setEgresoBusCount(int egresoBusCount) {
        this.egresoBusCount = egresoBusCount;
    }

    /**
     * @return the egresoCajaTerminalCount
     */
    public int getEgresoCajaRecaudacionCount() {
        return egresoCajaRecaudacionCount;
    }

    /**
     * @param egresoCajaTerminalCount the egresoCajaTerminalCount to set
     */
    public void setEgresoCajaRecaudacionCount(int egresoCajaTerminalCount) {
        this.egresoCajaRecaudacionCount = egresoCajaTerminalCount;
    }

    /**
     * @return the egresoFlotaCount
     */
    public int getEgresoFlotaCount() {
        return egresoFlotaCount;
    }

    /**
     * @param egresoFlotaCount the egresoFlotaCount to set
     */
    public void setEgresoFlotaCount(int egresoFlotaCount) {
        this.egresoFlotaCount = egresoFlotaCount;
    }

    /**
     * @return the egresoGuiaCount
     */
    public int getEgresoGuiaCount() {
        return egresoGuiaCount;
    }

    /**
     * @param egresoGuiaCount the egresoGuiaCount to set
     */
    public void setEgresoGuiaCount(int egresoGuiaCount) {
        this.egresoGuiaCount = egresoGuiaCount;
    }

    /**
     * @return the egresoProcesoCount
     */
    public int getEgresoProcesoRecaudacionCount() {
        return egresoProcesoRecaudacionCount;
    }

    /**
     * @param egresoProcesoCount the egresoProcesoCount to set
     */
    public void setEgresoProcesoRecaudacionCount(int egresoProcesoCount) {
        this.egresoProcesoRecaudacionCount = egresoProcesoCount;
    }

    /**
     * @return the egresoRecaudacionCount
     */
    public int getEgresoRecaudacionCount() {
        return egresoRecaudacionCount;
    }

    /**
     * @param egresoRecaudacionCount the egresoRecaudacionCount to set
     */
    public void setEgresoRecaudacionCount(int egresoRecaudacionCount) {
        this.egresoRecaudacionCount = egresoRecaudacionCount;
    }

    /**
     * @return the empresaCount
     */
    public int getEmpresaCount() {
        return empresaCount;
    }

    /**
     * @param empresaCount the empresaCount to set
     */
    public void setEmpresaCount(int empresaCount) {
        this.empresaCount = empresaCount;
    }

    /**
     * @return the estadoBusCount
     */
    public int getEstadoBusCount() {
        return estadoBusCount;
    }

    /**
     * @param estadoBusCount the estadoBusCount to set
     */
    public void setEstadoBusCount(int estadoBusCount) {
        this.estadoBusCount = estadoBusCount;
    }

    /**
     * @return the estadoCivilCount
     */
    public int getEstadoCivilCount() {
        return estadoCivilCount;
    }

    /**
     * @param estadoCivilCount the estadoCivilCount to set
     */
    public void setEstadoCivilCount(int estadoCivilCount) {
        this.estadoCivilCount = estadoCivilCount;
    }

    /**
     * @return the estadoGuiaCount
     */
    public int getEstadoGuiaCount() {
        return estadoGuiaCount;
    }

    /**
     * @param estadoGuiaCount the estadoGuiaCount to set
     */
    public void setEstadoGuiaCount(int estadoGuiaCount) {
        this.estadoGuiaCount = estadoGuiaCount;
    }

    /**
     * @return the fechaCount
     */
    public int getFechaCount() {
        return fechaCount;
    }

    /**
     * @param fechaCount the fechaCount to set
     */
    public void setFechaCount(int fechaCount) {
        this.fechaCount = fechaCount;
    }

    /**
     * @return the feriadoLegalCount
     */
    public int getFeriadoLegalCount() {
        return feriadoLegalCount;
    }

    /**
     * @param feriadoLegalCount the feriadoLegalCount to set
     */
    public void setFeriadoLegalCount(int feriadoLegalCount) {
        this.feriadoLegalCount = feriadoLegalCount;
    }

    /**
     * @return the finiquitoRelacionLaboralCount
     */
    public int getFiniquitoRelacionLaboralCount() {
        return finiquitoRelacionLaboralCount;
    }

    /**
     * @param finiquitoRelacionLaboralCount the finiquitoRelacionLaboralCount to
     * set
     */
    public void setFiniquitoRelacionLaboralCount(int finiquitoRelacionLaboralCount) {
        this.finiquitoRelacionLaboralCount = finiquitoRelacionLaboralCount;
    }

    /**
     * @return the flotaCount
     */
    public int getFlotaCount() {
        return flotaCount;
    }

    /**
     * @param flotaCount the flotaCount to set
     */
    public void setFlotaCount(int flotaCount) {
        this.flotaCount = flotaCount;
    }

    /**
     * @return the formaPagoCount
     */
    public int getFormaPagoCount() {
        return formaPagoCount;
    }

    /**
     * @param formaPagoCount the formaPagoCount to set
     */
    public void setFormaPagoCount(int formaPagoCount) {
        this.formaPagoCount = formaPagoCount;
    }

    /**
     * @return the frecuenciaServicioCount
     */
    public int getFrecuenciaServicioCount() {
        return frecuenciaServicioCount;
    }

    /**
     * @param frecuenciaServicioCount the frecuenciaServicioCount to set
     */
    public void setFrecuenciaServicioCount(int frecuenciaServicioCount) {
        this.frecuenciaServicioCount = frecuenciaServicioCount;
    }

    /**
     * @return the gastoAdministracionMensualCount
     */
    public int getGastoAdministracionMensualCount() {
        return gastoAdministracionMensualCount;
    }

    /**
     * @param gastoAdministracionMensualCount the
     * gastoAdministracionMensualCount to set
     */
    public void setGastoAdministracionMensualCount(int gastoAdministracionMensualCount) {
        this.gastoAdministracionMensualCount = gastoAdministracionMensualCount;
    }

    /**
     * @return the guiaCount
     */
    public int getGuiaCount() {
        return guiaCount;
    }

    /**
     * @param guiaCount the guiaCount to set
     */
    public void setGuiaCount(int guiaCount) {
        this.guiaCount = guiaCount;
    }

    /**
     * @return the grupoServicioCount
     */
    public int getGrupoServicioCount() {
        return grupoServicioCount;
    }

    /**
     * @param grupoServicioCount the grupoServicioCount to set
     */
    public void setGrupoServicioCount(int grupoServicioCount) {
        this.grupoServicioCount = grupoServicioCount;
    }

    /**
     * @return the haberLiquidacionCount
     */
    public int getHaberLiquidacionCount() {
        return haberLiquidacionCount;
    }

    /**
     * @param haberLiquidacionCount the haberLiquidacionCount to set
     */
    public void setHaberLiquidacionCount(int haberLiquidacionCount) {
        this.haberLiquidacionCount = haberLiquidacionCount;
    }

    /**
     * @return the haberTrabajadorCount
     */
    public int getHaberTrabajadorCount() {
        return haberTrabajadorCount;
    }

    /**
     * @param haberTrabajadorCount the haberTrabajadorCount to set
     */
    public void setHaberTrabajadorCount(int haberTrabajadorCount) {
        this.haberTrabajadorCount = haberTrabajadorCount;
    }

    /**
     * @return the horaExtraTrabajadorCount
     */
    public int getHoraExtraTrabajadorCount() {
        return horaExtraTrabajadorCount;
    }

    /**
     * @param horaExtraTrabajadorCount the horaExtraTrabajadorCount to set
     */
    public void setHoraExtraTrabajadorCount(int horaExtraTrabajadorCount) {
        this.horaExtraTrabajadorCount = horaExtraTrabajadorCount;
    }

    /**
     * @return the horarioJornadaCount
     */
    public int getHorarioJornadaCount() {
        return horarioJornadaCount;
    }

    /**
     * @param horarioJornadaCount the horarioJornadaCount to set
     */
    public void setHorarioJornadaCount(int horarioJornadaCount) {
        this.horarioJornadaCount = horarioJornadaCount;
    }

    /**
     * @return the horarioServicioCount
     */
    public int getHorarioServicioCount() {
        return horarioServicioCount;
    }

    /**
     * @param horarioServicioCount the horarioServicioCount to set
     */
    public void setHorarioServicioCount(int horarioServicioCount) {
        this.horarioServicioCount = horarioServicioCount;
    }

    /**
     * @return the impuestoSegundaCategoriaCount
     */
    public int getImpuestoSegundaCategoriaCount() {
        return impuestoSegundaCategoriaCount;
    }

    /**
     * @param impuestoSegundaCategoriaCount the impuestoSegundaCategoriaCount to
     * set
     */
    public void setImpuestoSegundaCategoriaCount(int impuestoSegundaCategoriaCount) {
        this.impuestoSegundaCategoriaCount = impuestoSegundaCategoriaCount;
    }

    /**
     * @return the institucionApvCount
     */
    public int getInstitucionApvCount() {
        return institucionApvCount;
    }

    /**
     * @param institucionApvCount the institucionApvCount to set
     */
    public void setInstitucionApvCount(int institucionApvCount) {
        this.institucionApvCount = institucionApvCount;
    }

    /**
     * @return the institucionPrevisionCount
     */
    public int getInstitucionPrevisionCount() {
        return institucionPrevisionCount;
    }

    /**
     * @param institucionPrevisionCount the institucionPrevisionCount to set
     */
    public void setInstitucionPrevisionCount(int institucionPrevisionCount) {
        this.institucionPrevisionCount = institucionPrevisionCount;
    }

    /**
     * @return the institucionSaludCount
     */
    public int getInstitucionSaludCount() {
        return institucionSaludCount;
    }

    /**
     * @param institucionSaludCount the institucionSaludCount to set
     */
    public void setInstitucionSaludCount(int institucionSaludCount) {
        this.institucionSaludCount = institucionSaludCount;
    }

    /**
     * @return the intervalosAdministracionCount
     */
    public int getIntervalosAdministracionCount() {
        return intervalosAdministracionCount;
    }

    /**
     * @param intervalosAdministracionCount the intervalosAdministracionCount to
     * set
     */
    public void setIntervalosAdministracionCount(int intervalosAdministracionCount) {
        this.intervalosAdministracionCount = intervalosAdministracionCount;
    }

    /**
     * @return the inventarioCajaCount
     */
    public int getInventarioCajaCount() {
        return inventarioCajaCount;
    }

    /**
     * @param inventarioCajaCount the inventarioCajaCount to set
     */
    public void setInventarioCajaCount(int inventarioCajaCount) {
        this.inventarioCajaCount = inventarioCajaCount;
    }

    /**
     * @return the inventarioInternoCount
     */
    public int getInventarioInternoCount() {
        return inventarioInternoCount;
    }

    /**
     * @param inventarioInternoCount the inventarioInternoCount to set
     */
    public void setInventarioInternoCount(int inventarioInternoCount) {
        this.inventarioInternoCount = inventarioInternoCount;
    }

    /**
     * @return the jornadaLaboralCount
     */
    public int getJornadaLaboralCount() {
        return jornadaLaboralCount;
    }

    /**
     * @param jornadaLaboralCount the jornadaLaboralCount to set
     */
    public void setJornadaLaboralCount(int jornadaLaboralCount) {
        this.jornadaLaboralCount = jornadaLaboralCount;
    }

    /**
     * @return the jornadaTrabajadorCount
     */
    public int getJornadaTrabajadorCount() {
        return jornadaTrabajadorCount;
    }

    /**
     * @param jornadaTrabajadorCount the jornadaTrabajadorCount to set
     */
    public void setJornadaTrabajadorCount(int jornadaTrabajadorCount) {
        this.jornadaTrabajadorCount = jornadaTrabajadorCount;
    }

    /**
     * @return the licenciaMedicaCount
     */
    public int getLicenciaMedicaCount() {
        return licenciaMedicaCount;
    }

    /**
     * @param licenciaMedicaCount the licenciaMedicaCount to set
     */
    public void setLicenciaMedicaCount(int licenciaMedicaCount) {
        this.licenciaMedicaCount = licenciaMedicaCount;
    }

    /**
     * @return the liquidacionEmpresaCount
     */
    public int getLiquidacionEmpresaCount() {
        return liquidacionEmpresaCount;
    }

    /**
     * @param liquidacionEmpresaCount the liquidacionEmpresaCount to set
     */
    public void setLiquidacionEmpresaCount(int liquidacionEmpresaCount) {
        this.liquidacionEmpresaCount = liquidacionEmpresaCount;
    }

    /**
     * @return the liquidacionSueldoCount
     */
    public int getLiquidacionSueldoCount() {
        return liquidacionSueldoCount;
    }

    /**
     * @param liquidacionSueldoCount the liquidacionSueldoCount to set
     */
    public void setLiquidacionSueldoCount(int liquidacionSueldoCount) {
        this.liquidacionSueldoCount = liquidacionSueldoCount;
    }

    /**
     * @return the logCount
     */
    public int getLogCount() {
        return logCount;
    }

    /**
     * @param logCount the logCount to set
     */
    public void setLogCount(int logCount) {
        this.logCount = logCount;
    }

    /**
     * @return the marcaBusCount
     */
    public int getMarcaBusCount() {
        return marcaBusCount;
    }

    /**
     * @param marcaBusCount the marcaBusCount to set
     */
    public void setMarcaBusCount(int marcaBusCount) {
        this.marcaBusCount = marcaBusCount;
    }

    /**
     * @return the metodoPagoCount
     */
    public int getMetodoPagoCount() {
        return metodoPagoCount;
    }

    /**
     * @param metodoPagoCount the metodoPagoCount to set
     */
    public void setMetodoPagoCount(int metodoPagoCount) {
        this.metodoPagoCount = metodoPagoCount;
    }

    /**
     * @return the modeloMarcaBusCount
     */
    public int getModeloMarcaBusCount() {
        return modeloMarcaBusCount;
    }

    /**
     * @param modeloMarcaBusCount the modeloMarcaBusCount to set
     */
    public void setModeloMarcaBusCount(int modeloMarcaBusCount) {
        this.modeloMarcaBusCount = modeloMarcaBusCount;
    }

    /**
     * @return the monedaPactadaInstitucionSaludCount
     */
    public int getMonedaPactadaInstitucionSaludCount() {
        return monedaPactadaInstitucionSaludCount;
    }

    /**
     * @param monedaPactadaInstitucionSaludCount the
     * monedaPactadaInstitucionSaludCount to set
     */
    public void setMonedaPactadaInstitucionSaludCount(int monedaPactadaInstitucionSaludCount) {
        this.monedaPactadaInstitucionSaludCount = monedaPactadaInstitucionSaludCount;
    }

    /**
     * @return the mutualCount
     */
    public int getMutualCount() {
        return mutualCount;
    }

    /**
     * @param mutualCount the mutualCount to set
     */
    public void setMutualCount(int mutualCount) {
        this.mutualCount = mutualCount;
    }

    /**
     * @return the numeralSurtidorCount
     */
    public int getNumeralSurtidorCount() {
        return numeralSurtidorCount;
    }

    /**
     * @param numeralSurtidorCount the numeralSurtidorCount to set
     */
    public void setNumeralSurtidorCount(int numeralSurtidorCount) {
        this.numeralSurtidorCount = numeralSurtidorCount;
    }

    /**
     * @return the observacionTrabajadorCount
     */
    public int getObservacionTrabajadorCount() {
        return observacionTrabajadorCount;
    }

    /**
     * @param observacionTrabajadorCount the observacionTrabajadorCount to set
     */
    public void setObservacionTrabajadorCount(int observacionTrabajadorCount) {
        this.observacionTrabajadorCount = observacionTrabajadorCount;
    }

    /**
     * @return the operadorTransporteCount
     */
    public int getOperadorTransporteCount() {
        return operadorTransporteCount;
    }

    /**
     * @param operadorTransporteCount the operadorTransporteCount to set
     */
    public void setOperadorTransporteCount(int operadorTransporteCount) {
        this.operadorTransporteCount = operadorTransporteCount;
    }

    /**
     * @return the parametroCesantiaCount
     */
    public int getParametroCesantiaCount() {
        return parametroCesantiaCount;
    }

    /**
     * @param parametroCesantiaCount the parametroCesantiaCount to set
     */
    public void setParametroCesantiaCount(int parametroCesantiaCount) {
        this.parametroCesantiaCount = parametroCesantiaCount;
    }

    /**
     * @return the parametroInterpolacionCount
     */
    public int getParametroInterpolacionCount() {
        return parametroInterpolacionCount;
    }

    /**
     * @param parametroInterpolacionCount the parametroInterpolacionCount to set
     */
    public void setParametroInterpolacionCount(int parametroInterpolacionCount) {
        this.parametroInterpolacionCount = parametroInterpolacionCount;
    }

    /**
     * @return the parentescoCargaCount
     */
    public int getParentescoCargaCount() {
        return parentescoCargaCount;
    }

    /**
     * @param parentescoCargaCount the parentescoCargaCount to set
     */
    public void setParentescoCargaCount(int parentescoCargaCount) {
        this.parentescoCargaCount = parentescoCargaCount;
    }

    /**
     * @return the periodoFrecuenciaCount
     */
    public int getPeriodoFrecuenciaCount() {
        return periodoFrecuenciaCount;
    }

    /**
     * @param periodoFrecuenciaCount the periodoFrecuenciaCount to set
     */
    public void setPeriodoFrecuenciaCount(int periodoFrecuenciaCount) {
        this.periodoFrecuenciaCount = periodoFrecuenciaCount;
    }

    /**
     * @return the precioCombustibleCount
     */
    public int getPrecioCombustibleCount() {
        return precioCombustibleCount;
    }

    /**
     * @param precioCombustibleCount the precioCombustibleCount to set
     */
    public void setPrecioCombustibleCount(int precioCombustibleCount) {
        this.precioCombustibleCount = precioCombustibleCount;
    }

    /**
     * @return the privilegioCount
     */
    public int getPrivilegioCount() {
        return privilegioCount;
    }

    /**
     * @param privilegioCount the privilegioCount to set
     */
    public void setPrivilegioCount(int privilegioCount) {
        this.privilegioCount = privilegioCount;
    }

    /**
     * @return the procesoRecaudacionCount
     */
    public int getProcesoRecaudacionCount() {
        return procesoRecaudacionCount;
    }

    /**
     * @param procesoRecaudacionCount the procesoRecaudacionCount to set
     */
    public void setProcesoRecaudacionCount(int procesoRecaudacionCount) {
        this.procesoRecaudacionCount = procesoRecaudacionCount;
    }

    /**
     * @return the reconocimientoDeudaCount
     */
    public int getReconocimientoDeudaCount() {
        return reconocimientoDeudaCount;
    }

    /**
     * @param reconocimientoDeudaCount the reconocimientoDeudaCount to set
     */
    public void setReconocimientoDeudaCount(int reconocimientoDeudaCount) {
        this.reconocimientoDeudaCount = reconocimientoDeudaCount;
    }

    /**
     * @return the regionCount
     */
    public int getRegionCount() {
        return regionCount;
    }

    /**
     * @param regionCount the regionCount to set
     */
    public void setRegionCount(int regionCount) {
        this.regionCount = regionCount;
    }

    /**
     * @return the registroMinutoCount
     */
    public int getRegistroMinutoCount() {
        return registroMinutoCount;
    }

    /**
     * @param registroMinutoCount the registroMinutoCount to set
     */
    public void setRegistroMinutoCount(int registroMinutoCount) {
        this.registroMinutoCount = registroMinutoCount;
    }

    /**
     * @return the relacionLaboralCount
     */
    public int getRelacionLaboralCount() {
        return relacionLaboralCount;
    }

    /**
     * @param relacionLaboralCount the relacionLaboralCount to set
     */
    public void setRelacionLaboralCount(int relacionLaboralCount) {
        this.relacionLaboralCount = relacionLaboralCount;
    }

    /**
     * @return the representanteEmpresaCount
     */
    public int getRepresentanteEmpresaCount() {
        return representanteEmpresaCount;
    }

    /**
     * @param representanteEmpresaCount the representanteEmpresaCount to set
     */
    public void setRepresentanteEmpresaCount(int representanteEmpresaCount) {
        this.representanteEmpresaCount = representanteEmpresaCount;
    }

    /**
     * @return the representanteLegalCount
     */
    public int getRepresentanteLegalCount() {
        return representanteLegalCount;
    }

    /**
     * @param representanteLegalCount the representanteLegalCount to set
     */
    public void setRepresentanteLegalCount(int representanteLegalCount) {
        this.representanteLegalCount = representanteLegalCount;
    }

    /**
     * @return the resumenRecaudacionCount
     */
    public int getResumenRecaudacionCount() {
        return resumenRecaudacionCount;
    }

    /**
     * @param resumenRecaudacionCount the resumenRecaudacionCount to set
     */
    public void setResumenRecaudacionCount(int resumenRecaudacionCount) {
        this.resumenRecaudacionCount = resumenRecaudacionCount;
    }

    /**
     * @return the rolCount
     */
    public int getRolCount() {
        return rolCount;
    }

    /**
     * @param rolCount the rolCount to set
     */
    public void setRolCount(int rolCount) {
        this.rolCount = rolCount;
    }

    /**
     * @return the rolPrivilegioCount
     */
    public int getRolPrivilegioCount() {
        return rolPrivilegioCount;
    }

    /**
     * @param rolPrivilegioCount the rolPrivilegioCount to set
     */
    public void setRolPrivilegioCount(int rolPrivilegioCount) {
        this.rolPrivilegioCount = rolPrivilegioCount;
    }

    /**
     * @return the serieBoletoGuiaCount
     */
    public int getSerieBoletoGuiaCount() {
        return serieBoletoGuiaCount;
    }

    /**
     * @param serieBoletoGuiaCount the serieBoletoGuiaCount to set
     */
    public void setSerieBoletoGuiaCount(int serieBoletoGuiaCount) {
        this.serieBoletoGuiaCount = serieBoletoGuiaCount;
    }

    /**
     * @return the servicioCount
     */
    public int getServicioCount() {
        return servicioCount;
    }

    /**
     * @param servicioCount the servicioCount to set
     */
    public void setServicioCount(int servicioCount) {
        this.servicioCount = servicioCount;
    }

    /**
     * @return the sindicatoCount
     */
    public int getSindicatoCount() {
        return sindicatoCount;
    }

    /**
     * @param sindicatoCount the sindicatoCount to set
     */
    public void setSindicatoCount(int sindicatoCount) {
        this.sindicatoCount = sindicatoCount;
    }

    /**
     * @return the sueldoBaseCount
     */
    public int getSueldoBaseCount() {
        return sueldoBaseCount;
    }

    /**
     * @param sueldoBaseCount the sueldoBaseCount to set
     */
    public void setSueldoBaseCount(int sueldoBaseCount) {
        this.sueldoBaseCount = sueldoBaseCount;
    }

    /**
     * @return the surtidorCount
     */
    public int getSurtidorCount() {
        return surtidorCount;
    }

    /**
     * @param surtidorCount the surtidorCount to set
     */
    public void setSurtidorCount(int surtidorCount) {
        this.surtidorCount = surtidorCount;
    }

    /**
     * @return the tarifaGrupoServicioCount
     */
    public int getTarifaGrupoServicioCount() {
        return tarifaGrupoServicioCount;
    }

    /**
     * @param tarifaGrupoServicioCount the tarifaServicioCount to set
     */
    public void setTarifaServicioCount(int tarifaGrupoServicioCount) {
        this.tarifaGrupoServicioCount = tarifaGrupoServicioCount;
    }

    /**
     * @return the terminalCount
     */
    public int getTerminalCount() {
        return terminalCount;
    }

    /**
     * @param terminalCount the terminalCount to set
     */
    public void setTerminalCount(int terminalCount) {
        this.terminalCount = terminalCount;
    }

    /**
     * @return the tipoAbonoCount
     */
    public int getTipoAbonoCount() {
        return tipoAbonoCount;
    }

    /**
     * @param tipoAbonoCount the tipoAbonoCount to set
     */
    public void setTipoAbonoCount(int tipoAbonoCount) {
        this.tipoAbonoCount = tipoAbonoCount;
    }

    /**
     * @return the tipoCargaFamiliarCount
     */
    public int getTipoCargaFamiliarCount() {
        return tipoCargaFamiliarCount;
    }

    /**
     * @param tipoCargaFamiliarCount the tipoCargaFamiliarCount to set
     */
    public void setTipoCargaFamiliarCount(int tipoCargaFamiliarCount) {
        this.tipoCargaFamiliarCount = tipoCargaFamiliarCount;
    }

    /**
     * @return the tipoCargoCount
     */
    public int getTipoCargoCount() {
        return tipoCargoCount;
    }

    /**
     * @param tipoCargoCount the tipoCargoCount to set
     */
    public void setTipoCargoCount(int tipoCargoCount) {
        this.tipoCargoCount = tipoCargoCount;
    }

    /**
     * @return the tipoCombustibleCount
     */
    public int getTipoCombustibleCount() {
        return tipoCombustibleCount;
    }

    /**
     * @param tipoCombustibleCount the tipoCombustibleCount to set
     */
    public void setTipoCombustibleCount(int tipoCombustibleCount) {
        this.tipoCombustibleCount = tipoCombustibleCount;
    }

    /**
     * @return the tipoContratoCount
     */
    public int getTipoContratoCount() {
        return tipoContratoCount;
    }

    /**
     * @param tipoContratoCount the tipoContratoCount to set
     */
    public void setTipoContratoCount(int tipoContratoCount) {
        this.tipoContratoCount = tipoContratoCount;
    }

    /**
     * @return the tipoControlCount
     */
    public int getTipoControlCount() {
        return tipoControlCount;
    }

    /**
     * @param tipoControlCount the tipoControlCount to set
     */
    public void setTipoControlCount(int tipoControlCount) {
        this.tipoControlCount = tipoControlCount;
    }

    /**
     * @return the tipoCotizacionTrabajadorCount
     */
    public int getTipoCotizacionTrabajadorCount() {
        return tipoCotizacionTrabajadorCount;
    }

    /**
     * @param tipoCotizacionTrabajadorCount the tipoCotizacionTrabajadorCount to
     * set
     */
    public void setTipoCotizacionTrabajadorCount(int tipoCotizacionTrabajadorCount) {
        this.tipoCotizacionTrabajadorCount = tipoCotizacionTrabajadorCount;
    }

    /**
     * @return the tipoCuentaBancoCount
     */
    public int getTipoCuentaBancoCount() {
        return tipoCuentaBancoCount;
    }

    /**
     * @param tipoCuentaBancoCount the tipoCuentaBancoCount to set
     */
    public void setTipoCuentaBancoCount(int tipoCuentaBancoCount) {
        this.tipoCuentaBancoCount = tipoCuentaBancoCount;
    }

    /**
     * @return the tipoDemandaCount
     */
    public int getTipoDemandaCount() {
        return tipoDemandaCount;
    }

    /**
     * @param tipoDemandaCount the tipoDemandaCount to set
     */
    public void setTipoDemandaCount(int tipoDemandaCount) {
        this.tipoDemandaCount = tipoDemandaCount;
    }

    /**
     * @return the tipoDescuentoTrabajadorCount
     */
    public int getTipoDescuentoTrabajadorCount() {
        return tipoDescuentoTrabajadorCount;
    }

    /**
     * @param tipoDescuentoTrabajadorCount the tipoDescuentoTrabajadorCount to
     * set
     */
    public void setTipoDescuentoTrabajadorCount(int tipoDescuentoTrabajadorCount) {
        this.tipoDescuentoTrabajadorCount = tipoDescuentoTrabajadorCount;
    }

    /**
     * @return the tipoDiaFrecuenciaCount
     */
    public int getTipoDiaFrecuenciaCount() {
        return tipoDiaFrecuenciaCount;
    }

    /**
     * @param tipoDiaFrecuenciaCount the tipoDiaFrecuenciaCount to set
     */
    public void setTipoDiaFrecuenciaCount(int tipoDiaFrecuenciaCount) {
        this.tipoDiaFrecuenciaCount = tipoDiaFrecuenciaCount;
    }

    /**
     * @return the tipoEstacionalidadCount
     */
    public int getTipoEstacionalidadCount() {
        return tipoEstacionalidadCount;
    }

    /**
     * @param tipoEstacionalidadCount the tipoEstacionalidadCount to set
     */
    public void setTipoEstacionalidadCount(int tipoEstacionalidadCount) {
        this.tipoEstacionalidadCount = tipoEstacionalidadCount;
    }

    /**
     * @return the tipoHaberTrabajadorCount
     */
    public int getTipoHaberTrabajadorCount() {
        return tipoHaberTrabajadorCount;
    }

    /**
     * @param tipoHaberTrabajadorCount the tipoHaberTrabajadorCount to set
     */
    public void setTipoHaberTrabajadorCount(int tipoHaberTrabajadorCount) {
        this.tipoHaberTrabajadorCount = tipoHaberTrabajadorCount;
    }

    /**
     * @return the tipoInstitucionApvCount
     */
    public int getTipoInstitucionApvCount() {
        return tipoInstitucionApvCount;
    }

    /**
     * @param tipoInstitucionApvCount the tipoInstitucionApvCount to set
     */
    public void setTipoInstitucionApvCount(int tipoInstitucionApvCount) {
        this.tipoInstitucionApvCount = tipoInstitucionApvCount;
    }

    /**
     * @return the tipoLineaCount
     */
    public int getTipoLineaCount() {
        return tipoLineaCount;
    }

    /**
     * @param tipoLineaCount the tipoLineaCount to set
     */
    public void setTipoLineaCount(int tipoLineaCount) {
        this.tipoLineaCount = tipoLineaCount;
    }

    /**
     * @return the tipoMovimientoPersonalCount
     */
    public int getTipoMovimientoPersonalCount() {
        return tipoMovimientoPersonalCount;
    }

    /**
     * @param tipoMovimientoPersonalCount the tipoMovimientoPersonalCount to set
     */
    public void setTipoMovimientoPersonalCount(int tipoMovimientoPersonalCount) {
        this.tipoMovimientoPersonalCount = tipoMovimientoPersonalCount;
    }

    /**
     * @return the tipoNominaCount
     */
    public int getTipoNominaCount() {
        return tipoNominaCount;
    }

    /**
     * @param tipoNominaCount the tipoNominaCount to set
     */
    public void setTipoNominaCount(int tipoNominaCount) {
        this.tipoNominaCount = tipoNominaCount;
    }

    /**
     * @return the tipoObservacionCount
     */
    public int getTipoObservacionCount() {
        return tipoObservacionCount;
    }

    /**
     * @param tipoObservacionCount the tipoObservacionCount to set
     */
    public void setTipoObservacionCount(int tipoObservacionCount) {
        this.tipoObservacionCount = tipoObservacionCount;
    }

    /**
     * @return the tipoTrabajadorCount
     */
    public int getTipoTrabajadorCount() {
        return tipoTrabajadorCount;
    }

    /**
     * @param tipoTrabajadorCount the tipoTrabajadorCount to set
     */
    public void setTipoTrabajadorCount(int tipoTrabajadorCount) {
        this.tipoTrabajadorCount = tipoTrabajadorCount;
    }

    /**
     * @return the topeCount
     */
    public int getTopeCount() {
        return topeCount;
    }

    /**
     * @param topeCount the topeCount to set
     */
    public void setTopeCount(int topeCount) {
        this.topeCount = topeCount;
    }

    /**
     * @return the trabajadorCount
     */
    public int getTrabajadorCount() {
        return trabajadorCount;
    }

    /**
     * @param trabajadorCount the trabajadorCount to set
     */
    public void setTrabajadorCount(int trabajadorCount) {
        this.trabajadorCount = trabajadorCount;
    }

    /**
     * @return the trabajadorAdicionalSaludCount
     */
    public int getTrabajadorAdicionalSaludCount() {
        return trabajadorAdicionalSaludCount;
    }

    /**
     * @param trabajadorAdicionalSaludCount the trabajadorAdicionalSaludCount to
     * set
     */
    public void setTrabajadorAdicionalSaludCount(int trabajadorAdicionalSaludCount) {
        this.trabajadorAdicionalSaludCount = trabajadorAdicionalSaludCount;
    }

    /**
     * @return the unidadNegocioCount
     */
    public int getUnidadNegocioCount() {
        return unidadNegocioCount;
    }

    /**
     * @param unidadNegocioCount the unidadNegocioCount to set
     */
    public void setUnidadNegocioCount(int unidadNegocioCount) {
        this.unidadNegocioCount = unidadNegocioCount;
    }

    /**
     * @return the usuarioCount
     */
    public int getUsuarioCount() {
        return usuarioCount;
    }

    /**
     * @param usuarioCount the usuarioCount to set
     */
    public void setUsuarioCount(int usuarioCount) {
        this.usuarioCount = usuarioCount;
    }

    /**
     * @return the valorFijoCount
     */
    public int getValorFijoCount() {
        return valorFijoCount;
    }

    /**
     * @param valorFijoCount the valorFijoCount to set
     */
    public void setValorFijoCount(int valorFijoCount) {
        this.valorFijoCount = valorFijoCount;
    }

    /**
     * @return the valorMinutoCount
     */
    public int getValorMinutoCount() {
        return valorMinutoCount;
    }

    /**
     * @param valorMinutoCount the valorMinutoCount to set
     */
    public void setValorMinutoCount(int valorMinutoCount) {
        this.valorMinutoCount = valorMinutoCount;
    }

    /**
     * @return the valorRolloUnidadCount
     */
    public int getValorRolloUnidadCount() {
        return valorRolloUnidadCount;
    }

    /**
     * @param valorRolloUnidadCount the valorRolloUnidadCount to set
     */
    public void setValorRolloUnidadCount(int valorRolloUnidadCount) {
        this.valorRolloUnidadCount = valorRolloUnidadCount;
    }

    /**
     * @return the valorUfCount
     */
    public int getValorUfCount() {
        return valorUfCount;
    }

    /**
     * @param valorUfCount the valorUfCount to set
     */
    public void setValorUfCount(int valorUfCount) {
        this.valorUfCount = valorUfCount;
    }

    /**
     * @return the valorUtmCount
     */
    public int getValorUtmCount() {
        return valorUtmCount;
    }

    /**
     * @param valorUtmCount the valorUtmCount to set
     */
    public void setValorUtmCount(int valorUtmCount) {
        this.valorUtmCount = valorUtmCount;
    }

    /**
     * @return the ventaBoletoCount
     */
    public int getVentaBoletoCount() {
        return ventaBoletoCount;
    }

    /**
     * @param ventaBoletoCount the ventaBoletoCount to set
     */
    public void setVentaBoletoCount(int ventaBoletoCount) {
        this.ventaBoletoCount = ventaBoletoCount;
    }

    /**
     * @return the ventaCombustibleCount
     */
    public int getVentaCombustibleCount() {
        return ventaCombustibleCount;
    }

    /**
     * @param ventaCombustibleCount the ventaCombustibleCount to set
     */
    public void setVentaCombustibleCount(int ventaCombustibleCount) {
        this.ventaCombustibleCount = ventaCombustibleCount;
    }

}
