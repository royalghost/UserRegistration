/**
 * 
 */
package com.user.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.registration.model.PersonForm;

/**
 * @author prabinpaudel
 *
 */
public interface PersonRepository extends JpaRepository<PersonForm, String> {
	
}
