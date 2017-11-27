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
@Table(name = "horario_servicio", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HorarioServicio.findAll", query = "SELECT h FROM HorarioServicio h")
    , @NamedQuery(name = "HorarioServicio.findByHorarioServicioId", query = "SELECT h FROM HorarioServicio h WHERE h.horarioServicioId = :horarioServicioId")
    , @NamedQuery(name = "HorarioServicio.findByHorarioServicioNombre", query = "SELECT h FROM HorarioServicio h WHERE h.horarioServicioNombre = :horarioServicioNombre")
    , @NamedQuery(name = "HorarioServicio.findByHorarioServicioDesde", query = "SELECT h FROM HorarioServicio h WHERE h.horarioServicioDesde = :horarioServicioDesde")
    , @NamedQuery(name = "HorarioServicio.findByHorarioServicioHasta", query = "SELECT h FROM HorarioServicio h WHERE h.horarioServicioHasta = :horarioServicioHasta")
    , @NamedQuery(name = "HorarioServicio.findByHorarioServicioLunes", query = "SELECT h FROM HorarioServicio h WHERE h.horarioServicioLunes = :horarioServicioLunes")
    , @NamedQuery(name = "HorarioServicio.findByHorarioServicioMartes", query = "SELECT h FROM HorarioServicio h WHERE h.horarioServicioMartes = :horarioServicioMartes")
    , @NamedQuery(name = "HorarioServicio.findByHorarioServicioMiercoles", query = "SELECT h FROM HorarioServicio h WHERE h.horarioServicioMiercoles = :horarioServicioMiercoles")
    , @NamedQuery(name = "HorarioServicio.findByHorarioServicioJueves", query = "SELECT h FROM HorarioServicio h WHERE h.horarioServicioJueves = :horarioServicioJueves")
    , @NamedQuery(name = "HorarioServicio.findByHorarioServicioViernes", query = "SELECT h FROM HorarioServicio h WHERE h.horarioServicioViernes = :horarioServicioViernes")
    , @NamedQuery(name = "HorarioServicio.findByHorarioServicioSabado", query = "SELECT h FROM HorarioServicio h WHERE h.horarioServicioSabado = :horarioServicioSabado")
    , @NamedQuery(name = "HorarioServicio.findByHorarioServicioDomingo", query = "SELECT h FROM HorarioServicio h WHERE h.horarioServicioDomingo = :horarioServicioDomingo")
    })
