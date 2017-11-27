/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "jornada_laboral", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JornadaLaboral.findAll", query = "SELECT j FROM JornadaLaboral j")
    , @NamedQuery(name = "JornadaLaboral.findByJornadaLaboralId", query = "SELECT j FROM JornadaLaboral j WHERE j.jornadaLaboralId = :jornadaLaboralId")
    , @NamedQuery(name = "JornadaLaboral.findByJornadaLaboralNombre", query = "SELECT j FROM JornadaLaboral j WHERE j.jornadaLaboralNombre = :jornadaLaboralNombre")
    , @NamedQuery(name = "JornadaLaboral.findByJornadaLaboralLunes", query = "SELECT j FROM JornadaLaboral j WHERE j.jornadaLaboralLunes = :jornadaLaboralLunes")
    , @NamedQuery(name = "JornadaLaboral.findByJornadaLaboralMartes", query = "SELECT j FROM JornadaLaboral j WHERE j.jornadaLaboralMartes = :jornadaLaboralMartes")
    , @NamedQuery(name = "JornadaLaboral.findByJornadaLaboralMiercoles", query = "SELECT j FROM JornadaLaboral j WHERE j.jornadaLaboralMiercoles = :jornadaLaboralMiercoles")
    , @NamedQuery(name = "JornadaLaboral.findByJornadaLaboralJueves", query = "SELECT j FROM JornadaLaboral j WHERE j.jornadaLaboralJueves = :jornadaLaboralJueves")
    , @NamedQuery(name = "JornadaLaboral.findByJornadaLaboralViernes", query = "SELECT j FROM JornadaLaboral j WHERE j.jornadaLaboralViernes = :jornadaLaboralViernes")
    , @NamedQuery(name = "JornadaLaboral.findByJornadaLaboralSabado", query = "SELECT j FROM JornadaLaboral j WHERE j.jornadaLaboralSabado = :jornadaLaboralSabado")
    , @NamedQuery(name = "JornadaLaboral.findByJornadaLaboralDomingo", query = "SELECT j FROM JornadaLaboral j WHERE j.jornadaLaboralDomingo = :jornadaLaboralDomingo")})
