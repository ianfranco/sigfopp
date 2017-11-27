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
@Table(name = "descuento_trabajador", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescuentoTrabajador.findAll", query = "SELECT d FROM DescuentoTrabajador d")
    , @NamedQuery(name = "DescuentoTrabajador.findByDescuentoTrabajadorId", query = "SELECT d FROM DescuentoTrabajador d WHERE d.descuentoTrabajadorId = :descuentoTrabajadorId")
    , @NamedQuery(name = "DescuentoTrabajador.findByDescuentoTrabajadorFechaInicio", query = "SELECT d FROM DescuentoTrabajador d WHERE d.descuentoTrabajadorFechaInicio = :descuentoTrabajadorFechaInicio")
    , @NamedQuery(name = "DescuentoTrabajador.findByDescuentoTrabajadorFechaTermino", query = "SELECT d FROM DescuentoTrabajador d WHERE d.descuentoTrabajadorFechaTermino = :descuentoTrabajadorFechaTermino")
    , @NamedQuery(name = "DescuentoTrabajador.findByDescuentoTrabajadorCuotaActual", query = "SELECT d FROM DescuentoTrabajador d WHERE d.descuentoTrabajadorCuotaActual = :descuentoTrabajadorCuotaActual")
    , @NamedQuery(name = "DescuentoTrabajador.findByDescuentoTrabajadorTotalCuotas", query = "SELECT d FROM DescuentoTrabajador d WHERE d.descuentoTrabajadorTotalCuotas = :descuentoTrabajadorTotalCuotas")
    , @NamedQuery(name = "DescuentoTrabajador.findByDescuentoTrabajadorMontoFijo", query = "SELECT d FROM DescuentoTrabajador d WHERE d.descuentoTrabajadorMontoFijo = :descuentoTrabajadorMontoFijo")
    , @NamedQuery(name = "DescuentoTrabajador.findByDescuentoTrabajadorDescripcion", query = "SELECT d FROM DescuentoTrabajador d WHERE d.descuentoTrabajadorDescripcion = :descuentoTrabajadorDescripcion")
    , @NamedQuery(name = "DescuentoTrabajador.findByDescuentoTrabajadorActivo", query = "SELECT d FROM DescuentoTrabajador d WHERE d.descuentoTrabajadorActivo = :descuentoTrabajadorActivo")
    })
