/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "log", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l")
    , @NamedQuery(name = "Log.findByLogId", query = "SELECT l FROM Log l WHERE l.logId = :logId")
    , @NamedQuery(name = "Log.findByLogTipoAccion", query = "SELECT l FROM Log l WHERE l.logTipoAccion = :logTipoAccion")
    , @NamedQuery(name = "Log.findByLogDescripcion", query = "SELECT l FROM Log l WHERE l.logDescripcion = :logDescripcion")
    })
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "log_id")
    private Integer logId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "log_tipo_accion")
    private String logTipoAccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "log_descripcion")
    private String logDescripcion;    
    @JoinColumn(name = "log_id_privilegio", referencedColumnName = "privilegio_id", nullable = false)
    @ManyToOne(optional = false)
    private Privilegio logIdPrivilegio;
    @JoinColumn(name = "log_id_usuario", referencedColumnName = "usuario_id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario logIdUsuario;

    public Log() {
    }

    public Log(Integer logId) {
        this.logId = logId;
    }

    public Log(Integer logId, String logTipoAccion, String logDescripcion) {
        this.logId = logId;
        this.logTipoAccion = logTipoAccion;
        this.logDescripcion = logDescripcion;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogTipoAccion() {
        return logTipoAccion;
    }

    public void setLogTipoAccion(String logTipoAccion) {
        this.logTipoAccion = logTipoAccion;
    }

    public String getLogDescripcion() {
        return logDescripcion;
    }

    public void setLogDescripcion(String logDescripcion) {
        this.logDescripcion = logDescripcion;
    }

    public Privilegio getLogIdPrivilegio() {
        return logIdPrivilegio;
    }

    public void setLogIdPrivilegio(Privilegio logIdPrivilegio) {
        this.logIdPrivilegio = logIdPrivilegio;
    }

    public Usuario getLogIdUsuario() {
        return logIdUsuario;
    }

    public void setLogIdUsuario(Usuario logIdUsuario) {
        this.logIdUsuario = logIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Log[ logId=" + logId + " ]";
    }
    
}
