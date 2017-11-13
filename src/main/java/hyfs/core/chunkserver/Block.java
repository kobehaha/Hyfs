package hyfs.core.chunkserver;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 1:10 PM
 * @email zhangzhiyuan@218.gmail
 */
public class Block {

	public String id;

	public String filePath;

	public long size;

	public boolean isComplete;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean complete) {
		isComplete = complete;
	}
}
