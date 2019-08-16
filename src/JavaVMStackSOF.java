
public class JavaVMStackSOF {

	private int stackLength = 1;
	
	public void stackLeak() {
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] arg) {
		JavaVMStackSOF sof = new JavaVMStackSOF();
		
		try {
			sof.stackLeak();
		} catch(Throwable t) {
			System.out.println("StackLength: " + sof.stackLength);
			throw t;
		}
	}
}
