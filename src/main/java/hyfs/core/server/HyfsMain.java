package hyfs.core.server;


import hyfs.core.beans.ConfigBean;
import hyfs.core.config.MainConfigLoader;
import org.apache.log4j.Logger;

public class HyfsMain {

	private static Logger logger = Logger.getLogger(HyfsMain.class);

	public static void main(String[] args) {

		try {
			ConfigBean configBean = new MainConfigLoader().load();
			RpcService.getInstance().start(configBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
