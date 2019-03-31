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

    @Override
    XXXRepository<ScCatalogoItem, Long> getRepository() {
        return repository;
    }

    @Override
    void validarAlta(ScCatalogoItem obj) throws ProyectoException {
        //TODO
    }

    @Override
    void validarModificacion(ScCatalogoItem objAnterior, ScCatalogoItem objNuevo) throws ProyectoException {
        //TODO
    }

    @Override
    void validarBorrado(ScCatalogoItem obj) throws ProyectoException {
        //TODO
    }

}
