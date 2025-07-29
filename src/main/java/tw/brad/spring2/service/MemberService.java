package tw.brad.spring2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
	
	public boolean login(String account, String passwd) {
		Member member = memberRepository.findByAccount(account).orElse(null);
		if (member != null && BCrypt.checkpw(passwd, member.getPasswd())) {
			return true;
		}
		return false;
	}
	
	public boolean loginV2(String account, String passwd) {
		Member member = new Member();
		member.setAccount(account);
		
		Example<Member> ex = Example.of(member);
		if (memberRepository.exists(ex)) {
			List<Member> members = memberRepository.findAll(ex);
			if (BCrypt.checkpw(passwd, members.get(0).getPasswd())) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	
}
