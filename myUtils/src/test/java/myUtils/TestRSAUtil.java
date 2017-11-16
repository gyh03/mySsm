package myUtils;

import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import org.junit.Test;

import com.gyh.utils.encrypt.RSAUtil;

public class TestRSAUtil {
	//RSA�㷨ÿ�����ɵĹ�Կ��˽Կ����һ���������ò�ͬ�Ĺ�Կ˽Կ���ܽ��ܵĽ��Ҳ��һ��
	//ֻ����һ�ι�Կ˽Կ���浽�����ļ����ڴ��У�ʹ�õ�ʱ��ֱ�ӻ�ȡ�������Ϳ��Գɹ����ܽ��ܣ�������ÿ�μ��ܵĽ����һ�µ�������޷�����
	
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
		// ���ɹ�Կ��˽Կ
		RSAPublicKey publicKey = (RSAPublicKey) map.get(RSAUtil.PUBLIC_KEY);
		// ģ
		String modulus = publicKey.getModulus().toString();
		// ��Կָ��
		String public_exponent = publicKey.getPublicExponent().toString();
		// ʹ��ģ��ָ�����ɹ�Կ��˽Կ
		RSAPublicKey pubKey = RSAUtil.getPublicKey(modulus, public_exponent);
		return RSAUtil.encryptByPublicKey(text, pubKey);
	}

	public String decryptByPrivateKey(String text) throws Exception {
		Map<String, RSAKey> map = RSAUtil.getKeys();
		// ���ɹ�Կ��˽Կ
		RSAPublicKey publicKey = (RSAPublicKey) map.get(RSAUtil.PUBLIC_KEY);
		RSAPrivateKey privateKey = (RSAPrivateKey) map.get(RSAUtil.PRIVATE_KEY);
		// ģ
		String modulus = publicKey.getModulus().toString();
		// ��Կָ��
		// ˽Կָ��
		String private_exponent = privateKey.getPrivateExponent().toString();
		// ʹ��ģ��ָ�����ɹ�Կ��˽Կ
		RSAPrivateKey priKey = RSAUtil.getPrivateKey(modulus, private_exponent);
		// ���ܺ������
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
		// ���ɹ�Կ��˽Կ
//		RSAPublicKey publicKey = (RSAPublicKey) map.get(RSAUtil.PUBLIC_KEY);
//		RSAPrivateKey privateKey = (RSAPrivateKey) map.get(RSAUtil.PRIVATE_KEY);
		// ģ
		String modulus = publicKey.getModulus().toString();
		// ˽Կָ��
		String private_exponent = privateKey.getPrivateExponent().toString();
		// ����
		// ʹ��ģ��ָ�����ɹ�Կ��˽Կ
		RSAPrivateKey priKey = RSAUtil.getPrivateKey(modulus, private_exponent);
		 System.out.println("priKey:" + priKey);
		// ���ܺ������
		String ming = RSAUtil.decryptByPrivateKey(mi, priKey);
		System.out.println(ming);
		return ming;
	}

	public static String encrypt(String ming) throws Exception {
		Map<String, RSAKey> map = RSAUtil.getKeys();
		// ���ɹ�Կ��˽Կ
//		RSAPublicKey publicKey = (RSAPublicKey) map.get(RSAUtil.PUBLIC_KEY);
		// ģ
		String modulus = publicKey.getModulus().toString();
		// ��Կָ��
		String public_exponent = publicKey.getPublicExponent().toString();
		// ʹ��ģ��ָ�����ɹ�Կ��˽Կ
		RSAPublicKey pubKey = RSAUtil.getPublicKey(modulus, public_exponent);
		 System.out.println("pubKey:" + pubKey);
		System.out.println(ming);
		// ���ܺ������
		String mi = RSAUtil.encryptByPublicKey(ming, pubKey);
		System.out.println(mi);
		return mi;
	}

	public static void test(String ming) throws Exception {
		Map<String, RSAKey> map = RSAUtil.getKeys();
		// ���ɹ�Կ��˽Կ
		publicKey = (RSAPublicKey) map.get(RSAUtil.PUBLIC_KEY);
		privateKey = (RSAPrivateKey) map.get(RSAUtil.PRIVATE_KEY);
		// ģ
		String modulus = publicKey.getModulus().toString();
		// ��Կָ��
		String public_exponent = publicKey.getPublicExponent().toString();
		// ˽Կָ��
		String private_exponent = privateKey.getPrivateExponent().toString();
		// ����
		// ʹ��ģ��ָ�����ɹ�Կ��˽Կ
		RSAPublicKey pubKey = RSAUtil.getPublicKey(modulus, public_exponent);
		RSAPrivateKey priKey = RSAUtil.getPrivateKey(modulus, private_exponent);
		 System.out.println("pubKey:" + pubKey + ",priKey:" + priKey);
		System.out.println(ming);
		// ���ܺ������
		String mi = RSAUtil.encryptByPublicKey(ming, pubKey);
		System.out.println(mi);
		// ���ܺ������
		ming = RSAUtil.decryptByPrivateKey(mi, priKey);
		System.out.println(ming);
	}
}
