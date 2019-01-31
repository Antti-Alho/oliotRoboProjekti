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
		while (k.painettu() == false) {
			if (k.getSensor() != null) continue;
			if (v.getSensor() != null) continue;
			if (m.getMoottoriB() != null) continue;
			if (m.getMoottoriC() != null) continue;
			System.out.println("paina minua!");
		}
		v.talennaVari();
		return true;
	}
	
}
