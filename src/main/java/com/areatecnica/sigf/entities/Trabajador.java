/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "trabajador", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trabajador.findAll", query = "SELECT t FROM Trabajador t")
    , @NamedQuery(name = "Trabajador.findAllByCuenta", query = "SELECT t FROM Trabajador t WHERE t.trabajadorIdCuenta =:idCuenta ORDER BY t.trabajadorCodigo ASC")
    , @NamedQuery(name = "Trabajador.findDefaultTrabajador", query = "SELECT t FROM Trabajador t WHERE t.trabajadorCodigo =:trabajadorCodigo AND t.trabajadorIdCuenta =:trabajadorIdCuenta")
    , @NamedQuery(name = "Trabajador.findByTrabajadorId", query = "SELECT t FROM Trabajador t WHERE t.trabajadorId = :trabajadorId")
    , @NamedQuery(name = "Trabajador.findMaxCuenta", query = "SELECT t FROM Trabajador t WHERE t.trabajadorIdCuenta = :trabajadorIdCuenta ORDER BY t.trabajadorCodigo DESC")
    , @NamedQuery(name = "Trabajador.findByTrabajadorIdTerminal", query = "SELECT t FROM Trabajador t WHERE t.trabajadorIdTerminal = :trabajadorIdTerminal")
    , @NamedQuery(name = "Trabajador.findByTrabajadorRutAndCuenta", query = "SELECT t FROM Trabajador t WHERE t.trabajadorRut = :trabajadorRut AND t.trabajadorIdCuenta =:trabajadorIdCuenta")
    , @NamedQuery(name = "Trabajador.findByTrabajadorFonasa", query = "SELECT t FROM Trabajador t WHERE t.trabajadorFonasa = :trabajadorFonasa")
    , @NamedQuery(name = "Trabajador.findByTrabajadorJubilado", query = "SELECT t FROM Trabajador t WHERE t.trabajadorJubilado = :trabajadorJubilado")
    , @NamedQuery(name = "Trabajador.findByTrabajadorIps", query = "SELECT t FROM Trabajador t WHERE t.trabajadorIps = :trabajadorIps")
    , @NamedQuery(name = "Trabajador.findByTrabajadorCodigo", query = "SELECT t FROM Trabajador t WHERE t.trabajadorCodigo = :trabajadorCodigo")
    , @NamedQuery(name = "Trabajador.findByTrabajadorRut", query = "SELECT t FROM Trabajador t WHERE t.trabajadorRut = :trabajadorRut")
    , @NamedQuery(name = "Trabajador.findByTrabajadorNombres", query = "SELECT t FROM Trabajador t WHERE t.trabajadorNombres = :trabajadorNombres")
    , @NamedQuery(name = "Trabajador.findByTrabajadorApellidoPaterno", query = "SELECT t FROM Trabajador t WHERE t.trabajadorApellidoPaterno = :trabajadorApellidoPaterno")
    , @NamedQuery(name = "Trabajador.findByTrabajadorApellidoMaterno", query = "SELECT t FROM Trabajador t WHERE t.trabajadorApellidoMaterno = :trabajadorApellidoMaterno")
    , @NamedQuery(name = "Trabajador.findByTrabajadorFechaNacimiento", query = "SELECT t FROM Trabajador t WHERE t.trabajadorFechaNacimiento = :trabajadorFechaNacimiento")
    , @NamedQuery(name = "Trabajador.findByTrabajadorNacionalidad", query = "SELECT t FROM Trabajador t WHERE t.trabajadorNacionalidad = :trabajadorNacionalidad")
    , @NamedQuery(name = "Trabajador.findByTrabajadorSexo", query = "SELECT t FROM Trabajador t WHERE t.trabajadorSexo = :trabajadorSexo")
    , @NamedQuery(name = "Trabajador.findByTrabajadorCalle", query = "SELECT t FROM Trabajador t WHERE t.trabajadorCalle = :trabajadorCalle")
    , @NamedQuery(name = "Trabajador.findByTrabajadorNumeroDireccion", query = "SELECT t FROM Trabajador t WHERE t.trabajadorNumeroDireccion = :trabajadorNumeroDireccion")
    , @NamedQuery(name = "Trabajador.findByTrabajadorTelefonoFijo", query = "SELECT t FROM Trabajador t WHERE t.trabajadorTelefonoFijo = :trabajadorTelefonoFijo")
    , @NamedQuery(name = "Trabajador.findByTrabajadorCelular", query = "SELECT t FROM Trabajador t WHERE t.trabajadorCelular = :trabajadorCelular")
    , @NamedQuery(name = "Trabajador.findByTrabajadorEmail", query = "SELECT t FROM Trabajador t WHERE t.trabajadorEmail = :trabajadorEmail")
    , @NamedQuery(name = "Trabajador.findByTrabajadorPoseeCargas", query = "SELECT t FROM Trabajador t WHERE t.trabajadorPoseeCargas = :trabajadorPoseeCargas")
    , @NamedQuery(name = "Trabajador.findByTrabajadorNumeroCargas", query = "SELECT t FROM Trabajador t WHERE t.trabajadorNumeroCargas = :trabajadorNumeroCargas")
    , @NamedQuery(name = "Trabajador.findByTrabajadorMontoSalud", query = "SELECT t FROM Trabajador t WHERE t.trabajadorMontoSalud = :trabajadorMontoSalud")
    , @NamedQuery(name = "Trabajador.findByTrabajadorPoseeApv", query = "SELECT t FROM Trabajador t WHERE t.trabajadorPoseeApv = :trabajadorPoseeApv")
    , @NamedQuery(name = "Trabajador.findByTrabajadorFormaPagoApv", query = "SELECT t FROM Trabajador t WHERE t.trabajadorFormaPagoApv = :trabajadorFormaPagoApv")
    , @NamedQuery(name = "Trabajador.findByTrabajadorMontoApv", query = "SELECT t FROM Trabajador t WHERE t.trabajadorMontoApv = :trabajadorMontoApv")
    , @NamedQuery(name = "Trabajador.findByTrabajadorSubsidioJoven", query = "SELECT t FROM Trabajador t WHERE t.trabajadorSubsidioJoven = :trabajadorSubsidioJoven")
    , @NamedQuery(name = "Trabajador.findByTrabajadorCesantia", query = "SELECT t FROM Trabajador t WHERE t.trabajadorCesantia = :trabajadorCesantia")
    , @NamedQuery(name = "Trabajador.findByTrabajadorContratado", query = "SELECT t FROM Trabajador t WHERE t.trabajadorContratado = :trabajadorContratado")
    , @NamedQuery(name = "Trabajador.findByTrabajadorPoseeCuentaBanco", query = "SELECT t FROM Trabajador t WHERE t.trabajadorPoseeCuentaBanco = :trabajadorPoseeCuentaBanco")
})
public class Trabajador extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trabajador_id")
    private Integer trabajadorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trabajador_fonasa")
    private boolean trabajadorFonasa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trabajador_jubilado")
    private boolean trabajadorJubilado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trabajador_ips")
    private boolean trabajadorIps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trabajador_codigo")
    private int trabajadorCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "trabajador_rut")
    private String trabajadorRut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "trabajador_nombres")
    private String trabajadorNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "trabajador_apellido_paterno")
    private String trabajadorApellidoPaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "trabajador_apellido_materno")
    private String trabajadorApellidoMaterno;
    @Column(name = "trabajador_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date trabajadorFechaNacimiento;
    @Column(name = "trabajador_nacionalidad")
    private Boolean trabajadorNacionalidad;
    @Column(name = "trabajador_sexo")
    private Boolean trabajadorSexo;
    @Size(max = 255)
    @Column(name = "trabajador_calle")
    private String trabajadorCalle;
    @Size(max = 45)
    @Column(name = "trabajador_numero_direccion")
    private String trabajadorNumeroDireccion;
    @Size(max = 255)
    @Column(name = "trabajador_telefono_fijo")
    private String trabajadorTelefonoFijo;
    @Size(max = 255)
    @Column(name = "trabajador_celular")
    private String trabajadorCelular;
    @Size(max = 255)
    @Column(name = "trabajador_email")
    private String trabajadorEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trabajador_posee_cargas")
    private boolean trabajadorPoseeCargas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trabajador_numero_cargas")
    private int trabajadorNumeroCargas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "trabajador_monto_salud")
    private Float trabajadorMontoSalud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trabajador_posee_apv")
    private boolean trabajadorPoseeApv;
    @Column(name = "trabajador_forma_pago_apv")
    private Boolean trabajadorFormaPagoApv;
    @Column(name = "trabajador_monto_apv")
    private Integer trabajadorMontoApv;
    @Column(name = "trabajador_subsidio_joven")
    private Boolean trabajadorSubsidioJoven;
    @Column(name = "trabajador_cesantia")
    private Boolean trabajadorCesantia;
    @Column(name = "trabajador_contratado")
    private Boolean trabajadorContratado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trabajador_posee_cuenta_banco")
    private boolean trabajadorPoseeCuentaBanco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "haberTrabajadorIdTrabajador")
    private List<HaberTrabajador> haberTrabajadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargaTrabajadorIdTrabajador")
    private List<CargaTrabajador> cargaTrabajadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorAdicionalSaludIdTrabajador")
    private List<TrabajadorAdicionalSalud> trabajadorAdicionalSaludList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "despachoIdTrabajador")
    private List<Despacho> despachoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventaBoletoIdTrabajador")
    private List<VentaBoleto> ventaBoletoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "liquidacionSueldoIdTrabajador")
    private List<LiquidacionSueldo> liquidacionSueldoList;
    @JoinColumn(name = "trabajador_id_institucion_prevision", referencedColumnName = "institucion_prevision_id", nullable = false)
    @ManyToOne(optional = false)
    private InstitucionPrevision trabajadorIdInstitucionPrevision;
    @JoinColumn(name = "trabajador_id_institucion_apv", referencedColumnName = "institucion_apv_id", nullable = false)
    @ManyToOne(optional = false)
    private InstitucionApv trabajadorIdInstitucionApv;
    @JoinColumn(name = "trabajador_id_asignacion_familiar", referencedColumnName = "asignacion_familiar_id", nullable = false)
    @OrderBy("asignacionFamiliarId DESC")
    @ManyToOne(optional = false)
    private AsignacionFamiliar trabajadorIdAsignacionFamiliar;
    @JoinColumn(name = "trabajador_id_centro_costo", referencedColumnName = "centro_costo_id", nullable = false)
    @ManyToOne(optional = false)
    private CentroCosto trabajadorIdCentroCosto;
    @JoinColumn(name = "trabajador_id_comuna", referencedColumnName = "comuna_id", nullable = false)
    @ManyToOne(optional = false)
    private Comuna trabajadorIdComuna;
    @JoinColumn(name = "trabajador_id_tipo_cotizacion_trabajador", referencedColumnName = "tipo_cotizacion_trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoCotizacionTrabajador trabajadorIdTipoCotizacionTrabajador;
    @JoinColumn(name = "trabajador_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta trabajadorIdCuenta;
    @JoinColumn(name = "trabajador_id_estado_civil", referencedColumnName = "estado_civil_id", nullable = false)
    @ManyToOne(optional = false)
    private EstadoCivil trabajadorIdEstadoCivil;
    @JoinColumn(name = "trabajador_id_forma_pago", referencedColumnName = "forma_pago_id", nullable = false)
    @ManyToOne(optional = false)
    private FormaPago trabajadorIdFormaPago;
    @JoinColumn(name = "trabajador_id_institucion_salud", referencedColumnName = "institucion_salud_id", nullable = false)
    @ManyToOne(optional = false)
    private InstitucionSalud trabajadorIdInstitucionSalud;
    @JoinColumn(name = "trabajador_id_terminal", referencedColumnName = "terminal_id", nullable = false)
    @ManyToOne(optional = false)
    private Terminal trabajadorIdTerminal;
    @JoinColumn(name = "trabajador_id_sindicato", referencedColumnName = "sindicato_id", nullable = false)
    @ManyToOne(optional = false)
    private Sindicato trabajadorIdSindicato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reconocimientoDeudaIdTrabajador")
    private List<ReconocimientoDeuda> reconocimientoDeudaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recaudacionIdTrabajador")
    private List<Recaudacion> recaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relacionLaboralIdTrabajador")
    private List<RelacionLaboral> relacionLaboralList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargaRetroactivaIdTrabajador")
    private List<CargaRetroactiva> cargaRetroactivaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaBancoTrabajadorTrabajador")
    private List<CuentaBancoTrabajador> cuentaBancoTrabajadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "licenciaMedicaIdTrabajador")
    private List<LicenciaMedica> licenciaMedicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horaExtraTrabajadorIdTrabajador")
    private List<HoraExtraTrabajador> horaExtraTrabajadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "descuentoTrabajadorIdTrabajador")
    private List<DescuentoTrabajador> descuentoTrabajadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guiaIdTrabajador")
    private List<Guia> guiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feriadoLegalIdTrabajador")
    private List<FeriadoLegal> feriadoLegalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jornadaTrabajadorIdTrabajador")
    private List<JornadaTrabajador> jornadaTrabajadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "controlAsistenciaIdTrabajador")
    private List<ControlAsistencia> controlAsistenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "observacionTrabajadorIdTrabajador")
    private List<ObservacionTrabajador> observacionTrabajadorList;

    public Trabajador() {
    }

    public Trabajador(Integer trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    public Trabajador(Integer trabajadorId, boolean trabajadorFonasa, boolean trabajadorJubilado, boolean trabajadorIps, int trabajadorCodigo, String trabajadorRut, String trabajadorNombres, String trabajadorApellidoPaterno, String trabajadorApellidoMaterno, boolean trabajadorPoseeCargas, int trabajadorNumeroCargas, boolean trabajadorPoseeApv, boolean trabajadorPoseeCuentaBanco) {
        this.trabajadorId = trabajadorId;
        this.trabajadorFonasa = trabajadorFonasa;
        this.trabajadorJubilado = trabajadorJubilado;
        this.trabajadorIps = trabajadorIps;
        this.trabajadorCodigo = trabajadorCodigo;
        this.trabajadorRut = trabajadorRut;
        this.trabajadorNombres = trabajadorNombres;
        this.trabajadorApellidoPaterno = trabajadorApellidoPaterno;
        this.trabajadorApellidoMaterno = trabajadorApellidoMaterno;
        this.trabajadorPoseeCargas = trabajadorPoseeCargas;
        this.trabajadorNumeroCargas = trabajadorNumeroCargas;
        this.trabajadorPoseeApv = trabajadorPoseeApv;
        this.trabajadorPoseeCuentaBanco = trabajadorPoseeCuentaBanco;
    }

    public Integer getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(Integer trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    public boolean getTrabajadorFonasa() {
        return trabajadorFonasa;
    }

    public void setTrabajadorFonasa(boolean trabajadorFonasa) {
        this.trabajadorFonasa = trabajadorFonasa;
    }

    public boolean getTrabajadorJubilado() {
        return trabajadorJubilado;
    }

    public void setTrabajadorJubilado(boolean trabajadorJubilado) {
        this.trabajadorJubilado = trabajadorJubilado;
    }

    public boolean getTrabajadorIps() {
        return trabajadorIps;
    }

    public void setTrabajadorIps(boolean trabajadorIps) {
        this.trabajadorIps = trabajadorIps;
    }

    public int getTrabajadorCodigo() {
        return trabajadorCodigo;
    }

    public void setTrabajadorCodigo(int trabajadorCodigo) {
        this.trabajadorCodigo = trabajadorCodigo;
    }

    public String getTrabajadorRut() {
        return trabajadorRut;
    }

    public void setTrabajadorRut(String trabajadorRut) {
        this.trabajadorRut = trabajadorRut;
    }

    public String getTrabajadorNombres() {
        return trabajadorNombres;
    }

    public void setTrabajadorNombres(String trabajadorNombres) {
        this.trabajadorNombres = trabajadorNombres;
    }

    public String getTrabajadorApellidoPaterno() {
        return trabajadorApellidoPaterno;
    }

    public void setTrabajadorApellidoPaterno(String trabajadorApellidoPaterno) {
        this.trabajadorApellidoPaterno = trabajadorApellidoPaterno;
    }

    public String getTrabajadorApellidoMaterno() {
        return trabajadorApellidoMaterno;
    }

    public void setTrabajadorApellidoMaterno(String trabajadorApellidoMaterno) {
        this.trabajadorApellidoMaterno = trabajadorApellidoMaterno;
    }

    public Date getTrabajadorFechaNacimiento() {
        return trabajadorFechaNacimiento;
    }

    public void setTrabajadorFechaNacimiento(Date trabajadorFechaNacimiento) {
        this.trabajadorFechaNacimiento = trabajadorFechaNacimiento;
    }

    public Boolean getTrabajadorNacionalidad() {
        return trabajadorNacionalidad;
    }

    public void setTrabajadorNacionalidad(Boolean trabajadorNacionalidad) {
        this.trabajadorNacionalidad = trabajadorNacionalidad;
    }

    public Boolean getTrabajadorSexo() {
        return trabajadorSexo;
    }

    public void setTrabajadorSexo(Boolean trabajadorSexo) {
        this.trabajadorSexo = trabajadorSexo;
    }

    public String getTrabajadorCalle() {
        return trabajadorCalle;
    }

    public void setTrabajadorCalle(String trabajadorCalle) {
        this.trabajadorCalle = trabajadorCalle;
    }

    public String getTrabajadorNumeroDireccion() {
        return trabajadorNumeroDireccion;
    }

    public void setTrabajadorNumeroDireccion(String trabajadorNumeroDireccion) {
        this.trabajadorNumeroDireccion = trabajadorNumeroDireccion;
    }

    public String getTrabajadorTelefonoFijo() {
        return trabajadorTelefonoFijo;
    }

    public void setTrabajadorTelefonoFijo(String trabajadorTelefonoFijo) {
        this.trabajadorTelefonoFijo = trabajadorTelefonoFijo;
    }

    public String getTrabajadorCelular() {
        return trabajadorCelular;
    }

    public void setTrabajadorCelular(String trabajadorCelular) {
        this.trabajadorCelular = trabajadorCelular;
    }

    public String getTrabajadorEmail() {
        return trabajadorEmail;
    }

    public void setTrabajadorEmail(String trabajadorEmail) {
        this.trabajadorEmail = trabajadorEmail;
    }

    public boolean getTrabajadorPoseeCargas() {
        return trabajadorPoseeCargas;
    }

    public void setTrabajadorPoseeCargas(boolean trabajadorPoseeCargas) {
        this.trabajadorPoseeCargas = trabajadorPoseeCargas;
    }

    public int getTrabajadorNumeroCargas() {
        return trabajadorNumeroCargas;
    }

    public void setTrabajadorNumeroCargas(int trabajadorNumeroCargas) {
        this.trabajadorNumeroCargas = trabajadorNumeroCargas;
    }

    public Float getTrabajadorMontoSalud() {
        return trabajadorMontoSalud;
    }

    public void setTrabajadorMontoSalud(Float trabajadorMontoSalud) {
        this.trabajadorMontoSalud = trabajadorMontoSalud;
    }

    public boolean getTrabajadorPoseeApv() {
        return trabajadorPoseeApv;
    }

    public void setTrabajadorPoseeApv(boolean trabajadorPoseeApv) {
        this.trabajadorPoseeApv = trabajadorPoseeApv;
    }

    public Boolean getTrabajadorFormaPagoApv() {
        return trabajadorFormaPagoApv;
    }

    public void setTrabajadorFormaPagoApv(Boolean trabajadorFormaPagoApv) {
        this.trabajadorFormaPagoApv = trabajadorFormaPagoApv;
    }

    public Integer getTrabajadorMontoApv() {
        return trabajadorMontoApv;
    }

    public void setTrabajadorMontoApv(Integer trabajadorMontoApv) {
        this.trabajadorMontoApv = trabajadorMontoApv;
    }

    public Boolean getTrabajadorSubsidioJoven() {
        return trabajadorSubsidioJoven;
    }

    public void setTrabajadorSubsidioJoven(Boolean trabajadorSubsidioJoven) {
        this.trabajadorSubsidioJoven = trabajadorSubsidioJoven;
    }

    public Boolean getTrabajadorCesantia() {
        return trabajadorCesantia;
    }

    public void setTrabajadorCesantia(Boolean trabajadorCesantia) {
        this.trabajadorCesantia = trabajadorCesantia;
    }

    public Boolean getTrabajadorContratado() {
        return trabajadorContratado;
    }

    public void setTrabajadorContratado(Boolean trabajadorContratado) {
        this.trabajadorContratado = trabajadorContratado;
    }

    public boolean getTrabajadorPoseeCuentaBanco() {
        return trabajadorPoseeCuentaBanco;
    }

    public void setTrabajadorPoseeCuentaBanco(boolean trabajadorPoseeCuentaBanco) {
        this.trabajadorPoseeCuentaBanco = trabajadorPoseeCuentaBanco;
    }

    @XmlTransient
    public List<HaberTrabajador> getHaberTrabajadorList() {
        return haberTrabajadorList;
    }

    public void setHaberTrabajadorList(List<HaberTrabajador> haberTrabajadorList) {
        this.haberTrabajadorList = haberTrabajadorList;
    }

    @XmlTransient
    public List<CargaTrabajador> getCargaTrabajadorList() {
        return cargaTrabajadorList;
    }

    public void setCargaTrabajadorList(List<CargaTrabajador> cargaTrabajadorList) {
        this.cargaTrabajadorList = cargaTrabajadorList;
    }

    @XmlTransient
    public List<TrabajadorAdicionalSalud> getTrabajadorAdicionalSaludList() {
        return trabajadorAdicionalSaludList;
    }

    public void setTrabajadorAdicionalSaludList(List<TrabajadorAdicionalSalud> trabajadorAdicionalSaludList) {
        this.trabajadorAdicionalSaludList = trabajadorAdicionalSaludList;
    }

    @XmlTransient
    public List<Despacho> getDespachoList() {
        return despachoList;
    }

    public void setDespachoList(List<Despacho> despachoList) {
        this.despachoList = despachoList;
    }

    @XmlTransient
    public List<VentaBoleto> getVentaBoletoList() {
        return ventaBoletoList;
    }

    public void setVentaBoletoList(List<VentaBoleto> ventaBoletoList) {
        this.ventaBoletoList = ventaBoletoList;
    }

    @XmlTransient
    public List<LiquidacionSueldo> getLiquidacionSueldoList() {
        return liquidacionSueldoList;
    }

    public void setLiquidacionSueldoList(List<LiquidacionSueldo> liquidacionSueldoList) {
        this.liquidacionSueldoList = liquidacionSueldoList;
    }

    public InstitucionPrevision getTrabajadorIdInstitucionPrevision() {
        return trabajadorIdInstitucionPrevision;
    }

    public void setTrabajadorIdInstitucionPrevision(InstitucionPrevision trabajadorIdInstitucionPrevision) {
        this.trabajadorIdInstitucionPrevision = trabajadorIdInstitucionPrevision;
    }

    public InstitucionApv getTrabajadorIdInstitucionApv() {
        return trabajadorIdInstitucionApv;
    }

    public void setTrabajadorIdInstitucionApv(InstitucionApv trabajadorIdInstitucionApv) {
        this.trabajadorIdInstitucionApv = trabajadorIdInstitucionApv;
    }

    public AsignacionFamiliar getTrabajadorIdAsignacionFamiliar() {
        return trabajadorIdAsignacionFamiliar;
    }

    public void setTrabajadorIdAsignacionFamiliar(AsignacionFamiliar trabajadorIdAsignacionFamiliar) {
        this.trabajadorIdAsignacionFamiliar = trabajadorIdAsignacionFamiliar;
    }

    public CentroCosto getTrabajadorIdCentroCosto() {
        return trabajadorIdCentroCosto;
    }

    public void setTrabajadorIdCentroCosto(CentroCosto trabajadorIdCentroCosto) {
        this.trabajadorIdCentroCosto = trabajadorIdCentroCosto;
    }

    public Comuna getTrabajadorIdComuna() {
        return trabajadorIdComuna;
    }

    public void setTrabajadorIdComuna(Comuna trabajadorIdComuna) {
        this.trabajadorIdComuna = trabajadorIdComuna;
    }

    public TipoCotizacionTrabajador getTrabajadorIdTipoCotizacionTrabajador() {
        return trabajadorIdTipoCotizacionTrabajador;
    }

    public void setTrabajadorIdTipoCotizacionTrabajador(TipoCotizacionTrabajador trabajadorIdTipoCotizacionTrabajador) {
        this.trabajadorIdTipoCotizacionTrabajador = trabajadorIdTipoCotizacionTrabajador;
    }

    public Cuenta getTrabajadorIdCuenta() {
        return trabajadorIdCuenta;
    }

    public void setTrabajadorIdCuenta(Cuenta trabajadorIdCuenta) {
        this.trabajadorIdCuenta = trabajadorIdCuenta;
    }

    public EstadoCivil getTrabajadorIdEstadoCivil() {
        return trabajadorIdEstadoCivil;
    }

    public void setTrabajadorIdEstadoCivil(EstadoCivil trabajadorIdEstadoCivil) {
        this.trabajadorIdEstadoCivil = trabajadorIdEstadoCivil;
    }

    public FormaPago getTrabajadorIdFormaPago() {
        return trabajadorIdFormaPago;
    }

    public void setTrabajadorIdFormaPago(FormaPago trabajadorIdFormaPago) {
        this.trabajadorIdFormaPago = trabajadorIdFormaPago;
    }

    public InstitucionSalud getTrabajadorIdInstitucionSalud() {
        return trabajadorIdInstitucionSalud;
    }

    public void setTrabajadorIdInstitucionSalud(InstitucionSalud trabajadorIdInstitucionSalud) {
        this.trabajadorIdInstitucionSalud = trabajadorIdInstitucionSalud;
    }

    public Terminal getTrabajadorIdTerminal() {
        return trabajadorIdTerminal;
    }

    public void setTrabajadorIdTerminal(Terminal trabajadorIdTerminal) {
        this.trabajadorIdTerminal = trabajadorIdTerminal;
    }

    public Sindicato getTrabajadorIdSindicato() {
        return trabajadorIdSindicato;
    }

    public void setTrabajadorIdSindicato(Sindicato trabajadorIdSindicato) {
        this.trabajadorIdSindicato = trabajadorIdSindicato;
    }

    @XmlTransient
    public List<ReconocimientoDeuda> getReconocimientoDeudaList() {
        return reconocimientoDeudaList;
    }

    public void setReconocimientoDeudaList(List<ReconocimientoDeuda> reconocimientoDeudaList) {
        this.reconocimientoDeudaList = reconocimientoDeudaList;
    }

    @XmlTransient
    public List<Recaudacion> getRecaudacionList() {
        return recaudacionList;
    }

    public void setRecaudacionList(List<Recaudacion> recaudacionList) {
        this.recaudacionList = recaudacionList;
    }

    @XmlTransient
    public List<RelacionLaboral> getRelacionLaboralList() {
        return relacionLaboralList;
    }

    public void setRelacionLaboralList(List<RelacionLaboral> relacionLaboralList) {
        this.relacionLaboralList = relacionLaboralList;
    }

    @XmlTransient
    public List<CargaRetroactiva> getCargaRetroactivaList() {
        return cargaRetroactivaList;
    }

    public void setCargaRetroactivaList(List<CargaRetroactiva> cargaRetroactivaList) {
        this.cargaRetroactivaList = cargaRetroactivaList;
    }

    @XmlTransient
    public List<CuentaBancoTrabajador> getCuentaBancoTrabajadorList() {
        return cuentaBancoTrabajadorList;
    }

    public void setCuentaBancoTrabajadorList(List<CuentaBancoTrabajador> cuentaBancoTrabajadorList) {
        this.cuentaBancoTrabajadorList = cuentaBancoTrabajadorList;
    }

    @XmlTransient
    public List<LicenciaMedica> getLicenciaMedicaList() {
        return licenciaMedicaList;
    }

    public void setLicenciaMedicaList(List<LicenciaMedica> licenciaMedicaList) {
        this.licenciaMedicaList = licenciaMedicaList;
    }

    @XmlTransient
    public List<HoraExtraTrabajador> getHoraExtraTrabajadorList() {
        return horaExtraTrabajadorList;
    }

    public void setHoraExtraTrabajadorList(List<HoraExtraTrabajador> horaExtraTrabajadorList) {
        this.horaExtraTrabajadorList = horaExtraTrabajadorList;
    }

    @XmlTransient
    public List<DescuentoTrabajador> getDescuentoTrabajadorList() {
        return descuentoTrabajadorList;
    }

    public void setDescuentoTrabajadorList(List<DescuentoTrabajador> descuentoTrabajadorList) {
        this.descuentoTrabajadorList = descuentoTrabajadorList;
    }

    @XmlTransient
    public List<Guia> getGuiaList() {
        return guiaList;
    }

    public void setGuiaList(List<Guia> guiaList) {
        this.guiaList = guiaList;
    }

    @XmlTransient
    public List<FeriadoLegal> getFeriadoLegalList() {
        return feriadoLegalList;
    }

    public void setFeriadoLegalList(List<FeriadoLegal> feriadoLegalList) {
        this.feriadoLegalList = feriadoLegalList;
    }

    @XmlTransient
    public List<JornadaTrabajador> getJornadaTrabajadorList() {
        return jornadaTrabajadorList;
    }

    public void setJornadaTrabajadorList(List<JornadaTrabajador> jornadaTrabajadorList) {
        this.jornadaTrabajadorList = jornadaTrabajadorList;
    }

    @XmlTransient
    public List<ControlAsistencia> getControlAsistenciaList() {
        return controlAsistenciaList;
    }

    public void setControlAsistenciaList(List<ControlAsistencia> controlAsistenciaList) {
        this.controlAsistenciaList = controlAsistenciaList;
    }

    @XmlTransient
    public List<ObservacionTrabajador> getObservacionTrabajadorList() {
        return observacionTrabajadorList;
    }

    public void setObservacionTrabajadorList(List<ObservacionTrabajador> observacionTrabajadorList) {
        this.observacionTrabajadorList = observacionTrabajadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trabajadorId != null ? trabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trabajador)) {
            return false;
        }
        Trabajador other = (Trabajador) object;
        if ((this.trabajadorId == null && other.trabajadorId != null) || (this.trabajadorId != null && !this.trabajadorId.equals(other.trabajadorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Trabajador[ trabajadorId=" + trabajadorId + " ]";
    }

}
