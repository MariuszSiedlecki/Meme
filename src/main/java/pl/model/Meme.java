package pl.model;

import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StreamUtils;
import pl.controller.security.UserDto;

import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Meme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    @NonNull
    private UserDto userDtoOwner;
    @NonNull
    private String name;
    @URL
    @NonNull
    @ToString.Exclude
    private String imageUrl;
    @NonNull
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    @NonNull
    private boolean favorite;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateDate = LocalDateTime.now();
    @Column(columnDefinition = "LONGTEXT")
    private String image;

    public void setImage(InputStream inputStream) throws IOException {
        byte[] imageBytes = StreamUtils.copyToByteArray(inputStream);
        String imageStr = Base64.encodeBase64String(imageBytes);
        inputStream.close();
        this.image = imageStr;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
