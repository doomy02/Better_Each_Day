package app.controller.rest;

import app.model.Person;
import app.model.Quest;
import app.service.QuestService;
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

// every request to http://localhost:8080/quest will enter on this controller
@RequestMapping
public class QuestController {
    private QuestService questService = ServiceSinglePointAccess.getQuestService();

    // Map http://localhost:8080/quest/all
    // Get - to take data
    @GetMapping("/all")
    public ResponseEntity<List<Quest>> getAllQuests() {
        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(questService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    @GetMapping("/id/{id}")
    public ResponseEntity<Quest> getQuestById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(questService.findById(id));
    }

    // RequestBody - is the data sent to server through request
    // Post - create data
    @PostMapping("/create")
    public ResponseEntity<Quest> createQuest(@RequestBody Quest quest) {
        return ResponseEntity.status(HttpStatus.OK).body(questService.save(quest));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateAvailability/{id}/{name}")
    public ResponseEntity<Quest> updateQuestAvailability(@PathVariable Integer id, @PathVariable Boolean availability) {
        Quest quest = questService.findById(id);
        quest.setAvailability(availability);
        Quest questUpdated = questService.update(quest);
        return ResponseEntity.status(HttpStatus.OK).body(questUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<Quest> update(@RequestBody Quest quest) {
        Quest questFromDb = questService.findById(quest.getId());
        questFromDb.setName(quest.getName());
        questFromDb.setAvailability(quest.getAvailability());
        questFromDb.setTokens(quest.getTokens());
        questFromDb.setOwner(quest.getOwner());
        questFromDb.setDescription(quest.getDescription());
        Quest questUpdated = questService.update(quest);
        return ResponseEntity.status(HttpStatus.OK).body(questUpdated);
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateTokens/{id}/{tokens}")
    public ResponseEntity<Quest> updateQuestTokens(@PathVariable Integer id, @PathVariable Integer tokens) {
        Quest quest = questService.findById(id);
        quest.setTokens(tokens);
        Quest questUpdated = questService.update(quest);
        return ResponseEntity.status(HttpStatus.OK).body(questUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Quest quest = questService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(questService.delete(quest));
    }

}
