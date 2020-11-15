package pub.moi.wendao.java.config;

/**
 * 常量
 * @author ScienJus
 * @date 2015/7/31.
 */
public class Constants {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
    public static final String CURRENT_USER = "CURRENT_USER";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";


    /** 文件上传/图片   根目录 */
    public static final String UPLOAD_PATH = System.getProperty("user.home") + "/upload/";

    /** 图片目录 */
    public static final String IMG_FILE_NAME = "image";

    /** 图片相对路径 */
    public static final String VIRTUAL_IMG_PATH = "image";

}
