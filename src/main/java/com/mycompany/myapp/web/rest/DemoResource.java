package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.domain.Demo;
import com.mycompany.myapp.repository.DemoRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Demo.
 */
@RestController
@RequestMapping("/api")
public class DemoResource {

    private final Logger log = LoggerFactory.getLogger(DemoResource.class);

    private static final String ENTITY_NAME = "demo";

    private final DemoRepository demoRepository;

    public DemoResource(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    /**
     * POST  /demos : Create a new demo.
     *
     * @param demo the demo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new demo, or with status 400 (Bad Request) if the demo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/demos")
    public ResponseEntity<Demo> createDemo(@RequestBody Demo demo) throws URISyntaxException {
        log.debug("REST request to save Demo : {}", demo);
        if (demo.getId() != null) {
            throw new BadRequestAlertException("A new demo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Demo result = demoRepository.save(demo);
        return ResponseEntity.created(new URI("/api/demos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /demos : Updates an existing demo.
     *
     * @param demo the demo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated demo,
     * or with status 400 (Bad Request) if the demo is not valid,
     * or with status 500 (Internal Server Error) if the demo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/demos")
    public ResponseEntity<Demo> updateDemo(@RequestBody Demo demo) throws URISyntaxException {
        log.debug("REST request to update Demo : {}", demo);
        if (demo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Demo result = demoRepository.save(demo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, demo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /demos : get all the demos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of demos in body
     */
    @GetMapping("/demos")
    public List<Demo> getAllDemos() {
        log.debug("REST request to get all Demos");
        return demoRepository.findAll();
    }

    /**
     * GET  /demos/:id : get the "id" demo.
     *
     * @param id the id of the demo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the demo, or with status 404 (Not Found)
     */
    @GetMapping("/demos/{id}")
    public ResponseEntity<Demo> getDemo(@PathVariable Long id) {
        log.debug("REST request to get Demo : {}", id);
        Optional<Demo> demo = demoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(demo);
    }

    /**
     * DELETE  /demos/:id : delete the "id" demo.
     *
     * @param id the id of the demo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/demos/{id}")
    public ResponseEntity<Void> deleteDemo(@PathVariable Long id) {
        log.debug("REST request to delete Demo : {}", id);
        demoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
