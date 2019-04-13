package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BestUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BestUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BestUserRepository extends JpaRepository<BestUser, Long> {

}
