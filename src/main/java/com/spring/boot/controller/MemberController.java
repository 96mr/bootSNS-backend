package com.spring.boot.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dto.MemberDto;
import com.spring.boot.entity.MemberDetails;
import com.spring.boot.member.repository.MemberRepository;
import com.spring.boot.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class MemberController {
	
	private final MemberService memberService;
	private final MemberRepository memberRepository;
	
	@GetMapping("/login")
	public ResponseEntity<String> loginGet(@AuthenticationPrincipal MemberDetails memberDetail) {
		if(memberDetail != null) {
			return ResponseEntity.status(HttpStatus.OK).body("home");
		}
		return ResponseEntity.ok("login");
	}
	
	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String id, @RequestParam String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.OK).body("home");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

	@GetMapping("/checkSession")
	public ResponseEntity<String> checkSession(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated() 
	        && !(authentication instanceof AnonymousAuthenticationToken)) {
	    	MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
	        return ResponseEntity.status(HttpStatus.OK).body(memberDetails.getMember().getId());
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
	    }
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logout";
	}
	
	@PostMapping("/join")
	public ResponseEntity<String> register(@RequestBody @Valid MemberDto.request dto){
		memberService.join(dto);	
		return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 되었습니다.");
	}
	
	@GetMapping("/check-id/{id}")
	public ResponseEntity<Void> checkId(@PathVariable String id){
		if(memberRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
