package untils;

import jakarta.servlet.http.Part;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@UtilityClass
public class ImageUploadHelper {
    private static final String IMAGE_UPLOAD_PATH = "images/%s.%s";
    private static final String ABSOLUTE_PATH = "C:/Users/Александра/Desktop/courseWork6/demo/src/main/resources/";

    public static String getPath(String fileName, String type) {
        return String.format(IMAGE_UPLOAD_PATH, fileName, type);
    }

    public static String generateImageName() {
        return UUID.randomUUID().toString();
    }

    public static String generateImageType(MultipartFile image) {
        return image.getContentType().split("/")[1];
    }

    public static String uploadImage(MultipartFile image) {
        try {
            String imagePath = getPath(generateImageName(), generateImageType(image));
            File file = new File(ABSOLUTE_PATH + imagePath);
            image.transferTo(file);
            return imagePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}