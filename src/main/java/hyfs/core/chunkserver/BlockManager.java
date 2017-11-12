package hyfs.core.chunkserver;


import java.util.List;

public class BlockManager implements  BlockManagerInter {


	public DiskManager diskManager;

	public BlockCheckMd5Helper blockCheckMd5Helper;

	public BlockReceiver blockReceiver;

	public BlockRecovery blockRecovery;

	public BlockScanner blockScanner;

	public BlockSender blockSender;

	public void init(){
		diskManager = new DiskManager();
		blockCheckMd5Helper = new BlockCheckMd5Helper();
		blockReceiver =  new BlockReceiver();
		blockScanner = new BlockScanner();
		blockSender = new BlockSender();
		blockRecovery = new BlockRecovery();
	}


	public List<Block> loadBlock() {
		return null;
	}

	public Block createBlock() {
		return null;
	}

	public boolean removeBlock() {
		return false;
	}

	public Block findBlock() {
		return null;
	}

}
