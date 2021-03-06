package pub.moi.wendao.java.utils;

import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import pub.moi.wendao.java.config.Constants;

import java.io.File;
import java.util.UUID;

import static org.hibernate.internal.util.StringHelper.isNotEmpty;

public class FileUtil {


    public static final org.slf4j.Logger inLogger = LoggerFactory.getLogger("in");
    public static final Gson gson = new Gson();
    /**
     * 图片存储 完整路径（{user.home}/img/coldStone/XXX.jpg）
     *
     * @param file
     * @return 返回相对路径
     */
    public static String saveImg(MultipartFile file) {
        //获取文件上传的根目录 C:\Users\wanghao/upload/img
        String path = Constants.UPLOAD_PATH + Constants.IMG_FILE_NAME;

        //拿到文件的后缀名和UUID进行拼接形成新的文件名
        //4ca64e85b1544c96b4a6154bb521476f.jpg
        String saveName = getuuid() + "." + getFileSuffix(file.getOriginalFilename());

        System.out.println(" --- 图片保存路径：{" + path + "}, 图片保存名称：{" + saveName + "} --- ");

        // 保存
        try {
            // 保存文件图片
            File targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(new File(path + "/" + saveName));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--- 图片保存异常：{} ---" + e.getMessage());
            return null;
        }
        String filePath = Constants.VIRTUAL_IMG_PATH;
        ;
        //返回相对路径  img/virtual/4ca64e85b1544c96b4a6154bb521476f.jpg
        return filePath + "/" + saveName;
    }

    /**
     * 文件存储 完整路径（{user.home}/img/coldStone/XXX.jpg）
     *
     * @param file
     * @return 返回相对路径
     */
    public static String saveFile(MultipartFile file) {
        //获取文件上传的根目录 C:\Users\wanghao/upload/img
        String path = Constants.UPLOAD_PATH + Constants.IMG_FILE_NAME;

        //拿到文件的后缀名和UUID进行拼接形成新的文件名
        //4ca64e85b1544c96b4a6154bb521476f.jpg
        String saveName = getuuid() + "." + getFileSuffix(file.getOriginalFilename());

        System.out.println(" --- 文件保存路径：{" + path + "}, 文件保存名称：{" + saveName + "} --- ");

        // 保存
        try {
            // 保存文件图片
            File targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(new File(path + "/" + saveName));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--- 文件保存异常：{} ---" + e.getMessage());
            return null;
        }
        String filePath = Constants.VIRTUAL_IMG_PATH;
        ;
        //返回相对路径  img/virtual/4ca64e85b1544c96b4a6154bb521476f.jpg
        return filePath + "/" + saveName;
    }

    /**
     * 返回截取的文件后缀
     *
     * @param path
     * @return
     */
    private static String getFileSuffix(String path) {
        return getFileSuffix(path, "2");
    }

    /**
     * 获取文件名称或后缀(最后一个"."之后内容)
     *
     * @param path
     * @param type 1名称 2后缀
     * @return
     */
    private static String getFileSuffix(String path, String type) {
        if (isNotEmpty(path) && path.indexOf(".") > 0) {
            // 名称
            String name = path.substring(0, path.lastIndexOf("."));

            // 后缀
            String suffix = path.substring(path.lastIndexOf(".") + 1);

            return "1".equals(type) ? name : suffix;
        } else {
            return null;
        }
    }

    public static String getuuid() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }



}