public class JornadaLaboral extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "jornada_laboral_id")
    private Integer jornadaLaboralId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "jornada_laboral_nombre")
    private String jornadaLaboralNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jornada_laboral_lunes")
    private boolean jornadaLaboralLunes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jornada_laboral_martes")
    private boolean jornadaLaboralMartes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jornada_laboral_miercoles")
    private boolean jornadaLaboralMiercoles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jornada_laboral_jueves")
    private boolean jornadaLaboralJueves;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jornada_laboral_viernes")
    private boolean jornadaLaboralViernes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jornada_laboral_sabado")
    private boolean jornadaLaboralSabado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jornada_laboral_domingo")
    private boolean jornadaLaboralDomingo;
    @JoinColumn(name = "jornada_laboral_id_horario_jornada", referencedColumnName = "horario_jornada_id", nullable = false)
    @ManyToOne(optional = false)
    private HorarioJornada jornadaLaboralIdHorarioJornada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jornadaTrabajadorIdJornadaLaboral")
    private List<JornadaTrabajador> jornadaTrabajadorList;

    public JornadaLaboral() {
    }

    public JornadaLaboral(Integer jornadaLaboralId) {
        this.jornadaLaboralId = jornadaLaboralId;
    }

    public JornadaLaboral(Integer jornadaLaboralId, String jornadaLaboralNombre, boolean jornadaLaboralLunes, boolean jornadaLaboralMartes, boolean jornadaLaboralMiercoles, boolean jornadaLaboralJueves, boolean jornadaLaboralViernes, boolean jornadaLaboralSabado, boolean jornadaLaboralDomingo) {
        this.jornadaLaboralId = jornadaLaboralId;
        this.jornadaLaboralNombre = jornadaLaboralNombre;
        this.jornadaLaboralLunes = jornadaLaboralLunes;
        this.jornadaLaboralMartes = jornadaLaboralMartes;
        this.jornadaLaboralMiercoles = jornadaLaboralMiercoles;
        this.jornadaLaboralJueves = jornadaLaboralJueves;
        this.jornadaLaboralViernes = jornadaLaboralViernes;
        this.jornadaLaboralSabado = jornadaLaboralSabado;
        this.jornadaLaboralDomingo = jornadaLaboralDomingo;
    }

    public Integer getJornadaLaboralId() {
        return jornadaLaboralId;
    }

    public void setJornadaLaboralId(Integer jornadaLaboralId) {
        this.jornadaLaboralId = jornadaLaboralId;
    }

    public String getJornadaLaboralNombre() {
        return jornadaLaboralNombre;
    }

    public void setJornadaLaboralNombre(String jornadaLaboralNombre) {
        this.jornadaLaboralNombre = jornadaLaboralNombre;
    }

    public boolean getJornadaLaboralLunes() {
        return jornadaLaboralLunes;
    }

    public void setJornadaLaboralLunes(boolean jornadaLaboralLunes) {
        this.jornadaLaboralLunes = jornadaLaboralLunes;
    }

    public boolean getJornadaLaboralMartes() {
        return jornadaLaboralMartes;
    }

    public void setJornadaLaboralMartes(boolean jornadaLaboralMartes) {
        this.jornadaLaboralMartes = jornadaLaboralMartes;
    }

    public boolean getJornadaLaboralMiercoles() {
        return jornadaLaboralMiercoles;
    }

    public void setJornadaLaboralMiercoles(boolean jornadaLaboralMiercoles) {
        this.jornadaLaboralMiercoles = jornadaLaboralMiercoles;
    }

    public boolean getJornadaLaboralJueves() {
        return jornadaLaboralJueves;
    }

    public void setJornadaLaboralJueves(boolean jornadaLaboralJueves) {
        this.jornadaLaboralJueves = jornadaLaboralJueves;
    }

    public boolean getJornadaLaboralViernes() {
        return jornadaLaboralViernes;
    }

    public void setJornadaLaboralViernes(boolean jornadaLaboralViernes) {
        this.jornadaLaboralViernes = jornadaLaboralViernes;
    }

    public boolean getJornadaLaboralSabado() {
        return jornadaLaboralSabado;
    }

    public void setJornadaLaboralSabado(boolean jornadaLaboralSabado) {
        this.jornadaLaboralSabado = jornadaLaboralSabado;
    }

    public boolean getJornadaLaboralDomingo() {
        return jornadaLaboralDomingo;
    }

    public void setJornadaLaboralDomingo(boolean jornadaLaboralDomingo) {
        this.jornadaLaboralDomingo = jornadaLaboralDomingo;
    }

    public HorarioJornada getJornadaLaboralIdHorarioJornada() {
        return jornadaLaboralIdHorarioJornada;
    }

    public void setJornadaLaboralIdHorarioJornada(HorarioJornada jornadaLaboralIdHorarioJornada) {
        this.jornadaLaboralIdHorarioJornada = jornadaLaboralIdHorarioJornada;
    }

    @XmlTransient
    public List<JornadaTrabajador> getJornadaTrabajadorList() {
        return jornadaTrabajadorList;
    }

    public void setJornadaTrabajadorList(List<JornadaTrabajador> jornadaTrabajadorList) {
        this.jornadaTrabajadorList = jornadaTrabajadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jornadaLaboralId != null ? jornadaLaboralId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JornadaLaboral)) {
            return false;
        }
        JornadaLaboral other = (JornadaLaboral) object;
        if ((this.jornadaLaboralId == null && other.jornadaLaboralId != null) || (this.jornadaLaboralId != null && !this.jornadaLaboralId.equals(other.jornadaLaboralId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.JornadaLaboral[ jornadaLaboralId=" + jornadaLaboralId + " ]";
    }
    
}
