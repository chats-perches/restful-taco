package los.campesinos.resttaco.web;


import los.campesinos.resttaco.data.OrderRepository;
import los.campesinos.resttaco.data.TacoRepository;
import los.campesinos.resttaco.data.UserRepository;
import los.campesinos.resttaco.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public Order newOrder(@RequestBody Order newOrder){

        return orderRepo.save(newOrder);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }



}
