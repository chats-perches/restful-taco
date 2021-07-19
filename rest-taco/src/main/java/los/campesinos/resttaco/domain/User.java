package los.campesinos.resttaco.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    private String roles;
    private Boolean active;



    public User(String username, String password, String roles, Boolean active) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

    public User(String username, String password, String fullName, String street, String city,
                String state, String zip, String phoneNumber, String roles, Boolean active) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.active = active;
    }

    public Boolean isActive() {
        return active;
    }
}