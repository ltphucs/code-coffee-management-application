package com.nobita.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface UploadService {

    Map upload(MultipartFile multipartFile) throws IOException;

    Map destroy(String publicId) throws IOException;
}
