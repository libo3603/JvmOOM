import java.util.Arrays;

public class TenuringThreshold {
private static final int _1M = 1024*1024;
	
	public static void main(String[] args) {
		TenuringThreshold t = new TenuringThreshold();
		String ret = t.customSortString("cba","abcd");
		System.out.println(ret);
		System.out.println(t.isMatch("aa", "a"));
	}
	
	public String customSortString(String s, String t) {
		int slen = s.length();
        if (slen==0) {
            return t;
        }
        
        int[] schi = new int[27];
        char[] chr = new char[27];
        for (int i=0; i<slen; i++) {
            schi[s.charAt(i)-'a'] = i+1;
            chr[i+1] = s.charAt(i);
        }
        
        int tlen = t.length();
        if (tlen==0) {
            return t;
        }
        
        int j = slen+1;
        int[] iarr = new int[tlen];
        for (int i=0; i<tlen; i++) {
            int chv = schi[t.charAt(i)-'a'];
            if (chv<=0) {
                schi[t.charAt(i)-'a'] = j;
                chr[j] = t.charAt(i);
                chv = j;
                j++;
            }
            iarr[i] = chv;
        }
        
        Arrays.sort(iarr);
        
        StringBuffer rb = new StringBuffer(tlen);
        for (int i=0; i<tlen; i++) {
        	rb.append(chr[iarr[i]]);
        }
        return rb.toString();
    }
	
	public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        
        if (pl==0) {
            return sl==0;
        }
        
        if (sl==0) {
            return pl==0;
        }
        
        if (allStart(p)) {
            return true;
        }
        
        String[] pstrs = p.split("\\*");
        int si = 0;
        for (String pstr: pstrs) {
            int psl = pstr.length();
            int psi = 0;
            for (;psi<psl && si<sl;psi++) {
                char pch = pstr.charAt(psi);
                if (pch=='?') {
                    si++;
                } else {
                    while (si<sl && s.charAt(si)==pch) {si++;pch++;}
                }
            }
            
            if (psi<psl) {
                return false;
            }
        }
        return si==sl;
    }
    
    boolean allStart(String p) {
        if (p.length()==0) {
            return false;
        }
        
        int pi = 0;
        int pl = p.length();
        while (pi<pl&&p.charAt(pi)=='*') {pi++;}
        return pi==pl;
    }
}


