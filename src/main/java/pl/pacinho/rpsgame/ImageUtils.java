package pl.pacinho.rpsgame;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ImageUtils {


    public static String getBase64Image(String img) {
        if (img == null || img.isEmpty()) return null;
        FileInputStream fileInputStreamReader = null;
        try {
            File file = ResourceUtils.getFile("classpath:img/" + img + ".png");
            fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            return new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStreamReader != null)
                    fileInputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
