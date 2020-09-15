package br.com.udemy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.udemy.domain.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong(); // utilizado para gerar id novo

	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Darlan");
		person.setLastName("Parreiras");
		person.setAddress("Belo Horizonte - MInas Gerais - Brasil");
		person.setGender("Male");
		return person;
	}

	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}

	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name" + i);
		person.setLastName("Last name");
		person.setAddress("Some address in Brasil");
		person.setGender("Male");
		return person;
	}

	public Person create(Person person) {
		return person;
	}

	public Person update(Person person) {
		return person;
	}

	public void delete(String id) {
	}
	
}
