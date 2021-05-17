package hr.alphacloud.server.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "company", schema = "public")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "full_name")
    private String fullName;
    private String email;

    private String oib;

    private String mbs;

    private String address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<User> accounts = new ArrayList<>();
    
}
