/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "tarifa_grupo_servicio", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TarifaGrupoServicio.findAll", query = "SELECT t FROM TarifaGrupoServicio t")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioId", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioId = :tarifaGrupoServicioId")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioValor", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioValor = :tarifaGrupoServicioValor")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioFecha", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioFecha = :tarifaGrupoServicioFecha")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioActiva", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioActiva = :tarifaGrupoServicioActiva")
    })
public class TarifaGrupoServicio extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tarifa_grupo_servicio_id")
    private Integer tarifaGrupoServicioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tarifa_grupo_servicio_valor")
    private int tarifaGrupoServicioValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tarifa_grupo_servicio_fecha")
    @Temporal(TemporalType.DATE)
    private Date tarifaGrupoServicioFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tarifa_grupo_servicio_activa")
    private boolean tarifaGrupoServicioActiva;    
    @JoinColumn(name = "tarifa_grupo_servicio_id_boleto", referencedColumnName = "boleto_id", nullable = false)
    @ManyToOne(optional = false)
    private Boleto tarifaGrupoServicioIdBoleto;
    @JoinColumn(name = "tarifa_grupo_servicio_id_grupo", referencedColumnName = "grupo_servicio_id", nullable = false)
    @ManyToOne(optional = false)
    private GrupoServicio tarifaGrupoServicioIdGrupo;

    public TarifaGrupoServicio() {
    }

    public TarifaGrupoServicio(Integer tarifaGrupoServicioId) {
        this.tarifaGrupoServicioId = tarifaGrupoServicioId;
    }

    public TarifaGrupoServicio(Integer tarifaGrupoServicioId, int tarifaGrupoServicioValor, Date tarifaGrupoServicioFecha, boolean tarifaGrupoServicioActiva) {
        this.tarifaGrupoServicioId = tarifaGrupoServicioId;
        this.tarifaGrupoServicioValor = tarifaGrupoServicioValor;
        this.tarifaGrupoServicioFecha = tarifaGrupoServicioFecha;
        this.tarifaGrupoServicioActiva = tarifaGrupoServicioActiva;
    }

    public Integer getTarifaGrupoServicioId() {
        return tarifaGrupoServicioId;
    }

    public void setTarifaGrupoServicioId(Integer tarifaGrupoServicioId) {
        this.tarifaGrupoServicioId = tarifaGrupoServicioId;
    }

    public int getTarifaGrupoServicioValor() {
        return tarifaGrupoServicioValor;
    }

    public void setTarifaGrupoServicioValor(int tarifaGrupoServicioValor) {
        this.tarifaGrupoServicioValor = tarifaGrupoServicioValor;
    }

    public Date getTarifaGrupoServicioFecha() {
        return tarifaGrupoServicioFecha;
    }

    public void setTarifaGrupoServicioFecha(Date tarifaGrupoServicioFecha) {
        this.tarifaGrupoServicioFecha = tarifaGrupoServicioFecha;
    }

    public boolean getTarifaGrupoServicioActiva() {
        return tarifaGrupoServicioActiva;
    }

    public void setTarifaGrupoServicioActiva(boolean tarifaGrupoServicioActiva) {
        this.tarifaGrupoServicioActiva = tarifaGrupoServicioActiva;
    }

    public Boleto getTarifaGrupoServicioIdBoleto() {
        return tarifaGrupoServicioIdBoleto;
    }

    public void setTarifaGrupoServicioIdBoleto(Boleto tarifaGrupoServicioIdBoleto) {
        this.tarifaGrupoServicioIdBoleto = tarifaGrupoServicioIdBoleto;
    }

    public GrupoServicio getTarifaGrupoServicioIdGrupo() {
        return tarifaGrupoServicioIdGrupo;
    }

    public void setTarifaGrupoServicioIdGrupo(GrupoServicio tarifaGrupoServicioIdGrupo) {
        this.tarifaGrupoServicioIdGrupo = tarifaGrupoServicioIdGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tarifaGrupoServicioId != null ? tarifaGrupoServicioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarifaGrupoServicio)) {
            return false;
        }
        TarifaGrupoServicio other = (TarifaGrupoServicio) object;
        if ((this.tarifaGrupoServicioId == null && other.tarifaGrupoServicioId != null) || (this.tarifaGrupoServicioId != null && !this.tarifaGrupoServicioId.equals(other.tarifaGrupoServicioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TarifaGrupoServicio[ tarifaGrupoServicioId=" + tarifaGrupoServicioId + " ]";
    }
    
}
