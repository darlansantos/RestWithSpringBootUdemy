package br.com.udemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemy.domain.Person;
import br.com.udemy.exception.ResourceNotFoundException;
import br.com.udemy.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;

	public Person findById(Long id) {
		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public Person create(Person person) {
		return personRepository.save(person);
	}

	public Person update(Person person) {
		Person entity = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return personRepository.save(entity);
	}

	public void delete(Long id) {
		Person entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		personRepository.delete(entity);
	}
	
}
