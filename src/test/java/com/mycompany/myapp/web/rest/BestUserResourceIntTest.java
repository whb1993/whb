package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.WhbApp;

import com.mycompany.myapp.domain.BestUser;
import com.mycompany.myapp.repository.BestUserRepository;
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
import java.util.List;


import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BestUserResource REST controller.
 *
 * @see BestUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WhbApp.class)
public class BestUserResourceIntTest {

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_VALUE_1 = 0;
    private static final Integer UPDATED_VALUE_1 = 1;

    private static final Integer DEFAULT_VALUE_2 = 0;
    private static final Integer UPDATED_VALUE_2 = 1;

    private static final Integer DEFAULT_VALUE_3 = 0;
    private static final Integer UPDATED_VALUE_3 = 1;

    private static final Integer DEFAULT_VALUE_4 = 0;
    private static final Integer UPDATED_VALUE_4 = 1;

    private static final Integer DEFAULT_VALUE_5 = 0;
    private static final Integer UPDATED_VALUE_5 = 1;

    private static final Integer DEFAULT_VALUE_6 = 0;
    private static final Integer UPDATED_VALUE_6 = 1;

    private static final Integer DEFAULT_VALUE_7 = 0;
    private static final Integer UPDATED_VALUE_7 = 1;

    private static final Integer DEFAULT_VALUE_8 = 0;
    private static final Integer UPDATED_VALUE_8 = 1;

    private static final Integer DEFAULT_VALUE_9 = 0;
    private static final Integer UPDATED_VALUE_9 = 1;

    private static final Integer DEFAULT_VALUE_10 = 0;
    private static final Integer UPDATED_VALUE_10 = 1;

    private static final Integer DEFAULT_VALUE_11 = 0;
    private static final Integer UPDATED_VALUE_11 = 1;

    private static final Integer DEFAULT_VALUE_12 = 0;
    private static final Integer UPDATED_VALUE_12 = 1;

    private static final String DEFAULT_YUANYIN = "AAAAAAAAAA";
    private static final String UPDATED_YUANYIN = "BBBBBBBBBB";

    private static final String DEFAULT_DES_1 = "AAAAAAAAAA";
    private static final String UPDATED_DES_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DES_2 = "AAAAAAAAAA";
    private static final String UPDATED_DES_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DES_3 = "AAAAAAAAAA";
    private static final String UPDATED_DES_3 = "BBBBBBBBBB";

    private static final String DEFAULT_DES_4 = "AAAAAAAAAA";
    private static final String UPDATED_DES_4 = "BBBBBBBBBB";

    private static final String DEFAULT_DES_5 = "AAAAAAAAAA";
    private static final String UPDATED_DES_5 = "BBBBBBBBBB";

    private static final String DEFAULT_DES_6 = "AAAAAAAAAA";
    private static final String UPDATED_DES_6 = "BBBBBBBBBB";

    private static final String DEFAULT_DES_7 = "AAAAAAAAAA";
    private static final String UPDATED_DES_7 = "BBBBBBBBBB";

    @Autowired
    private BestUserRepository bestUserRepository;

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

    private MockMvc restBestUserMockMvc;

    private BestUser bestUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BestUserResource bestUserResource = new BestUserResource(bestUserRepository);
        this.restBestUserMockMvc = MockMvcBuilders.standaloneSetup(bestUserResource)
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
    public static BestUser createEntity(EntityManager em) {
        BestUser bestUser = new BestUser()
            .userName(DEFAULT_USER_NAME)
            .value1(DEFAULT_VALUE_1)
            .value2(DEFAULT_VALUE_2)
            .value3(DEFAULT_VALUE_3)
            .value4(DEFAULT_VALUE_4)
            .value5(DEFAULT_VALUE_5)
            .value6(DEFAULT_VALUE_6)
            .value7(DEFAULT_VALUE_7)
            .value8(DEFAULT_VALUE_8)
            .value9(DEFAULT_VALUE_9)
            .value10(DEFAULT_VALUE_10)
            .value11(DEFAULT_VALUE_11)
            .value12(DEFAULT_VALUE_12)
            .yuanyin(DEFAULT_YUANYIN)
            .des1(DEFAULT_DES_1)
            .des2(DEFAULT_DES_2)
            .des3(DEFAULT_DES_3)
            .des4(DEFAULT_DES_4)
            .des5(DEFAULT_DES_5)
            .des6(DEFAULT_DES_6)
            .des7(DEFAULT_DES_7);
        return bestUser;
    }

