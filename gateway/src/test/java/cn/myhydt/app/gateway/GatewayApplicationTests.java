package cn.myhydt.app.gateway;

import cn.myhydt.app.gateway.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "hydt")
@Slf4j
public class GatewayApplicationTests {
    @Autowired
    private WebApplicationContext applicationContext;


    private MockMvc mockMvc;

    private  CsrfToken csrfToken;

    private MockHttpSession session;

    @Autowired
    private CommonService commonService;

    @Before
    public void init(){
        //mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).apply(SecurityMockMvcConfigurers.springSecurity()).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
        session = new MockHttpSession();
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testCipher() {
        PasswordEncoder passwordEncoder = new SCryptPasswordEncoder();
        boolean isMatch = passwordEncoder.matches("vgundam0097", "ec6d9d84a00ee55a91513e223614e91702346207a2b573c6da6ea756d1747fd72cbe9d55be569b393e88f2240cdafa0c");
        System.out.println(isMatch);
        System.out.println("------------SCrypt----------------");
        System.out.println(passwordEncoder.encode("vgundam0097"));



        passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("------------BCrypt----------------");
        System.out.println(passwordEncoder.encode("vgundam0097"));

        passwordEncoder = new Pbkdf2PasswordEncoder("didispace");
        System.out.println("------------Pbkdf2----------------");

        System.out.println(passwordEncoder.encode("vgundam0097"));

    }

    @Test
    @WithUserDetails
    //@WithAnonymousUser
    //CSRF 保护默认是开启的，那么在 POST 方式提交表单的时候就必须验证 Token，如果没有，那么自然也就是 403 没权限了。
    public void testHello() throws Exception{
        String uri = "/hello";
        String content = null;
        MvcResult mvcResult = mockMvc.perform(post(uri)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        if(response.getStatus() != HttpStatus.OK.value()){
            System.out.println(response.getErrorMessage());
        } else {
            String resp = response.getContentAsString();
            System.out.println(resp);
        }
    }

}
