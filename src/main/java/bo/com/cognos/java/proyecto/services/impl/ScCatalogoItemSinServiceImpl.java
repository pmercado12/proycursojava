package bo.com.cognos.java.proyecto.services.impl;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.ScCatalogoItemSin;
import bo.com.cognos.java.proyecto.repositories.ScCatalogoItemSinRepository;
import bo.com.cognos.java.proyecto.repositories.XXXRepository;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemSinService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pmercado
 */
@Service
@Transactional(readOnly = true)
public class ScCatalogoItemSinServiceImpl extends XXXServiceImpl<ScCatalogoItemSin, Long>
        implements ScCatalogoItemSinService {

    @Autowired
    ScCatalogoItemSinRepository repository;

    @Override
    XXXRepository<ScCatalogoItemSin, Long> getRepository() {
        return repository;
    }

    @Override
    void validarAlta(ScCatalogoItemSin obj) throws ProyectoException {
        //TODO
    }

    @Override
    void validarModificacion(ScCatalogoItemSin objAnterior, ScCatalogoItemSin objNuevo) throws ProyectoException {
        //TODO
    }

    @Override
    void validarBorrado(ScCatalogoItemSin obj) throws ProyectoException {
        //TODO
    }

    public List<ScCatalogoItemSin> buscarPorItem(Long idItem) {
        return repository.buscarPorItem(idItem);
    }

}
