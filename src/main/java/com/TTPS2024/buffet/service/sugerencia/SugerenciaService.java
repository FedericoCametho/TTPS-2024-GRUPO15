package com.TTPS2024.buffet.service.sugerencia;

import com.TTPS2024.buffet.controller.request.sugerencia.SugerenciaRequest;
import com.TTPS2024.buffet.dao.sugerencia.SugerenciaDAO;
import com.TTPS2024.buffet.helper.RequestValidatorHelper;
import com.TTPS2024.buffet.model.sugerencia.Sugerencia;
import com.TTPS2024.buffet.model.sugerencia.TipoSugerencia;
import com.TTPS2024.buffet.model.usuario.Alumno;
import com.TTPS2024.buffet.service.usuario.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
@Service
public class SugerenciaService {
    private static final Logger LOGGER = Logger.getLogger(SugerenciaService.class.getName());
    private SugerenciaDAO sugerenciaDAO;
    private AlumnoService alumnoService;
    @Autowired
    public SugerenciaService(SugerenciaDAO sugerenciaDAO, AlumnoService alumnoService) {
        this.sugerenciaDAO = sugerenciaDAO;
        this.alumnoService = alumnoService;
    }

    public Sugerencia save(SugerenciaRequest sugerenciaRequest){
        this.sanitize(sugerenciaRequest);
        Sugerencia sugerencia = this.createSugerencia(sugerenciaRequest);
        try{
            return this.sugerenciaDAO.saveAndFlush(sugerencia);
        } catch (Exception e){
            LOGGER.info("Error al guardar sugerencia");
            throw new IllegalArgumentException("Error al guardar sugerencia");
        }
    }

    public List<Sugerencia> getSugerenciaByTipo(TipoSugerencia tipo){
        if (tipo == null){
            LOGGER.info("ERROR: Tipo de sugerencia no puede ser nulo");
            throw new IllegalArgumentException("Tipo de sugerencia no puede ser nulo");
        }

        try{
            return this.sugerenciaDAO.getSugerenciasByTipo(tipo);
        } catch (Exception e){
            LOGGER.info("No se encontro sugerencia con tipo: " + tipo);
            throw new IllegalArgumentException("No se encontro sugerencia con tipo: " + tipo);
        }
    }


    public Sugerencia getById(Long id){
        RequestValidatorHelper.validateID(id);
        try{
            return this.sugerenciaDAO.findById(id).orElse(null);
        } catch (Exception e){
            LOGGER.info("No se encontro sugerencia con id: " + id);
            throw new IllegalArgumentException("No se encontro sugerencia con id: " + id);
        }
    }

    public List<Sugerencia> getAll(){
        return this.sugerenciaDAO.findAll();
    }

    public void delete(Long id){
        RequestValidatorHelper.validateID(id);
        try{
            this.sugerenciaDAO.deleteById(id);
        } catch (Exception e){
            LOGGER.info("Error al eliminar sugerencia");
            throw new IllegalArgumentException("Error al eliminar sugerencia");
        }
    }

    private void sanitize(SugerenciaRequest sugerenciaRequest) {
        if(sugerenciaRequest.getTitulo() == null || sugerenciaRequest.getTitulo().isEmpty()){
            throw new IllegalArgumentException("Titulo no puede ser nulo o vacio");
        }
        if(sugerenciaRequest.getTipoSugerencia() == null){
            throw new IllegalArgumentException("Tipo de sugerencia no puede ser nulo");
        }
        if(sugerenciaRequest.getMensajeOriginal() == null || sugerenciaRequest.getMensajeOriginal().isEmpty()){
            throw new IllegalArgumentException("Mensaje no puede ser nulo o vacio");
        }
        if(sugerenciaRequest.getAlumnoId() == null || sugerenciaRequest.getAlumnoId() <= 0){
            throw new IllegalArgumentException("Id de alumno no puede ser nulo o menor o igual a 0");
        }
    }

    private Sugerencia createSugerencia(SugerenciaRequest sugerenciaRequest){
        Sugerencia sugerencia = new Sugerencia();
        sugerencia.setTitulo(sugerenciaRequest.getTitulo());
        sugerencia.setTipo(sugerenciaRequest.getTipoSugerencia());
        sugerencia.setMensajeOriginal(sugerenciaRequest.getMensajeOriginal());
        Alumno alumno ;
        try{
            alumno = this.alumnoService.getUserById(sugerenciaRequest.getAlumnoId());
        } catch (Exception e){
            LOGGER.info("No se encontro el alumno con id: " + sugerenciaRequest.getAlumnoId());
            throw new IllegalArgumentException("No se encontro el alumno con id: " + sugerenciaRequest.getAlumnoId());
        }
        sugerencia.setUsuario(alumno);
        return sugerencia;
    }

}
