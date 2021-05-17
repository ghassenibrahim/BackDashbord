package hr.alphacloud.server.model.entity;

import hr.alphacloud.server.model.enums.UserRole;
import hr.alphacloud.server.model.enums.enum_converter.UserRoleConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_main", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "jwt_token")
    private String jwtToken;

    @Column(name = "jwt_token_reset_timer")
    private LocalDateTime jwtTokenResetTimer;

    @Column(name = "refresh_token")
    private String refreshToken;

    private String phone;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "position")
    private String position;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String address;

    @Column(name = "role", nullable = false)
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "designated_user")
    private Boolean designatedUser;

    // make enum out of this save it as ordinal
    @Column(name = "active_locale")
    private String activeLocale;

    @Column(name = "selected_company_id")
    private Long selectedCompanyId;

    @Column(name = "selected_company_name")
    private String selectedCompanyName;

	
}
