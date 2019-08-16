
public class ReferenceCountGC {

	private Object instance;
	
	private static final int _1M = 1024*1024;
	
	private byte[] size = new byte[_1M];
	
	public static void main(String[] args) {
		ReferenceCountGC a = new ReferenceCountGC();
		ReferenceCountGC b = new ReferenceCountGC();
		a.instance = b;
		b.instance = a;
		
		a = null;
		b = null;
		
		System.gc();
	}
}
