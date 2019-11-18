package gt.com.actividades.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.com.actividades.model.TcCiclo;

@Repository
public interface TcCicloRepository extends JpaRepository<TcCiclo, Long> {
    
    List<TcCiclo>  findByNombre(String ciclo);
    Optional<TcCiclo> findByIdCiclo(Long idCiclo);
}
