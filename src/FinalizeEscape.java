
public class FinalizeEscape {
	public static FinalizeEscape saveHook = null;
	
	public void isAlive() {
		System.out.println("I'm Alive!");
	}
	
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Finalized!");
		FinalizeEscape.saveHook = this;
	}
	
	public static void main(String[] args) throws Throwable {
		saveHook = new FinalizeEscape();
		saveHook = null;
		System.gc();
		Thread.sleep(1);
		if (null!=saveHook) {
			saveHook.isAlive();
		} else {
			System.out.println("I'm Dead!");
		}
		
		saveHook = null;
		System.gc();
		Thread.sleep(1);
		if (null!=saveHook) {
			saveHook.isAlive();
		} else {
			System.out.println("I'm Dead!");
		}
	}
}
