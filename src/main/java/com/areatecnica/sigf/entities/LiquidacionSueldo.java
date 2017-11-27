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
@Table(name = "liquidacion_sueldo", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LiquidacionSueldo.findAll", query = "SELECT l FROM LiquidacionSueldo l")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoId", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoId = :liquidacionSueldoId")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoNombreTerminal", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoNombreTerminal = :liquidacionSueldoNombreTerminal")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoFecha", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoFecha = :liquidacionSueldoFecha")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoBruto", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoBruto = :liquidacionSueldoMontoBruto")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoSueldoBase", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoSueldoBase = :liquidacionSueldoMontoSueldoBase")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoHorasExtras", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoHorasExtras = :liquidacionSueldoHorasExtras")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoImponible", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoImponible = :liquidacionSueldoMontoImponible")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoImponibleAjustado", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoImponibleAjustado = :liquidacionSueldoMontoImponibleAjustado")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoCincoTotal", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoCincoTotal = :liquidacionSueldoMontoCincoTotal")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoCincoFiltrado", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoCincoFiltrado = :liquidacionSueldoMontoCincoFiltrado")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoAhorro", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoAhorro = :liquidacionSueldoMontoAhorro")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoAbono", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoAbono = :liquidacionSueldoMontoAbono")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoFeriado", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoFeriado = :liquidacionSueldoMontoFeriado")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoNoImponible", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoNoImponible = :liquidacionSueldoMontoNoImponible")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoNumeroCarga", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoNumeroCarga = :liquidacionSueldoNumeroCarga")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoCarga", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoCarga = :liquidacionSueldoMontoCarga")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoRetroactivo", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoRetroactivo = :liquidacionSueldoMontoRetroactivo")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoFechaContrato", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoFechaContrato = :liquidacionSueldoFechaContrato")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoFechaFiniquito", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoFechaFiniquito = :liquidacionSueldoFechaFiniquito")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoFechaDesdeFeriado", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoFechaDesdeFeriado = :liquidacionSueldoFechaDesdeFeriado")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoFechaHastaFeriado", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoFechaHastaFeriado = :liquidacionSueldoFechaHastaFeriado")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoDiasFeriado", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoDiasFeriado = :liquidacionSueldoDiasFeriado")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoDiasLicencias", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoDiasLicencias = :liquidacionSueldoDiasLicencias")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoDiasDescanso", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoDiasDescanso = :liquidacionSueldoDiasDescanso")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoDiasGuias", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoDiasGuias = :liquidacionSueldoDiasGuias")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoDiasTrabajados", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoDiasTrabajados = :liquidacionSueldoDiasTrabajados")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoNombrePrevision", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoNombrePrevision = :liquidacionSueldoNombrePrevision")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoPorcentajePrevision", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoPorcentajePrevision = :liquidacionSueldoPorcentajePrevision")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoPrevision", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoPrevision = :liquidacionSueldoMontoPrevision")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoApv", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoApv = :liquidacionSueldoMontoApv")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoCesantiaTrabajador", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoCesantiaTrabajador = :liquidacionSueldoMontoCesantiaTrabajador")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoCesantiaEmpresa", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoCesantiaEmpresa = :liquidacionSueldoMontoCesantiaEmpresa")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoCesantiaTotal", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoCesantiaTotal = :liquidacionSueldoMontoCesantiaTotal")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoSis", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoSis = :liquidacionSueldoMontoSis")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoInp", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoInp = :liquidacionSueldoMontoInp")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoCajaCompensacion", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoCajaCompensacion = :liquidacionSueldoMontoCajaCompensacion")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoNombreIsapre", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoNombreIsapre = :liquidacionSueldoNombreIsapre")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoIsapre", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoIsapre = :liquidacionSueldoMontoIsapre")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoSindicato", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoSindicato = :liquidacionSueldoMontoSindicato")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoSaldoAnterior", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoSaldoAnterior = :liquidacionSueldoMontoSaldoAnterior")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoRetencionJudicial", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoRetencionJudicial = :liquidacionSueldoMontoRetencionJudicial")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoCreditoSalud", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoCreditoSalud = :liquidacionSueldoMontoCreditoSalud")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoSeguro", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoSeguro = :liquidacionSueldoMontoSeguro")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoTotalDescuentos", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoTotalDescuentos = :liquidacionSueldoMontoTotalDescuentos")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoAlcanceLiquido", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoAlcanceLiquido = :liquidacionSueldoMontoAlcanceLiquido")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoAnticipo", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoAnticipo = :liquidacionSueldoMontoAnticipo")
    , @NamedQuery(name = "LiquidacionSueldo.findByLiquidacionSueldoMontoSaldo", query = "SELECT l FROM LiquidacionSueldo l WHERE l.liquidacionSueldoMontoSaldo = :liquidacionSueldoMontoSaldo")
    })
