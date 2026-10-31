
package com.swynx.linkpeer_backend.auth.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {

    @GetMapping("/api-docs")
    public String apiDocs() {
        return "forward:/api-docs.html";
    }
}
