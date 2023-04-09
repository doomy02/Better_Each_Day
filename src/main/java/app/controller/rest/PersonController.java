package app.controller.rest;

import app.model.Person;
import app.service.PersonService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController

// every request to http://localhost:8080/person will enter on this controller
@RequestMapping("/person")
public class PersonController {
    private PersonService personService = ServiceSinglePointAccess.getPersonService();

    // Map http://localhost:8080/person/all
    // Get - to take data
    @GetMapping("/all")
    public ResponseEntity<List<Person>>getAllPersons(){
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    @GetMapping("/id/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Person> update(@RequestBody Person person) {
        Person personFromDb = personService.findById(person.getId());
        personFromDb.setName(person.getName());
        personFromDb.setPassword(person.getPassword());
        personFromDb.setEmail(person.getEmail());
        personFromDb.setTokens(person.getTokens());
        personFromDb.setNoOfQuest(person.getNoOfQuest());
        Person personUpdated = personService.update(person);
        return ResponseEntity.status(HttpStatus.OK).body(personUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Person person = personService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(personService.delete(person));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updatePassword/{id}/{password}")
    public ResponseEntity<Person> updatePersonPassword(@PathVariable Integer id, @PathVariable String password) {
        Person person = personService.findById(id);
        person.setPassword(password);
        Person personUpdated = personService.update(person);
        return ResponseEntity.status(HttpStatus.OK).body(personUpdated);
    }

    @PutMapping("/updateEmail/{id}/{email}")
    public ResponseEntity<Person> updatePersonEmail(@PathVariable Integer id, @PathVariable String email) {
        Person person = personService.findById(id);
        person.setEmail(email);
        Person personUpdated = personService.update(person);
        return ResponseEntity.status(HttpStatus.OK).body(personUpdated);
    }

    // RequestBody - is the data sent to server through request
    // Post - create data
    @PostMapping("/create")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.save(person));
    }


}