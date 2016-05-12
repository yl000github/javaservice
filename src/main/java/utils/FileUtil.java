package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
	public static void createFile(String filepath) throws IOException {
		File f = new File(filepath);
		if (!f.exists()) {
			String parent = f.getParent();
			File p = new File(parent);
			if (!p.exists())
				p.mkdirs();
			f.createNewFile();
		}
	}

	public static void write(String filepath, String content) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
			bw.write(content);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeAdd(String filepath, String content) {
		try {
			createFile(filepath);
			FileWriter fw = new FileWriter(filepath, true);
			fw.write(content);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String read(String filepath) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}
			// System.out.println(sb.toString());
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 返回一个文件夹下所有对应类型文件的总行数
	 * @param filePath
	 * @return
	 * @throws IOException 
	 */
	public static int getFilesLines(String filePath,String type) throws IOException {
		File root = new File(filePath);
		if(!root.isDirectory()) return -1;
		File[] files = root.listFiles();
		if(files.length==0) return 0;
		int s=0;
		for (File file : files) {
			if (file.isDirectory()) {
				s+=getFilesLines(file.getAbsolutePath(),type);
//				System.out.println("显示" + filePath + "下所有子目录及其文件" + file.getAbsolutePath());
			} else {
//				System.out.println("显示" + filePath + "下所有子目录" + file.getAbsolutePath());
				if(file.getName().endsWith("."+type))
					s+=readLines(file);
			}
		}
		return s;
	}
	public static int readLines(File f) throws IOException{
		InputStream input = new FileInputStream(f);BufferedReader b = new BufferedReader(new InputStreamReader(input));
		int count=0;
		while(b.readLine()!=null) count++;
		b.close();
		input.close();
		return count;
	}
	/**
	 * 将文件中某个字段的值做一定的运算
	 * @param args
	 * @throws IOException
	 */
	public static void reappearChange(){
		String path="d:/logs/move1.txt";
		int t=1600;
		try {
			StringBuffer sb=new StringBuffer();
			BufferedReader br=new BufferedReader(new FileReader(path));
			String line;
			while((line=br.readLine())!=null){
				String num=line.substring(line.lastIndexOf("=")+1, line.length());
				String ano=String.valueOf(Integer.parseInt(num)-t);
				line=line.replace(num, ano);
				sb.append(line);
				sb.append("\r\n");
			}
			br.close();
			write(path,sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws IOException {
		reappearChange();
//		String c = "hello yang";
//		String filepath = "D:\\git\\myself\\src\\main\\java";
//		System.out.println(getFilesLines(filepath,"java"));
//		String filepath = "D:\\git\\myself\\src\\main\\java";
//		System.out.println(getFilesLines(filepath,"java"));
//		System.out.println(getFilesLines("D:\\HbuilderWorkspace\\omp\\server","js"));
//		System.out.println(getFilesLines("D:\\HbuilderWorkspace\\omp\\client\\app","js"));
		// write(filepath, c);
		// System.out.println(read(filepath));
	}
}
