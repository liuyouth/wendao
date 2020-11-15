package pub.moi.wendao.java.service.impl;

import pub.moi.wendao.java.service.StaticService;
import pub.moi.wendao.java.utils.FileUtil;
import org.springframework.web.multipart.MultipartFile;

public class StaticServiceImpl implements StaticService {
    @Override
    public String saveFile(MultipartFile file) {
        return FileUtil.saveFile(file);
    }

    @Override
    public String saveImage(MultipartFile file) {
        return FileUtil.saveImg(file);
    }
}
