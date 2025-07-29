package tw.brad.spring2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.brad.spring2.entity.Member;
import tw.brad.spring2.repository.MemberRepository;
import tw.brad.spring2.utils.BCrypt;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public MemberService() {System.out.println("MemberService()");}
	
	public boolean checkAccount(String account) {
		return memberRepository.existsByAccount(account);
	}
	
	public String register(Member member) {
		if (memberRepository.existsByAccount(member.getAccount())) {
			return "Account 已使用";
		}
		member.setPasswd(BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
		memberRepository.save(member);
		return "註冊成功";
	}
	
}
