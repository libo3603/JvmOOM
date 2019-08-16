
public class TestAllocation {
	private static final int _1M = 1024*1024;
	
	public static void main(String[] args) {
		byte[] a1,a2,a3,a4;
		a1 = new byte[3*_1M];
		a2 = new byte[3*_1M];
		a3 = new byte[3*_1M];
		a4 = new byte[5*_1M];
	}
}
