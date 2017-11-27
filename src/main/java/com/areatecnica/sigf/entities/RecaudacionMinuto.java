/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "recaudacion_minuto", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecaudacionMinuto.findAll", query = "SELECT r FROM RecaudacionMinuto r")
    , @NamedQuery(name = "RecaudacionMinuto.findByRecaudacionMinutoId", query = "SELECT r FROM RecaudacionMinuto r WHERE r.recaudacionMinutoId = :recaudacionMinutoId")
    , @NamedQuery(name = "RecaudacionMinuto.findByFechaRecaudacionMinuto", query = "SELECT r FROM RecaudacionMinuto r WHERE r.recaudacionMinutoIdRecaudacion.recaudacionFecha = :recaudacionFecha")
    , @NamedQuery(name = "RecaudacionMinuto.findByFechaRecaudacionMinutoCaja", query = "SELECT r FROM RecaudacionMinuto r WHERE r.recaudacionMinutoIdRecaudacion.recaudacionFecha = :recaudacionFecha AND r.recaudacionMinutoIdRecaudacion.recaudacionIdCaja =:recaudacionIdCaja")
    , @NamedQuery(name = "RecaudacionMinuto.findByBusAndDate", query = "SELECT r FROM RecaudacionMinuto r WHERE r.recaudacionMinutoIdRecaudacion.recaudacionFecha = :recaudacionFecha AND r.recaudacionMinutoIdRecaudacion.recaudacionIdBus =:recaudacionIdBus")    
    , @NamedQuery(name = "RecaudacionMinuto.findByTerminalDate", query = "SELECT r FROM RecaudacionMinuto r WHERE r.recaudacionMinutoIdRecaudacion.recaudacionFecha = :recaudacionFecha AND r.recaudacionMinutoIdRecaudacion.recaudacionIdBus.busIdTerminal =:busIdTerminal")
    , @NamedQuery(name = "RecaudacionMinuto.findByRecaudacionMinutoMonto", query = "SELECT r FROM RecaudacionMinuto r WHERE r.recaudacionMinutoMonto = :recaudacionMinutoMonto")})
public class RecaudacionMinuto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recaudacion_minuto_id")
    private Integer recaudacionMinutoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recaudacion_minuto_monto")
    private int recaudacionMinutoMonto;
    @JoinColumn(name = "recaudacion_minuto_id_recaudacion", referencedColumnName = "recaudacion_id")
    @ManyToOne(optional = false)
    private Recaudacion recaudacionMinutoIdRecaudacion;
    @JoinColumn(name = "recaudacion_minuto_id_registro_minuto", referencedColumnName = "registro_minuto_id")
    @ManyToOne(optional = false)
    private RegistroMinuto recaudacionMinutoIdRegistroMinuto;

    public RecaudacionMinuto() {
    }

    public RecaudacionMinuto(Integer recaudacionMinutoId) {
        this.recaudacionMinutoId = recaudacionMinutoId;
    }

    public RecaudacionMinuto(Integer recaudacionMinutoId, int recaudacionMinutoMonto) {
        this.recaudacionMinutoId = recaudacionMinutoId;
        this.recaudacionMinutoMonto = recaudacionMinutoMonto;
    }

    public Integer getRecaudacionMinutoId() {
        return recaudacionMinutoId;
    }

    public void setRecaudacionMinutoId(Integer recaudacionMinutoId) {
        this.recaudacionMinutoId = recaudacionMinutoId;
    }

    public int getRecaudacionMinutoMonto() {
        return recaudacionMinutoMonto;
    }

    public void setRecaudacionMinutoMonto(int recaudacionMinutoMonto) {
        this.recaudacionMinutoMonto = recaudacionMinutoMonto;
    }

    public Recaudacion getRecaudacionMinutoIdRecaudacion() {
        return recaudacionMinutoIdRecaudacion;
    }

    public void setRecaudacionMinutoIdRecaudacion(Recaudacion recaudacionMinutoIdRecaudacion) {
        this.recaudacionMinutoIdRecaudacion = recaudacionMinutoIdRecaudacion;
    }

    public RegistroMinuto getRecaudacionMinutoIdRegistroMinuto() {
        return recaudacionMinutoIdRegistroMinuto;
    }

    public void setRecaudacionMinutoIdRegistroMinuto(RegistroMinuto recaudacionMinutoIdRegistroMinuto) {
        this.recaudacionMinutoIdRegistroMinuto = recaudacionMinutoIdRegistroMinuto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recaudacionMinutoId != null ? recaudacionMinutoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecaudacionMinuto)) {
            return false;
        }
        RecaudacionMinuto other = (RecaudacionMinuto) object;
        if ((this.recaudacionMinutoId == null && other.recaudacionMinutoId != null) || (this.recaudacionMinutoId != null && !this.recaudacionMinutoId.equals(other.recaudacionMinutoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RecaudacionMinuto[ recaudacionMinutoId=" + recaudacionMinutoId + " ]";
    }
    
}
