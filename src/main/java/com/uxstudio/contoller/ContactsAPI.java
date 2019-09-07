package com.uxstudio.contoller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uxstudio.entity.Contact;
import com.uxstudio.service.ContactService;

@RestController
@RequestMapping("/api/contacts")
public class ContactsAPI {
	
	@Autowired
	private ContactService service;
	
	@GetMapping
	public ResponseEntity<Iterable<Contact>> getContacts(){
		return ResponseEntity.ok(service.getContacts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contact> getContact(@PathVariable Integer id){
		Optional<Contact> contact = service.getContact(id);
		if(contact.isPresent()) {
			return ResponseEntity.ok(contact.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
		Contact c = service.addContact(contact);
		return ResponseEntity.ok(c);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable Integer id, @RequestBody Contact contact){
		Optional<Contact> c = service.getContact(id);
		if(c.isPresent()) {
			contact.setId(id);
			return ResponseEntity.ok(service.addContact(contact));
		} else {
            return ResponseEntity.notFound().build();
        }
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Contact> deleteContact(@PathVariable Integer id){
		Optional<Contact> c = service.getContact(id);
		if(c.isPresent()) {
			service.deleteContact(id);
			return ResponseEntity.ok().build();
		} else {
            return ResponseEntity.notFound().build();
        }
	}

}
