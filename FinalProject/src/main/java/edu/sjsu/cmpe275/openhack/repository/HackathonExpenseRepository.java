package edu.sjsu.cmpe275.openhack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sjsu.cmpe275.openhack.model.Hackathon;
import edu.sjsu.cmpe275.openhack.model.HackathonExpense;

/**
 * 
 * @author pratikb
 *
 */
public interface HackathonExpenseRepository extends JpaRepository<HackathonExpense, Long>{
	
	public HackathonExpense findByHackathon(Hackathon h);
	
}
