package gt.com.actividades.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.com.actividades.model.TcDepartamento;;

@Repository
public interface TcDepartamentoRepository extends JpaRepository<TcDepartamento, Long> {
    
    List<TcDepartamento>  findByNomDepto(String nomDepto);
    Optional<TcDepartamento> findByIdDepto(Long idDepto);
    
}