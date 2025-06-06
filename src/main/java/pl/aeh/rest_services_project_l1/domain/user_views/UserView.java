package pl.aeh.rest_services_project_l1.domain.user_views;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String viewName;

    // Stringified List<RegionInfoResponse> to omit unecessery relations in db
    @Lob
    @Column(length = 10000)
    private String regionInfo;
}
