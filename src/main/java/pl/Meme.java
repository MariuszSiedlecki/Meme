package pl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meme {

    private int id;
    private String name;
    @ToString.Exclude
    private String imageUrl;
    private String description;
    private boolean favorite;


}
