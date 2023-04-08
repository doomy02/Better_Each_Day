package app.service;

import app.model.Badge;
import java.util.List;

public interface BadgeService {
    Badge save(Badge badge);

    Badge update(Badge badge);

    List<Badge> findAll();

    Badge findById(Integer id);

    boolean delete(Badge badge);
}
