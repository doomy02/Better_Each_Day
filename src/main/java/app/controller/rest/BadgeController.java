package app.controller.rest;

import app.model.Badge;
import app.service.BadgeService;
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

@RestController

// every request to http://localhost:8080/badge will enter on this controller
@RequestMapping("/badge")
public class BadgeController {
    private BadgeService badgeService = ServiceSinglePointAccess.getBadgeService();

    // {id} - is a value sent by url and is named path variable
    @GetMapping("/id/{id}")
    public ResponseEntity<Badge> getJourneyById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(badgeService.findById(id));
    }

    // RequestBody - is the data sent to server through request
    // Post - create data
    @PostMapping("/create")
    public ResponseEntity<Badge> createJourney(@RequestBody Badge badge) {
        return ResponseEntity.status(HttpStatus.OK).body(badgeService.save(badge));
    }

    @PutMapping("/update")
    public ResponseEntity<Badge> update(@RequestBody Badge badge) {
        Badge journeyFromDB = badgeService.findById(badge.getId());
        journeyFromDB.setName(badge.getName());
        Badge journeyUpdated = badgeService.update(badge);
        return ResponseEntity.status(HttpStatus.OK).body(journeyUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Badge badge = badgeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(badgeService.delete(badge));
    }
}
