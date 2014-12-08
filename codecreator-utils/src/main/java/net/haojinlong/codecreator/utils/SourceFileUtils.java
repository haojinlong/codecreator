/**
 * # SourceFileUtils.java -- (2014年11月2日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package net.haojinlong.codecreator.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.haojinlong.tools.file.TextFileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年11月2日
 *
 */
public class SourceFileUtils {
	static Logger logger = LoggerFactory.getLogger(SourceFileUtils.class);

	/**
	 * 读取源文件内容
	 * 
	 * @param srcDir
	 *            源文件根目录
	 * @param packageName
	 *            源文件所在的包名
	 * @param fileName
	 *            文件名称
	 * @return 文件全文
	 * @throws IOException
	 *             读取异常
	 */
	public static String readSourceFile(String srcDir, String packageName,
			String fileName) throws IOException {
		String filePath = srcDir + "/" + packageName.replace('.', '/') + "/"
				+ fileName;
		return TextFileUtils.readFile(filePath);
	}

	/**
	 * 读取源文件内容，并分行保存到列表中
	 * 
	 * @param srcDir
	 *            源文件根目录
	 * @param packageName
	 *            源文件所在的包名
	 * @param fileName
	 *            文件名称
	 * @return 文件全文
	 * @throws IOException
	 *             读取异常
	 */
	public static List<String> readSourceFileToList(String srcDir,
			String packageName, String fileName) throws IOException {
		return TextFileUtils.readToList(srcDir + "/"
				+ packageName.replace('.', '/') + "/" + fileName);
	}

	/**
	 * 写相应的源文件
	 * 
	 * @param srcDir
	 *            源文件根目录
	 * @param packageName
	 *            包名
	 * @param fileName
	 *            文件名
	 * @param content
	 *            要写的内容
	 * @throws IOException
	 *             文件读写异常
	 */
	public static void writeSourceFile(String srcDir, String packageName,
			String fileName, String content) throws IOException {
		String fileDir = srcDir + "/" + packageName.replace('.', '/') + "/";
		File file = new File(fileDir);
		if (!file.exists()) {
			file.mkdirs();
		} else if (!file.isDirectory()) {
			throw new IOException(fileDir + " is not a directory!");
		}
		TextFileUtils.writeFile(content, fileDir + fileName);
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param srcDir
	 *            源文件根目录
	 * @param packageName
	 *            源文件所在的根目录
	 * @param fileName
	 *            文件名
	 * @return 文件是否存在
	 */
	public static boolean exists(String srcDir, String packageName,
			String fileName) {
		File file = new File(srcDir + "/" + packageName.replace('.', '/') + "/"
				+ fileName);
		return file.exists();
	}

	// public static void main(String[] args) throws IOException {
	// writeSourceFile("/home/haojinlong/2-dev/111/222/", "test.t",
	// "file.java", "file");
	// }
}
