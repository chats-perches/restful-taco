package los.campesinos.resttaco.domain;

import lombok.Data;
import los.campesinos.resttaco.data.TacoRepository;
import los.campesinos.resttaco.data.UserRepository;
import los.campesinos.resttaco.model.Order;
import los.campesinos.resttaco.model.Taco;
import los.campesinos.resttaco.model.User;

import java.util.ArrayList;
import java.util.List;
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

    private List<Long> tacoIds = new ArrayList<>();

    public User getUserFromDB(UserRepository repo){
        Optional<User> user = repo.findById(userId);
        return user.isPresent() ?
                user.get() : null;
    }

    public void updateOrder(Order order, TacoRepository tacoRepo){
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
        if(tacoIds != null) {
            List<Taco> tacos = new ArrayList<>();
            tacoIds.forEach(x -> tacos.add(tacoRepo.findById(x).orElse(null)));
            order.setTacos(tacos);
        }
    }
}