public class DescuentoTrabajador extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "descuento_trabajador_id")
    private Integer descuentoTrabajadorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_trabajador_fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date descuentoTrabajadorFechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_trabajador_fecha_termino")
    @Temporal(TemporalType.DATE)
    private Date descuentoTrabajadorFechaTermino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_trabajador_cuota_actual")
    private int descuentoTrabajadorCuotaActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_trabajador_total_cuotas")
    private int descuentoTrabajadorTotalCuotas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_trabajador_monto_fijo")
    private int descuentoTrabajadorMontoFijo;
    @Size(max = 45)
    @Column(name = "descuento_trabajador_descripcion")
    private String descuentoTrabajadorDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_trabajador_activo")
    private boolean descuentoTrabajadorActivo;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "descuentoLiquidacionIdDescuentoTrabajador")
    private List<DescuentoLiquidacion> descuentoLiquidacionList;
    @JoinColumn(name = "descuento_trabajador_id_descuento", referencedColumnName = "tipo_descuento_trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoDescuentoTrabajador descuentoTrabajadorIdDescuento;
    @JoinColumn(name = "descuento_trabajador_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador descuentoTrabajadorIdTrabajador;

    public DescuentoTrabajador() {
    }

    public DescuentoTrabajador(Integer descuentoTrabajadorId) {
        this.descuentoTrabajadorId = descuentoTrabajadorId;
    }

    public DescuentoTrabajador(Integer descuentoTrabajadorId, Date descuentoTrabajadorFechaInicio, Date descuentoTrabajadorFechaTermino, int descuentoTrabajadorCuotaActual, int descuentoTrabajadorTotalCuotas, int descuentoTrabajadorMontoFijo, boolean descuentoTrabajadorActivo) {
        this.descuentoTrabajadorId = descuentoTrabajadorId;
        this.descuentoTrabajadorFechaInicio = descuentoTrabajadorFechaInicio;
        this.descuentoTrabajadorFechaTermino = descuentoTrabajadorFechaTermino;
        this.descuentoTrabajadorCuotaActual = descuentoTrabajadorCuotaActual;
        this.descuentoTrabajadorTotalCuotas = descuentoTrabajadorTotalCuotas;
        this.descuentoTrabajadorMontoFijo = descuentoTrabajadorMontoFijo;
        this.descuentoTrabajadorActivo = descuentoTrabajadorActivo;
    }

    public Integer getDescuentoTrabajadorId() {
        return descuentoTrabajadorId;
    }

    public void setDescuentoTrabajadorId(Integer descuentoTrabajadorId) {
        this.descuentoTrabajadorId = descuentoTrabajadorId;
    }

    public Date getDescuentoTrabajadorFechaInicio() {
        return descuentoTrabajadorFechaInicio;
    }

    public void setDescuentoTrabajadorFechaInicio(Date descuentoTrabajadorFechaInicio) {
        this.descuentoTrabajadorFechaInicio = descuentoTrabajadorFechaInicio;
    }

    public Date getDescuentoTrabajadorFechaTermino() {
        return descuentoTrabajadorFechaTermino;
    }

    public void setDescuentoTrabajadorFechaTermino(Date descuentoTrabajadorFechaTermino) {
        this.descuentoTrabajadorFechaTermino = descuentoTrabajadorFechaTermino;
    }

    public int getDescuentoTrabajadorCuotaActual() {
        return descuentoTrabajadorCuotaActual;
    }

    public void setDescuentoTrabajadorCuotaActual(int descuentoTrabajadorCuotaActual) {
        this.descuentoTrabajadorCuotaActual = descuentoTrabajadorCuotaActual;
    }

    public int getDescuentoTrabajadorTotalCuotas() {
        return descuentoTrabajadorTotalCuotas;
    }

    public void setDescuentoTrabajadorTotalCuotas(int descuentoTrabajadorTotalCuotas) {
        this.descuentoTrabajadorTotalCuotas = descuentoTrabajadorTotalCuotas;
    }

    public int getDescuentoTrabajadorMontoFijo() {
        return descuentoTrabajadorMontoFijo;
    }

    public void setDescuentoTrabajadorMontoFijo(int descuentoTrabajadorMontoFijo) {
        this.descuentoTrabajadorMontoFijo = descuentoTrabajadorMontoFijo;
    }

    public String getDescuentoTrabajadorDescripcion() {
        return descuentoTrabajadorDescripcion;
    }

    public void setDescuentoTrabajadorDescripcion(String descuentoTrabajadorDescripcion) {
        this.descuentoTrabajadorDescripcion = descuentoTrabajadorDescripcion;
    }

    public boolean getDescuentoTrabajadorActivo() {
        return descuentoTrabajadorActivo;
    }

    public void setDescuentoTrabajadorActivo(boolean descuentoTrabajadorActivo) {
        this.descuentoTrabajadorActivo = descuentoTrabajadorActivo;
    }

    @XmlTransient
    public List<DescuentoLiquidacion> getDescuentoLiquidacionList() {
        return descuentoLiquidacionList;
    }

    public void setDescuentoLiquidacionList(List<DescuentoLiquidacion> descuentoLiquidacionList) {
        this.descuentoLiquidacionList = descuentoLiquidacionList;
    }

    public TipoDescuentoTrabajador getDescuentoTrabajadorIdDescuento() {
        return descuentoTrabajadorIdDescuento;
    }

    public void setDescuentoTrabajadorIdDescuento(TipoDescuentoTrabajador descuentoTrabajadorIdDescuento) {
        this.descuentoTrabajadorIdDescuento = descuentoTrabajadorIdDescuento;
    }

    public Trabajador getDescuentoTrabajadorIdTrabajador() {
        return descuentoTrabajadorIdTrabajador;
    }

    public void setDescuentoTrabajadorIdTrabajador(Trabajador descuentoTrabajadorIdTrabajador) {
        this.descuentoTrabajadorIdTrabajador = descuentoTrabajadorIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (descuentoTrabajadorId != null ? descuentoTrabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescuentoTrabajador)) {
            return false;
        }
        DescuentoTrabajador other = (DescuentoTrabajador) object;
        if ((this.descuentoTrabajadorId == null && other.descuentoTrabajadorId != null) || (this.descuentoTrabajadorId != null && !this.descuentoTrabajadorId.equals(other.descuentoTrabajadorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DescuentoTrabajador[ descuentoTrabajadorId=" + descuentoTrabajadorId + " ]";
    }
    
}