public class HorarioServicio extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "horario_servicio_id")
    private Integer horarioServicioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "horario_servicio_nombre")
    private String horarioServicioNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_servicio_desde")
    @Temporal(TemporalType.TIME)
    private Date horarioServicioDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_servicio_hasta")
    @Temporal(TemporalType.TIME)
    private Date horarioServicioHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_servicio_lunes")
    private boolean horarioServicioLunes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_servicio_martes")
    private boolean horarioServicioMartes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_servicio_miercoles")
    private boolean horarioServicioMiercoles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_servicio_jueves")
    private boolean horarioServicioJueves;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_servicio_viernes")
    private boolean horarioServicioViernes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_servicio_sabado")
    private boolean horarioServicioSabado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_servicio_domingo")
    private boolean horarioServicioDomingo;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "controlHorarioServicioIdHorario")
    private List<ControlHorarioServicio> controlHorarioServicioList;
    @JoinColumn(name = "horario_servicio_id_servicio", referencedColumnName = "servicio_id", nullable = false)
    @ManyToOne(optional = false)
    private Servicio horarioServicioIdServicio;
    @JoinColumn(name = "horario_servicio_id_tipo_estacionalidad", referencedColumnName = "tipo_estacionalidad_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoEstacionalidad horarioServicioIdTipoEstacionalidad;

    public HorarioServicio() {
    }

    public HorarioServicio(Integer horarioServicioId) {
        this.horarioServicioId = horarioServicioId;
    }

    public HorarioServicio(Integer horarioServicioId, String horarioServicioNombre, Date horarioServicioDesde, Date horarioServicioHasta, boolean horarioServicioLunes, boolean horarioServicioMartes, boolean horarioServicioMiercoles, boolean horarioServicioJueves, boolean horarioServicioViernes, boolean horarioServicioSabado, boolean horarioServicioDomingo) {
        this.horarioServicioId = horarioServicioId;
        this.horarioServicioNombre = horarioServicioNombre;
        this.horarioServicioDesde = horarioServicioDesde;
        this.horarioServicioHasta = horarioServicioHasta;
        this.horarioServicioLunes = horarioServicioLunes;
        this.horarioServicioMartes = horarioServicioMartes;
        this.horarioServicioMiercoles = horarioServicioMiercoles;
        this.horarioServicioJueves = horarioServicioJueves;
        this.horarioServicioViernes = horarioServicioViernes;
        this.horarioServicioSabado = horarioServicioSabado;
        this.horarioServicioDomingo = horarioServicioDomingo;
    }

    public Integer getHorarioServicioId() {
        return horarioServicioId;
    }

    public void setHorarioServicioId(Integer horarioServicioId) {
        this.horarioServicioId = horarioServicioId;
    }

    public String getHorarioServicioNombre() {
        return horarioServicioNombre;
    }

    public void setHorarioServicioNombre(String horarioServicioNombre) {
        this.horarioServicioNombre = horarioServicioNombre;
    }

    public Date getHorarioServicioDesde() {
        return horarioServicioDesde;
    }

    public void setHorarioServicioDesde(Date horarioServicioDesde) {
        this.horarioServicioDesde = horarioServicioDesde;
    }

    public Date getHorarioServicioHasta() {
        return horarioServicioHasta;
    }

    public void setHorarioServicioHasta(Date horarioServicioHasta) {
        this.horarioServicioHasta = horarioServicioHasta;
    }

    public boolean getHorarioServicioLunes() {
        return horarioServicioLunes;
    }

    public void setHorarioServicioLunes(boolean horarioServicioLunes) {
        this.horarioServicioLunes = horarioServicioLunes;
    }

    public boolean getHorarioServicioMartes() {
        return horarioServicioMartes;
    }

    public void setHorarioServicioMartes(boolean horarioServicioMartes) {
        this.horarioServicioMartes = horarioServicioMartes;
    }

    public boolean getHorarioServicioMiercoles() {
        return horarioServicioMiercoles;
    }

    public void setHorarioServicioMiercoles(boolean horarioServicioMiercoles) {
        this.horarioServicioMiercoles = horarioServicioMiercoles;
    }

    public boolean getHorarioServicioJueves() {
        return horarioServicioJueves;
    }

    public void setHorarioServicioJueves(boolean horarioServicioJueves) {
        this.horarioServicioJueves = horarioServicioJueves;
    }

    public boolean getHorarioServicioViernes() {
        return horarioServicioViernes;
    }

    public void setHorarioServicioViernes(boolean horarioServicioViernes) {
        this.horarioServicioViernes = horarioServicioViernes;
    }

    public boolean getHorarioServicioSabado() {
        return horarioServicioSabado;
    }

    public void setHorarioServicioSabado(boolean horarioServicioSabado) {
        this.horarioServicioSabado = horarioServicioSabado;
    }

    public boolean getHorarioServicioDomingo() {
        return horarioServicioDomingo;
    }

    public void setHorarioServicioDomingo(boolean horarioServicioDomingo) {
        this.horarioServicioDomingo = horarioServicioDomingo;
    }

    @XmlTransient
    public List<ControlHorarioServicio> getControlHorarioServicioList() {
        return controlHorarioServicioList;
    }

    public void setControlHorarioServicioList(List<ControlHorarioServicio> controlHorarioServicioList) {
        this.controlHorarioServicioList = controlHorarioServicioList;
    }

    public Servicio getHorarioServicioIdServicio() {
        return horarioServicioIdServicio;
    }

    public void setHorarioServicioIdServicio(Servicio horarioServicioIdServicio) {
        this.horarioServicioIdServicio = horarioServicioIdServicio;
    }

    public TipoEstacionalidad getHorarioServicioIdTipoEstacionalidad() {
        return horarioServicioIdTipoEstacionalidad;
    }

    public void setHorarioServicioIdTipoEstacionalidad(TipoEstacionalidad horarioServicioIdTipoEstacionalidad) {
        this.horarioServicioIdTipoEstacionalidad = horarioServicioIdTipoEstacionalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horarioServicioId != null ? horarioServicioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioServicio)) {
            return false;
        }
        HorarioServicio other = (HorarioServicio) object;
        if ((this.horarioServicioId == null && other.horarioServicioId != null) || (this.horarioServicioId != null && !this.horarioServicioId.equals(other.horarioServicioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.HorarioServicio[ horarioServicioId=" + horarioServicioId + " ]";
    }
    
}
