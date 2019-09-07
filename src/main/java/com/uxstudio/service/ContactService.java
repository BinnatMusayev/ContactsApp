package com.uxstudio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uxstudio.entity.Contact;
import com.uxstudio.repository.ContactRepo;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepo repo;
	
	public Iterable<Contact> getContacts(){
		return repo.findAll();
	}
	
	public Optional<Contact> getContact(Integer id) {
		return repo.findById(id);
	}
	
	public Contact addContact(Contact c) {
		return repo.save(c);
	}
	
	public void deleteContact(Integer id) {
		repo.deleteById(id);
	}
	
}
