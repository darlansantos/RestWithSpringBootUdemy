package br.com.udemy.service;

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
		person.setGender("Masculino");
		return person;
	}

}
