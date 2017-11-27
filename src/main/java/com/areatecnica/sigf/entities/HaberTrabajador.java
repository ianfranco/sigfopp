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
@Table(name = "haber_trabajador", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HaberTrabajador.findAll", query = "SELECT h FROM HaberTrabajador h")
    , @NamedQuery(name = "HaberTrabajador.findByHaberTrabajadorId", query = "SELECT h FROM HaberTrabajador h WHERE h.haberTrabajadorId = :haberTrabajadorId")
    , @NamedQuery(name = "HaberTrabajador.findByHaberTrabajadorFechaInicio", query = "SELECT h FROM HaberTrabajador h WHERE h.haberTrabajadorFechaInicio = :haberTrabajadorFechaInicio")
    , @NamedQuery(name = "HaberTrabajador.findByHaberTrabajadorFechaTermino", query = "SELECT h FROM HaberTrabajador h WHERE h.haberTrabajadorFechaTermino = :haberTrabajadorFechaTermino")
    , @NamedQuery(name = "HaberTrabajador.findByHaberTrabajadorCuotaActual", query = "SELECT h FROM HaberTrabajador h WHERE h.haberTrabajadorCuotaActual = :haberTrabajadorCuotaActual")
    , @NamedQuery(name = "HaberTrabajador.findByHaberTrabajadorTotalCuotas", query = "SELECT h FROM HaberTrabajador h WHERE h.haberTrabajadorTotalCuotas = :haberTrabajadorTotalCuotas")
    , @NamedQuery(name = "HaberTrabajador.findByHaberTrabajadorMontoFijo", query = "SELECT h FROM HaberTrabajador h WHERE h.haberTrabajadorMontoFijo = :haberTrabajadorMontoFijo")
    , @NamedQuery(name = "HaberTrabajador.findByHaberTrabajadorDescripcion", query = "SELECT h FROM HaberTrabajador h WHERE h.haberTrabajadorDescripcion = :haberTrabajadorDescripcion")
    , @NamedQuery(name = "HaberTrabajador.findByHaberTrabajadorActivo", query = "SELECT h FROM HaberTrabajador h WHERE h.haberTrabajadorActivo = :haberTrabajadorActivo")
    })
