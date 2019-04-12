package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.VueUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the VueUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VueUserRepository extends JpaRepository<VueUser, Long> {
    Optional<VueUser> findOneByName(String name);
    Optional<VueUser> findOneByMobile(String mobile);
    Optional<VueUser> findOneByEmailIgnoreCase(String email);

}
