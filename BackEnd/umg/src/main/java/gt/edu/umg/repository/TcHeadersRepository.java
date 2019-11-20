package gt.edu.umg.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.edu.umg.model.TcHeaders;

@Repository
public interface TcHeadersRepository extends JpaRepository<TcHeaders, Long> {
 
    Optional<TcHeaders>  findByIdCorrelativo(Long idCorrelativo);

    

    
}