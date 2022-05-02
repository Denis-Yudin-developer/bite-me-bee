package ru.coderiders.bitemebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.bitemebee.entity.ERole;
import ru.coderiders.bitemebee.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByName(ERole name);
}
