package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.WhbApp;

import com.mycompany.myapp.domain.VueUser;
import com.mycompany.myapp.repository.VueUserRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VueUserResource REST controller.
 *
 * @see VueUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WhbApp.class)
public class VueUserResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_CNAME = "AAAAAAAAAA";
    private static final String UPDATED_CNAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_PIC = "AAAAAAAAAA";
    private static final String UPDATED_USER_PIC = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final Integer DEFAULT_MOBILE = 1;
    private static final Integer UPDATED_MOBILE = 2;

    private static final String DEFAULT_EMAIL = "WU@-x.D5.Uw";
    private static final String UPDATED_EMAIL = "IU@u.VL.H9bO";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREAT_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREAT_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_LOGIN_NUM = 1;
    private static final Integer UPDATED_LOGIN_NUM = 2;

    private static final Integer DEFAULT_ERR_NMU = 1;
    private static final Integer UPDATED_ERR_NMU = 2;

    private static final Integer DEFAULT_DEPT_ID = 1;
    private static final Integer UPDATED_DEPT_ID = 2;

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LOCK_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCK_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LOCK_REASON = "AAAAAAAAAA";
    private static final String UPDATED_LOCK_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_RESERVE = "AAAAAAAAAA";
    private static final String UPDATED_RESERVE = "BBBBBBBBBB";

    @Autowired
    private VueUserRepository vueUserRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restVueUserMockMvc;

    private VueUser vueUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VueUserResource vueUserResource = new VueUserResource(vueUserRepository);
        this.restVueUserMockMvc = MockMvcBuilders.standaloneSetup(vueUserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VueUser createEntity(EntityManager em) {
        VueUser vueUser = new VueUser()
            .name(DEFAULT_NAME)
            .password(DEFAULT_PASSWORD)
            .cname(DEFAULT_CNAME)
            .userPic(DEFAULT_USER_PIC)
            .address(DEFAULT_ADDRESS)
            .age(DEFAULT_AGE)
            .mobile(DEFAULT_MOBILE)
            .email(DEFAULT_EMAIL)
            .status(DEFAULT_STATUS)
            .creatTime(DEFAULT_CREAT_TIME)
            .loginNum(DEFAULT_LOGIN_NUM)
            .errNmu(DEFAULT_ERR_NMU)
            .deptId(DEFAULT_DEPT_ID)
            .creator(DEFAULT_CREATOR)
            .lockTime(DEFAULT_LOCK_TIME)
            .lockReason(DEFAULT_LOCK_REASON)
            .description(DEFAULT_DESCRIPTION)
            .reserve(DEFAULT_RESERVE);
        return vueUser;
    }

    @Before
    public void initTest() {
        vueUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createVueUser() throws Exception {
        int databaseSizeBeforeCreate = vueUserRepository.findAll().size();

        // Create the VueUser
        restVueUserMockMvc.perform(post("/api/vue-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vueUser)))
            .andExpect(status().isCreated());

        // Validate the VueUser in the database
        List<VueUser> vueUserList = vueUserRepository.findAll();
        assertThat(vueUserList).hasSize(databaseSizeBeforeCreate + 1);
        VueUser testVueUser = vueUserList.get(vueUserList.size() - 1);
        assertThat(testVueUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVueUser.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testVueUser.getCname()).isEqualTo(DEFAULT_CNAME);
        assertThat(testVueUser.getUserPic()).isEqualTo(DEFAULT_USER_PIC);
        assertThat(testVueUser.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testVueUser.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testVueUser.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testVueUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testVueUser.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testVueUser.getCreatTime()).isEqualTo(DEFAULT_CREAT_TIME);
        assertThat(testVueUser.getLoginNum()).isEqualTo(DEFAULT_LOGIN_NUM);
        assertThat(testVueUser.getErrNmu()).isEqualTo(DEFAULT_ERR_NMU);
        assertThat(testVueUser.getDeptId()).isEqualTo(DEFAULT_DEPT_ID);
        assertThat(testVueUser.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testVueUser.getLockTime()).isEqualTo(DEFAULT_LOCK_TIME);
        assertThat(testVueUser.getLockReason()).isEqualTo(DEFAULT_LOCK_REASON);
        assertThat(testVueUser.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVueUser.getReserve()).isEqualTo(DEFAULT_RESERVE);
    }

    @Test
    @Transactional
    public void createVueUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vueUserRepository.findAll().size();

        // Create the VueUser with an existing ID
        vueUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVueUserMockMvc.perform(post("/api/vue-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vueUser)))
            .andExpect(status().isBadRequest());

        // Validate the VueUser in the database
        List<VueUser> vueUserList = vueUserRepository.findAll();
        assertThat(vueUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = vueUserRepository.findAll().size();
        // set the field null
        vueUser.setName(null);

        // Create the VueUser, which fails.

        restVueUserMockMvc.perform(post("/api/vue-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vueUser)))
            .andExpect(status().isBadRequest());

        List<VueUser> vueUserList = vueUserRepository.findAll();
        assertThat(vueUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = vueUserRepository.findAll().size();
        // set the field null
        vueUser.setPassword(null);

        // Create the VueUser, which fails.

        restVueUserMockMvc.perform(post("/api/vue-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vueUser)))
            .andExpect(status().isBadRequest());

        List<VueUser> vueUserList = vueUserRepository.findAll();
        assertThat(vueUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = vueUserRepository.findAll().size();
        // set the field null
        vueUser.setEmail(null);

        // Create the VueUser, which fails.

        restVueUserMockMvc.perform(post("/api/vue-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vueUser)))
            .andExpect(status().isBadRequest());

        List<VueUser> vueUserList = vueUserRepository.findAll();
        assertThat(vueUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVueUsers() throws Exception {
        // Initialize the database
        vueUserRepository.saveAndFlush(vueUser);

        // Get all the vueUserList
        restVueUserMockMvc.perform(get("/api/vue-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vueUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].cname").value(hasItem(DEFAULT_CNAME.toString())))
            .andExpect(jsonPath("$.[*].userPic").value(hasItem(DEFAULT_USER_PIC.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].creatTime").value(hasItem(DEFAULT_CREAT_TIME.toString())))
            .andExpect(jsonPath("$.[*].loginNum").value(hasItem(DEFAULT_LOGIN_NUM)))
            .andExpect(jsonPath("$.[*].errNmu").value(hasItem(DEFAULT_ERR_NMU)))
            .andExpect(jsonPath("$.[*].deptId").value(hasItem(DEFAULT_DEPT_ID)))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].lockTime").value(hasItem(DEFAULT_LOCK_TIME.toString())))
            .andExpect(jsonPath("$.[*].lockReason").value(hasItem(DEFAULT_LOCK_REASON.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].reserve").value(hasItem(DEFAULT_RESERVE.toString())));
    }
    
    @Test
    @Transactional
    public void getVueUser() throws Exception {
        // Initialize the database
        vueUserRepository.saveAndFlush(vueUser);

        // Get the vueUser
        restVueUserMockMvc.perform(get("/api/vue-users/{id}", vueUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vueUser.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.cname").value(DEFAULT_CNAME.toString()))
            .andExpect(jsonPath("$.userPic").value(DEFAULT_USER_PIC.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.creatTime").value(DEFAULT_CREAT_TIME.toString()))
            .andExpect(jsonPath("$.loginNum").value(DEFAULT_LOGIN_NUM))
            .andExpect(jsonPath("$.errNmu").value(DEFAULT_ERR_NMU))
            .andExpect(jsonPath("$.deptId").value(DEFAULT_DEPT_ID))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.lockTime").value(DEFAULT_LOCK_TIME.toString()))
            .andExpect(jsonPath("$.lockReason").value(DEFAULT_LOCK_REASON.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.reserve").value(DEFAULT_RESERVE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVueUser() throws Exception {
        // Get the vueUser
        restVueUserMockMvc.perform(get("/api/vue-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVueUser() throws Exception {
        // Initialize the database
        vueUserRepository.saveAndFlush(vueUser);

        int databaseSizeBeforeUpdate = vueUserRepository.findAll().size();

        // Update the vueUser
        VueUser updatedVueUser = vueUserRepository.findById(vueUser.getId()).get();
        // Disconnect from session so that the updates on updatedVueUser are not directly saved in db
        em.detach(updatedVueUser);
        updatedVueUser
            .name(UPDATED_NAME)
            .password(UPDATED_PASSWORD)
            .cname(UPDATED_CNAME)
            .userPic(UPDATED_USER_PIC)
            .address(UPDATED_ADDRESS)
            .age(UPDATED_AGE)
            .mobile(UPDATED_MOBILE)
            .email(UPDATED_EMAIL)
            .status(UPDATED_STATUS)
            .creatTime(UPDATED_CREAT_TIME)
            .loginNum(UPDATED_LOGIN_NUM)
            .errNmu(UPDATED_ERR_NMU)
            .deptId(UPDATED_DEPT_ID)
            .creator(UPDATED_CREATOR)
            .lockTime(UPDATED_LOCK_TIME)
            .lockReason(UPDATED_LOCK_REASON)
            .description(UPDATED_DESCRIPTION)
            .reserve(UPDATED_RESERVE);

        restVueUserMockMvc.perform(put("/api/vue-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVueUser)))
            .andExpect(status().isOk());

        // Validate the VueUser in the database
        List<VueUser> vueUserList = vueUserRepository.findAll();
        assertThat(vueUserList).hasSize(databaseSizeBeforeUpdate);
        VueUser testVueUser = vueUserList.get(vueUserList.size() - 1);
        assertThat(testVueUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVueUser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testVueUser.getCname()).isEqualTo(UPDATED_CNAME);
        assertThat(testVueUser.getUserPic()).isEqualTo(UPDATED_USER_PIC);
        assertThat(testVueUser.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testVueUser.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testVueUser.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testVueUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testVueUser.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVueUser.getCreatTime()).isEqualTo(UPDATED_CREAT_TIME);
        assertThat(testVueUser.getLoginNum()).isEqualTo(UPDATED_LOGIN_NUM);
        assertThat(testVueUser.getErrNmu()).isEqualTo(UPDATED_ERR_NMU);
        assertThat(testVueUser.getDeptId()).isEqualTo(UPDATED_DEPT_ID);
        assertThat(testVueUser.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testVueUser.getLockTime()).isEqualTo(UPDATED_LOCK_TIME);
        assertThat(testVueUser.getLockReason()).isEqualTo(UPDATED_LOCK_REASON);
        assertThat(testVueUser.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVueUser.getReserve()).isEqualTo(UPDATED_RESERVE);
    }

    @Test
    @Transactional
    public void updateNonExistingVueUser() throws Exception {
        int databaseSizeBeforeUpdate = vueUserRepository.findAll().size();

        // Create the VueUser

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVueUserMockMvc.perform(put("/api/vue-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vueUser)))
            .andExpect(status().isBadRequest());

        // Validate the VueUser in the database
        List<VueUser> vueUserList = vueUserRepository.findAll();
        assertThat(vueUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVueUser() throws Exception {
        // Initialize the database
        vueUserRepository.saveAndFlush(vueUser);

        int databaseSizeBeforeDelete = vueUserRepository.findAll().size();

        // Delete the vueUser
        restVueUserMockMvc.perform(delete("/api/vue-users/{id}", vueUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VueUser> vueUserList = vueUserRepository.findAll();
        assertThat(vueUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VueUser.class);
        VueUser vueUser1 = new VueUser();
        vueUser1.setId(1L);
        VueUser vueUser2 = new VueUser();
        vueUser2.setId(vueUser1.getId());
        assertThat(vueUser1).isEqualTo(vueUser2);
        vueUser2.setId(2L);
        assertThat(vueUser1).isNotEqualTo(vueUser2);
        vueUser1.setId(null);
        assertThat(vueUser1).isNotEqualTo(vueUser2);
    }
}
