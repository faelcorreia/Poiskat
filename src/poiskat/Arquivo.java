package poiskat;

import java.io.*;
import java.util.Collection;
import org.apache.commons.io.FileUtils;

public class Arquivo {
	public static String fileToString(Reader reader) {
		String str = "";
		try {
			while (reader.ready()) {
				str += (char)reader.read();
			}
		} catch (Exception e) {
		}
		return str;
	}
	
	public static File[] getArquivos(String path) {
		File root = new File(path);
		try {
			String[] extensions = { "html", "htm", "txt" };
			boolean recursive = true;
			Collection<File> files = FileUtils.listFiles(root, extensions,
					recursive);
			File[] fileVet = (File[]) files.toArray(new File[files.size()]);
			return fileVet;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
