package gt.edu.umg.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.edu.umg.model.TcTema;


@Repository
public interface TcTemaRepository extends JpaRepository<TcTema, Long> {
 
    Optional<TcTema> findByIdTema(Long idTema);
    Optional<TcTema> findByNombre(String nombre);
    
}