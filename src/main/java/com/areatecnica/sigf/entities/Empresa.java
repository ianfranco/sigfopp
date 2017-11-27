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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "empresa", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findAllByCuenta", query = "SELECT e FROM Empresa e WHERE e.empresaIdCuenta = :idCuenta")
    , @NamedQuery(name = "Empresa.findByEmpresaId", query = "SELECT e FROM Empresa e WHERE e.empresaId = :empresaId")
    , @NamedQuery(name = "Empresa.findByEmpresaRut", query = "SELECT e FROM Empresa e WHERE e.empresaRut = :empresaRut")
    , @NamedQuery(name = "Empresa.findByEmpresaNombre", query = "SELECT e FROM Empresa e WHERE e.empresaNombre = :empresaNombre")
    , @NamedQuery(name = "Empresa.findByEmpresaGiro", query = "SELECT e FROM Empresa e WHERE e.empresaGiro = :empresaGiro")
    , @NamedQuery(name = "Empresa.findByEmpresaDireccion", query = "SELECT e FROM Empresa e WHERE e.empresaDireccion = :empresaDireccion")
    , @NamedQuery(name = "Empresa.findByEmpresaTelefono", query = "SELECT e FROM Empresa e WHERE e.empresaTelefono = :empresaTelefono")
    , @NamedQuery(name = "Empresa.findByEmpresaCelular", query = "SELECT e FROM Empresa e WHERE e.empresaCelular = :empresaCelular")
    , @NamedQuery(name = "Empresa.findByEmpresaEmail", query = "SELECT e FROM Empresa e WHERE e.empresaEmail = :empresaEmail")
    , @NamedQuery(name = "Empresa.findByEmpresaPorcentajeMutual", query = "SELECT e FROM Empresa e WHERE e.empresaPorcentajeMutual = :empresaPorcentajeMutual")
    })
