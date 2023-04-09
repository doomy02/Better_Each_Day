package app.controller.rest;

import app.model.Journey;
import app.service.JourneyService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController

// every request to http://localhost:8080/journey will enter on this controller
@RequestMapping("/journey")
public class JourneyController {
    private JourneyService journeyService = ServiceSinglePointAccess.getJourneyService();
    // {id} - is a value sent by url and is named path variable
    @GetMapping("/id/{id}")
    public ResponseEntity<Journey> getJourneyById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(journeyService.findById(id));
    }

    // RequestBody - is the data sent to server through request
    // Post - create data
    @PostMapping("/create")
    public ResponseEntity<Journey> createJourney(@RequestBody Journey journey) {
        return ResponseEntity.status(HttpStatus.OK).body(journeyService.save(journey));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateName/{id}/{noQuests}")
    public ResponseEntity<Journey> updatePersonName(@PathVariable Integer id, @PathVariable Integer noQuests) {
        Journey journey = journeyService.findById(id);
        journey.setNoQuests(noQuests);
        Journey journeyUpdated = journeyService.update(journey);
        return ResponseEntity.status(HttpStatus.OK).body(journeyUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<Journey> update(@RequestBody Journey journey) {
        Journey journeyFromDB = journeyService.findById(journey.getId());
        journeyFromDB.setNoQuests(journey.getNoQuests());
        journeyFromDB.setQuests(journey.getQuests());
        Journey journeyUpdated = journeyService.update(journey);
        return ResponseEntity.status(HttpStatus.OK).body(journeyUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Journey journey = journeyService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(journeyService.delete(journey));
    }

}
