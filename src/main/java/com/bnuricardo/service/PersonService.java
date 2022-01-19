package com.bnuricardo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnuricardo.converter.DozerConverter;
import com.bnuricardo.data.model.Person;
import com.bnuricardo.data.vo.PersonVO;
import com.bnuricardo.exception.ResourceNotFoundException;
import com.bnuricardo.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public PersonVO create(PersonVO person) {
	var entity = DozerConverter.parseObject(person, Person.class);
	return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
	var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Registro nao encontrado."));
	entity.setFirstName(person.getFirstName());
	entity.setLastName(person.getLastName());
	entity.setAddress(person.getAddress());
	entity.setGender(person.getGender());
	return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
	var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro nao encontrado."));
	repository.delete(entity);
    }

    public PersonVO findById(Long id) {
	var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro nao encontrado."));
	return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
	return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
    }

}
