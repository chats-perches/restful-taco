package los.campesinos.resttaco.data;

import los.campesinos.resttaco.model.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
