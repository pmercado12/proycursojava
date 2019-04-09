package bo.com.cognos.java.proyecto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class XXXModel<ID extends Number> {

    @XmlTransient
    public abstract ID getId();

    public abstract void setId(ID id);

    @Column(name = "API_ESTADO", length = 25)
    private String apiEstado;
    @Column(name = "API_TRANSACCION", length = 30)
    private String apiTransaccion;
    @Column(name = "USU_CRE", length = 30, updatable = false)
    private String usuCre;
    @Column(name = "FEC_CRE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCre;
    @Column(name = "USU_MOD", length = 30)
    private String usuMod;
    @Column(name = "FEC_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecMod;
}
