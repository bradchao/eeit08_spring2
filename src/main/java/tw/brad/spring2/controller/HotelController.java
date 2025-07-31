package tw.brad.spring2.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.spring2.entity.Hotel;
import tw.brad.spring2.repository.HotelRepository;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

	@Autowired
	private HotelRepository hotelRepository;
	
	@GetMapping("/all")
	public ResponseEntity<Page<Hotel>> getHotel(
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size){
		
		Pageable pageable = PageRequest.of(page, size);
		Page<Hotel> pageHotel = hotelRepository.findAll(pageable);
		
		//Map<String,String> response = new HashMap<String, String>();
		
		return ResponseEntity.ok(pageHotel);		
	}
	
}
