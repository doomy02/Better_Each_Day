package app.service;

import app.model.Quest;

import java.util.List;

public interface QuestService {
    Quest save(Quest quest);

    Quest update(Quest quest);

    List<Quest> findAll();

    Quest findById(Integer id);

    boolean delete(Quest quest);
}
