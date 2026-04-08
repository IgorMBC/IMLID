package imliddev.imlid.repository;

import imliddev.imlid.domain.model.CasoImagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CasoImagemRepository extends JpaRepository<CasoImagem, UUID> {

    List<CasoImagem> findByCaso_Id(UUID casoId);
}
