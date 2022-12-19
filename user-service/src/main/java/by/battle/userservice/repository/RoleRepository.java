package by.battle.userservice.repository;

import by.battle.userservice.entity.Role;
import by.battle.userservice.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByName(RoleName roleName);
}
