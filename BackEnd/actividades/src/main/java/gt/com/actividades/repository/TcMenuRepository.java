package gt.com.actividades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  gt.com.actividades.model.TcMenu;

@Repository
public interface TcMenuRepository extends JpaRepository<TcMenu, Long> {
    
}