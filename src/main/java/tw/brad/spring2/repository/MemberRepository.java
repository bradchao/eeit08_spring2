package tw.brad.spring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.brad.spring2.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	boolean existsByAccount(String account);
	
	
	
}
