package hyfs.core.launch;

import hyfs.core.beans.ConfigBean;
import hyfs.core.chunkserver.rpc.ChunkServerRpcServer;
import hyfs.core.chunkserver.tpcfile.ChunkServerFileService;
import hyfs.core.config.ChunkServerConfigLoader;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 1:07 PM
 * @email zhangzhiyuan@218.gmail
 */
public class ChunkServerMain {

	public static void main(String[] args){

		try{
			ConfigBean config = new ChunkServerConfigLoader().load();
			//ChunkServerRpcServer.getInstance().start(config);
			ChunkServerFileService.getInstance().start(config);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
