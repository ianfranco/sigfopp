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
@Table(name = "cuenta", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c")
    , @NamedQuery(name = "Cuenta.findByCuentaId", query = "SELECT c FROM Cuenta c WHERE c.cuentaId = :cuentaId")
    , @NamedQuery(name = "Cuenta.findByCuentaAdministrador", query = "SELECT c FROM Cuenta c WHERE c.cuentaAdministrador = :cuentaAdministrador")
    , @NamedQuery(name = "Cuenta.findByCuentaRut", query = "SELECT c FROM Cuenta c WHERE c.cuentaRut = :cuentaRut")
    , @NamedQuery(name = "Cuenta.findByCuentaActiva", query = "SELECT c FROM Cuenta c WHERE c.cuentaActiva = :cuentaActiva")
    })
public class Cuenta extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuenta_id")
    private Integer cuentaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cuenta_administrador")
    private String cuentaAdministrador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "cuenta_rut")
    private String cuentaRut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuenta_activa")
    private boolean cuentaActiva;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horarioJornadaIdCuenta")
    private List<HorarioJornada> horarioJornadaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boletoIdCuenta")
    private List<Boleto> boletoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "valorMinutoIdCuenta")
    private List<ValorMinuto> valorMinutoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "representanteLegalIdCuenta")
    private List<RepresentanteLegal> representanteLegalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bancoIdCuenta")
    private List<Banco> bancoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoControlIdCuenta")
    private List<TipoControl> tipoControlList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "centroCostoIdCuenta")
    private List<CentroCosto> centroCostoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdCuenta")
    private List<Usuario> usuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignacionFamiliarIdCuenta")
    private List<AsignacionFamiliar> asignacionFamiliarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionSaludIdCuenta")
    private List<InstitucionSalud> institucionSaludList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flotaIdCuenta")
    private List<Flota> flotaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoIdCuenta")
    private List<Egreso> egresoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoObservacionIdCuenta")
    private List<TipoObservacion> tipoObservacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "administracionMensualIdCuenta")
    private List<AdministracionMensual> administracionMensualList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intervalosAdministracionIdCuenta")
    private List<IntervalosAdministracion> intervalosAdministracionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoHaberTrabajadorIdCuenta")
    private List<TipoHaberTrabajador> tipoHaberTrabajadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorIdCuenta")
    private List<Trabajador> trabajadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "precioCombustibleIdCuenta")
    private List<PrecioCombustible> precioCombustibleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionApvIdCuenta")
    private List<InstitucionApv> institucionApvList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoContratoIdCuenta")
    private List<TipoContrato> tipoContratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sindicatoIdCuenta")
    private List<Sindicato> sindicatoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraBoletoIdCuenta")
    private List<CompraBoleto> compraBoletoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoCargoIdCuenta")
    private List<TipoCargo> tipoCargoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDescuentoTrabajadorIdCuenta")
    private List<TipoDescuentoTrabajador> tipoDescuentoTrabajadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionPrevisionIdCuenta")
    private List<InstitucionPrevision> institucionPrevisionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoServicioIdCuenta")
    private List<GrupoServicio> grupoServicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cajaRecaudacionIdCuenta")
    private List<CajaRecaudacion> cajaRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraCombustibleIdCuenta")
    private List<CompraCombustible> compraCombustibleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mutualIdCuenta")
    private List<Mutual> mutualList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAbonoIdCuenta")
    private List<TipoAbono> tipoAbonoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaIdCuenta")
    private List<Empresa> empresaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadNegocioIdCuenta")
    private List<UnidadNegocio> unidadNegocioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procesoRecaudacionIdCuenta")
    private List<ProcesoRecaudacion> procesoRecaudacionList;
    @JoinColumn(name = "cuenta_id_tipo", referencedColumnName = "tipo_cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoCuenta cuentaIdTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioIdCuenta")
    private List<Servicio> servicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoTrabajadorIdCuenta")
    private List<TipoTrabajador> tipoTrabajadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cajaCompensacionIdCuenta")
    private List<CajaCompensacion> cajaCompensacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "controlIdCuenta")
    private List<Control> controlList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terminalIdCuenta")
    private List<Terminal> terminalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamentoIdCuenta")
    private List<Departamento> departamentoList;

    public Cuenta() {
    }

    public Cuenta(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }

    public Cuenta(Integer cuentaId, String cuentaAdministrador, String cuentaRut, boolean cuentaActiva) {
        this.cuentaId = cuentaId;
        this.cuentaAdministrador = cuentaAdministrador;
        this.cuentaRut = cuentaRut;
        this.cuentaActiva = cuentaActiva;
    }

    public Integer getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getCuentaAdministrador() {
        return cuentaAdministrador;
    }

    public void setCuentaAdministrador(String cuentaAdministrador) {
        this.cuentaAdministrador = cuentaAdministrador;
    }

    public String getCuentaRut() {
        return cuentaRut;
    }

    public void setCuentaRut(String cuentaRut) {
        this.cuentaRut = cuentaRut;
    }

    public boolean getCuentaActiva() {
        return cuentaActiva;
    }

    public void setCuentaActiva(boolean cuentaActiva) {
        this.cuentaActiva = cuentaActiva;
    }

    @XmlTransient
    public List<HorarioJornada> getHorarioJornadaList() {
        return horarioJornadaList;
    }

    public void setHorarioJornadaList(List<HorarioJornada> horarioJornadaList) {
        this.horarioJornadaList = horarioJornadaList;
    }

    @XmlTransient
    public List<Boleto> getBoletoList() {
        return boletoList;
    }

    public void setBoletoList(List<Boleto> boletoList) {
        this.boletoList = boletoList;
    }

    @XmlTransient
    public List<ValorMinuto> getValorMinutoList() {
        return valorMinutoList;
    }

    public void setValorMinutoList(List<ValorMinuto> valorMinutoList) {
        this.valorMinutoList = valorMinutoList;
    }

    @XmlTransient
    public List<RepresentanteLegal> getRepresentanteLegalList() {
        return representanteLegalList;
    }

    public void setRepresentanteLegalList(List<RepresentanteLegal> representanteLegalList) {
        this.representanteLegalList = representanteLegalList;
    }

    @XmlTransient
    public List<Banco> getBancoList() {
        return bancoList;
    }

    public void setBancoList(List<Banco> bancoList) {
        this.bancoList = bancoList;
    }

    @XmlTransient
    public List<TipoControl> getTipoControlList() {
        return tipoControlList;
    }

    public void setTipoControlList(List<TipoControl> tipoControlList) {
        this.tipoControlList = tipoControlList;
    }

    @XmlTransient
    public List<CentroCosto> getCentroCostoList() {
        return centroCostoList;
    }

    public void setCentroCostoList(List<CentroCosto> centroCostoList) {
        this.centroCostoList = centroCostoList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<AsignacionFamiliar> getAsignacionFamiliarList() {
        return asignacionFamiliarList;
    }

    public void setAsignacionFamiliarList(List<AsignacionFamiliar> asignacionFamiliarList) {
        this.asignacionFamiliarList = asignacionFamiliarList;
    }

    @XmlTransient
    public List<InstitucionSalud> getInstitucionSaludList() {
        return institucionSaludList;
    }

    public void setInstitucionSaludList(List<InstitucionSalud> institucionSaludList) {
        this.institucionSaludList = institucionSaludList;
    }

    @XmlTransient
    public List<Flota> getFlotaList() {
        return flotaList;
    }

    public void setFlotaList(List<Flota> flotaList) {
        this.flotaList = flotaList;
    }

    @XmlTransient
    public List<Egreso> getEgresoList() {
        return egresoList;
    }

    public void setEgresoList(List<Egreso> egresoList) {
        this.egresoList = egresoList;
    }

    @XmlTransient
    public List<TipoObservacion> getTipoObservacionList() {
        return tipoObservacionList;
    }

    public void setTipoObservacionList(List<TipoObservacion> tipoObservacionList) {
        this.tipoObservacionList = tipoObservacionList;
    }

    @XmlTransient
    public List<AdministracionMensual> getAdministracionMensualList() {
        return administracionMensualList;
    }

    public void setAdministracionMensualList(List<AdministracionMensual> administracionMensualList) {
        this.administracionMensualList = administracionMensualList;
    }

    @XmlTransient
    public List<IntervalosAdministracion> getIntervalosAdministracionList() {
        return intervalosAdministracionList;
    }

    public void setIntervalosAdministracionList(List<IntervalosAdministracion> intervalosAdministracionList) {
        this.intervalosAdministracionList = intervalosAdministracionList;
    }

    @XmlTransient
    public List<TipoHaberTrabajador> getTipoHaberTrabajadorList() {
        return tipoHaberTrabajadorList;
    }

    public void setTipoHaberTrabajadorList(List<TipoHaberTrabajador> tipoHaberTrabajadorList) {
        this.tipoHaberTrabajadorList = tipoHaberTrabajadorList;
    }

    @XmlTransient
    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    @XmlTransient
    public List<PrecioCombustible> getPrecioCombustibleList() {
        return precioCombustibleList;
    }

    public void setPrecioCombustibleList(List<PrecioCombustible> precioCombustibleList) {
        this.precioCombustibleList = precioCombustibleList;
    }

    @XmlTransient
    public List<InstitucionApv> getInstitucionApvList() {
        return institucionApvList;
    }

    public void setInstitucionApvList(List<InstitucionApv> institucionApvList) {
        this.institucionApvList = institucionApvList;
    }

    @XmlTransient
    public List<TipoContrato> getTipoContratoList() {
        return tipoContratoList;
    }

    public void setTipoContratoList(List<TipoContrato> tipoContratoList) {
        this.tipoContratoList = tipoContratoList;
    }

    @XmlTransient
    public List<Sindicato> getSindicatoList() {
        return sindicatoList;
    }

    public void setSindicatoList(List<Sindicato> sindicatoList) {
        this.sindicatoList = sindicatoList;
    }

    @XmlTransient
    public List<CompraBoleto> getCompraBoletoList() {
        return compraBoletoList;
    }

    public void setCompraBoletoList(List<CompraBoleto> compraBoletoList) {
        this.compraBoletoList = compraBoletoList;
    }

    @XmlTransient
    public List<TipoCargo> getTipoCargoList() {
        return tipoCargoList;
    }

    public void setTipoCargoList(List<TipoCargo> tipoCargoList) {
        this.tipoCargoList = tipoCargoList;
    }

    @XmlTransient
    public List<TipoDescuentoTrabajador> getTipoDescuentoTrabajadorList() {
        return tipoDescuentoTrabajadorList;
    }

    public void setTipoDescuentoTrabajadorList(List<TipoDescuentoTrabajador> tipoDescuentoTrabajadorList) {
        this.tipoDescuentoTrabajadorList = tipoDescuentoTrabajadorList;
    }

    @XmlTransient
    public List<InstitucionPrevision> getInstitucionPrevisionList() {
        return institucionPrevisionList;
    }

    public void setInstitucionPrevisionList(List<InstitucionPrevision> institucionPrevisionList) {
        this.institucionPrevisionList = institucionPrevisionList;
    }

    @XmlTransient
    public List<GrupoServicio> getGrupoServicioList() {
        return grupoServicioList;
    }

    public void setGrupoServicioList(List<GrupoServicio> grupoServicioList) {
        this.grupoServicioList = grupoServicioList;
    }

    @XmlTransient
    public List<CajaRecaudacion> getCajaRecaudacionList() {
        return cajaRecaudacionList;
    }

    public void setCajaRecaudacionList(List<CajaRecaudacion> cajaRecaudacionList) {
        this.cajaRecaudacionList = cajaRecaudacionList;
    }

    @XmlTransient
    public List<CompraCombustible> getCompraCombustibleList() {
        return compraCombustibleList;
    }

    public void setCompraCombustibleList(List<CompraCombustible> compraCombustibleList) {
        this.compraCombustibleList = compraCombustibleList;
    }

    @XmlTransient
    public List<Mutual> getMutualList() {
        return mutualList;
    }

    public void setMutualList(List<Mutual> mutualList) {
        this.mutualList = mutualList;
    }

    @XmlTransient
    public List<TipoAbono> getTipoAbonoList() {
        return tipoAbonoList;
    }

    public void setTipoAbonoList(List<TipoAbono> tipoAbonoList) {
        this.tipoAbonoList = tipoAbonoList;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @XmlTransient
    public List<UnidadNegocio> getUnidadNegocioList() {
        return unidadNegocioList;
    }

    public void setUnidadNegocioList(List<UnidadNegocio> unidadNegocioList) {
        this.unidadNegocioList = unidadNegocioList;
    }

    @XmlTransient
    public List<ProcesoRecaudacion> getProcesoRecaudacionList() {
        return procesoRecaudacionList;
    }

    public void setProcesoRecaudacionList(List<ProcesoRecaudacion> procesoRecaudacionList) {
        this.procesoRecaudacionList = procesoRecaudacionList;
    }

    public TipoCuenta getCuentaIdTipo() {
        return cuentaIdTipo;
    }

    public void setCuentaIdTipo(TipoCuenta cuentaIdTipo) {
        this.cuentaIdTipo = cuentaIdTipo;
    }

    @XmlTransient
    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    @XmlTransient
    public List<TipoTrabajador> getTipoTrabajadorList() {
        return tipoTrabajadorList;
    }

    public void setTipoTrabajadorList(List<TipoTrabajador> tipoTrabajadorList) {
        this.tipoTrabajadorList = tipoTrabajadorList;
    }

    @XmlTransient
    public List<CajaCompensacion> getCajaCompensacionList() {
        return cajaCompensacionList;
    }

    public void setCajaCompensacionList(List<CajaCompensacion> cajaCompensacionList) {
        this.cajaCompensacionList = cajaCompensacionList;
    }

    @XmlTransient
    public List<Control> getControlList() {
        return controlList;
    }

    public void setControlList(List<Control> controlList) {
        this.controlList = controlList;
    }

    @XmlTransient
    public List<Terminal> getTerminalList() {
        return terminalList;
    }

    public void setTerminalList(List<Terminal> terminalList) {
        this.terminalList = terminalList;
    }

    @XmlTransient
    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaId != null ? cuentaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.cuentaId == null && other.cuentaId != null) || (this.cuentaId != null && !this.cuentaId.equals(other.cuentaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Cuenta[ cuentaId=" + cuentaId + " ]";
    }
    
}