    @Before
    public void initTest() {
        bestUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createBestUser() throws Exception {
        int databaseSizeBeforeCreate = bestUserRepository.findAll().size();

        // Create the BestUser
        restBestUserMockMvc.perform(post("/api/best-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bestUser)))
            .andExpect(status().isCreated());

        // Validate the BestUser in the database
        List<BestUser> bestUserList = bestUserRepository.findAll();
        assertThat(bestUserList).hasSize(databaseSizeBeforeCreate + 1);
        BestUser testBestUser = bestUserList.get(bestUserList.size() - 1);
        assertThat(testBestUser.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testBestUser.getValue1()).isEqualTo(DEFAULT_VALUE_1);
        assertThat(testBestUser.getValue2()).isEqualTo(DEFAULT_VALUE_2);
        assertThat(testBestUser.getValue3()).isEqualTo(DEFAULT_VALUE_3);
        assertThat(testBestUser.getValue4()).isEqualTo(DEFAULT_VALUE_4);
        assertThat(testBestUser.getValue5()).isEqualTo(DEFAULT_VALUE_5);
        assertThat(testBestUser.getValue6()).isEqualTo(DEFAULT_VALUE_6);
        assertThat(testBestUser.getValue7()).isEqualTo(DEFAULT_VALUE_7);
        assertThat(testBestUser.getValue8()).isEqualTo(DEFAULT_VALUE_8);
        assertThat(testBestUser.getValue9()).isEqualTo(DEFAULT_VALUE_9);
        assertThat(testBestUser.getValue10()).isEqualTo(DEFAULT_VALUE_10);
        assertThat(testBestUser.getValue11()).isEqualTo(DEFAULT_VALUE_11);
        assertThat(testBestUser.getValue12()).isEqualTo(DEFAULT_VALUE_12);
        assertThat(testBestUser.getYuanyin()).isEqualTo(DEFAULT_YUANYIN);
        assertThat(testBestUser.getDes1()).isEqualTo(DEFAULT_DES_1);
        assertThat(testBestUser.getDes2()).isEqualTo(DEFAULT_DES_2);
        assertThat(testBestUser.getDes3()).isEqualTo(DEFAULT_DES_3);
        assertThat(testBestUser.getDes4()).isEqualTo(DEFAULT_DES_4);
        assertThat(testBestUser.getDes5()).isEqualTo(DEFAULT_DES_5);
        assertThat(testBestUser.getDes6()).isEqualTo(DEFAULT_DES_6);
        assertThat(testBestUser.getDes7()).isEqualTo(DEFAULT_DES_7);
    }

    @Test
    @Transactional
    public void createBestUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bestUserRepository.findAll().size();

        // Create the BestUser with an existing ID
        bestUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBestUserMockMvc.perform(post("/api/best-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bestUser)))
            .andExpect(status().isBadRequest());

        // Validate the BestUser in the database
        List<BestUser> bestUserList = bestUserRepository.findAll();
        assertThat(bestUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = bestUserRepository.findAll().size();
        // set the field null
        bestUser.setUserName(null);

        // Create the BestUser, which fails.

        restBestUserMockMvc.perform(post("/api/best-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bestUser)))
            .andExpect(status().isBadRequest());

        List<BestUser> bestUserList = bestUserRepository.findAll();
        assertThat(bestUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBestUsers() throws Exception {
        // Initialize the database
        bestUserRepository.saveAndFlush(bestUser);

        // Get all the bestUserList
        restBestUserMockMvc.perform(get("/api/best-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bestUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].value1").value(hasItem(DEFAULT_VALUE_1)))
            .andExpect(jsonPath("$.[*].value2").value(hasItem(DEFAULT_VALUE_2)))
            .andExpect(jsonPath("$.[*].value3").value(hasItem(DEFAULT_VALUE_3)))
            .andExpect(jsonPath("$.[*].value4").value(hasItem(DEFAULT_VALUE_4)))
            .andExpect(jsonPath("$.[*].value5").value(hasItem(DEFAULT_VALUE_5)))
            .andExpect(jsonPath("$.[*].value6").value(hasItem(DEFAULT_VALUE_6)))
            .andExpect(jsonPath("$.[*].value7").value(hasItem(DEFAULT_VALUE_7)))
            .andExpect(jsonPath("$.[*].value8").value(hasItem(DEFAULT_VALUE_8)))
            .andExpect(jsonPath("$.[*].value9").value(hasItem(DEFAULT_VALUE_9)))
            .andExpect(jsonPath("$.[*].value10").value(hasItem(DEFAULT_VALUE_10)))
            .andExpect(jsonPath("$.[*].value11").value(hasItem(DEFAULT_VALUE_11)))
            .andExpect(jsonPath("$.[*].value12").value(hasItem(DEFAULT_VALUE_12)))
            .andExpect(jsonPath("$.[*].yuanyin").value(hasItem(DEFAULT_YUANYIN.toString())))
            .andExpect(jsonPath("$.[*].des1").value(hasItem(DEFAULT_DES_1.toString())))
            .andExpect(jsonPath("$.[*].des2").value(hasItem(DEFAULT_DES_2.toString())))
            .andExpect(jsonPath("$.[*].des3").value(hasItem(DEFAULT_DES_3.toString())))
            .andExpect(jsonPath("$.[*].des4").value(hasItem(DEFAULT_DES_4.toString())))
            .andExpect(jsonPath("$.[*].des5").value(hasItem(DEFAULT_DES_5.toString())))
            .andExpect(jsonPath("$.[*].des6").value(hasItem(DEFAULT_DES_6.toString())))
            .andExpect(jsonPath("$.[*].des7").value(hasItem(DEFAULT_DES_7.toString())));
    }
    
    @Test
    @Transactional
    public void getBestUser() throws Exception {
        // Initialize the database
        bestUserRepository.saveAndFlush(bestUser);

        // Get the bestUser
        restBestUserMockMvc.perform(get("/api/best-users/{id}", bestUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bestUser.getId().intValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.value1").value(DEFAULT_VALUE_1))
            .andExpect(jsonPath("$.value2").value(DEFAULT_VALUE_2))
            .andExpect(jsonPath("$.value3").value(DEFAULT_VALUE_3))
            .andExpect(jsonPath("$.value4").value(DEFAULT_VALUE_4))
            .andExpect(jsonPath("$.value5").value(DEFAULT_VALUE_5))
            .andExpect(jsonPath("$.value6").value(DEFAULT_VALUE_6))
            .andExpect(jsonPath("$.value7").value(DEFAULT_VALUE_7))
            .andExpect(jsonPath("$.value8").value(DEFAULT_VALUE_8))
            .andExpect(jsonPath("$.value9").value(DEFAULT_VALUE_9))
            .andExpect(jsonPath("$.value10").value(DEFAULT_VALUE_10))
            .andExpect(jsonPath("$.value11").value(DEFAULT_VALUE_11))
            .andExpect(jsonPath("$.value12").value(DEFAULT_VALUE_12))
            .andExpect(jsonPath("$.yuanyin").value(DEFAULT_YUANYIN.toString()))
            .andExpect(jsonPath("$.des1").value(DEFAULT_DES_1.toString()))
            .andExpect(jsonPath("$.des2").value(DEFAULT_DES_2.toString()))
            .andExpect(jsonPath("$.des3").value(DEFAULT_DES_3.toString()))
            .andExpect(jsonPath("$.des4").value(DEFAULT_DES_4.toString()))
            .andExpect(jsonPath("$.des5").value(DEFAULT_DES_5.toString()))
            .andExpect(jsonPath("$.des6").value(DEFAULT_DES_6.toString()))
            .andExpect(jsonPath("$.des7").value(DEFAULT_DES_7.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBestUser() throws Exception {
        // Get the bestUser
        restBestUserMockMvc.perform(get("/api/best-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBestUser() throws Exception {
        // Initialize the database
        bestUserRepository.saveAndFlush(bestUser);

        int databaseSizeBeforeUpdate = bestUserRepository.findAll().size();

        // Update the bestUser
        BestUser updatedBestUser = bestUserRepository.findById(bestUser.getId()).get();
        // Disconnect from session so that the updates on updatedBestUser are not directly saved in db
        em.detach(updatedBestUser);
        updatedBestUser
            .userName(UPDATED_USER_NAME)
            .value1(UPDATED_VALUE_1)
            .value2(UPDATED_VALUE_2)
            .value3(UPDATED_VALUE_3)
            .value4(UPDATED_VALUE_4)
            .value5(UPDATED_VALUE_5)
            .value6(UPDATED_VALUE_6)
            .value7(UPDATED_VALUE_7)
            .value8(UPDATED_VALUE_8)
            .value9(UPDATED_VALUE_9)
            .value10(UPDATED_VALUE_10)
            .value11(UPDATED_VALUE_11)
            .value12(UPDATED_VALUE_12)
            .yuanyin(UPDATED_YUANYIN)
            .des1(UPDATED_DES_1)
            .des2(UPDATED_DES_2)
            .des3(UPDATED_DES_3)
            .des4(UPDATED_DES_4)
            .des5(UPDATED_DES_5)
            .des6(UPDATED_DES_6)
            .des7(UPDATED_DES_7);

        restBestUserMockMvc.perform(put("/api/best-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBestUser)))
            .andExpect(status().isOk());

        // Validate the BestUser in the database
        List<BestUser> bestUserList = bestUserRepository.findAll();
        assertThat(bestUserList).hasSize(databaseSizeBeforeUpdate);
        BestUser testBestUser = bestUserList.get(bestUserList.size() - 1);
        assertThat(testBestUser.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testBestUser.getValue1()).isEqualTo(UPDATED_VALUE_1);
        assertThat(testBestUser.getValue2()).isEqualTo(UPDATED_VALUE_2);
        assertThat(testBestUser.getValue3()).isEqualTo(UPDATED_VALUE_3);
        assertThat(testBestUser.getValue4()).isEqualTo(UPDATED_VALUE_4);
        assertThat(testBestUser.getValue5()).isEqualTo(UPDATED_VALUE_5);
        assertThat(testBestUser.getValue6()).isEqualTo(UPDATED_VALUE_6);
        assertThat(testBestUser.getValue7()).isEqualTo(UPDATED_VALUE_7);
        assertThat(testBestUser.getValue8()).isEqualTo(UPDATED_VALUE_8);
        assertThat(testBestUser.getValue9()).isEqualTo(UPDATED_VALUE_9);
        assertThat(testBestUser.getValue10()).isEqualTo(UPDATED_VALUE_10);
        assertThat(testBestUser.getValue11()).isEqualTo(UPDATED_VALUE_11);
        assertThat(testBestUser.getValue12()).isEqualTo(UPDATED_VALUE_12);
        assertThat(testBestUser.getYuanyin()).isEqualTo(UPDATED_YUANYIN);
        assertThat(testBestUser.getDes1()).isEqualTo(UPDATED_DES_1);
        assertThat(testBestUser.getDes2()).isEqualTo(UPDATED_DES_2);
        assertThat(testBestUser.getDes3()).isEqualTo(UPDATED_DES_3);
        assertThat(testBestUser.getDes4()).isEqualTo(UPDATED_DES_4);
        assertThat(testBestUser.getDes5()).isEqualTo(UPDATED_DES_5);
        assertThat(testBestUser.getDes6()).isEqualTo(UPDATED_DES_6);
        assertThat(testBestUser.getDes7()).isEqualTo(UPDATED_DES_7);
    }

    @Test
    @Transactional
    public void updateNonExistingBestUser() throws Exception {
        int databaseSizeBeforeUpdate = bestUserRepository.findAll().size();

        // Create the BestUser

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBestUserMockMvc.perform(put("/api/best-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bestUser)))
            .andExpect(status().isBadRequest());

        // Validate the BestUser in the database
        List<BestUser> bestUserList = bestUserRepository.findAll();
        assertThat(bestUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBestUser() throws Exception {
        // Initialize the database
        bestUserRepository.saveAndFlush(bestUser);

        int databaseSizeBeforeDelete = bestUserRepository.findAll().size();

        // Delete the bestUser
        restBestUserMockMvc.perform(delete("/api/best-users/{id}", bestUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BestUser> bestUserList = bestUserRepository.findAll();
        assertThat(bestUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BestUser.class);
        BestUser bestUser1 = new BestUser();
        bestUser1.setId(1L);
        BestUser bestUser2 = new BestUser();
        bestUser2.setId(bestUser1.getId());
        assertThat(bestUser1).isEqualTo(bestUser2);
        bestUser2.setId(2L);
        assertThat(bestUser1).isNotEqualTo(bestUser2);
        bestUser1.setId(null);
        assertThat(bestUser1).isNotEqualTo(bestUser2);
    }
}
