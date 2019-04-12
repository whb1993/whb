package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.domain.VueUser;
import com.mycompany.myapp.repository.VueUserRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.errors.EmailAlreadyUsedException;
import com.mycompany.myapp.web.rest.errors.ErrorConstants;
import com.mycompany.myapp.web.rest.errors.LoginAlreadyUsedException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing VueUser.
 */
@RestController
@RequestMapping("/api")
public class VueUserResource {

    private final Logger log = LoggerFactory.getLogger(VueUserResource.class);

    private static final String ENTITY_NAME = "vueUser";

    private final VueUserRepository vueUserRepository;

    public VueUserResource(VueUserRepository vueUserRepository) {
        this.vueUserRepository = vueUserRepository;
    }

    /**
     * 放开注册请求url  vue
     *
     * @param vueUser
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/user/register")
    public ResponseEntity<VueUser> registerVueUser(@Valid @RequestBody VueUser vueUser) throws URISyntaxException {
        log.debug("REST request to save VueUser : {}", vueUser);
        if (vueUser.getId() != null) {
            throw new BadRequestAlertException("A new vueUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        checkUser(vueUser);
        vueUser.setCreator("resign");
        vueUser.setCreatTime(Instant.now());
        VueUser result = vueUserRepository.save(vueUser);
        return ResponseEntity.created(new URI("/api/vue-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    private void checkUser(VueUser vueUser) {
        //  修改
        if (vueUser.getId() != null) {
            //校验用户名是否重复
            vueUserRepository.findOneByName(vueUser.getName().toLowerCase()).ifPresent(existingUser -> {
                if (!vueUser.getId().equals(existingUser.getId())) {
                    throw new BadRequestAlertException(ErrorConstants.DEFAULT_TYPE, "Name is already in use!", "vmsUserManagement", "vmsUser.name.exists");
                }
            });
            //校验手机号是否重复
            vueUserRepository.findOneByMobile(vueUser.getMobile()).ifPresent(existingUser -> {
                if (!vueUser.getId().equals(existingUser.getId())) {
                    throw new BadRequestAlertException(ErrorConstants.DEFAULT_TYPE, "Mobile is already in use!", "vmsUserManagement", "vmsUser.mobile.exists");
                }
            });
            //校验邮箱是否重复
            vueUserRepository.findOneByEmailIgnoreCase(vueUser.getEmail()).ifPresent(existingUser -> {
                if (!vueUser.getId().equals(existingUser.getId())) {
                    throw new BadRequestAlertException(ErrorConstants.DEFAULT_TYPE, "Email is already in use!", "vmsUserManagement", "vmsUser.email.exists");
                }
            });
        } else {
            //校验用户名是否重复
            vueUserRepository.findOneByName(vueUser.getName().toLowerCase()).ifPresent(existingUser -> {
                throw new BadRequestAlertException(ErrorConstants.DEFAULT_TYPE, "Name is already in use!", "vmsUserManagement", "vmsUser.name.exists");
            });
            //校验手机号是否重复
            vueUserRepository.findOneByMobile(vueUser.getMobile()).ifPresent(existingUser -> {
                throw new BadRequestAlertException(ErrorConstants.DEFAULT_TYPE, "Mobile is already in use!", "vmsUserManagement", "vmsUser.mobile.exists");
            });
            //校验邮箱是否重复
            vueUserRepository.findOneByEmailIgnoreCase(vueUser.getEmail()).ifPresent(existingUser -> {
                throw new BadRequestAlertException(ErrorConstants.DEFAULT_TYPE, "Email is already in use!", "vmsUserManagement", "vmsUser.email.exists");
            });
        }

    }

    /**
     * POST  /vue-users : Create a new vueUser.
     *
     * @param vueUser the vueUser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vueUser, or with status 400 (Bad Request) if the vueUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vue-users")
    public ResponseEntity<VueUser> createVueUser(@Valid @RequestBody VueUser vueUser) throws URISyntaxException {
        log.debug("REST request to save VueUser : {}", vueUser);
        if (vueUser.getId() != null) {
            throw new BadRequestAlertException("A new vueUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        checkUser(vueUser);
        vueUser.setCreatTime(Instant.now());
        VueUser result = vueUserRepository.save(vueUser);
        return ResponseEntity.created(new URI("/api/vue-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vue-users : Updates an existing vueUser.
     *
     * @param vueUser the vueUser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vueUser,
     * or with status 400 (Bad Request) if the vueUser is not valid,
     * or with status 500 (Internal Server Error) if the vueUser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vue-users")
    public ResponseEntity<VueUser> updateVueUser(@Valid @RequestBody VueUser vueUser) throws URISyntaxException {
        log.debug("REST request to update VueUser : {}", vueUser);
        if (vueUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        checkUser(vueUser);
        vueUser.setCreatTime(Instant.now());
        VueUser result = vueUserRepository.save(vueUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vueUser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vue-users : get all the vueUsers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of vueUsers in body
     */
    @GetMapping("/vue-users")
    public List<VueUser> getAllVueUsers() {
        log.debug("REST request to get all VueUsers");
        return vueUserRepository.findAll();
    }

    /**
     * GET  /vue-users/:id : get the "id" vueUser.
     *
     * @param id the id of the vueUser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vueUser, or with status 404 (Not Found)
     */
    @GetMapping("/vue-users/{id}")
    public ResponseEntity<VueUser> getVueUser(@PathVariable Long id) {
        log.debug("REST request to get VueUser : {}", id);
        Optional<VueUser> vueUser = vueUserRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vueUser);
    }

    /**
     * DELETE  /vue-users/:id : delete the "id" vueUser.
     *
     * @param id the id of the vueUser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vue-users/{id}")
    public ResponseEntity<Void> deleteVueUser(@PathVariable Long id) {
        log.debug("REST request to delete VueUser : {}", id);
        vueUserRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
