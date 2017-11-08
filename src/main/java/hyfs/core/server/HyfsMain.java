package hyfs.core.server;


public class HyfsMain {

	public static void main(String[] args) {

		try {
			NettyService.getInstance().start();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
