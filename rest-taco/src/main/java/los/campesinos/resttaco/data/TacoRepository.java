package los.campesinos.resttaco.data;

import los.campesinos.resttaco.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="tacos", path="tacos")
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
