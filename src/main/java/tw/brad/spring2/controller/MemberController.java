package tw.brad.spring2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.spring2.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	public MemberController() {
		System.out.println("MemberController()");
	}
	
	@GetMapping("/exist")
	public void checkAccount(@RequestParam String account) {
		boolean  isExist = memberService.checkAccount(account);
		System.out.println(isExist);
	}
	
	@PostMapping("/register")
	public void register(@RequestBody String aaa) {
		
	}
	
}
