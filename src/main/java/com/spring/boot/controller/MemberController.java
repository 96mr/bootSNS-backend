package com.spring.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.entity.MemberDetails;
import com.spring.boot.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/login")
	public ResponseEntity<String> loginGet(@AuthenticationPrincipal MemberDetails memberDetail) {
		if(memberDetail != null) {
			System.out.println("login GET");
			return ResponseEntity.ok("home");
		}
		return ResponseEntity.ok("login");
	}
	
	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String id, @RequestParam String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("home");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

	@GetMapping("/checkSession")
	public ResponseEntity<String> checkSession(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    // 로그를 통해 authentication 객체를 확인
	    System.out.println("Authentication: " + authentication);
	    
	    // 인증 객체가 인증된 상태인지 확인
	    if (authentication != null && authentication.isAuthenticated() 
	        && !(authentication instanceof AnonymousAuthenticationToken)) {
	        System.out.println("Authenticated: " + authentication.getPrincipal());
	        return ResponseEntity.ok("Authenticated");
	    } else {
	        // 인증되지 않은 경우
	        System.out.println("Not Authenticated");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
	    }
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logout";
	}
	
	@PostMapping("/join")
	public ResponseEntity<String> join(){
		memberService.join();	
		return ResponseEntity.ok()
							.body("회원가입");
	}
	
	@GetMapping("/user/info")
	public ResponseEntity<?> userInfo(@AuthenticationPrincipal MemberDetails memberDetail){
		if(memberDetail != null) {
			return ResponseEntity.ok().body(memberDetail.getMember().getId());
		}
		else
			return ResponseEntity.notFound().build();
	}
}
