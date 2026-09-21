package vn.iotstar.example1.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.example1.security.Example1Principal;

@ControllerAdvice(annotations = Controller.class)
public class CurrentUserAdvice {
    @ModelAttribute("currentUser") Example1Principal currentUser(Authentication authentication) { return authentication != null && authentication.getPrincipal() instanceof Example1Principal principal ? principal : null; }
}
