package hr.alphacloud.server.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Account {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    // make enum out of this save it as ordinal
    @Column(name = "active_locale")
    private String activeLocale;

    private String address;
    
}
