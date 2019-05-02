package si.dg;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MaintenanceUnit {
	static final Logger log = Logger.getLogger(MaintenanceUnit.class);
	static Logger logger = Logger.getLogger("DATEFILE");

	Map<String, String> config;
	Properties properties;

	public static void main(String[] args) {
		MaintenanceUnit mu = new MaintenanceUnit();
		mu.getProperites();
		
	}
	
	public Map<String, String> getProperites()
	{
		try {
			properties = new Properties();
			config = new HashMap<String, String>();
			String resourcePah = "C:\\java\\workspace\\MManager\\resource\\dgConfig.properties";
			
	        properties.load(new FileInputStream(resourcePah));
		
//	        System.out.println(properties.getProperty("dgfilepath.dataupload"));
	        log.debug("프로퍼티 파일 읽기 성공. (경로 : "+ resourcePah+" ");
	        log.debug("-----------------------------------------");
	        log.debug("----------------프로젝트 시작------------------");
	        log.debug("-----------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config;
	}

}
