package myUtils;

import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import org.junit.Test;

import com.gyh.utils.encrypt.RSAUtil;

public class TestRSAUtil {
	//RSA算法每次生成的公钥和私钥都不一样，所以用不同的公钥私钥加密解密的结果也不一样
	//只生成一次公钥私钥，存到配置文件或内存中，使用的时候直接获取，这样就可以成功加密解密，而不是每次加密的结果不一致导致最后无法解密
	
	static RSAPublicKey publicKey = null;
	static RSAPrivateKey privateKey = null;
//	@Test
	public void m1() throws Exception {
		String s = encryptByPublicKey("AVAILABLE_POINT");
		System.out.println(s.length() + "," + s);
		s = decryptByPrivateKey(s);
		System.out.println(s.length() + "," + s);
	}

	public String encryptByPublicKey(String text) throws Exception {
		Map<String, RSAKey> map = RSAUtil.getKeys();
		// 生成公钥和私钥
		RSAPublicKey publicKey = (RSAPublicKey) map.get(RSAUtil.PUBLIC_KEY);
		// 模
		String modulus = publicKey.getModulus().toString();
		// 公钥指数
		String public_exponent = publicKey.getPublicExponent().toString();
		// 使用模和指数生成公钥和私钥
		RSAPublicKey pubKey = RSAUtil.getPublicKey(modulus, public_exponent);
		return RSAUtil.encryptByPublicKey(text, pubKey);
	}

	public String decryptByPrivateKey(String text) throws Exception {
		Map<String, RSAKey> map = RSAUtil.getKeys();
		// 生成公钥和私钥
		RSAPublicKey publicKey = (RSAPublicKey) map.get(RSAUtil.PUBLIC_KEY);
		RSAPrivateKey privateKey = (RSAPrivateKey) map.get(RSAUtil.PRIVATE_KEY);
		// 模
		String modulus = publicKey.getModulus().toString();
		// 公钥指数
		// 私钥指数
		String private_exponent = privateKey.getPrivateExponent().toString();
		// 使用模和指数生成公钥和私钥
		RSAPrivateKey priKey = RSAUtil.getPrivateKey(modulus, private_exponent);
		// 解密后的明文
		return RSAUtil.decryptByPrivateKey(text, priKey);
	}

	public static void main(String[] args) throws Exception {
		String text = "gyh03";
		test(text);
		String mi = encrypt(text);
		String ming = unEncrypt(mi);
		System.out.println(mi);
		System.out.println(ming);
	}

	public static String unEncrypt(String mi) throws Exception {
		Map<String, RSAKey> map = RSAUtil.getKeys();
		// 生成公钥和私钥
//		RSAPublicKey publicKey = (RSAPublicKey) map.get(RSAUtil.PUBLIC_KEY);
//		RSAPrivateKey privateKey = (RSAPrivateKey) map.get(RSAUtil.PRIVATE_KEY);
		// 模
		String modulus = publicKey.getModulus().toString();
		// 私钥指数
		String private_exponent = privateKey.getPrivateExponent().toString();
		// 明文
		// 使用模和指数生成公钥和私钥
		RSAPrivateKey priKey = RSAUtil.getPrivateKey(modulus, private_exponent);
		 System.out.println("priKey:" + priKey);
		// 解密后的明文
		String ming = RSAUtil.decryptByPrivateKey(mi, priKey);
		System.out.println(ming);
		return ming;
	}

	public static String encrypt(String ming) throws Exception {
		Map<String, RSAKey> map = RSAUtil.getKeys();
		// 生成公钥和私钥
//		RSAPublicKey publicKey = (RSAPublicKey) map.get(RSAUtil.PUBLIC_KEY);
		// 模
		String modulus = publicKey.getModulus().toString();
		// 公钥指数
		String public_exponent = publicKey.getPublicExponent().toString();
		// 使用模和指数生成公钥和私钥
		RSAPublicKey pubKey = RSAUtil.getPublicKey(modulus, public_exponent);
		 System.out.println("pubKey:" + pubKey);
		System.out.println(ming);
		// 加密后的密文
		String mi = RSAUtil.encryptByPublicKey(ming, pubKey);
		System.out.println(mi);
		return mi;
	}

	public static void test(String ming) throws Exception {
		Map<String, RSAKey> map = RSAUtil.getKeys();
		// 生成公钥和私钥
		publicKey = (RSAPublicKey) map.get(RSAUtil.PUBLIC_KEY);
		privateKey = (RSAPrivateKey) map.get(RSAUtil.PRIVATE_KEY);
		// 模
		String modulus = publicKey.getModulus().toString();
		// 公钥指数
		String public_exponent = publicKey.getPublicExponent().toString();
		// 私钥指数
		String private_exponent = privateKey.getPrivateExponent().toString();
		// 明文
		// 使用模和指数生成公钥和私钥
		RSAPublicKey pubKey = RSAUtil.getPublicKey(modulus, public_exponent);
		RSAPrivateKey priKey = RSAUtil.getPrivateKey(modulus, private_exponent);
		 System.out.println("pubKey:" + pubKey + ",priKey:" + priKey);
		System.out.println(ming);
		// 加密后的密文
		String mi = RSAUtil.encryptByPublicKey(ming, pubKey);
		System.out.println(mi);
		// 解密后的明文
		ming = RSAUtil.decryptByPrivateKey(mi, priKey);
		System.out.println(ming);
	}
}
