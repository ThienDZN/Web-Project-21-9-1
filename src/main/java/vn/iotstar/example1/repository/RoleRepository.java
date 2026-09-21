package vn.iotstar.example1.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.example1.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> { Optional<Role> findByName(String name); }
