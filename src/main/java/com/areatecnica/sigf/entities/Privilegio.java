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
import javax.persistence.Cacheable;
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
import javax.persistence.OrderBy;
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
@Table(name = "privilegio", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilegio.findAll", query = "SELECT p FROM Privilegio p")
    , @NamedQuery(name = "Privilegio.findByPrivilegioId", query = "SELECT p FROM Privilegio p WHERE p.privilegioId = :privilegioId")
    , @NamedQuery(name = "Privilegio.findByPrivilegioNombre", query = "SELECT p FROM Privilegio p WHERE p.privilegioNombre = :privilegioNombre")
    , @NamedQuery(name = "Privilegio.findByPrivilegioDescripcion", query = "SELECT p FROM Privilegio p WHERE p.privilegioDescripcion = :privilegioDescripcion")
    , @NamedQuery(name = "Privilegio.findByPrivilegioMenuLink", query = "SELECT p FROM Privilegio p WHERE p.privilegioMenuLink = :privilegioMenuLink")
    , @NamedQuery(name = "Privilegio.findByPrivilegioTabla", query = "SELECT p FROM Privilegio p WHERE p.privilegioTabla = :privilegioTabla")
    , @NamedQuery(name = "Privilegio.findByPrivilegioIcon", query = "SELECT p FROM Privilegio p WHERE p.privilegioIcon = :privilegioIcon")
    , @NamedQuery(name = "Privilegio.findByPrivilegioNumeroOrden", query = "SELECT p FROM Privilegio p WHERE p.privilegioNumeroOrden = :privilegioNumeroOrden")
    , @NamedQuery(name = "Privilegio.findByPrivilegioColor", query = "SELECT p FROM Privilegio p WHERE p.privilegioColor = :privilegioColor")
    , @NamedQuery(name = "Privilegio.findByPrivilegioTipoMenu", query = "SELECT p FROM Privilegio p WHERE p.privilegioTipoMenu = :privilegioTipoMenu")})
public class Privilegio extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "privilegio_id")
    private Integer privilegioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "privilegio_nombre")
    private String privilegioNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "privilegio_descripcion")
    private String privilegioDescripcion;
    @Size(max = 100)
    @Column(name = "privilegio_menu_link")
    private String privilegioMenuLink;
    @Size(max = 100)
    @Column(name = "privilegio_tabla")
    private String privilegioTabla;
    @Size(max = 45)
    @Column(name = "privilegio_icon")
    private String privilegioIcon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "privilegio_numero_orden")
    @OrderBy("privilegioNumeroOrden")
    private int privilegioNumeroOrden;
    @Size(max = 10)
    @Column(name = "privilegio_color")
    private String privilegioColor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "privilegio_tipo_menu")
    private int privilegioTipoMenu;
    @JoinColumn(name = "privilegio_id_menu", referencedColumnName = "menu_id", nullable = false)
    @ManyToOne(optional = false)
    private Menu privilegioIdMenu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "logIdPrivilegio")
    private List<Log> logList;

    public Privilegio() {
    }

    public Privilegio(Integer privilegioId) {
        this.privilegioId = privilegioId;
    }

    public Privilegio(Integer privilegioId, String privilegioNombre, String privilegioDescripcion, int privilegioNumeroOrden, int privilegioTipoMenu) {
        this.privilegioId = privilegioId;
        this.privilegioNombre = privilegioNombre;
        this.privilegioDescripcion = privilegioDescripcion;
        this.privilegioNumeroOrden = privilegioNumeroOrden;
        this.privilegioTipoMenu = privilegioTipoMenu;
    }

    public Integer getPrivilegioId() {
        return privilegioId;
    }

    public void setPrivilegioId(Integer privilegioId) {
        this.privilegioId = privilegioId;
    }

    public String getPrivilegioNombre() {
        return privilegioNombre;
    }

    public void setPrivilegioNombre(String privilegioNombre) {
        this.privilegioNombre = privilegioNombre;
    }

    public String getPrivilegioDescripcion() {
        return privilegioDescripcion;
    }

    public void setPrivilegioDescripcion(String privilegioDescripcion) {
        this.privilegioDescripcion = privilegioDescripcion;
    }

    public String getPrivilegioMenuLink() {
        return privilegioMenuLink;
    }

    public void setPrivilegioMenuLink(String privilegioMenuLink) {
        this.privilegioMenuLink = privilegioMenuLink;
    }

    public String getPrivilegioTabla() {
        return privilegioTabla;
    }

    public void setPrivilegioTabla(String privilegioTabla) {
        this.privilegioTabla = privilegioTabla;
    }

    public String getPrivilegioIcon() {
        return privilegioIcon;
    }

    public void setPrivilegioIcon(String privilegioIcon) {
        this.privilegioIcon = privilegioIcon;
    }

    public int getPrivilegioNumeroOrden() {
        return privilegioNumeroOrden;
    }

    public void setPrivilegioNumeroOrden(int privilegioNumeroOrden) {
        this.privilegioNumeroOrden = privilegioNumeroOrden;
    }

    public String getPrivilegioColor() {
        return privilegioColor;
    }

    public void setPrivilegioColor(String privilegioColor) {
        this.privilegioColor = privilegioColor;
    }

    public int getPrivilegioTipoMenu() {
        return privilegioTipoMenu;
    }

    public void setPrivilegioTipoMenu(int privilegioTipoMenu) {
        this.privilegioTipoMenu = privilegioTipoMenu;
    }

    public Menu getPrivilegioIdMenu() {
        return privilegioIdMenu;
    }

    public void setPrivilegioIdMenu(Menu privilegioIdMenu) {
        this.privilegioIdMenu = privilegioIdMenu;
    }

    @XmlTransient
    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (privilegioId != null ? privilegioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilegio)) {
            return false;
        }
        Privilegio other = (Privilegio) object;
        if ((this.privilegioId == null && other.privilegioId != null) || (this.privilegioId != null && !this.privilegioId.equals(other.privilegioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Privilegio[ privilegioId=" + privilegioId + " ]";
    }
    
}
