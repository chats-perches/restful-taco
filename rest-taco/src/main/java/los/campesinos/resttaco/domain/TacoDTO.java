package los.campesinos.resttaco.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TacoDTO{

    String name;
    List<String> ingredients = new ArrayList<String>();

}
