package com.peopleai.gateway.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import java.util.Map;
@RestController @RequestMapping("/api") public class GatewayController {
 private final RestClient client; private final Map<String,String> services;
 public GatewayController(RestClient.Builder b,@Value("${services.auth}") String auth,@Value("${services.jobs}") String jobs,@Value("${services.candidates}") String candidates,@Value("${services.contact}") String contact){client=b.build();services=Map.of("auth",auth,"jobs",jobs,"candidates",candidates,"contact",contact);}
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","UP","service","gateway");}
 @GetMapping("/dashboard") public Map<String,Object> dashboard(){return Map.of("stats",new Object[]{Map.of("label","Interviews completed","value","12.4k"),Map.of("label","Hiring velocity","value","3.2x"),Map.of("label","AI score accuracy","value","96%"),Map.of("label","Client retention","value","89%")},"pipeline",new Object[]{Map.of("name","Senior Frontend Engineer","count",18),Map.of("name","AI Product Manager","count",9),Map.of("name","ML Ops Engineer","count",14)});}
 @RequestMapping(value="/{service}/**",method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}) public Object proxy(@PathVariable String service,HttpServletRequest request,@RequestBody(required=false) Object body){String base=services.get(service);if(base==null)throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND);String uri=request.getRequestURI().substring("/api".length());var spec=client.method(org.springframework.http.HttpMethod.valueOf(request.getMethod())).uri(base+uri).contentType(MediaType.APPLICATION_JSON);if(body!=null)spec.body(body);return spec.retrieve().body(Object.class);}
}
