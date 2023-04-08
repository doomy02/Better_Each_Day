package app.service.implementation;

import app.configuration.HibernateConfiguration;
import app.model.Journey;
import app.model.Quest;
import app.repository.JourneyRepository;
import app.repository.QuestRepository;
import app.service.JourneyService;
import app.single_point_access.RepositorySinglePointAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class JourneyServiceImpl implements JourneyService {
    private final JourneyRepository journeyRepository = RepositorySinglePointAccess.getJourneyRepository();
    private final QuestRepository questRepository = RepositorySinglePointAccess.getQuestRepository();
    @Override
    public Journey findById(Integer id) {
        return journeyRepository.findById(id);
    }

    @Override
    public void addQuestJourney(Journey j, Quest q) {
        if (j.getQuests() == null) {
            j.setQuests(new ArrayList<>());
        }
        if (q.getCategory() == null || questRepository.findById(q.getId()) == null) {
            q = questRepository.save(q);
        }

        j.getQuests().add(q);
        Integer aux = j.getNoQuests() + 1;
        j.setNoQuests(aux);

        journeyRepository.update(j);
    }

    @Override
    public Journey save(Journey j) {
        return journeyRepository.save(j);
    }
}
