package app.service;

import app.model.Person;
import app.model.Ranking;

import java.util.List;

public interface RankingService {
    Ranking findById(Integer id);
    void addPersonRanking(Ranking r, Person p);
    Ranking save(Ranking r);
    Ranking update(Ranking r);
    Boolean delete(Ranking r);
}
