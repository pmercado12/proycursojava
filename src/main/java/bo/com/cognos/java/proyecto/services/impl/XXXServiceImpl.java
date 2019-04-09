package bo.com.cognos.java.proyecto.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.Usuario;
import bo.com.cognos.java.proyecto.model.XXXModel;
import bo.com.cognos.java.proyecto.repositories.XXXRepository;
import bo.com.cognos.java.proyecto.services.XXXService;

@Transactional(readOnly = true)
public abstract class XXXServiceImpl<T extends XXXModel<ID>, ID extends Number> implements XXXService<T, ID> {

    abstract XXXRepository<T, ID> getRepository();

    abstract void validarAlta(T obj) throws ProyectoException;

    abstract void validarModificacion(T objAnterior, T objNuevo) throws ProyectoException;

    abstract void validarBorrado(final T obj) throws ProyectoException;

    @Override
    public T obtener(ID id) throws ProyectoException {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public List<T> buscar(String filtro) throws ProyectoException {
        filtro = filtro == null ? "%" : "%" + filtro + "%";
        return getRepository().buscar(filtro);
    }

    @Override
    public List<T> buscar(String filtro, Date fechaInicial, Date fechaFinal) throws ProyectoException {
        filtro = filtro == null ? "%" : "%" + filtro + "%";
        return getRepository().buscarPorRangoFecha(filtro, fechaInicial, fechaFinal);
    }

    Logger log = Logger.getLogger(this.getClass());

    @Override
    @Transactional(rollbackFor = ProyectoException.class)
    public void borrar(ID id) throws ProyectoException {
        T item = this.obtener(id);
        validarBorrado(item);
        getRepository().delete(item);
    }

    @Override
    @Transactional(rollbackFor = ProyectoException.class)
    public T guardar(T item) throws ProyectoException {
        validarApi(item);
        if (item.getId() == null) {
            validarApiIns(item);
            validarAlta(item);
            item.setFecCre(new Date());
        } else {
            T itemExistente = getRepository().findById(item.getId()).orElse(null);
            validarApiMod(item);
            validarModificacion(itemExistente, item);
            item.setFecMod(new Date());
        }
        
        item = getRepository().saveAndFlush(item);
        item = obtener(item.getId());
        return item;
    }

    public void validarApi(T obj) throws ProyectoException {
        if (obj.getApiTransaccion() == null || obj.getApiTransaccion().trim().length() == 0) {
            throw new ProyectoException(0, "La api transaccion no puede ser nula");
        }
        if (obj.getApiEstado() == null || obj.getApiEstado().trim().length() == 0) {
            throw new ProyectoException(0, "El estado del registro no puede ser nulo");
        }        
    }
    
    public void validarApiIns(T obj) throws ProyectoException {
        if (obj.getUsuCre() == null || obj.getUsuCre().trim().length() == 0) {
            throw new ProyectoException(0, "El usuario de creacion no puede ser nulo");
        }
    }

    public void validarApiMod(T obj) throws ProyectoException {
        if (obj.getUsuMod() == null || obj.getUsuMod().trim().length() == 0) {
            throw new ProyectoException(0, "El usuario de modificacion no puede ser nulo");
        }
    }

}
