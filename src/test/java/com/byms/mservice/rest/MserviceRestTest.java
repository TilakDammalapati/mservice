package com.byms.mservice.rest;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;
import com.byms.mservice.model.User;
import com.byms.mservice.service.MService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class MserviceRestTest {

    @Mock
    private MService mService;

    @Autowired
    private MserviceRest mserviceRest;
    @Test
    public void testWelcome() {
        String name = "alex";
        Mockito.when(mService.getWelcomeMessage(anyString())).thenReturn(buildUser("name"));


       // mserviceRest.hello("alex");
    }

    private User buildUser(String name){
        User u = new User();
        u.setUsername(name);
        u.setId(UUID.randomUUID());
        return u;
    }
}
