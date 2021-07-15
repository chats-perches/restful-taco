package los.campesinos.resttaco.web;


import los.campesinos.resttaco.data.OrderRepository;
import los.campesinos.resttaco.data.TacoRepository;
import los.campesinos.resttaco.data.UserRepository;
import los.campesinos.resttaco.domain.Order;
import los.campesinos.resttaco.domain.OrderDTO;
import los.campesinos.resttaco.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/order" , produces="application/json")
@CrossOrigin(origins="*")
public class OrderController {
    private TacoRepository tacoRepo;
    private OrderRepository orderRepo;
    private UserRepository userRepo;

    @Autowired
    public OrderController(TacoRepository tacoRepo,
                           OrderRepository orderRepo, UserRepository userRepository)
    {
        this.tacoRepo = tacoRepo;
        this.orderRepo = orderRepo;
        this.userRepo = userRepository;
    }
    @GetMapping(produces="application/json")
    Iterable<Order> all(){
        return orderRepo.findAll();
    }

    @GetMapping("/{id}")
    Order one(@PathVariable Long id){
        return orderRepo.findById(id)
                .orElse(new Order());
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order newOrder(@RequestBody OrderDTO o){

        List<Taco> tacos = new ArrayList<>();
                o.getTacoIds().forEach(
                x -> tacos.add(tacoRepo.findById(x).orElse(null)));
        Order order = new Order(o.getOrderName(),o.getStreet(),o.getCity(),
                o.getState(),o.getZip(),o.getCcNumber(),o.getCcExpiration(),o.getCcCVV(), tacos);

        //TODO: fetch user from the session . . .
        order.setUser(o.getUserFromDB(userRepo));
        return orderRepo.save(order);
    }

    @PutMapping(path="/{orderId}", consumes="application/json")
    public Order putOrder(@RequestBody OrderDTO o) {
        Optional<Order> order = orderRepo.findById(o.getId());
        if(order.isPresent()) {
            Order update = order.get();
            o.updateOrder(update, tacoRepo);
            return orderRepo.save(update);
        } else
            return null;
    }


    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }



}
