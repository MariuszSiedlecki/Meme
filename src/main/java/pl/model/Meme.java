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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateDate = LocalDateTime.now();
    //private MultipartFile file;
    private String image;

    public Meme(String barbados, String s, String s1, Boolean aFalse) {
    }

    public void setImage(InputStream inputStream) throws IOException {
        byte[] imageBytes = StreamUtils.copyToByteArray(inputStream);
        String imageStr = Base64.encodeBase64String(imageBytes);
        inputStream.close();
        this.image = imageStr;
    }

    public void setImage(String image){
        this.image = image;
    }
}
