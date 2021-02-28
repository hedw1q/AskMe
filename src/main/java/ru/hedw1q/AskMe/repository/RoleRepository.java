package ru.hedw1q.AskMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hedw1q.AskMe.models.Role;

/**
 * @author hedw1q
 * Repository class for Role entity.This interface can be extended for the Role's descendants
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}