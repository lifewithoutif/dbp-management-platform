
package com.hna.dbp.utils;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Formatter;

@SuppressWarnings("resource")
public final class Constants {

	/** 换行符 */
	public static final String NEWLINE;
	/** 字符编码 */
	public static final String UTF8_CHARSET = "UTF-8";
	public static final Charset UTF8;
	static {
		String newLine;
		try {
			newLine = new Formatter().format("%n").toString();
		} catch (Exception e) {
			newLine = "\n";
		}
		NEWLINE = newLine;

		Charset charset = null;
		try {
			charset = Charset.forName(UTF8_CHARSET);
		} catch (UnsupportedCharsetException ignored) {
		}
		UTF8 = charset;
	}

	/** Cpu核心数 */
	public static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

}
