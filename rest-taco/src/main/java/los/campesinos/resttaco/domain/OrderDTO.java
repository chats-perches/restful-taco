package los.campesinos.resttaco.domain;

import lombok.Data;
import los.campesinos.resttaco.data.UserRepository;

import javax.persistence.ManyToOne;
import java.util.Optional;

@Data
public class OrderDTO {

    //only for updates
    private Long id;

    private String orderName;

    private String street;

    private String city;

    private String state;

    private String zip;

    private String ccNumber;

    private String ccExpiration;

    private String ccCVV;

    private Long userId;

    public User getUserFromDB(UserRepository repo){
        Optional<User> user = repo.findById(userId);
        return user.isPresent() ?
                user.get() : null;
    }

    public void updateOrder(Order order){
        if(orderName != null)
            order.setOrderName(orderName);
        if(street != null)
            order.setStreet(street);
        if(city != null)
            order.setCity(city);
        if(state != null)
            order.setState(state);
        if(ccNumber != null)
            order.setCcNumber(ccNumber);
        if(ccExpiration != null)
            order.setCcExpiration(ccExpiration);
        if(ccCVV != null)
            order.setCcCVV(ccCVV);
    }
}
