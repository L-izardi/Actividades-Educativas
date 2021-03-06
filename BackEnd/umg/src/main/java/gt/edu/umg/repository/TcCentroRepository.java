package gt.edu.umg.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.edu.umg.model.TcCentro;

@Repository
public interface TcCentroRepository extends JpaRepository<TcCentro, Long> {
    
    List<TcCentro>  findByNombre(String nombre);
    Optional<TcCentro> findByIdCentro(Long idCentro);
    
}