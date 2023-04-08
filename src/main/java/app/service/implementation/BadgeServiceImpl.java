package app.service.implementation;

import app.model.Badge;
import app.repository.BadgeRepository;
import app.service.BadgeService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class BadgeServiceImpl implements BadgeService {
    private BadgeRepository badgeRepository = RepositorySinglePointAccess.getBadgeRepository();
    @Override
    public Badge save(Badge badge) {
        return badgeRepository.save(badge);
    }

    @Override
    public Badge update(Badge badge) {
        return badgeRepository.update(badge);
    }

    @Override
    public List<Badge> findAll() {
        return badgeRepository.findAll();
    }

    @Override
    public Badge findById(Integer id) {
        return badgeRepository.findById(id);
    }

    @Override
    public boolean delete(Badge badge) {
        return badgeRepository.delete(badge);
    }
}
