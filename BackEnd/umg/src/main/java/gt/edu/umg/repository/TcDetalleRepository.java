package gt.edu.umg.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gt.edu.umg.model.TcDetalle;
import gt.edu.umg.model.TcTema;

@Repository
public interface TcDetalleRepository extends JpaRepository<TcDetalle, Long> {
     
    @Query("SELECT d FROM TcDetalle d WHERE d.tcTema = :tcTema")
    List<TcDetalle> findByTcTema (TcTema tcTema);
    
    Optional<TcDetalle> findByIdCorrelativo(Long idCorrelativo);
    Optional<TcDetalle> findBySubtema(String subtema);
    
}