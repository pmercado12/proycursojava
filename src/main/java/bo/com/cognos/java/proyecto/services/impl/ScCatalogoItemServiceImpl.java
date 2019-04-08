package bo.com.cognos.java.proyecto.services.impl;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.ScCatalogoItem;
import bo.com.cognos.java.proyecto.repositories.ScCatalogoItemRepository;
import bo.com.cognos.java.proyecto.repositories.XXXRepository;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DellPedro
 */
@Service
@Transactional(readOnly = true)
public class ScCatalogoItemServiceImpl extends XXXServiceImpl<ScCatalogoItem, Long>
        implements ScCatalogoItemService {

    @Autowired
    ScCatalogoItemRepository repository;

    public final static String TRANSACCION_CREAR = "CREAR";
    public final static String TRANSACCION_MODIFICAR = "MODIFICAR";
    public final static String TRANSACCION_ACTIVAR = "ACTIVAR";

    public final static String ESTADO_CREADO = "CREADO";
    public final static String ESTADO_ACTIVO = "ACTIVO";

    @Override
    XXXRepository<ScCatalogoItem, Long> getRepository() {
        return repository;
    }

    @Override
    void validarAlta(ScCatalogoItem obj) throws ProyectoException {
        validarCodigoItem(obj);
        obj.setApiTransaccion(TRANSACCION_CREAR);
        obj.setApiEstado(ESTADO_CREADO);
    }

    @Override
    void validarModificacion(ScCatalogoItem objAnterior, ScCatalogoItem objNuevo) throws ProyectoException {

        validarCodigoItem(objNuevo);
        validarActivo(objAnterior);

        objNuevo.setApiEstado(ESTADO_CREADO);
        if (objNuevo.getApiTransaccion().equals(TRANSACCION_ACTIVAR)) {
            objNuevo.setApiEstado(ESTADO_ACTIVO);
        }
    }

    @Override
    void validarBorrado(ScCatalogoItem obj) throws ProyectoException {
        validarActivo(obj);
    }

    private void validarActivo(ScCatalogoItem objAnterior) throws ProyectoException {
        if (objAnterior.getApiEstado().equals(ESTADO_ACTIVO)) {
            throw new ProyectoException(4, "No se pueden realizar cambios en estado ACTIVO");
        }
    }

    private void validarCodigoItem(ScCatalogoItem obj) throws ProyectoException {
        if (obj.getCodigoItem() == null || obj.getCodigoItem().trim().length() == 0) {
            throw new ProyectoException(1, "El codigo de item no puede ser nulo");
        }
        if (obj.getCodigoItem().trim().length() != 8) {
            throw new ProyectoException(2, "El codigo de item debe ser de 8 digitos");
        }

        try {
            Long.parseLong(obj.getCodigoItem());
        } catch (Exception e) {
            throw new ProyectoException(3, "El codigo de item debe ser de 8 digitos numerico");
        }
    }

}
