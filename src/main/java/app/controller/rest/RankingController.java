package app.controller.rest;

import app.model.Ranking;
import app.service.RankingService;
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

// every request to http://localhost:8080/ranking will enter on this controller
@RequestMapping("/ranking")
public class RankingController {
    private RankingService rankingService = ServiceSinglePointAccess.getRankingService();

    // {id} - is a value sent by url and is named path variable
    @GetMapping("/id/{id}")
    public ResponseEntity<Ranking> getRankingById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(rankingService.findById(id));
    }

    // RequestBody - is the data sent to server through request
    // Post - create data
    @PostMapping("/create")
    public ResponseEntity<Ranking> createPerson(@RequestBody Ranking ranking) {
        return ResponseEntity.status(HttpStatus.OK).body(rankingService.save(ranking));
    }

    @PutMapping("/update")
    public ResponseEntity<Ranking> update(@RequestBody Ranking ranking) {
        Ranking rankingFromDb = rankingService.findById(ranking.getId());
        rankingFromDb.setPersons(ranking.getPersons());
        rankingFromDb.setNoOfPersons(ranking.getNoOfPersons());
        rankingFromDb.setId(ranking.getId());
        Ranking rankingUpdated = rankingService.update(ranking);
        return ResponseEntity.status(HttpStatus.OK).body(rankingUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Ranking ranking = rankingService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(rankingService.delete(ranking));
    }

}
