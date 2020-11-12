package pub.moi.wendao.service.impl;

import pub.moi.wendao.service.StaticService;
import pub.moi.wendao.utils.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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
