package apap.tutorial.emsidi.repository;

import apap.tutorial.emsidi.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDB extends JpaRepository<RoleModel,Long> {
}