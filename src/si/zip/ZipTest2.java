package si.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.26</version>
</dependency>
*/

public class ZipTest2 {
	private static final Logger log = LoggerFactory.getLogger(ZipTest2.class);
	private static String ZIP_FROM_PATH = "temp";
	
	/**
     * 디렉토리 및 파일을 압축한다.
     * @param toPath 압축파일을 생성할 경로
     * @param outZipName 압축파일명
     * @param fileList 압축파일의 이름
     */
    public static void createZipFile(String toPath, String outZipName, List<String> fileList) {
 
        String outputPath = "C:"+File.separator+"Users"+File.separator+"jwkim"+File.separator+"Desktop"+File.separator+"zipTest"+File.separator+"ziptarget";
        File f = new File(outputPath);
        if(!f.exists())
        {
        	f.mkdirs();
        }
        
        try {
        	ZipOutputStream zip_out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(toPath+File.separator+outZipName), 2048));
    		
        	for(String fileNm : fileList)
        	{
        		zip_folder(new File(fileNm), zip_out);
        	}
        	
            zip_out.close();
 
        } catch (FileNotFoundException e) {
            log.debug("File not found : ", e.getMessage());
 
        } catch (IOException e) {
        	log.debug("IOException : ", e.getMessage());
        } finally {
 
 
        }
    }
 
    /**
     * ZipOutputStream를 넘겨 받아서 하나의 압축파일로 만든다.
     * @param parent 상위폴더명
     * @param file 압축할 파일
     * @param zout 압축전체스트림
     * @throws IOException
     */
    private static void zip_folder(File file, ZipOutputStream zout) throws IOException {
        byte[] data = new byte[2048];
        int read;
 
        if (file.isFile()) {
            ZipEntry entry = new ZipEntry(file.getName());
            zout.putNextEntry(entry);
            BufferedInputStream instream = new BufferedInputStream(new FileInputStream(file));
 
            while ((read = instream.read(data, 0, 2048)) != -1)
                zout.write(data, 0, read);
 
            zout.flush();
            zout.closeEntry();
            instream.close();
 
        } else if (file.isDirectory()) {
            String parentString = file.getPath().replace(ZIP_FROM_PATH,"");
            parentString = parentString.substring(0,parentString.length() - file.getName().length());
            ZipEntry entry = new ZipEntry(parentString+file.getName()+"/");
            zout.putNextEntry(entry);
 
            String[] list = file.list();
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    zip_folder(new File(file.getPath() + File.separator + list[i]), zout);
                }
            }
        }
    }


	public static void main(String[] args) {
		System.out.println("------ 압축 시작 -------");
		String propertiesPath = "C:"+File.separator;

		// 1.압축 대상 파일을 수집 한다.
		List<String> fileList = new ArrayList<String>();
		fileList.add(propertiesPath + File.separator + "DGGisDev\\readme.txt");
		fileList.add(propertiesPath + File.separator + "DGGisDev\\server\\apache-tomcat-6.0.44-windows-x64.zip");
		fileList.add(propertiesPath + File.separator + "java\\eclipse\\eclipse.exe");
		fileList.add(propertiesPath + File.separator + "java\\eclipse\\log\\2018\\12\\07\\app_2018120710.log");
		
		// 2.대상 파일을 압축한다.
		String toPath = "C:\\Users\\jwkim\\Desktop\\temp"; //압축생성 경로
		createZipFile(toPath, "doyebNumber.zip", fileList);
		System.out.println("------ 압축 끝 -------");
		
	}

}

