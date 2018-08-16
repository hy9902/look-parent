package cn.myhydt.app.gateway;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.test.context.annotation.SecurityTestExecutionListeners;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "hydt")
public class GatewayApplicationTests {
    @Autowired
    private WebApplicationContext applicationContext;


    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).apply(SecurityMockMvcConfigurers.springSecurity()).build();
                //.apply(SecurityMockMvcConfigurers.springSecurity()).build();
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
    //@WithUserDetails
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
