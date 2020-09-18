package br.com.udemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemy.converter.DozerConverter;
import br.com.udemy.converter.custom.PersonConverter;
import br.com.udemy.domain.Person;
import br.com.udemy.exception.ResourceNotFoundException;
import br.com.udemy.repository.PersonRepository;
import br.com.udemy.v2.PersonVOV2;
import br.com.udemy.vo.PersonVO;
import lombok.var;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PersonConverter personConverter;

	public PersonVO findById(Long id) {
		var entity = personRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public List<PersonVO> findAll() {
		return DozerConverter.parseListObjects(personRepository.findAll(), PersonVO.class);
	}

	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class); // var = PersonVO
		var vo = DozerConverter.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		var entity = personConverter.convertToVOEntity(person);
		var vo = personConverter.convertEntityToVO(personRepository.save(entity));
		return vo;
	}

	public void update(PersonVO person) {
		personRepository
		.findById(person.getId())
		.map(entity -> {
			entity.setFirstName(person.getFirstName());
			entity.setLastName(person.getLastName());
			entity.setAddress(person.getAddress());
			entity.setGender(person.getGender());
			return DozerConverter.parseObject(personRepository.save(entity), PersonVO.class);
		})
		.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}

	public void delete(Long id) {
		personRepository.findById(id)
		.map(person -> {
			personRepository.delete(person);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}
	
}
