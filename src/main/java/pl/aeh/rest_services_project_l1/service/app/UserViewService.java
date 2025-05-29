package pl.aeh.rest_services_project_l1.service.app;

import org.springframework.stereotype.Service;
import pl.aeh.rest_services_project_l1.domain.user_views.UserView;
import pl.aeh.rest_services_project_l1.repository.UserViewRepository;

import java.util.List;

@Service
public class UserViewService {

    private final UserViewRepository userViewRepository;

    public UserViewService(UserViewRepository userViewRepository) {
        this.userViewRepository = userViewRepository;
    }

    public UserView saveUserView(UserView userView) {
        return this.userViewRepository.save(userView);
    }

    public List<UserView> getAllUserViews(Long userId) {
        return this.userViewRepository.findByUserId(userId);
    }

    public UserView getUserView(long id) {
        return this.userViewRepository.getUserViewById(id);
    }

    public void deleteUserView(long id) {
        this.userViewRepository.deleteUserViewById(id);
    }
}
