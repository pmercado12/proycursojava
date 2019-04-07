/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.com.cognos.java.proyecto.view.jsf;

import bo.com.cognos.java.proyecto.vo.UbicacionesGeograficasResponseVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author DellPedro
 */
@ManagedBean
@ApplicationScoped
@Getter
@Setter
public class ApplicationBean {

    private List<UbicacionesGeograficasResponseVo> ubicacionesGeograficas = new ArrayList<UbicacionesGeograficasResponseVo>();

    @PostConstruct
    public void init() {
        ubicacionesGeograficas.add(new UbicacionesGeograficasResponseVo(1l, 1, "CHUQUISACA"));
        ubicacionesGeograficas.add(new UbicacionesGeograficasResponseVo(53l, 2, "LA PAZ"));
        ubicacionesGeograficas.add(new UbicacionesGeograficasResponseVo(181l, 3, "COCHABAMBA"));

        System.out.println("yo soy grrood: v");
    }
}
