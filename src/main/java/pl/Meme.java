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

    public Meme(String name, String imageUrl) {
    }


    public void setImageUrl(String imageUrl) {
        if (imageUrl.startsWith("https://*") ||
                imageUrl.startsWith("http://*") ||
                imageUrl.startsWith("www.*")) {
            this.imageUrl = imageUrl;
        } else {
            System.out.println("URL is error");
        }
    }
}