public class HaberTrabajador extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "haber_trabajador_id")
    private Integer haberTrabajadorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "haber_trabajador_fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date haberTrabajadorFechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "haber_trabajador_fecha_termino")
    @Temporal(TemporalType.DATE)
    private Date haberTrabajadorFechaTermino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "haber_trabajador_cuota_actual")
    private int haberTrabajadorCuotaActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "haber_trabajador_total_cuotas")
    private int haberTrabajadorTotalCuotas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "haber_trabajador_monto_fijo")
    private int haberTrabajadorMontoFijo;
    @Size(max = 45)
    @Column(name = "haber_trabajador_descripcion")
    private String haberTrabajadorDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "haber_trabajador_activo")
    private boolean haberTrabajadorActivo;
    @JoinColumn(name = "haber_trabajador_id_haber", referencedColumnName = "tipo_haber_trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoHaberTrabajador haberTrabajadorIdHaber;
    @JoinColumn(name = "haber_trabajador_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador haberTrabajadorIdTrabajador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "haberLiquidacionIdHaber")
    private List<HaberLiquidacion> haberLiquidacionList;

    public HaberTrabajador() {
    }

    public HaberTrabajador(Integer haberTrabajadorId) {
        this.haberTrabajadorId = haberTrabajadorId;
    }

    public HaberTrabajador(Integer haberTrabajadorId, Date haberTrabajadorFechaInicio, Date haberTrabajadorFechaTermino, int haberTrabajadorCuotaActual, int haberTrabajadorTotalCuotas, int haberTrabajadorMontoFijo, boolean haberTrabajadorActivo) {
        this.haberTrabajadorId = haberTrabajadorId;
        this.haberTrabajadorFechaInicio = haberTrabajadorFechaInicio;
        this.haberTrabajadorFechaTermino = haberTrabajadorFechaTermino;
        this.haberTrabajadorCuotaActual = haberTrabajadorCuotaActual;
        this.haberTrabajadorTotalCuotas = haberTrabajadorTotalCuotas;
        this.haberTrabajadorMontoFijo = haberTrabajadorMontoFijo;
        this.haberTrabajadorActivo = haberTrabajadorActivo;
    }

    public Integer getHaberTrabajadorId() {
        return haberTrabajadorId;
    }

    public void setHaberTrabajadorId(Integer haberTrabajadorId) {
        this.haberTrabajadorId = haberTrabajadorId;
    }

    public Date getHaberTrabajadorFechaInicio() {
        return haberTrabajadorFechaInicio;
    }

    public void setHaberTrabajadorFechaInicio(Date haberTrabajadorFechaInicio) {
        this.haberTrabajadorFechaInicio = haberTrabajadorFechaInicio;
    }

    public Date getHaberTrabajadorFechaTermino() {
        return haberTrabajadorFechaTermino;
    }

    public void setHaberTrabajadorFechaTermino(Date haberTrabajadorFechaTermino) {
        this.haberTrabajadorFechaTermino = haberTrabajadorFechaTermino;
    }

    public int getHaberTrabajadorCuotaActual() {
        return haberTrabajadorCuotaActual;
    }

    public void setHaberTrabajadorCuotaActual(int haberTrabajadorCuotaActual) {
        this.haberTrabajadorCuotaActual = haberTrabajadorCuotaActual;
    }

    public int getHaberTrabajadorTotalCuotas() {
        return haberTrabajadorTotalCuotas;
    }

    public void setHaberTrabajadorTotalCuotas(int haberTrabajadorTotalCuotas) {
        this.haberTrabajadorTotalCuotas = haberTrabajadorTotalCuotas;
    }

    public int getHaberTrabajadorMontoFijo() {
        return haberTrabajadorMontoFijo;
    }

    public void setHaberTrabajadorMontoFijo(int haberTrabajadorMontoFijo) {
        this.haberTrabajadorMontoFijo = haberTrabajadorMontoFijo;
    }

    public String getHaberTrabajadorDescripcion() {
        return haberTrabajadorDescripcion;
    }

    public void setHaberTrabajadorDescripcion(String haberTrabajadorDescripcion) {
        this.haberTrabajadorDescripcion = haberTrabajadorDescripcion;
    }

    public boolean getHaberTrabajadorActivo() {
        return haberTrabajadorActivo;
    }

    public void setHaberTrabajadorActivo(boolean haberTrabajadorActivo) {
        this.haberTrabajadorActivo = haberTrabajadorActivo;
    }
    
    public TipoHaberTrabajador getHaberTrabajadorIdHaber() {
        return haberTrabajadorIdHaber;
    }

    public void setHaberTrabajadorIdHaber(TipoHaberTrabajador haberTrabajadorIdHaber) {
        this.haberTrabajadorIdHaber = haberTrabajadorIdHaber;
    }

    public Trabajador getHaberTrabajadorIdTrabajador() {
        return haberTrabajadorIdTrabajador;
    }

    public void setHaberTrabajadorIdTrabajador(Trabajador haberTrabajadorIdTrabajador) {
        this.haberTrabajadorIdTrabajador = haberTrabajadorIdTrabajador;
    }

    @XmlTransient
    public List<HaberLiquidacion> getHaberLiquidacionList() {
        return haberLiquidacionList;
    }

    public void setHaberLiquidacionList(List<HaberLiquidacion> haberLiquidacionList) {
        this.haberLiquidacionList = haberLiquidacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (haberTrabajadorId != null ? haberTrabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HaberTrabajador)) {
            return false;
        }
        HaberTrabajador other = (HaberTrabajador) object;
        if ((this.haberTrabajadorId == null && other.haberTrabajadorId != null) || (this.haberTrabajadorId != null && !this.haberTrabajadorId.equals(other.haberTrabajadorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.HaberTrabajador[ haberTrabajadorId=" + haberTrabajadorId + " ]";
    }
    
}
