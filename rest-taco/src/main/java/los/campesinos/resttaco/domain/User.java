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
    //TODO: return the following fields: fullname, street, city, state, zip, & phoneNumber
    private String roles;
    private Boolean active;


    public User(String username, String password, String roles, Boolean active) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

    public Boolean isActive() {
        return active;
    }
}