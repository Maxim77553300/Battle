package by.battle.userservice.repository;

import by.battle.common.RoleName;
import by.battle.userservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByName(RoleName roleName);
}
