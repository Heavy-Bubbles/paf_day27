package sg.edu.nus.iss.paf_day27.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TvShow {

   private Integer id;
   private String name;
   private String summary;
   private String image;
   
}
