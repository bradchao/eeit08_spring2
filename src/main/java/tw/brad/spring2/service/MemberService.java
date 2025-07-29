package tw.brad.spring2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.brad.spring2.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public MemberService() {System.out.println("MemberService()");}
	
	public boolean checkAccount(String account) {
		
		
		return memberRepository.existsByAccount(account);
	}
	
}
