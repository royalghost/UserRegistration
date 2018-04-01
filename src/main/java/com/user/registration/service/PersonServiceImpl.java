package com.user.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.registration.model.PersonForm;
import com.user.registration.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public PersonServiceImpl() {
	}

	@Override
	public void add(PersonForm person) {
		personRepository.save(person);
	}

}
