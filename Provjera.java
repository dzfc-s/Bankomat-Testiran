
public class Provjera {
	
	public static boolean brojRacunaPozitivan(int brojRacuna) throws Exception {
		if(brojRacuna < 0) {
			System.out.println("Broj racuna mora biti pozitivan..");
			return false;
		}
		
		return true;
	}
	
	public static boolean brojRacunaUnikatan(int brojRacuna) throws Exception {
		for(Racun r : Bankomat.racuni)
			if(brojRacuna == r.getBrojRacuna()) {
				System.out.println("Vec postoji identican broj racuna..");
				return false;
			}
		
		return true;
	}
	
	public static boolean iznosNaRacunuPozitivan(double iznos) throws Exception {
		if(iznos < 0) {
			System.out.println("Iznos mora biti pozitivan..");
			return false;
		}
		
		return true;
	}

	public static boolean sourceAcconutPostoji(int acc) throws Exception {
		Racun trenutniRacun = null;
		
		for(Racun r : Bankomat.racuni)
			if(acc == r.getBrojRacuna())
				trenutniRacun = r;
		
		if(trenutniRacun != null) {
			acc = trenutniRacun.getBrojRacuna();
			return true;
		}
		
		System.out.println("Racun nije pronadjen..");
		return false;
	}
	
	public static boolean targetAccountPostoji(int acc) throws Exception {
		Racun trenutniRacun = null;
				
		for(Racun r : Bankomat.racuni)
			if(acc == r.getBrojRacuna())
				trenutniRacun = r;
		
		if(trenutniRacun != null) {
			acc = trenutniRacun.getBrojRacuna();
			return true;
		}
		
		System.out.println("Racun nije pronadjen..");
		return false;
	}
	
	public static boolean iznosNaSoruceAccOdgovarajuci(int sourceAcc, double iznos) throws Exception {
		Racun trenutniRacun = null;
		
		for(Racun r : Bankomat.racuni)
			if(sourceAcc == r.getBrojRacuna())
				trenutniRacun = r;
		
		if(trenutniRacun != null) {
			sourceAcc = trenutniRacun.getBrojRacuna();
			if(trenutniRacun.getIznosNaRacunu() < iznos) {
				System.out.println("Nemate dovoljno sredstava na racunu za zeljenu opciju..");
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean racunZaIspisDetaljaPostoji(int racun) throws Exception {
		
		for(Racun r : Bankomat.racuni)
			if(racun == r.getBrojRacuna())
				return true;
		
		System.out.println("Racun nije pronadjen..");
		return false;

	}
}
