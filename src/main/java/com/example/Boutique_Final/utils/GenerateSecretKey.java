//import java.util.Base64;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//
//public class GenerateSecretKey {
//    public static void main(String[] args) throws Exception {
//        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//        keyGen.init(256); // 256-bit key
//        SecretKey secretKey = keyGen.generateKey();
//        String base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
//
//        System.out.println("Base64 Encoded Secret Key: " + base64Key);
//    }
//}
