package com.uxstudio.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uxstudio.entity.Contact;
import com.uxstudio.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	@Autowired
	ContactService service;
	
	@GetMapping("")
	public String getContacts(Model model) {
		Iterable<Contact> contacts = service.getContacts();
		model.addAttribute("contacts", contacts);
		
		return "contacts_list.html";
	}
	
	@GetMapping("/new")
	public String addContact(Model model) {
		model.addAttribute("contact", new Contact());
		return "create";
	}
	
	@PostMapping("/new")
	public String addContactToDB(@ModelAttribute Contact contact) {
		service.addContact(contact);
        return "redirect:/contacts";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteContact(@PathVariable Integer id) {
		service.deleteContact(id);
		return "redirect:/contacts";
		
	}
	
	@GetMapping("/edit/{id}")
	public String editContact(@PathVariable Integer id, Model model) {
		model.addAttribute("contact", service.getContact(id).get() );
		return "edit";
	}
	
	@PostMapping("/edit/{id}")
	public String editContactInDB(@PathVariable Integer id, @ModelAttribute Contact contact) {
		service.deleteContact(id);
		service.addContact(contact);
		return "redirect:/contacts";
	}

}
