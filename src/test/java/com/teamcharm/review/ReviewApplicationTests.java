package com.teamcharm.review;

import org.assertj.core.api.Assertions;
import com.teamcharm.review.model.Address;
import com.teamcharm.review.model.Place;
import com.teamcharm.review.repository.AddressRepository;
import com.teamcharm.review.repository.PlaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class ReviewApplicationTests {
    
        @Autowired
        PlaceRepository placeRepository;
        @Autowired
        AddressRepository addressRepository;

	@Test
	public void placeSearch() {
            Place place = new Place();
            place.setId(1);
            place.setName("Test1Detelte");
            Address address = new Address();
            address.setDong("대구");
            address.setZipCode(700290);
            address = addressRepository.save(address);
            place.setAddress(address);
            
            placeRepository.save(place);
            
            Place place2 = new Place();
            place.setId(3);
            place2.setName("Test1Detelte2");
            Address address2 = new Address();
            address2.setSido("대구");
            address2.setZipCode(700290);
            address2 = addressRepository.save(address2);
            place2.setAddress(address2);
            placeRepository.save(place2);
            
            place = new Place();
            place.setId(2);
            place.setName("대구");
            address = new Address();
            address.setDong("이곡동");
            address.setZipCode(700290);
            address = addressRepository.save(address);
            place.setAddress(address);
            placeRepository.save(place);
            
            
            Page<Place> result2 = placeRepository.findByAddressZipCode(700290, null);
            Assertions.assertThat(result2.getTotalElements() > 1).isTrue();
            
            Page<Place> result1 = placeRepository.findAllByAddressDongContainingIgnoreCaseOrAddressSidoContainingIgnoreCaseOrAddressSigunguContainingIgnoreCaseOrNameContaining("대구", "대구", "대구", "대구", null);
            Assertions.assertThat(result1.getTotalElements() ==3).isTrue();
            
	}

}
