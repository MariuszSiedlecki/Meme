package pl.model;

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

    public void setImage(InputStream inputStream) throws IOException {
        byte[] imageBytes = StreamUtils.copyToByteArray(inputStream);
        String imageStr = Base64.encodeBase64String(imageBytes);
        inputStream.close();
        this.image = imageStr;
    }
//todo
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
