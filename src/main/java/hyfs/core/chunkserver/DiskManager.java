package hyfs.core.chunkserver;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 2:19 PM
 * @email zhangzhiyuan@218.gmail
 */


// DiskManager for dataNode
public class DiskManager implements  DiskManagerInter{

	protected long capacity;

	protected long used;

	protected long left;

	public enum Status {NOMAIL,FULL, EMPTY}

	public boolean loadStorage() {
		return false;
	}

	public boolean initDir() {
		return false;
	}

	public boolean createDir() {
		return false;
	}



}
