package los.campesinos.resttaco.web;


import los.campesinos.resttaco.data.OrderRepository;
import los.campesinos.resttaco.data.TacoRepository;
import los.campesinos.resttaco.data.UserRepository;
import los.campesinos.resttaco.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping
    List<Order> all(){
        List<Order> all = new ArrayList<>();
        orderRepo.findAll().forEach((x) -> all.add(x));
        return all;
    }

    @GetMapping("/{id}")
    Order one(@PathVariable Long id){
        return orderRepo.findById(id)
                .orElseThrow(() -> null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order newOrder(@RequestBody Order newOrder){
        return newOrder;
    }


}
