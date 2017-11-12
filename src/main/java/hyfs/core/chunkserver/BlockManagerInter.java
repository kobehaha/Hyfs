package hyfs.core.chunkserver;

import java.util.List;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 1:49 PM
 * @email zhangzhiyuan@218.gmail
 */
public interface BlockManagerInter {


	public List<Block> loadBlock();

	public Block createBlock();

	public boolean removeBlock();

	public Block findBlock();
}
