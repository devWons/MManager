package si.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipTest {

	public static void main(String[] args) throws Exception {

		String path = "C:\\Users\\jwkim\\Desktop\\temp";

		FileOutputStream zipFileOutputStream = new FileOutputStream(path + File.separator + "files.zip");
		ZipOutputStream zipOutputStream = new ZipOutputStream(zipFileOutputStream);

		ZipEntry zipEntry = new ZipEntry("LSMD_CONT_LDREG.txt");

		zipOutputStream.putNextEntry(zipEntry);
		zipOutputStream.write("하하하하하\n쓰자".getBytes(StandardCharsets.UTF_8));

		ZipEntry zipEntry2 = new ZipEntry("LSMD_CONT_LDREG.shx");

		zipOutputStream.putNextEntry(zipEntry2);
		zipOutputStream.write("두번째에여...".getBytes(StandardCharsets.UTF_8));

		zipOutputStream.close();
		zipFileOutputStream.close();
		
	}

}
