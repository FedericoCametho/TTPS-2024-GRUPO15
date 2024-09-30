package com.TTPS2024.buffet.model.sugerencia;

import com.TTPS2024.buffet.model.usuario.Alumno;
import com.TTPS2024.buffet.model.usuario.Usuario;
import jakarta.persistence.*;

@Entity
public class Sugerencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private TipoSugerencia tipo;
    private String mensajeOriginal;
    private String respuesta;
    @OneToOne
    private Alumno alumno;


    public Sugerencia() {

    }
    public Sugerencia(String titulo, TipoSugerencia tipo, String mensaje, Alumno alumno) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.mensajeOriginal = mensaje;
        this.alumno = alumno;
    }

    private void setRespuesta(String respuesta){
        this.respuesta = respuesta;
    }

}
