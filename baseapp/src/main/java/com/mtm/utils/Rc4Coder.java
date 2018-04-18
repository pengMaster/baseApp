package com.mtm.utils;

import java.nio.charset.Charset;

public class Rc4Coder {
	public static String KEY = "mtm2000";
	private static final String key0 = "mtm";
	private static final Charset charset = Charset.forName("UTF-8");
	private static byte[] keyBytes = key0.getBytes(charset);

	/**
	 * RC4 加密解密 大字段
	 * 
	 * @param aInput
	 *            文本
	 * @param aKey
	 *            mtm2000
	 * @return 加密或解密的 string
	 */
	public static String HloveyRC4(String aInput) {
		String aKey = "mtm2000";
		int[] iS = new int[256];
		byte[] iK = new byte[256];

		for (int i = 0; i < 256; i++)
			iS[i] = i;

		int j = 1;

		for (short i = 0; i < 256; i++) {
			iK[i] = (byte) aKey.charAt((i % aKey.length()));
		}

		j = 0;

		for (int i = 0; i < 255; i++) {
			j = (j + iS[i] + iK[i]) % 256;
			int temp = iS[i];
			iS[i] = iS[j];
			iS[j] = temp;
		}

		int i = 0;
		j = 0;
		char[] iInputChar = aInput.toCharArray();
		char[] iOutputChar = new char[iInputChar.length];
		for (short x = 0; x < iInputChar.length; x++) {
			i = (i + 1) % 256;
			j = (j + iS[i]) % 256;
			int temp = iS[i];
			iS[i] = iS[j];
			iS[j] = temp;
			int t = (iS[i] + (iS[j] % 256)) % 256;
			int iY = iS[t];
			char iCY = (char) iY;
			iOutputChar[x] = (char) (iInputChar[x] ^ iCY);
		}
		return new String(iOutputChar);
	}

	/**
	 * 加密 请求参数等小字段
	 * 
	 * @param enc
	 *            加密的文本
	 * 
	 * @return 加密 string
	 */
	public static final String encode(String enc) {
		byte[] b = enc.getBytes(charset);
		for (int i = 0, size = b.length; i < size; i++) {
			for (byte keyBytes0 : keyBytes) {
				b[i] = (byte) (b[i] ^ keyBytes0);
			}
		}
		return new String(b);
	}

	/**
	 * 解密 请求参数等小字段
	 * 
	 * @param dec
	 *            解密的文本
	 * 
	 * @return 解密 string
	 */
	public static String decode(String dec) {
		byte[] e = dec.getBytes(charset);
		byte[] dee = e;
		for (int i = 0, size = e.length; i < size; i++) {
			for (byte keyBytes0 : keyBytes) {
				e[i] = (byte) (dee[i] ^ keyBytes0);
			}
		}
		return new String(e);
	}
}