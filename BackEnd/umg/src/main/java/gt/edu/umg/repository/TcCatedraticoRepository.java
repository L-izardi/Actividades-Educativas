package gt.edu.umg.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.edu.umg.model.TcCatedratico;;

@Repository
public interface TcCatedraticoRepository extends JpaRepository<TcCatedratico, Long> {
    
    List<TcCatedratico>  findByNombre(String nombre);
    Optional<TcCatedratico> findByIdCatedratico(Long idCatedratico);
    
}