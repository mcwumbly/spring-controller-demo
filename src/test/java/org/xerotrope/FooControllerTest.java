package org.xerotrope;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringControllerDemoApplication.class)
public class FooControllerTest {

    MockMvc mockMvc;

    @Autowired
    FooController fooController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(fooController).build();
    }

    @Test
    public void getFoo_whenCalledWithVersion1Header_returnsVersion1Response() throws Exception {
        mockMvc.perform(get("/foo")
                .accept("application/json")
                .header("version", 1)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value(1))
                .andExpect(jsonPath("$.foo").value("foo"))
                .andExpect(jsonPath("$.*", hasSize(2)))
        ;
    }

    @Test
    public void getFoo_whenCalledWithVersion2Header_returnsVersion2Response() throws Exception {
        mockMvc.perform(get("/foo")
                        .accept("application/json")
                        .header("version", 2)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value(2))
                .andExpect(jsonPath("$.foo").value("foo"))
                .andExpect(jsonPath("$.bar").value("bar"))
                .andExpect(jsonPath("$.*", hasSize(3)))
        ;
    }

    @Test
    public void getFoo_whenCalledWithNoVersionHeader_returnsVersion2Response() throws Exception {
        mockMvc.perform(get("/foo")
                        .accept("application/json")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value(2))
                .andExpect(jsonPath("$.foo").value("foo"))
                .andExpect(jsonPath("$.bar").value("bar"))
                .andExpect(jsonPath("$.*", hasSize(3)))
        ;
    }

    @Test
    public void getFoo_whenCalledWithIllegalVersionHeader_returnsErrorResponse() throws Exception {
        mockMvc.perform(get("/foo")
                        .accept("application/json")
                        .header("version", "BOOM!")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid version"))
                .andExpect(jsonPath("$.message").value("Failed to convert value of type [java.lang.String] to required type [java.lang.Integer]; nested exception is java.lang.NumberFormatException: For input string: \"BOOM!\""))
                .andExpect(jsonPath("$.*", hasSize(2)))
        ;
    }


}
