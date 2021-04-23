package com.nobita.demo.uploader;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.uploader")
@Data
class CloudinaryConfig {
    @Value("domia39")
    private String cloudName;
    @Value("application.uploader.api-key")
    private String apiKey;
    @Value("application.uploader.api-secret")
    private String apiSecret;
}
