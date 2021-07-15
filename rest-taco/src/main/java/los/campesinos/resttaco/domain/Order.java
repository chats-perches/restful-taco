package los.campesinos.resttaco.domain;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
//This specifies that the Order entities should persist to the Taco_Order table
// we could have included it on the other classes but the JPA defaults to Table name = class name
// Order is a reserved word in SQL and would have created confilcts as a table name, so we specify a different name
@Table(name = "Taco_Order")
@RestResource(rel="order", path="order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date placedAt;

    private String orderName;

    private String street;

    private String city;

    private String state;

    private String zip;

    private String ccNumber;

    private String ccExpiration;

    private String ccCVV;

    @ManyToOne
    private User user;

    // note: ingredient -> taco & taco -> order

    public Order(){}

    public Order(String name, String street, String city, String state, String zip,
                 String ccNumber, String ccExpiration, String ccCVV) {
        this.orderName = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.ccCVV = ccCVV;
    }


    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }

}
