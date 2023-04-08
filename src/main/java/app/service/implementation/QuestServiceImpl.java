package app.service.implementation;

import app.model.Quest;
import app.repository.QuestRepository;
import app.repository.implemetation.QuestRepositoryImpl;
import app.service.QuestService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class QuestServiceImpl implements QuestService {

    private final QuestRepository questRepository = RepositorySinglePointAccess.getQuestRepository();
    @Override
    public Quest save(Quest quest) {return questRepository.save(quest);}

    @Override
    public Quest update(Quest quest) {
        return questRepository.update(quest);
    }

    @Override
    public List<Quest> findAll() {
        return questRepository.findAll();
    }

    @Override
    public Quest findById(Integer id) {
        return questRepository.findById(id);
    }

    @Override
    public Quest findByName(String name) {
        return questRepository.findByName(name);
    }

    @Override
    public boolean delete(Quest quest) {
        return questRepository.delete(quest);
    }
}
