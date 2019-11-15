package gt.com.actividades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.com.actividades.model.TcRol;

@Repository
public interface TcRolRepository extends JpaRepository<TcRol, Long> {
    
}