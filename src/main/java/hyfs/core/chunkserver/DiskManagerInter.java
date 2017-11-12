package hyfs.core.chunkserver;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 2:24 PM
 * @email zhangzhiyuan@218.gmail
 */
public interface DiskManagerInter {

	public boolean loadStorage();

	public boolean initDir();

	public boolean createDir();

}
