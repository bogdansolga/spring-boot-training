package net.safedata.springboot.training.d01.s05.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class SessionScopedService {

    // we'd need to run the app in a web server to actually use the scope
}
