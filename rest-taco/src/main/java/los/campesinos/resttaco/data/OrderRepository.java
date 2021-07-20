package los.campesinos.resttaco.data;


import los.campesinos.resttaco.model.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
