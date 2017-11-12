package hyfs.core.beans;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 1:06 PM
 * @email zhangzhiyuan@218.gmail
 */
public class ChunkServerConfBean extends ConfigBean {

	private String ip = "127.0.0.1";

	//rpc
	private int port = 9000;

	//fileUpload
	private int filePort = 1000;

	private String log = "/tmp/logs/hyfs_ChunkServer.log";


	@Override
	public String getIp() {
		return ip;
	}

	@Override
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void setPort(int port) {
		this.port = port;
	}

	public int getFilePort() {
		return filePort;
	}

	public void setFilePort(int filePort) {
		this.filePort = filePort;
	}

	@Override
	public String getLog() {
		return log;
	}

	@Override
	public void setLog(String log) {
		this.log = log;
	}
}
