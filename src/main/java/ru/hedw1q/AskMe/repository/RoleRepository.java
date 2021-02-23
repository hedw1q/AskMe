package ru.hedw1q.AskMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hedw1q.AskMe.models.Role;

/**
 * @author hedw1q
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}