package pub.moi.wendao.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface StaticService {
    String saveFile(MultipartFile file);
    String saveImage(MultipartFile file);
}
