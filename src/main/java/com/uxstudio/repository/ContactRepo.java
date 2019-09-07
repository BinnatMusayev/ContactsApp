package com.uxstudio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uxstudio.entity.Contact;


@Repository
public interface ContactRepo extends CrudRepository<Contact, Integer>{

}
