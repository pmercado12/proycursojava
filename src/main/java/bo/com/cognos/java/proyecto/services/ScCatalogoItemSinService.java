package bo.com.cognos.java.proyecto.services;

import bo.com.cognos.java.proyecto.model.ScCatalogoItemSin;
import java.util.List;

/**
 *
 * @author pmercado
 */
public interface ScCatalogoItemSinService extends XXXService<ScCatalogoItemSin, Long> {

    public List<ScCatalogoItemSin> buscarPorItem(Long idItem);
}
