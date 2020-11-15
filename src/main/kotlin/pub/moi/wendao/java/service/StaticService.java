package pub.moi.wendao.java.service;

import org.springframework.web.multipart.MultipartFile;

public interface StaticService {
    String saveFile(MultipartFile file);
    String saveImage(MultipartFile file);
}
