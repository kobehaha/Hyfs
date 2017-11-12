package hyfs.core.chunkserver;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 1:15 PM
 * @email zhangzhiyuan@218.gmail
 */
public class ChunkServerID {

	// hostname:port
	public String name;

	public String storageId;

	public int chunkPort;

	public int rpcPort;

	public ChunkServerID() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public int getChunkPort() {
		return chunkPort;
	}

	public void setChunkPort(int chunkPort) {
		this.chunkPort = chunkPort;
	}

	public int getRpcPort() {
		return rpcPort;
	}

	public void setRpcPort(int rpcPort) {
		this.rpcPort = rpcPort;
	}
}
