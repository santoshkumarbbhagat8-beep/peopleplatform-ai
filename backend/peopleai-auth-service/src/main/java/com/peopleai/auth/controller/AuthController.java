package com.peopleai.auth.controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/auth") public class AuthController {
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","UP");}
 @PostMapping("/login") public Map<String,Object> login(@RequestBody LoginRequest request){if(request.email()==null||request.password()==null||request.password().isBlank())throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST,"Email and password are required");return Map.of("success",true,"token","demo-token-not-for-production","user",Map.of("id","demo-user","name","PeopleAI Demo","email",request.email(),"role","Admin"));}
 public record LoginRequest(String email,String password){}
}
