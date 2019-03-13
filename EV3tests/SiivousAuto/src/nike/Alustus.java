package nike;

public class Alustus {
	private KosketusAnturi k;
	private VariAnturi v;
	private Moottorit m;

	public Alustus(KosketusAnturi k, Moottorit m, VariAnturi v) {
		this.k = k;
		this.m = m;
		this.v = v;
	}
	
	public boolean lahtoValmis() {
		while (k.painettu() == true) {
			v.talennaVari();
			return true;
		}
		return false;
	}
}
