package gt.edu.umg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.edu.umg.model.TcCiclo;

@Repository
public interface TcCicloRepository extends JpaRepository<TcCiclo, Long> {
    
    List<TcCiclo>  findByCiclo1(String ciclo1);
    Optional<TcCiclo> findByIdCiclo(Long idCiclo);
}
