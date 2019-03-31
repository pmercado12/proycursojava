package bo.com.cognos.java.proyecto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DellPedro
 */
@Entity
@Table(name = "UBICACIONES_GEOGRAFICAS", catalog = "", schema = "DBADMIN")
@XmlRootElement
public class UbicacionesGeograficas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_UBIGEO", nullable = false)
    private Long idUbigeo;
    @Column(name = "UBIGEO", nullable = false)
    private int ubigeo;
    @Column(name = "DESC_UBIGEO", nullable = false, length = 110)
    private String descUbigeo;
    @Column(name = "SIGLA_UBIGEO", nullable = false, length = 15)
    private String siglaUbigeo;    
    @Column(name = "NIVEL", nullable = false, length = 10)
    private String nivel;    
    @Column(name = "FECHA_INI", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIni;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;    
    @Column(name = "API_ESTADO", nullable = false, length = 25)
    private String apiEstado;    
    @Column(name = "API_TRANSACCION", nullable = false, length = 30)
    private String apiTransaccion;    
    @Column(name = "USU_CRE", nullable = false, length = 30)
    private String usuCre;    
    @Column(name = "FEC_CRE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCre;    
    @Column(name = "USU_MOD", length = 30)
    private String usuMod;
    @Column(name = "FEC_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecMod;    
    @Column(name = "DISPOSICION_LEGAL", nullable = false, length = 240)
    private String disposicionLegal;    
    @Column(name = "ARCHIVO_DISPOSICION", nullable = false, length = 110)
    private String archivoDisposicion;    
    @Column(name = "DISP_FIN", nullable = false, length = 1)
    private String dispFin;
    @OneToMany(mappedBy = "idPadre", fetch = FetchType.LAZY)
    private List<UbicacionesGeograficas> ubicacionesGeograficasList;
    @JoinColumn(name = "ID_PADRE", referencedColumnName = "ID_UBIGEO")
    @ManyToOne(fetch = FetchType.LAZY)
    private UbicacionesGeograficas idPadre;

    public UbicacionesGeograficas() {
    }

    public UbicacionesGeograficas(Long idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public UbicacionesGeograficas(Long idUbigeo, int ubigeo, String descUbigeo, String siglaUbigeo,
            String nivel, Date fechaIni, String apiEstado, String apiTransaccion, String usuCre,
            Date fecCre, String disposicionLegal, String archivoDisposicion, String dispFin) {
        this.idUbigeo = idUbigeo;
        this.ubigeo = ubigeo;
        this.descUbigeo = descUbigeo;
        this.siglaUbigeo = siglaUbigeo;
        this.nivel = nivel;
        this.fechaIni = fechaIni;
        this.apiEstado = apiEstado;
        this.apiTransaccion = apiTransaccion;
        this.usuCre = usuCre;
        this.fecCre = fecCre;
        this.disposicionLegal = disposicionLegal;
        this.archivoDisposicion = archivoDisposicion;
        this.dispFin = dispFin;
    }

    public Long getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(Long idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public int getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(int ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getDescUbigeo() {
        return descUbigeo;
    }

    public void setDescUbigeo(String descUbigeo) {
        this.descUbigeo = descUbigeo;
    }

    @XmlTransient
    public String getSiglaUbigeo() {
        return siglaUbigeo;
    }

    public void setSiglaUbigeo(String siglaUbigeo) {
        this.siglaUbigeo = siglaUbigeo;
    }

    @XmlTransient
    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @XmlTransient
    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    @XmlTransient
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public String getApiEstado() {
        return apiEstado;
    }

    public void setApiEstado(String apiEstado) {
        this.apiEstado = apiEstado;
    }

    @XmlTransient
    public String getApiTransaccion() {
        return apiTransaccion;
    }

    public void setApiTransaccion(String apiTransaccion) {
        this.apiTransaccion = apiTransaccion;
    }

    @XmlTransient
    public String getUsuCre() {
        return usuCre;
    }

    public void setUsuCre(String usuCre) {
        this.usuCre = usuCre;
    }

    @XmlTransient
    public Date getFecCre() {
        return fecCre;
    }

    public void setFecCre(Date fecCre) {
        this.fecCre = fecCre;
    }

    @XmlTransient
    public String getUsuMod() {
        return usuMod;
    }

    public void setUsuMod(String usuMod) {
        this.usuMod = usuMod;
    }

    @XmlTransient
    public Date getFecMod() {
        return fecMod;
    }

    public void setFecMod(Date fecMod) {
        this.fecMod = fecMod;
    }

    @XmlTransient
    public String getDisposicionLegal() {
        return disposicionLegal;
    }

    public void setDisposicionLegal(String disposicionLegal) {
        this.disposicionLegal = disposicionLegal;
    }

    @XmlTransient
    public String getArchivoDisposicion() {
        return archivoDisposicion;
    }

    public void setArchivoDisposicion(String archivoDisposicion) {
        this.archivoDisposicion = archivoDisposicion;
    }

    @XmlTransient
    public String getDispFin() {
        return dispFin;
    }

    public void setDispFin(String dispFin) {
        this.dispFin = dispFin;
    }

    @XmlTransient
    public List<UbicacionesGeograficas> getUbicacionesGeograficasList() {
        return ubicacionesGeograficasList;
    }

    public void setUbicacionesGeograficasList(
            List<UbicacionesGeograficas> ubicacionesGeograficasList) {
        this.ubicacionesGeograficasList = ubicacionesGeograficasList;
    }

    @XmlTransient
    public UbicacionesGeograficas getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(UbicacionesGeograficas idPadre) {
        this.idPadre = idPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUbigeo != null ? idUbigeo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UbicacionesGeograficas)) {
            return false;
        }
        UbicacionesGeograficas other = (UbicacionesGeograficas) object;
        if ((this.idUbigeo == null && other.idUbigeo != null)
                || (this.idUbigeo != null && !this.idUbigeo.equals(other.idUbigeo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UbicacionesGeograficas{" + "idUbigeo=" + idUbigeo + ", ubigeo=" + ubigeo + ", descUbigeo=" + descUbigeo + ", siglaUbigeo=" + siglaUbigeo + ", fechaFin=" + fechaFin + ", apiTransaccion=" + apiTransaccion + ", fecCre=" + fecCre + ", fecMod=" + fecMod + ", idPadre=" + idPadre + '}';
    }

}
