package hyfs.core.config;


import hyfs.core.beans.ConfigBean;
import hyfs.util.Constant;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainConfigLoader implements ConfigLoader{

	private static  Logger logger = Logger.getLogger(MainConfigLoader.class);

	public ConfigBean load(){

		Properties pro = new Properties();
		InputStream input = null;
		ConfigBean configBean = new ConfigBean();
		try{

			String filename  = Constant.SERVERCONFIG;
			input = MainConfigLoader.class.getClassLoader().getResourceAsStream(filename);
			if (input == null){
				logger.error("read server.properties error");
				return null;
			}
			pro.load(input);
			configBean.setIp(pro.getProperty("ip"));
			configBean.setLog(pro.getProperty("log"));
			configBean.setPort(Integer.valueOf(pro.getProperty("port")));
			return configBean;

		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if (input != null){
				try {
					input.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
			return configBean;

		}

	}

}
