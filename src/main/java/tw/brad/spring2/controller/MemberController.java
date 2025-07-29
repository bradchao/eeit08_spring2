package tw.brad.spring2.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.brad.spring2.entity.Member;
import tw.brad.spring2.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	public MemberController() {
		System.out.println("MemberController()");
	}
	
	/*
	 * response: true/false => Boolean
	 */
	@GetMapping("/exist")
	public ResponseEntity<Boolean> checkAccount(@RequestParam String account) {
		boolean  isExist = memberService.checkAccount(account);
		return ResponseEntity.ok(isExist);
	}
	
	/*
	 * request: Member {}
	 * response: {"message":"success/failure"} => Map<String,String>
	 */
	@PostMapping("/register")
	public ResponseEntity<Map<String,String>> register(@RequestBody Member member) {
		
		String result = memberService.register(member);
		
		Map<String,String> response = new HashMap<String, String>();
		response.put("message", result);
		
		return ResponseEntity.ok(response);
	}
	
	/*
	 * Request: {
	 * 	account:xxx,
	 * 	passwd:xxx
	 * }
	 * 
	 * Response: {
	 * 	success: true/false,
	 * 	mesg: ''
	 * }
	 */
	@PostMapping("/login")
	public ResponseEntity<Map<String,Object>> login(
			@RequestBody Map<String,String> body){
		String account = body.get("account");
		String passwd = body.get("passwd");
		
		boolean isValid = memberService.loginV2(account, passwd);
		
		Map<String,Object> response = new HashMap<>();
		response.put("success", isValid);
		response.put("mesg", isValid?"登入成功":"登入失敗");
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login3")
	public ResponseEntity<Map<String,Object>> login3(
			@RequestBody Map<String,String> body,
			HttpSession session){
		
		String account = body.get("account");
		String passwd = body.get("passwd");
		
		Member member = memberService.loginV3(account, passwd);
		if (member != null) {
			session.setAttribute("member", member);
		}else {
			session.invalidate();
		}
	
		Map<String,Object> response = new HashMap<>();
		response.put("success", member != null);
		response.put("mesg", member != null?"登入成功":"登入失敗");
		
		return ResponseEntity.ok(response);	
	}	
	
	@RequestMapping("/logout")
	public ResponseEntity<Map<String,Object>> logout(HttpSession session) {
		session.invalidate();
		
		Map<String,Object> response = new HashMap<>();
		response.put("success", true);
		response.put("mesg", "登出成功");
		
		return ResponseEntity.ok(response);	
		
	}
	
	@Autowired
	@Qualifier("companyName")
	private String companyName;
	
	@Value("${brad.company.tel}")
	private String companyTel;
	
	@RequestMapping("/status")
	public ResponseEntity<Map<String,Object>> status(HttpSession session){
		Object member = session.getAttribute("member");
		
		Map<String,Object> response = new HashMap<>();
		response.put("success", member != null);
		response.put("member", member);
		response.put("companyName", companyName);
		response.put("companyTel", companyTel);
		
		return ResponseEntity.ok(response);	
	}
	
	
	
	
	
	
	
	
	
}
