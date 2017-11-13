package hyfs.core.config;


import hyfs.core.beans.ChunkServerConfBean;
import hyfs.core.beans.ConfigBean;
import hyfs.util.Constant;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 3:26 PM
 * @email zhangzhiyuan@218.gmail
 */
public class ChunkServerConfigLoader implements ConfigLoader{

	public static Logger logger = Logger.getLogger(ChunkServerConfigLoader.class);

	public ConfigBean load() {
		Properties pro = new Properties();
		InputStream input = null;
		ChunkServerConfBean configBean = new ChunkServerConfBean();
		try{

			String filename  = Constant.CHUNKSERVERCONFIG;
			input = MainConfigLoader.class.getClassLoader().getResourceAsStream(filename);
			if (input == null){
				logger.error("read chunkserver.properties error");
				return null;
			}
			pro.load(input);
			configBean.setIp(pro.getProperty("ip"));
			configBean.setLog(pro.getProperty("log"));
			configBean.setFilePort(Integer.valueOf(pro.getProperty("filePort")));
			configBean.setDataDir(pro.getProperty("dataDir"));
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
