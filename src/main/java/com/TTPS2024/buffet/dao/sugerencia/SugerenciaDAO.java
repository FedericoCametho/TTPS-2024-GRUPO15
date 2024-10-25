package com.TTPS2024.buffet.dao.sugerencia;

import com.TTPS2024.buffet.model.sugerencia.Sugerencia;
import com.TTPS2024.buffet.model.sugerencia.TipoSugerencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SugerenciaDAO extends JpaRepository<Sugerencia, Long> {
    List<Sugerencia> getSugerenciasByTipo(TipoSugerencia tipo);

}
