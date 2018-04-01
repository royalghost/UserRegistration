/**
 * 
 */
package com.user.registration.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.user.registration.model.PersonForm;
import com.user.registration.service.PersonService;

/**
 * @author prabinpaudel
 *
 */

@Controller
public class PersonRegistration implements WebMvcConfigurer {
	
	@Autowired
	private PersonService personService;
	
	private static final String USER_REGISTRATION_FORM = "personForm";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }
	
	@GetMapping("/")
	public String showForm(PersonForm personForm) {
		return USER_REGISTRATION_FORM;
	}
	
	@PostMapping("/")
	public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
		System.out.println(personForm.toString());
		if (bindingResult.hasErrors()) {
			return USER_REGISTRATION_FORM;
		}
		//Save the Username and Password in HSQLDB
		personService.add(personForm);
		
		return "redirect:/results";
	}

}
