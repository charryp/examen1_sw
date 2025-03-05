package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteService {
    @Autowired
	PacienteRepository pacienteRepository;

    public PacienteEntity createPaciente(PacienteEntity entity) throws IllegalOperationException, EntityNotFoundException {
        log.info("Inicia el proceso de crear un paciente,");
        if (entity.getNombre() == null)
			throw new IllegalOperationException("El paciente carece de nombre");
        
        if (entity.getEdad() == null)
			throw new IllegalOperationException("El paciente carece de edad");

        if (entity.getId() == null)
			throw new IllegalOperationException("El paciente carece de identificador");

        if (entity.getCelular() == null)
			throw new IllegalOperationException("El paciente carece de celular");

        if (entity.getCorreo() == null)
			throw new IllegalOperationException("El paciente carece de correo");

        log.info("El proceso ha terminado con exito");

        return pacienteRepository.save(entity);
}
}