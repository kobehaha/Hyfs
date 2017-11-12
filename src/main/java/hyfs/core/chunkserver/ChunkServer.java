package hyfs.core.chunkserver;

import hyfs.core.chunkserver.ChunkServerID;

import java.util.Map;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 1:04 PM
 * @email zhangzhiyuan@218.gmail
 */
public class ChunkServer extends ChunkServerID {


	protected int recover_count;

	protected int save_count;

	protected int delete_count;

	protected Map<String, String> recoverBlcokMap;

	protected Map<String, String> addingBlockMap;

	protected Map<String, String> blockIdFilePathMap;

	protected Map<String, String> deleteBlockMap;

	public enum Status {NOMAIL, DOWN, DOWNING}


	public int getRecover_count() {
		return recover_count;
	}

	public void setRecover_count(int recover_count) {
		this.recover_count = recover_count;
	}

	public int getSave_count() {
		return save_count;
	}

	public void setSave_count(int save_count) {
		this.save_count = save_count;
	}

	public int getDelete_count() {
		return delete_count;
	}

	public void setDelete_count(int delete_count) {
		this.delete_count = delete_count;
	}

	public Map<String, String> getDeleteBlockMap() {
		return deleteBlockMap;
	}

	public void setDeleteBlockMap(Map<String, String> deleteBlockMap) {
		this.deleteBlockMap = deleteBlockMap;
	}

	public Map<String, String> getRecoverBlcokMap() {
		return recoverBlcokMap;
	}

	public void setRecoverBlcokMap(Map<String, String> recoverBlcokMap) {
		this.recoverBlcokMap = recoverBlcokMap;
	}

	public Map<String, String> getAddingBlockMap() {
		return addingBlockMap;
	}

	public void setAddingBlockMap(Map<String, String> addingBlockMap) {
		this.addingBlockMap = addingBlockMap;
	}



	public Map<String, String> getBlockIdFilePathMap() {
		return blockIdFilePathMap;
	}

	public void setBlockIdFilePathMap(Map<String, String> blockIdFilePathMap) {
		this.blockIdFilePathMap = blockIdFilePathMap;
	}




}
