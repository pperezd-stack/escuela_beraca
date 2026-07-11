package com.beraca.Service;

import com.beraca.Repository.CalificacionRepository;
import com.beraca.model.Calificacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionService {

    private final CalificacionRepository calificacionRepository;

    public CalificacionService(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }

    // ==========================
    // CREAR
    // ==========================
    public Calificacion guardar(Calificacion calificacion) {

        validarCalificacion(calificacion);

        return calificacionRepository.save(calificacion);
    }

    // ==========================
    // ACTUALIZAR
    // ==========================
    public Calificacion actualizar(Calificacion calificacion) {

        if (calificacion.getId() == null) {
            throw new RuntimeException("Debe indicar el ID de la calificación.");
        }

        if (!calificacionRepository.existsById(calificacion.getId())) {
            throw new RuntimeException("La calificación no existe.");
        }

        validarCalificacion(calificacion);

        return calificacionRepository.save(calificacion);
    }

    // ==========================
    // OBTENER TODAS
    // ==========================
    public List<Calificacion> obtenerTodos() {
        return calificacionRepository.findAll();
    }

    // ==========================
    // BUSCAR POR ID
    // ==========================
    public Calificacion buscar(Long id) {
        return calificacionRepository.findById(id).orElse(null);
    }

    // ==========================
    // ELIMINAR
    // ==========================
    public boolean eliminar(Long id) {

        if (!calificacionRepository.existsById(id)) {
            return false;
        }

        calificacionRepository.deleteById(id);
        return true;
    }

    private void validarCalificacion(Calificacion calificacion) {

        if (calificacion.getEstudiante() == null) {
            throw new RuntimeException("Debe indicar el estudiante.");
        }

        if (calificacion.getModulo() == null) {
            throw new RuntimeException("Debe indicar el módulo.");
        }

        if (calificacion.getCorte1() == null) {
            calificacion.setCorte1("0");
        }

        if (calificacion.getCorte2() == null) {
            calificacion.setCorte2("0");
        }

        if (calificacion.getCorte3() == null) {
            calificacion.setCorte3("0");
        }

        if (calificacion.getNotaFinal() == null) {

            double c1 = Double.parseDouble(calificacion.getCorte1());
            double c2 = Double.parseDouble(calificacion.getCorte2());
            double c3 = Double.parseDouble(calificacion.getCorte3());

            double promedio = (c1 + c2 + c3) / 3;

            calificacion.setNotaFinal(String.valueOf(promedio));

        }

    }
    public List<Calificacion> obtenerPorEstudiante(Long estudianteId){

        return calificacionRepository.findByEstudiante_Id(estudianteId);

    }

    public List<Calificacion> obtenerPorModulo(Long moduloId){

        return calificacionRepository.findByModulo_Id(moduloId);

    }

    public Calificacion obtenerPorEstudianteYModulo(Long estudianteId,
                                                    Long moduloId){

        return calificacionRepository
                .findByEstudiante_IdAndModulo_Id(estudianteId, moduloId)
                .orElse(null);

    }

}
