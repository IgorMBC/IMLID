package imliddev.imlid.service;

import imliddev.imlid.domain.model.Caso;
import imliddev.imlid.repository.CasoRepository;
import imliddev.imlid.dto.CasoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CasoService {

    private final CasoRepository repository;

    public void cadastrarCaso(CasoRequestDTO request) {
        Caso caso = request.toEntity();

        repository.save(caso);
    }

    public List<Caso> listar() {
        return repository.findAll();
    }
}