public class LiquidacionSueldo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "liquidacion_sueldo_id")
    private Integer liquidacionSueldoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "liquidacion_sueldo_nombre_terminal")
    private String liquidacionSueldoNombreTerminal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_fecha")
    @Temporal(TemporalType.DATE)
    private Date liquidacionSueldoFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_bruto")
    private int liquidacionSueldoMontoBruto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_sueldo_base")
    private int liquidacionSueldoMontoSueldoBase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_horas_extras")
    private int liquidacionSueldoHorasExtras;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_imponible")
    private int liquidacionSueldoMontoImponible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_imponible_ajustado")
    private int liquidacionSueldoMontoImponibleAjustado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_cinco_total")
    private int liquidacionSueldoMontoCincoTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_cinco_filtrado")
    private int liquidacionSueldoMontoCincoFiltrado;
    @Column(name = "liquidacion_sueldo_monto_ahorro")
    private Integer liquidacionSueldoMontoAhorro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_abono")
    private int liquidacionSueldoMontoAbono;
    @Column(name = "liquidacion_sueldo_monto_feriado")
    private Integer liquidacionSueldoMontoFeriado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_no_imponible")
    private int liquidacionSueldoMontoNoImponible;
    @Column(name = "liquidacion_sueldo_numero_carga")
    private Integer liquidacionSueldoNumeroCarga;
    @Column(name = "liquidacion_sueldo_monto_carga")
    private Integer liquidacionSueldoMontoCarga;
    @Column(name = "liquidacion_sueldo_monto_retroactivo")
    private Integer liquidacionSueldoMontoRetroactivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_fecha_contrato")
    @Temporal(TemporalType.DATE)
    private Date liquidacionSueldoFechaContrato;
    @Column(name = "liquidacion_sueldo_fecha_finiquito")
    @Temporal(TemporalType.DATE)
    private Date liquidacionSueldoFechaFiniquito;
    @Column(name = "liquidacion_sueldo_fecha_desde_feriado")
    @Temporal(TemporalType.DATE)
    private Date liquidacionSueldoFechaDesdeFeriado;
    @Column(name = "liquidacion_sueldo_fecha_hasta_feriado")
    @Temporal(TemporalType.DATE)
    private Date liquidacionSueldoFechaHastaFeriado;
    @Column(name = "liquidacion_sueldo_dias_feriado")
    private Integer liquidacionSueldoDiasFeriado;
    @Column(name = "liquidacion_sueldo_dias_licencias")
    private Integer liquidacionSueldoDiasLicencias;
    @Column(name = "liquidacion_sueldo_dias_descanso")
    private Integer liquidacionSueldoDiasDescanso;
    @Column(name = "liquidacion_sueldo_dias_guias")
    private Integer liquidacionSueldoDiasGuias;
    @Column(name = "liquidacion_sueldo_dias_trabajados")
    private Integer liquidacionSueldoDiasTrabajados;
    @Size(max = 100)
    @Column(name = "liquidacion_sueldo_nombre_prevision")
    private String liquidacionSueldoNombrePrevision;
    @Size(max = 45)
    @Column(name = "liquidacion_sueldo_porcentaje_prevision")
    private String liquidacionSueldoPorcentajePrevision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_prevision")
    private int liquidacionSueldoMontoPrevision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_apv")
    private int liquidacionSueldoMontoApv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_cesantia_trabajador")
    private int liquidacionSueldoMontoCesantiaTrabajador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_cesantia_empresa")
    private int liquidacionSueldoMontoCesantiaEmpresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_cesantia_total")
    private int liquidacionSueldoMontoCesantiaTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_sis")
    private int liquidacionSueldoMontoSis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_inp")
    private int liquidacionSueldoMontoInp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_caja_compensacion")
    private int liquidacionSueldoMontoCajaCompensacion;
    @Size(max = 45)
    @Column(name = "liquidacion_sueldo_nombre_isapre")
    private String liquidacionSueldoNombreIsapre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_isapre")
    private int liquidacionSueldoMontoIsapre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_sindicato")
    private int liquidacionSueldoMontoSindicato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_saldo_anterior")
    private int liquidacionSueldoMontoSaldoAnterior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_retencion_judicial")
    private int liquidacionSueldoMontoRetencionJudicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_credito_salud")
    private int liquidacionSueldoMontoCreditoSalud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_seguro")
    private int liquidacionSueldoMontoSeguro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_total_descuentos")
    private int liquidacionSueldoMontoTotalDescuentos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_alcance_liquido")
    private int liquidacionSueldoMontoAlcanceLiquido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_anticipo")
    private int liquidacionSueldoMontoAnticipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_sueldo_monto_saldo")
    private int liquidacionSueldoMontoSaldo;    
    @JoinColumn(name = "liquidacion_sueldo_id_empresa", referencedColumnName = "empresa_id", nullable = false)
    @ManyToOne(optional = false)
    private Empresa liquidacionSueldoIdEmpresa;
    @JoinColumn(name = "liquidacion_sueldo_id_movimiento_personal", referencedColumnName = "tipo_movimiento_personal_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoMovimientoPersonal liquidacionSueldoIdMovimientoPersonal;
    @JoinColumn(name = "liquidacion_sueldo_id_terminal", referencedColumnName = "terminal_id", nullable = false)
    @ManyToOne(optional = false)
    private Terminal liquidacionSueldoIdTerminal;
    @JoinColumn(name = "liquidacion_sueldo_id_tipo_contrato", referencedColumnName = "tipo_contrato_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoContrato liquidacionSueldoIdTipoContrato;
    @JoinColumn(name = "liquidacion_sueldo_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador liquidacionSueldoIdTrabajador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "haberLiquidacionIdLiquidacionSueldo")
    private List<HaberLiquidacion> haberLiquidacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "descuentoLiquidacionIdLiquidacionSueldo")
    private List<DescuentoLiquidacion> descuentoLiquidacionList;

    public LiquidacionSueldo() {
    }

    public LiquidacionSueldo(Integer liquidacionSueldoId) {
        this.liquidacionSueldoId = liquidacionSueldoId;
    }

    public LiquidacionSueldo(Integer liquidacionSueldoId, String liquidacionSueldoNombreTerminal, Date liquidacionSueldoFecha, int liquidacionSueldoMontoBruto, int liquidacionSueldoMontoSueldoBase, int liquidacionSueldoHorasExtras, int liquidacionSueldoMontoImponible, int liquidacionSueldoMontoImponibleAjustado, int liquidacionSueldoMontoCincoTotal, int liquidacionSueldoMontoCincoFiltrado, int liquidacionSueldoMontoAbono, int liquidacionSueldoMontoNoImponible, Date liquidacionSueldoFechaContrato, int liquidacionSueldoMontoPrevision, int liquidacionSueldoMontoApv, int liquidacionSueldoMontoCesantiaTrabajador, int liquidacionSueldoMontoCesantiaEmpresa, int liquidacionSueldoMontoCesantiaTotal, int liquidacionSueldoMontoSis, int liquidacionSueldoMontoInp, int liquidacionSueldoMontoCajaCompensacion, int liquidacionSueldoMontoIsapre, int liquidacionSueldoMontoSindicato, int liquidacionSueldoMontoSaldoAnterior, int liquidacionSueldoMontoRetencionJudicial, int liquidacionSueldoMontoCreditoSalud, int liquidacionSueldoMontoSeguro, int liquidacionSueldoMontoTotalDescuentos, int liquidacionSueldoMontoAlcanceLiquido, int liquidacionSueldoMontoAnticipo, int liquidacionSueldoMontoSaldo) {
        this.liquidacionSueldoId = liquidacionSueldoId;
        this.liquidacionSueldoNombreTerminal = liquidacionSueldoNombreTerminal;
        this.liquidacionSueldoFecha = liquidacionSueldoFecha;
        this.liquidacionSueldoMontoBruto = liquidacionSueldoMontoBruto;
        this.liquidacionSueldoMontoSueldoBase = liquidacionSueldoMontoSueldoBase;
        this.liquidacionSueldoHorasExtras = liquidacionSueldoHorasExtras;
        this.liquidacionSueldoMontoImponible = liquidacionSueldoMontoImponible;
        this.liquidacionSueldoMontoImponibleAjustado = liquidacionSueldoMontoImponibleAjustado;
        this.liquidacionSueldoMontoCincoTotal = liquidacionSueldoMontoCincoTotal;
        this.liquidacionSueldoMontoCincoFiltrado = liquidacionSueldoMontoCincoFiltrado;
        this.liquidacionSueldoMontoAbono = liquidacionSueldoMontoAbono;
        this.liquidacionSueldoMontoNoImponible = liquidacionSueldoMontoNoImponible;
        this.liquidacionSueldoFechaContrato = liquidacionSueldoFechaContrato;
        this.liquidacionSueldoMontoPrevision = liquidacionSueldoMontoPrevision;
        this.liquidacionSueldoMontoApv = liquidacionSueldoMontoApv;
        this.liquidacionSueldoMontoCesantiaTrabajador = liquidacionSueldoMontoCesantiaTrabajador;
        this.liquidacionSueldoMontoCesantiaEmpresa = liquidacionSueldoMontoCesantiaEmpresa;
        this.liquidacionSueldoMontoCesantiaTotal = liquidacionSueldoMontoCesantiaTotal;
        this.liquidacionSueldoMontoSis = liquidacionSueldoMontoSis;
        this.liquidacionSueldoMontoInp = liquidacionSueldoMontoInp;
        this.liquidacionSueldoMontoCajaCompensacion = liquidacionSueldoMontoCajaCompensacion;
        this.liquidacionSueldoMontoIsapre = liquidacionSueldoMontoIsapre;
        this.liquidacionSueldoMontoSindicato = liquidacionSueldoMontoSindicato;
        this.liquidacionSueldoMontoSaldoAnterior = liquidacionSueldoMontoSaldoAnterior;
        this.liquidacionSueldoMontoRetencionJudicial = liquidacionSueldoMontoRetencionJudicial;
        this.liquidacionSueldoMontoCreditoSalud = liquidacionSueldoMontoCreditoSalud;
        this.liquidacionSueldoMontoSeguro = liquidacionSueldoMontoSeguro;
        this.liquidacionSueldoMontoTotalDescuentos = liquidacionSueldoMontoTotalDescuentos;
        this.liquidacionSueldoMontoAlcanceLiquido = liquidacionSueldoMontoAlcanceLiquido;
        this.liquidacionSueldoMontoAnticipo = liquidacionSueldoMontoAnticipo;
        this.liquidacionSueldoMontoSaldo = liquidacionSueldoMontoSaldo;
    }

    public Integer getLiquidacionSueldoId() {
        return liquidacionSueldoId;
    }

    public void setLiquidacionSueldoId(Integer liquidacionSueldoId) {
        this.liquidacionSueldoId = liquidacionSueldoId;
    }

    public String getLiquidacionSueldoNombreTerminal() {
        return liquidacionSueldoNombreTerminal;
    }

    public void setLiquidacionSueldoNombreTerminal(String liquidacionSueldoNombreTerminal) {
        this.liquidacionSueldoNombreTerminal = liquidacionSueldoNombreTerminal;
    }

    public Date getLiquidacionSueldoFecha() {
        return liquidacionSueldoFecha;
    }

    public void setLiquidacionSueldoFecha(Date liquidacionSueldoFecha) {
        this.liquidacionSueldoFecha = liquidacionSueldoFecha;
    }

    public int getLiquidacionSueldoMontoBruto() {
        return liquidacionSueldoMontoBruto;
    }

    public void setLiquidacionSueldoMontoBruto(int liquidacionSueldoMontoBruto) {
        this.liquidacionSueldoMontoBruto = liquidacionSueldoMontoBruto;
    }

    public int getLiquidacionSueldoMontoSueldoBase() {
        return liquidacionSueldoMontoSueldoBase;
    }

    public void setLiquidacionSueldoMontoSueldoBase(int liquidacionSueldoMontoSueldoBase) {
        this.liquidacionSueldoMontoSueldoBase = liquidacionSueldoMontoSueldoBase;
    }

    public int getLiquidacionSueldoHorasExtras() {
        return liquidacionSueldoHorasExtras;
    }

    public void setLiquidacionSueldoHorasExtras(int liquidacionSueldoHorasExtras) {
        this.liquidacionSueldoHorasExtras = liquidacionSueldoHorasExtras;
    }

    public int getLiquidacionSueldoMontoImponible() {
        return liquidacionSueldoMontoImponible;
    }

    public void setLiquidacionSueldoMontoImponible(int liquidacionSueldoMontoImponible) {
        this.liquidacionSueldoMontoImponible = liquidacionSueldoMontoImponible;
    }

    public int getLiquidacionSueldoMontoImponibleAjustado() {
        return liquidacionSueldoMontoImponibleAjustado;
    }

    public void setLiquidacionSueldoMontoImponibleAjustado(int liquidacionSueldoMontoImponibleAjustado) {
        this.liquidacionSueldoMontoImponibleAjustado = liquidacionSueldoMontoImponibleAjustado;
    }

    public int getLiquidacionSueldoMontoCincoTotal() {
        return liquidacionSueldoMontoCincoTotal;
    }

    public void setLiquidacionSueldoMontoCincoTotal(int liquidacionSueldoMontoCincoTotal) {
        this.liquidacionSueldoMontoCincoTotal = liquidacionSueldoMontoCincoTotal;
    }

    public int getLiquidacionSueldoMontoCincoFiltrado() {
        return liquidacionSueldoMontoCincoFiltrado;
    }

    public void setLiquidacionSueldoMontoCincoFiltrado(int liquidacionSueldoMontoCincoFiltrado) {
        this.liquidacionSueldoMontoCincoFiltrado = liquidacionSueldoMontoCincoFiltrado;
    }

    public Integer getLiquidacionSueldoMontoAhorro() {
        return liquidacionSueldoMontoAhorro;
    }

    public void setLiquidacionSueldoMontoAhorro(Integer liquidacionSueldoMontoAhorro) {
        this.liquidacionSueldoMontoAhorro = liquidacionSueldoMontoAhorro;
    }

    public int getLiquidacionSueldoMontoAbono() {
        return liquidacionSueldoMontoAbono;
    }

    public void setLiquidacionSueldoMontoAbono(int liquidacionSueldoMontoAbono) {
        this.liquidacionSueldoMontoAbono = liquidacionSueldoMontoAbono;
    }

    public Integer getLiquidacionSueldoMontoFeriado() {
        return liquidacionSueldoMontoFeriado;
    }

    public void setLiquidacionSueldoMontoFeriado(Integer liquidacionSueldoMontoFeriado) {
        this.liquidacionSueldoMontoFeriado = liquidacionSueldoMontoFeriado;
    }

    public int getLiquidacionSueldoMontoNoImponible() {
        return liquidacionSueldoMontoNoImponible;
    }

    public void setLiquidacionSueldoMontoNoImponible(int liquidacionSueldoMontoNoImponible) {
        this.liquidacionSueldoMontoNoImponible = liquidacionSueldoMontoNoImponible;
    }

    public Integer getLiquidacionSueldoNumeroCarga() {
        return liquidacionSueldoNumeroCarga;
    }

    public void setLiquidacionSueldoNumeroCarga(Integer liquidacionSueldoNumeroCarga) {
        this.liquidacionSueldoNumeroCarga = liquidacionSueldoNumeroCarga;
    }

    public Integer getLiquidacionSueldoMontoCarga() {
        return liquidacionSueldoMontoCarga;
    }

    public void setLiquidacionSueldoMontoCarga(Integer liquidacionSueldoMontoCarga) {
        this.liquidacionSueldoMontoCarga = liquidacionSueldoMontoCarga;
    }

    public Integer getLiquidacionSueldoMontoRetroactivo() {
        return liquidacionSueldoMontoRetroactivo;
    }

    public void setLiquidacionSueldoMontoRetroactivo(Integer liquidacionSueldoMontoRetroactivo) {
        this.liquidacionSueldoMontoRetroactivo = liquidacionSueldoMontoRetroactivo;
    }

    public Date getLiquidacionSueldoFechaContrato() {
        return liquidacionSueldoFechaContrato;
    }

    public void setLiquidacionSueldoFechaContrato(Date liquidacionSueldoFechaContrato) {
        this.liquidacionSueldoFechaContrato = liquidacionSueldoFechaContrato;
    }

    public Date getLiquidacionSueldoFechaFiniquito() {
        return liquidacionSueldoFechaFiniquito;
    }

    public void setLiquidacionSueldoFechaFiniquito(Date liquidacionSueldoFechaFiniquito) {
        this.liquidacionSueldoFechaFiniquito = liquidacionSueldoFechaFiniquito;
    }

    public Date getLiquidacionSueldoFechaDesdeFeriado() {
        return liquidacionSueldoFechaDesdeFeriado;
    }

    public void setLiquidacionSueldoFechaDesdeFeriado(Date liquidacionSueldoFechaDesdeFeriado) {
        this.liquidacionSueldoFechaDesdeFeriado = liquidacionSueldoFechaDesdeFeriado;
    }

    public Date getLiquidacionSueldoFechaHastaFeriado() {
        return liquidacionSueldoFechaHastaFeriado;
    }

    public void setLiquidacionSueldoFechaHastaFeriado(Date liquidacionSueldoFechaHastaFeriado) {
        this.liquidacionSueldoFechaHastaFeriado = liquidacionSueldoFechaHastaFeriado;
    }

    public Integer getLiquidacionSueldoDiasFeriado() {
        return liquidacionSueldoDiasFeriado;
    }

    public void setLiquidacionSueldoDiasFeriado(Integer liquidacionSueldoDiasFeriado) {
        this.liquidacionSueldoDiasFeriado = liquidacionSueldoDiasFeriado;
    }

    public Integer getLiquidacionSueldoDiasLicencias() {
        return liquidacionSueldoDiasLicencias;
    }

    public void setLiquidacionSueldoDiasLicencias(Integer liquidacionSueldoDiasLicencias) {
        this.liquidacionSueldoDiasLicencias = liquidacionSueldoDiasLicencias;
    }

    public Integer getLiquidacionSueldoDiasDescanso() {
        return liquidacionSueldoDiasDescanso;
    }

    public void setLiquidacionSueldoDiasDescanso(Integer liquidacionSueldoDiasDescanso) {
        this.liquidacionSueldoDiasDescanso = liquidacionSueldoDiasDescanso;
    }

    public Integer getLiquidacionSueldoDiasGuias() {
        return liquidacionSueldoDiasGuias;
    }

    public void setLiquidacionSueldoDiasGuias(Integer liquidacionSueldoDiasGuias) {
        this.liquidacionSueldoDiasGuias = liquidacionSueldoDiasGuias;
    }

    public Integer getLiquidacionSueldoDiasTrabajados() {
        return liquidacionSueldoDiasTrabajados;
    }

    public void setLiquidacionSueldoDiasTrabajados(Integer liquidacionSueldoDiasTrabajados) {
        this.liquidacionSueldoDiasTrabajados = liquidacionSueldoDiasTrabajados;
    }

    public String getLiquidacionSueldoNombrePrevision() {
        return liquidacionSueldoNombrePrevision;
    }

    public void setLiquidacionSueldoNombrePrevision(String liquidacionSueldoNombrePrevision) {
        this.liquidacionSueldoNombrePrevision = liquidacionSueldoNombrePrevision;
    }

    public String getLiquidacionSueldoPorcentajePrevision() {
        return liquidacionSueldoPorcentajePrevision;
    }

    public void setLiquidacionSueldoPorcentajePrevision(String liquidacionSueldoPorcentajePrevision) {
        this.liquidacionSueldoPorcentajePrevision = liquidacionSueldoPorcentajePrevision;
    }

    public int getLiquidacionSueldoMontoPrevision() {
        return liquidacionSueldoMontoPrevision;
    }

    public void setLiquidacionSueldoMontoPrevision(int liquidacionSueldoMontoPrevision) {
        this.liquidacionSueldoMontoPrevision = liquidacionSueldoMontoPrevision;
    }

    public int getLiquidacionSueldoMontoApv() {
        return liquidacionSueldoMontoApv;
    }

    public void setLiquidacionSueldoMontoApv(int liquidacionSueldoMontoApv) {
        this.liquidacionSueldoMontoApv = liquidacionSueldoMontoApv;
    }

    public int getLiquidacionSueldoMontoCesantiaTrabajador() {
        return liquidacionSueldoMontoCesantiaTrabajador;
    }

    public void setLiquidacionSueldoMontoCesantiaTrabajador(int liquidacionSueldoMontoCesantiaTrabajador) {
        this.liquidacionSueldoMontoCesantiaTrabajador = liquidacionSueldoMontoCesantiaTrabajador;
    }

    public int getLiquidacionSueldoMontoCesantiaEmpresa() {
        return liquidacionSueldoMontoCesantiaEmpresa;
    }

    public void setLiquidacionSueldoMontoCesantiaEmpresa(int liquidacionSueldoMontoCesantiaEmpresa) {
        this.liquidacionSueldoMontoCesantiaEmpresa = liquidacionSueldoMontoCesantiaEmpresa;
    }

    public int getLiquidacionSueldoMontoCesantiaTotal() {
        return liquidacionSueldoMontoCesantiaTotal;
    }

    public void setLiquidacionSueldoMontoCesantiaTotal(int liquidacionSueldoMontoCesantiaTotal) {
        this.liquidacionSueldoMontoCesantiaTotal = liquidacionSueldoMontoCesantiaTotal;
    }

    public int getLiquidacionSueldoMontoSis() {
        return liquidacionSueldoMontoSis;
    }

    public void setLiquidacionSueldoMontoSis(int liquidacionSueldoMontoSis) {
        this.liquidacionSueldoMontoSis = liquidacionSueldoMontoSis;
    }

    public int getLiquidacionSueldoMontoInp() {
        return liquidacionSueldoMontoInp;
    }

    public void setLiquidacionSueldoMontoInp(int liquidacionSueldoMontoInp) {
        this.liquidacionSueldoMontoInp = liquidacionSueldoMontoInp;
    }

    public int getLiquidacionSueldoMontoCajaCompensacion() {
        return liquidacionSueldoMontoCajaCompensacion;
    }

    public void setLiquidacionSueldoMontoCajaCompensacion(int liquidacionSueldoMontoCajaCompensacion) {
        this.liquidacionSueldoMontoCajaCompensacion = liquidacionSueldoMontoCajaCompensacion;
    }

    public String getLiquidacionSueldoNombreIsapre() {
        return liquidacionSueldoNombreIsapre;
    }

    public void setLiquidacionSueldoNombreIsapre(String liquidacionSueldoNombreIsapre) {
        this.liquidacionSueldoNombreIsapre = liquidacionSueldoNombreIsapre;
    }

    public int getLiquidacionSueldoMontoIsapre() {
        return liquidacionSueldoMontoIsapre;
    }

    public void setLiquidacionSueldoMontoIsapre(int liquidacionSueldoMontoIsapre) {
        this.liquidacionSueldoMontoIsapre = liquidacionSueldoMontoIsapre;
    }

    public int getLiquidacionSueldoMontoSindicato() {
        return liquidacionSueldoMontoSindicato;
    }

    public void setLiquidacionSueldoMontoSindicato(int liquidacionSueldoMontoSindicato) {
        this.liquidacionSueldoMontoSindicato = liquidacionSueldoMontoSindicato;
    }

    public int getLiquidacionSueldoMontoSaldoAnterior() {
        return liquidacionSueldoMontoSaldoAnterior;
    }

    public void setLiquidacionSueldoMontoSaldoAnterior(int liquidacionSueldoMontoSaldoAnterior) {
        this.liquidacionSueldoMontoSaldoAnterior = liquidacionSueldoMontoSaldoAnterior;
    }

    public int getLiquidacionSueldoMontoRetencionJudicial() {
        return liquidacionSueldoMontoRetencionJudicial;
    }

    public void setLiquidacionSueldoMontoRetencionJudicial(int liquidacionSueldoMontoRetencionJudicial) {
        this.liquidacionSueldoMontoRetencionJudicial = liquidacionSueldoMontoRetencionJudicial;
    }

    public int getLiquidacionSueldoMontoCreditoSalud() {
        return liquidacionSueldoMontoCreditoSalud;
    }

    public void setLiquidacionSueldoMontoCreditoSalud(int liquidacionSueldoMontoCreditoSalud) {
        this.liquidacionSueldoMontoCreditoSalud = liquidacionSueldoMontoCreditoSalud;
    }

    public int getLiquidacionSueldoMontoSeguro() {
        return liquidacionSueldoMontoSeguro;
    }

    public void setLiquidacionSueldoMontoSeguro(int liquidacionSueldoMontoSeguro) {
        this.liquidacionSueldoMontoSeguro = liquidacionSueldoMontoSeguro;
    }

    public int getLiquidacionSueldoMontoTotalDescuentos() {
        return liquidacionSueldoMontoTotalDescuentos;
    }

    public void setLiquidacionSueldoMontoTotalDescuentos(int liquidacionSueldoMontoTotalDescuentos) {
        this.liquidacionSueldoMontoTotalDescuentos = liquidacionSueldoMontoTotalDescuentos;
    }

    public int getLiquidacionSueldoMontoAlcanceLiquido() {
        return liquidacionSueldoMontoAlcanceLiquido;
    }

    public void setLiquidacionSueldoMontoAlcanceLiquido(int liquidacionSueldoMontoAlcanceLiquido) {
        this.liquidacionSueldoMontoAlcanceLiquido = liquidacionSueldoMontoAlcanceLiquido;
    }

    public int getLiquidacionSueldoMontoAnticipo() {
        return liquidacionSueldoMontoAnticipo;
    }

    public void setLiquidacionSueldoMontoAnticipo(int liquidacionSueldoMontoAnticipo) {
        this.liquidacionSueldoMontoAnticipo = liquidacionSueldoMontoAnticipo;
    }

    public int getLiquidacionSueldoMontoSaldo() {
        return liquidacionSueldoMontoSaldo;
    }

    public void setLiquidacionSueldoMontoSaldo(int liquidacionSueldoMontoSaldo) {
        this.liquidacionSueldoMontoSaldo = liquidacionSueldoMontoSaldo;
    }

    public Empresa getLiquidacionSueldoIdEmpresa() {
        return liquidacionSueldoIdEmpresa;
    }

    public void setLiquidacionSueldoIdEmpresa(Empresa liquidacionSueldoIdEmpresa) {
        this.liquidacionSueldoIdEmpresa = liquidacionSueldoIdEmpresa;
    }

    public TipoMovimientoPersonal getLiquidacionSueldoIdMovimientoPersonal() {
        return liquidacionSueldoIdMovimientoPersonal;
    }

    public void setLiquidacionSueldoIdMovimientoPersonal(TipoMovimientoPersonal liquidacionSueldoIdMovimientoPersonal) {
        this.liquidacionSueldoIdMovimientoPersonal = liquidacionSueldoIdMovimientoPersonal;
    }

    public Terminal getLiquidacionSueldoIdTerminal() {
        return liquidacionSueldoIdTerminal;
    }

    public void setLiquidacionSueldoIdTerminal(Terminal liquidacionSueldoIdTerminal) {
        this.liquidacionSueldoIdTerminal = liquidacionSueldoIdTerminal;
    }

    public TipoContrato getLiquidacionSueldoIdTipoContrato() {
        return liquidacionSueldoIdTipoContrato;
    }

    public void setLiquidacionSueldoIdTipoContrato(TipoContrato liquidacionSueldoIdTipoContrato) {
        this.liquidacionSueldoIdTipoContrato = liquidacionSueldoIdTipoContrato;
    }

    public Trabajador getLiquidacionSueldoIdTrabajador() {
        return liquidacionSueldoIdTrabajador;
    }

    public void setLiquidacionSueldoIdTrabajador(Trabajador liquidacionSueldoIdTrabajador) {
        this.liquidacionSueldoIdTrabajador = liquidacionSueldoIdTrabajador;
    }

    @XmlTransient
    public List<HaberLiquidacion> getHaberLiquidacionList() {
        return haberLiquidacionList;
    }

    public void setHaberLiquidacionList(List<HaberLiquidacion> haberLiquidacionList) {
        this.haberLiquidacionList = haberLiquidacionList;
    }

    @XmlTransient
    public List<DescuentoLiquidacion> getDescuentoLiquidacionList() {
        return descuentoLiquidacionList;
    }

    public void setDescuentoLiquidacionList(List<DescuentoLiquidacion> descuentoLiquidacionList) {
        this.descuentoLiquidacionList = descuentoLiquidacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (liquidacionSueldoId != null ? liquidacionSueldoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiquidacionSueldo)) {
            return false;
        }
        LiquidacionSueldo other = (LiquidacionSueldo) object;
        if ((this.liquidacionSueldoId == null && other.liquidacionSueldoId != null) || (this.liquidacionSueldoId != null && !this.liquidacionSueldoId.equals(other.liquidacionSueldoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.LiquidacionSueldo[ liquidacionSueldoId=" + liquidacionSueldoId + " ]";
    }
    
}
