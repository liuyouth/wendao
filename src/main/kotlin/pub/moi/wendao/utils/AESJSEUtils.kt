package pub.moi.wendao.utils


import java.math.BigInteger

import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec


import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.util.StringUtils
import sun.misc.BASE64Decoder
import kotlin.jvm.Throws


/**
 * AES的加密和解密对应JS
 *
 * @author libo
 */
object AESJSEncryptUtils {
    // 密钥必须16位 (需要前端和后端保持一致)
    private val KEY = "E8N3Rfk51nbm810V"

    // 算法
    private val ALGORITHMSTR = "AES/ECB/PKCS5Padding"

    /**
     * aes解密
     *
     * @param encrypt 内容
     * @return
     * @throws Exception
     */
    fun aesDecrypt(encrypt: String): String? {
        try {
            return aesDecrypt(encrypt, KEY)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }

    /**
     * aes加密
     *
     * @param content
     * @return
     * @throws Exception
     */
    fun aesEncrypt(content: String): String {
        try {
            return aesEncrypt(content, KEY)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    fun binary(bytes: ByteArray, radix: Int): String {
        return BigInteger(1, bytes).toString(radix)// 这里的1代表正数
    }

    /**
     * base 64 encode
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    fun base64Encode(bytes: ByteArray): String {
        return Base64.encodeBase64String(bytes)
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    @Throws(Exception::class)
    fun base64Decode(base64Code: String): ByteArray? {
        return if (StringUtils.isEmpty(base64Code)) null else BASE64Decoder().decodeBuffer(base64Code)
    }

    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    @Throws(Exception::class)
    fun aesEncryptToBytes(content: String, encryptKey: String): ByteArray {
        val kgen = KeyGenerator.getInstance("AES")
        kgen.init(128)
        val cipher = Cipher.getInstance(ALGORITHMSTR)
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(encryptKey.toByteArray(), "AES"))

        return cipher.doFinal(content.toByteArray(charset("utf-8")))
    }

    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    @Throws(Exception::class)
    fun aesEncrypt(content: String, encryptKey: String): String {
        return base64Encode(aesEncryptToBytes(content, encryptKey))
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    @Throws(Exception::class)
    fun aesDecryptByBytes(encryptBytes: ByteArray?, decryptKey: String): String {
        val kgen = KeyGenerator.getInstance("AES")
        kgen.init(128)

        val cipher = Cipher.getInstance(ALGORITHMSTR)
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(decryptKey.toByteArray(), "AES"))
        val decryptBytes = cipher.doFinal(encryptBytes!!)
        return String(decryptBytes)
    }

    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    @Throws(Exception::class)
    fun aesDecrypt(encryptStr: String, decryptKey: String): String? {
        return if (StringUtils.isEmpty(encryptStr)) null else aesDecryptByBytes(base64Decode(encryptStr), decryptKey)
    }

//    /**
//     * 测试
//     */
//    @Throws(Exception::class)
//    @JvmStatic
//    fun main(args: Array<String>) {
//        val content = "123"
//        println("加密前：$content")
//        println("加密密钥和解密密钥：$KEY")
//        val encrypt = aesEncrypt(content, KEY)
//        println("加密后：$encrypt")
//        val decrypt = aesDecrypt(encrypt, KEY)
//        println("解密后：" + decrypt!!)
//    }
}
