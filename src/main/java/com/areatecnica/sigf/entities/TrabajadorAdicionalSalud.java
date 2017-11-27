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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "trabajador_adicional_salud", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrabajadorAdicionalSalud.findAll", query = "SELECT t FROM TrabajadorAdicionalSalud t")
    , @NamedQuery(name = "TrabajadorAdicionalSalud.findByTrabajadorAdicionalSaludId", query = "SELECT t FROM TrabajadorAdicionalSalud t WHERE t.trabajadorAdicionalSaludId = :trabajadorAdicionalSaludId")
    , @NamedQuery(name = "TrabajadorAdicionalSalud.findByTrabajadorAdicionalSaludPorcentaje", query = "SELECT t FROM TrabajadorAdicionalSalud t WHERE t.trabajadorAdicionalSaludPorcentaje = :trabajadorAdicionalSaludPorcentaje")
    , @NamedQuery(name = "TrabajadorAdicionalSalud.findByTrabajadorAdicionalSaludMontoFijo", query = "SELECT t FROM TrabajadorAdicionalSalud t WHERE t.trabajadorAdicionalSaludMontoFijo = :trabajadorAdicionalSaludMontoFijo")})
public class TrabajadorAdicionalSalud extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trabajador_adicional_salud_id")
    private Integer trabajadorAdicionalSaludId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trabajador_adicional_salud_porcentaje")
    private float trabajadorAdicionalSaludPorcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trabajador_adicional_salud_monto_fijo")
    private int trabajadorAdicionalSaludMontoFijo;
    @JoinColumn(name = "trabajador_adicional_salud_id_moneda", referencedColumnName = "moneda_pactada_institucion_salud_id", nullable = false)
    @ManyToOne(optional = false)
    private MonedaPactadaInstitucionSalud trabajadorAdicionalSaludIdMoneda;
    @JoinColumn(name = "trabajador_adicional_salud_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador trabajadorAdicionalSaludIdTrabajador;

    public TrabajadorAdicionalSalud() {
    }

    public TrabajadorAdicionalSalud(Integer trabajadorAdicionalSaludId) {
        this.trabajadorAdicionalSaludId = trabajadorAdicionalSaludId;
    }

    public TrabajadorAdicionalSalud(Integer trabajadorAdicionalSaludId, float trabajadorAdicionalSaludPorcentaje, int trabajadorAdicionalSaludMontoFijo) {
        this.trabajadorAdicionalSaludId = trabajadorAdicionalSaludId;
        this.trabajadorAdicionalSaludPorcentaje = trabajadorAdicionalSaludPorcentaje;
        this.trabajadorAdicionalSaludMontoFijo = trabajadorAdicionalSaludMontoFijo;
    }

    public Integer getTrabajadorAdicionalSaludId() {
        return trabajadorAdicionalSaludId;
    }

    public void setTrabajadorAdicionalSaludId(Integer trabajadorAdicionalSaludId) {
        this.trabajadorAdicionalSaludId = trabajadorAdicionalSaludId;
    }

    public float getTrabajadorAdicionalSaludPorcentaje() {
        return trabajadorAdicionalSaludPorcentaje;
    }

    public void setTrabajadorAdicionalSaludPorcentaje(float trabajadorAdicionalSaludPorcentaje) {
        this.trabajadorAdicionalSaludPorcentaje = trabajadorAdicionalSaludPorcentaje;
    }

    public int getTrabajadorAdicionalSaludMontoFijo() {
        return trabajadorAdicionalSaludMontoFijo;
    }

    public void setTrabajadorAdicionalSaludMontoFijo(int trabajadorAdicionalSaludMontoFijo) {
        this.trabajadorAdicionalSaludMontoFijo = trabajadorAdicionalSaludMontoFijo;
    }

    public MonedaPactadaInstitucionSalud getTrabajadorAdicionalSaludIdMoneda() {
        return trabajadorAdicionalSaludIdMoneda;
    }

    public void setTrabajadorAdicionalSaludIdMoneda(MonedaPactadaInstitucionSalud trabajadorAdicionalSaludIdMoneda) {
        this.trabajadorAdicionalSaludIdMoneda = trabajadorAdicionalSaludIdMoneda;
    }

    public Trabajador getTrabajadorAdicionalSaludIdTrabajador() {
        return trabajadorAdicionalSaludIdTrabajador;
    }

    public void setTrabajadorAdicionalSaludIdTrabajador(Trabajador trabajadorAdicionalSaludIdTrabajador) {
        this.trabajadorAdicionalSaludIdTrabajador = trabajadorAdicionalSaludIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trabajadorAdicionalSaludId != null ? trabajadorAdicionalSaludId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrabajadorAdicionalSalud)) {
            return false;
        }
        TrabajadorAdicionalSalud other = (TrabajadorAdicionalSalud) object;
        if ((this.trabajadorAdicionalSaludId == null && other.trabajadorAdicionalSaludId != null) || (this.trabajadorAdicionalSaludId != null && !this.trabajadorAdicionalSaludId.equals(other.trabajadorAdicionalSaludId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TrabajadorAdicionalSalud[ trabajadorAdicionalSaludId=" + trabajadorAdicionalSaludId + " ]";
    }
    
}
