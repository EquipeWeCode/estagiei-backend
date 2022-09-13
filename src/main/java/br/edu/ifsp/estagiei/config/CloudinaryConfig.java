package br.edu.ifsp.estagiei.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@Configuration
public class CloudinaryConfig {
    @Value("${app.cloudinary.cloud-name}")
    private String cloud_name;
    @Value("${app.cloudinary.api-key}")
    private String api_key;
    @Value("${app.cloudinary.api-secret}")
    private String api_secret;

    @SuppressWarnings("unchecked")
	public Cloudinary getCloudinary() {
        Map<Object, Object> config = new HashMap<Object, Object>(ObjectUtils.asMap(
            "cloud_name", cloud_name,
            "api_key", api_key,
            "api_secret", api_secret
        ));
        return new Cloudinary(config);
    }
}
