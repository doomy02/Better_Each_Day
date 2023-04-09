package app.service;

import app.model.Journey;
import app.model.Quest;

public interface JourneyService {
    Journey findById(Integer id);
    void addQuestJourney(Journey j, Quest q);
    Journey save(Journey j);
    Journey update(Journey j);
}
