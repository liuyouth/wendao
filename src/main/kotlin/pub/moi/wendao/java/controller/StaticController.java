package pub.moi.wendao.java.controller;



import pub.moi.wendao.java.service.impl.StaticServiceImpl;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pub.moi.wendao.model.base.RBuilder;
import pub.moi.wendao.model.base.Result;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping("/static")
public class StaticController {
    private StaticServiceImpl staticService = new StaticServiceImpl();
//    @Autowired
//    @PersistenceContext
//    private FileBeanRepository fileBeanRepository;
    private static final String TAG = "StaticController";

    /**
     * 图片上传
     *
     * @return
     */
    @PostMapping(value = "/img/upload")
    public Result<String> uploadImg(@RequestParam("id") String id
            , @RequestParam MultipartFile multipartFile, HttpServletResponse response, HttpServletRequest request) {
        if (multipartFile.getSize()>1024*1024*10)
            return RBuilder.INSTANCE.failed("图片过大！请上传小于10MB的文件！");
        //图片上传调用工具类
        try {
            //保存图片
            String path = staticService.saveImage(multipartFile);
            // todo 应改为当前运行环境+端口
            return RBuilder.INSTANCE.seccess("http://192.168.1.128:8080/" + path);
        } catch (Exception e) {
            System.out.println(TAG + " error :" + e);
            return RBuilder.INSTANCE.failed("上传图片失败");
        }

    }

    /**
     * 文件上传
     *
     * @return
     */
    @PostMapping(value = "/upload")
    public Result<String> uploadFile(@RequestParam("name") String id, @RequestParam MultipartFile multipartFile) {
        System.out.println(" id" + id + multipartFile);

        //图片上传调用工具类
//        String[] paths = new String[9];
        ArrayList<String> paths = new ArrayList<>();
        try {
            //保存图片

            String path = staticService.saveFile(multipartFile);
//            fileBeanRepository.save( );

            return RBuilder.INSTANCE.seccess("http://192.168.1.114:6069/" + paths);
        } catch (Exception e) {
            System.out.println(TAG + " error :" + e);
            return RBuilder.INSTANCE.seccess("上传图片失败");
        }


    }


}
