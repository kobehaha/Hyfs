package netty.core;

public class NettyServer {

	public static void main(String[] args) {
		
		try {
			NettyService.getInstance().start();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
