package edu.sjsu.cmpe275.openhack.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.openhack.model.Hackathon;
import edu.sjsu.cmpe275.openhack.repository.HackathonRepository;

/**
 * 
 * @author pratikb
 *
 */

@Service
public class HackathonService {

	@Autowired
	private HackathonRepository hackathonRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	/**
	 * Fetches all hackathons
	 * @return List of hackathons
	 */
	public List<Hackathon> getAllHackathons() {
		List<Hackathon> hackList = new ArrayList<Hackathon>();
		hackathonRepository.findAll().forEach(hackList::add);
		return hackList;
	}
	
	/**
	 * Get hackathon by hackathon ID
	 * @param id
	 * @return hackathon object
	 */
	public Hackathon getHackathonById(Long id) {
		return hackathonRepository.findOne(id);
	}
	
	public void addHackathon(Hackathon obj) {
		hackathonRepository.save(obj);
	}
	
	public void deleteHackathonById(Long id) {
		hackathonRepository.delete(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Hackathon> getAllFutureOngoingHAckathons() {
		Query query = entityManager.createQuery("from Hackathon where curdate() between startDate AND endDate");
	    return  (List<Hackathon>) query.getResultList();
	}
}