public class Empresa extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "empresa_id")
    private Integer empresaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "empresa_rut")
    private String empresaRut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "empresa_nombre")
    private String empresaNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "empresa_giro")
    private String empresaGiro;
    @Size(max = 255)
    @Column(name = "empresa_direccion")
    private String empresaDireccion;
    @Size(max = 255)
    @Column(name = "empresa_telefono")
    private String empresaTelefono;
    @Size(max = 100)
    @Column(name = "empresa_celular")
    private String empresaCelular;
    @Size(max = 100)
    @Column(name = "empresa_email")
    private String empresaEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "empresa_porcentaje_mutual")
    private float empresaPorcentajeMutual;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "liquidacionEmpresaIdEmpresa")
    private List<LiquidacionEmpresa> liquidacionEmpresaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "representanteEmpresaIdEmpresa")
    private List<RepresentanteEmpresa> representanteEmpresaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaBancoEmpresaIdEmpresa")
    private List<CuentaBancoEmpresa> cuentaBancoEmpresaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "liquidacionSueldoIdEmpresa")
    private List<LiquidacionSueldo> liquidacionSueldoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reconocimientoDeudaIdEmpresa")
    private List<ReconocimientoDeuda> reconocimientoDeudaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busIdEmpresa")
    private List<Bus> busList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relacionLaboralIdEmpresa")
    private List<RelacionLaboral> relacionLaboralList;
    @JoinColumn(name = "empresa_id_caja_compensacion", referencedColumnName = "caja_compensacion_id", nullable = false)
    @ManyToOne(optional = false)
    private CajaCompensacion empresaIdCajaCompensacion;
    @JoinColumn(name = "empresa_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta empresaIdCuenta;
    @JoinColumn(name = "empresa_id_mutual", referencedColumnName = "mutual_id", nullable = false)
    @ManyToOne(optional = false)
    private Mutual empresaIdMutual;

    public Empresa() {
    }

    public Empresa(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public Empresa(Integer empresaId, String empresaRut, String empresaNombre, String empresaGiro, float empresaPorcentajeMutual) {
        this.empresaId = empresaId;
        this.empresaRut = empresaRut;
        this.empresaNombre = empresaNombre;
        this.empresaGiro = empresaGiro;
        this.empresaPorcentajeMutual = empresaPorcentajeMutual;
    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaRut() {
        return empresaRut;
    }

    public void setEmpresaRut(String empresaRut) {
        this.empresaRut = empresaRut;
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    public String getEmpresaGiro() {
        return empresaGiro;
    }

    public void setEmpresaGiro(String empresaGiro) {
        this.empresaGiro = empresaGiro;
    }

    public String getEmpresaDireccion() {
        return empresaDireccion;
    }

    public void setEmpresaDireccion(String empresaDireccion) {
        this.empresaDireccion = empresaDireccion;
    }

    public String getEmpresaTelefono() {
        return empresaTelefono;
    }

    public void setEmpresaTelefono(String empresaTelefono) {
        this.empresaTelefono = empresaTelefono;
    }

    public String getEmpresaCelular() {
        return empresaCelular;
    }

    public void setEmpresaCelular(String empresaCelular) {
        this.empresaCelular = empresaCelular;
    }

    public String getEmpresaEmail() {
        return empresaEmail;
    }

    public void setEmpresaEmail(String empresaEmail) {
        this.empresaEmail = empresaEmail;
    }

    public float getEmpresaPorcentajeMutual() {
        return empresaPorcentajeMutual;
    }

    public void setEmpresaPorcentajeMutual(float empresaPorcentajeMutual) {
        this.empresaPorcentajeMutual = empresaPorcentajeMutual;
    }

    @XmlTransient
    public List<LiquidacionEmpresa> getLiquidacionEmpresaList() {
        return liquidacionEmpresaList;
    }

    public void setLiquidacionEmpresaList(List<LiquidacionEmpresa> liquidacionEmpresaList) {
        this.liquidacionEmpresaList = liquidacionEmpresaList;
    }

    @XmlTransient
    public List<RepresentanteEmpresa> getRepresentanteEmpresaList() {
        return representanteEmpresaList;
    }

    public void setRepresentanteEmpresaList(List<RepresentanteEmpresa> representanteEmpresaList) {
        this.representanteEmpresaList = representanteEmpresaList;
    }

    @XmlTransient
    public List<CuentaBancoEmpresa> getCuentaBancoEmpresaList() {
        return cuentaBancoEmpresaList;
    }

    public void setCuentaBancoEmpresaList(List<CuentaBancoEmpresa> cuentaBancoEmpresaList) {
        this.cuentaBancoEmpresaList = cuentaBancoEmpresaList;
    }

    @XmlTransient
    public List<LiquidacionSueldo> getLiquidacionSueldoList() {
        return liquidacionSueldoList;
    }

    public void setLiquidacionSueldoList(List<LiquidacionSueldo> liquidacionSueldoList) {
        this.liquidacionSueldoList = liquidacionSueldoList;
    }

    @XmlTransient
    public List<ReconocimientoDeuda> getReconocimientoDeudaList() {
        return reconocimientoDeudaList;
    }

    public void setReconocimientoDeudaList(List<ReconocimientoDeuda> reconocimientoDeudaList) {
        this.reconocimientoDeudaList = reconocimientoDeudaList;
    }

    @XmlTransient
    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    @XmlTransient
    public List<RelacionLaboral> getRelacionLaboralList() {
        return relacionLaboralList;
    }

    public void setRelacionLaboralList(List<RelacionLaboral> relacionLaboralList) {
        this.relacionLaboralList = relacionLaboralList;
    }

    public CajaCompensacion getEmpresaIdCajaCompensacion() {
        return empresaIdCajaCompensacion;
    }

    public void setEmpresaIdCajaCompensacion(CajaCompensacion empresaIdCajaCompensacion) {
        this.empresaIdCajaCompensacion = empresaIdCajaCompensacion;
    }

    public Cuenta getEmpresaIdCuenta() {
        return empresaIdCuenta;
    }

    public void setEmpresaIdCuenta(Cuenta empresaIdCuenta) {
        this.empresaIdCuenta = empresaIdCuenta;
    }

    public Mutual getEmpresaIdMutual() {
        return empresaIdMutual;
    }

    public void setEmpresaIdMutual(Mutual empresaIdMutual) {
        this.empresaIdMutual = empresaIdMutual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empresaId != null ? empresaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.empresaId == null && other.empresaId != null) || (this.empresaId != null && !this.empresaId.equals(other.empresaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Empresa[ empresaId=" + empresaId + " ]";
    }

}
