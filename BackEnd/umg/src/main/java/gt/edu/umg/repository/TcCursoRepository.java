package gt.edu.umg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.edu.umg.model.TcCurso;

@Repository
public interface TcCursoRepository extends JpaRepository<TcCurso, Long> {
    
    List<TcCurso>  findByNomCurso(String nomCurso);
    Optional<TcCurso> findByIdCurso(Long idCurso);
}
