package pub.moi.wendao.utils;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.auvgo.health.config.EnvironmentAddress;
//import org.apache.commons.codec.binary.Base64;


import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.ArrayList;

/**
 * 微信解密开放数据
 *
 * @author fenggaoyang
 * @date 2020/7/6 13:17
 */
public class WXDecrypt {

    /**
     * key
     */
    private static final String STEPINFO = "stepInfoList";
    private static final String APPID = "appid";
    private static final String WATERMARK = "watermark";
    private static final String PHONE = "phoneNumber";

    private static final String appId = "";

    /**
     * 解密数据获取微信步数
     *
     * @return
     * @throws Exception
     */
    public static dayStep decrypt(String encryptedData, String sessionKey, String iv) throws Exception {

        Gson g = new Gson();
        Long stepNum = new Long(0);
        AES aes = new AES();
        byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
        if (null != resultByte && resultByte.length > 0) {
            String result = new String(WxPKCS7Encoder.decode(resultByte));
//            System.out.println(result);
            WxStepList list = g.fromJson(result, WxStepList.class);
            if (list == null)
                return new dayStep(stepNum);
            if (!list.watermark.appid.equals(appId)) {
                throw new Exception("APPID不一致,获取到:" + list.watermark.appid + ",入参:" + appId);
            }
            if (!list.stepInfoList.isEmpty())
                return list.getStepInfoList().get(list.getStepInfoList().size() - 1);
//            JSONObject jsonObject = g.fromJson(result);
//            if (jsonObject.isEmpty()) {
//                return stepNum;
//            }
//            String appNo = jsonObject.getJSONObject(WATERMARK).getString(APPID);
//            if (!appNo.equals(appId)) {
//                throw new Exception("APPID不一致,获取到:" + appNo + ",入参:" + appId);
//            }
//            JSONArray jsonArray = jsonObject.getJSONArray(STEPINFO);
//            if (jsonArray.isEmpty()) {
//                return stepNum;
//            }
//            JSONObject object = jsonArray.getJSONObject(jsonArray.size()-1);
//            stepNum = (Integer) object.get("step");
        }

        return new dayStep(stepNum);
    }


    /**
     * 解密数据获取电话
     *
     * @return
     * @throws Exception
     */
    public static String decryptUserInfo(String appId, String encryptedData, String sessionKey, String iv) throws Exception {
        String phone = "";
        AES aes = new AES();
        byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
        if (null != resultByte && resultByte.length > 0) {
            String result = new String(WxPKCS7Encoder.decode(resultByte));

//            JSONObject jsonObject = JSON.parseObject(result);
//            if(jsonObject.isEmpty()){
//                return phone;
//            }
//            String appNo = jsonObject.getJSONObject(WATERMARK).getString(APPID);
//            if (!appNo.equals(appId)) {
//                throw new Exception("APPID不一致,获取到:" + appNo + ",入参:" + appId);
//            }
//            phone = jsonObject.getString(PHONE);

        }
        return phone;

    }


    public class WxStepList {
        ArrayList<dayStep> stepInfoList;
        dayStep watermark;

        public dayStep getWatermark() {
            return watermark;
        }

        public void setWatermark(dayStep watermark) {
            this.watermark = watermark;
        }

        public ArrayList<dayStep> getStepInfoList() {
            return stepInfoList;
        }

        public void setStepInfoList(ArrayList<dayStep> stepInfoList) {
            this.stepInfoList = stepInfoList;
        }
    }

    public static class dayStep {
        String appid;
        long step;
        long timestamp;

        public dayStep(Long stepNum) {
            step = stepNum;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public long getStep() {
            return step;
        }

        public void setStep(long step) {
            this.step = step;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }
}
