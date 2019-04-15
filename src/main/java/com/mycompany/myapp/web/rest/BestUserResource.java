package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.domain.BestUser;
import com.mycompany.myapp.repository.BestUserRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BestUser.
 */
@RestController
@RequestMapping("/api")
public class BestUserResource {

    private final Logger log = LoggerFactory.getLogger(BestUserResource.class);

    private static final String ENTITY_NAME = "bestUser";

    private final BestUserRepository bestUserRepository;

    public BestUserResource(BestUserRepository bestUserRepository) {
        this.bestUserRepository = bestUserRepository;
    }

    /**
     * POST  /best-users : Create a new bestUser.
     *
     * @param bestUser the bestUser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bestUser, or with status 400 (Bad Request) if the bestUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/best-users")
    public ResponseEntity<BestUser> createBestUser(@Valid @RequestBody BestUser bestUser) throws URISyntaxException {
        log.debug("REST request to save BestUser : {}", bestUser);
        if (bestUser.getId() != null) {
            throw new BadRequestAlertException("A new bestUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BestUser result = bestUserRepository.save(bestUser);
        return ResponseEntity.created(new URI("/api/best-users/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /best-users : Updates an existing bestUser.
     *
     * @param bestUser the bestUser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bestUser,
     * or with status 400 (Bad Request) if the bestUser is not valid,
     * or with status 500 (Internal Server Error) if the bestUser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/best-users")
    public ResponseEntity<BestUser> updateBestUser(@Valid @RequestBody BestUser bestUser) throws URISyntaxException {
        log.debug("REST request to update BestUser : {}", bestUser);
        if (bestUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BestUser result = bestUserRepository.save(bestUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bestUser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /best-users : get all the bestUsers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bestUsers in body
     */
    @GetMapping("/best-users")
    public List<BestUser> getAllBestUsers() {
        log.debug("REST request to get all BestUsers");
        return bestUserRepository.findAll();
    }

    /**
     * GET  /best-users/:id : get the "id" bestUser.
     *
     * @param id the id of the bestUser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bestUser, or with status 404 (Not Found)
     */
    @GetMapping("/best-users/{id}")
    public ResponseEntity<BestUser> getBestUser(@PathVariable Long id) {
        log.debug("REST request to get BestUser : {}", id);
        Optional<BestUser> bestUser = bestUserRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bestUser);
    }

    /**
     * DELETE  /best-users/:id : delete the "id" bestUser.
     *
     * @param id the id of the bestUser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/best-users/{id}")
    public ResponseEntity<Void> deleteBestUser(@PathVariable Long id) {
        log.debug("REST request to delete BestUser : {}", id);
        bestUserRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
