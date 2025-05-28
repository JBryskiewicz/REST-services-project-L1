package pl.aeh.rest_services_project_l1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.aeh.rest_services_project_l1.domain.user_views.UserView;

@Repository
public interface UserViewRepository extends JpaRepository<UserView, Integer> {
    UserView getUserViewById(Long id);
    Long deleteUserViewById(Long id);
}
