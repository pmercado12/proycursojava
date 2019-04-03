package bo.com.cognos.java.proyecto.repositories;

import bo.com.cognos.java.proyecto.model.ScCatalogoItemSin;
import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author pmercado
 */
public interface ScCatalogoItemSinRepository extends XXXRepository<ScCatalogoItemSin, Long> {

    public List<ScCatalogoItemSin> buscarPorItem(@Param("idItem") Long idItem);
}
