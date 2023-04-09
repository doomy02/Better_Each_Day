package app.service.implementation;

import app.model.Person;
import app.model.Ranking;
import app.repository.PersonRepository;
import app.repository.RankingRepository;
import app.service.RankingService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.ArrayList;
import java.util.List;

public class RankingServiceImpl implements RankingService {
    private final RankingRepository rankingRepository = RepositorySinglePointAccess.getRankingRepository();
    private final PersonRepository personRepository = RepositorySinglePointAccess.getPersonRepository();

    @Override
    public Ranking findById(Integer id) {return rankingRepository.findById(id);}

    @Override
    public void addPersonRanking(Ranking r, Person p) {
        if (r.getPersons() == null) {
            r.setPersons(new ArrayList<>());
        }
        if (p.getName() == null || personRepository.findById(p.getId()) == null) {
            r = rankingRepository.save(r);
        }

        r.getPersons().add(p);
        Integer aux = r.getNoOfPersons() + 1;
        r.setNoOfPersons(aux);

        rankingRepository.update(r);
    }

    @Override
    public Ranking save(Ranking r) {return rankingRepository.save(r);}

    @Override
    public Ranking update(Ranking r) {return rankingRepository.update(r);}

    @Override
    public Boolean delete(Ranking r) {return rankingRepository.delete(r);}
}
