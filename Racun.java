
public class Racun {
	
	private String imeVlasnikaRacuna;
	private int brojRacuna;
	private double iznosNaRacunu;
	
	Racun() {
		
	}
	
	Racun(String imeVlasnikaRacuna, int brojRacuna, double iznosNaRacunu) {
		this.imeVlasnikaRacuna = imeVlasnikaRacuna;
		this.brojRacuna = brojRacuna;
		this.iznosNaRacunu = iznosNaRacunu;
	}
	
	@Override
	public String toString() {
		return "Racun [imeVlasnikaRacuna=" + imeVlasnikaRacuna + ", brojRacuna=" + brojRacuna + ", iznosNaRacunu="
				+ iznosNaRacunu + "]";
	}

	public String getImeVlasnikaRacuna() {
		return imeVlasnikaRacuna;
	}

	public int getBrojRacuna() {
		return brojRacuna;
	}

	public double getIznosNaRacunu() {
		return iznosNaRacunu;
	}

	public void setIznosNaRacunu(double iznosNaRacunu) {
		this.iznosNaRacunu = iznosNaRacunu;
	}
	

}
