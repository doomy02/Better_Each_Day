package app.service;

import app.model.Person;
import app.model.Ranking;

public interface RankingService {
    Ranking findById(Integer id);
    void addPersonRanking(Ranking r, Person p);
    Ranking save(Ranking r);
    Ranking update(Ranking r);
}
