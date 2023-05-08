package untils;


import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@UtilityClass
public class FileUploadHelper {


    private static final String CONTENT_UPLOAD_PATH = "content/%s.%s";
    private static final String ABSOLUTE_PATH = "C:/Users/Александра/Desktop/courseWork6/demo/src/main/resources/";


    public static String getPathToFile(String file_name) {
        return ABSOLUTE_PATH + "content/" + file_name;
    }

    public static String getPath(String fileName, String type) {
        return String.format(CONTENT_UPLOAD_PATH, fileName, type);
    }

    public static String generateFileName() {
        return UUID.randomUUID().toString();
    }

    public static String generateFileType(MultipartFile image) {
        return image.getContentType().split("/")[1];
    }

    public static String uploadImage(MultipartFile image) {
        try {
            String imagePath = getPath(generateFileName(), generateFileType(image));
            File file = new File(ABSOLUTE_PATH + imagePath);
            image.transferTo(file);
            return imagePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
