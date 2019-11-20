package gt.edu.umg.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.edu.umg.model.TcFacultad;;

@Repository
public interface TcFacultadRepository extends JpaRepository<TcFacultad, Long> {
    
    List<TcFacultad>  findByNombre(String nombre);
    Optional<TcFacultad> findByIdFacultad(Long idFacultad);
}