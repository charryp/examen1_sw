package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsultaMedicaService {
    @Autowired
	PacienteRepository pacienteRepository;

    @Autowired
    ConsultaMedicaRepository consultaMedicaRepository;

    public ConsultaMedicaEntity createConsultaMedica(ConsultaMedicaEntity entity) throws IllegalOperationException, EntityNotFoundException {
        log.info("Inicia el proceso de crear una consulta medica,");
        if (entity.getFecha() == null)
			throw new IllegalOperationException("El paciente carece de una fecha");
        
        if (entity.getId() == null)
			throw new IllegalOperationException("El paciente carece de un identificador");

        if (entity.getCausa() == null)
			throw new IllegalOperationException("El paciente carece de una causa");

        Date date = entity.getFecha();
        if (! date.after(new Date()))
            throw new IllegalOperationException("La fecha de la consulta no es valida");

        if (entity.getPaciente() == null)
            throw new IllegalOperationException("La consulta medica no es valida porque carece de paciente");

        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(entity.getPaciente().getId());
        entity.setPaciente(pacienteEntity.get());
        log.info("El proceso ha terminado con exito");

        return consultaMedicaRepository.save(entity);
}
}
