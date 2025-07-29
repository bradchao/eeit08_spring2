package tw.brad.spring2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.brad.spring2.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	boolean existsByAccount(String account);
	
	Optional<Member> findByAccount(String account);
	
}
