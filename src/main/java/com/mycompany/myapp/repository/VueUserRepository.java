package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.VueUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VueUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VueUserRepository extends JpaRepository<VueUser, Long> {

}
