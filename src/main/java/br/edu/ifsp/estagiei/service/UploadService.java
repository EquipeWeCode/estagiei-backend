package br.edu.ifsp.estagiei.service;

import br.edu.ifsp.estagiei.config.CloudinaryConfig;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@AllArgsConstructor
public class UploadService {
    private final CloudinaryConfig cloudinaryConfig;

    @SuppressWarnings("rawtypes")
	public String uploadFile(MultipartFile file, String fileName) throws FileUploadException {
        try {
            Map uploadResult = cloudinaryConfig
                .getCloudinary()
                .uploader()
                .upload(file.getBytes(), ObjectUtils.asMap("public_id", fileName));
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new FileUploadException("Error sending file: " + file.getName());
        }
    }
}
