import java.util.ArrayList;
import java.util.Scanner;

public class Bankomat {
	
	static Scanner input = new Scanner(System.in);
	static ArrayList<Racun> racuni = new ArrayList<Racun>();
	
	public static void menu() throws Exception {
		String string = "";
		int odabir = 0;
		while(true) {
	 		System.out.println(opcije(string));
			System.out.println(opcijaJedan(string));
			System.out.println(opcijaDva(string));
			System.out.println(opcijaTri(string));
			pozivZeljeneOpcije(odabir);
		}
	}
	
	public static String opcije(String opcije) {
		return "Izaberite zeljenu opciju: ";
	}
	
	public static String opcijaJedan(String opcija) {
		return "1.Kreirati racun";
	}
	
	public static String opcijaDva(String opcija) {
		return "2.Prebaciti novac na drugi racun";
	}
	
	public static String opcijaTri(String opcija) {
		return "3.Ispisati detalje postojeceg racuna";
	}
	
	public static int odabirOpcije(int odabir) throws Exception {
		odabir = input.nextInt();
		if(odabir < 1 || odabir > 3) {
			menu();
			return 0;
		}
		
		return odabir;
	}
	
	public static void pozivZeljeneOpcije(int odabir) throws Exception {
		odabir = odabirOpcije(odabir);
		
		switch(odabir) {
		
		case(1):
				pozivKreiranja();
			break;
		case(2):
				pozivTransfera();
			break;
		case(3):
				pozivDetalja();
			break;
		}
	}
	
	public static void pozivKreiranja() throws Exception {
		String imeVlasnika = "";
		int brojRacuna = 0;
		double iznosNaRacunu = 0;
		try {
			kreiranjeRacuna(imeVlasnikaRacuna(imeVlasnika), brojRacuna(brojRacuna), iznosNaRacunu(iznosNaRacunu));
		} catch (Exception e) {
			input.nextLine();
			kreiranjeRacuna(imeVlasnikaRacuna(imeVlasnika), brojRacuna(brojRacuna), iznosNaRacunu(iznosNaRacunu));
		}
		
	}
	
	public static void pozivTransfera() throws Exception {
		int acc = 0;
		double iznos = 0;
		try {
			transferNovca(sourceAccount(acc), targetAccount(acc), iznosZaPrebacivanje(iznos));
		} catch (Exception e) {
			input.nextLine();
			transferNovca(sourceAccount(acc), targetAccount(acc), iznosZaPrebacivanje(iznos));
		}
		
	}

	public static void pozivDetalja() throws Exception {
		int racun = 0;
		try {
			detaljiRacuna(racunZaIspisDetalja(racun));
		} catch (Exception e) {
			input.nextLine();
			detaljiRacuna(racunZaIspisDetalja(racun));
		}
		
	}
	
	public static Racun kreiranjeRacuna(String imeVlasnikaRacuna, int brojRacuna, double iznosNaRacunu) throws Exception {
		
		if(!Provjera.brojRacunaPozitivan(brojRacuna) || !Provjera.brojRacunaUnikatan(brojRacuna) || 
		   !Provjera.iznosNaRacunuPozitivan(iznosNaRacunu)) 
			return null;
		
		
		Racun racun = new Racun(imeVlasnikaRacuna, brojRacuna, iznosNaRacunu);
		racuni.add(racun);
		System.out.println("Racun uspjesno kreiran!");
		return racun;
		
	}
	
	public static String imeVlasnikaRacuna(String imeVlasnika) {
		System.out.print("Unesite ime vlasnika racuna: ");
		return input.next();
	}
	
	public static int brojRacuna(int brojRacuna) throws Exception {
		System.out.print("Unesite broj racuna: ");
		return input.nextInt();
	}
	
	public static double iznosNaRacunu(double iznos) throws Exception {
		System.out.print("Unesite iznos na racunu: ");
		return input.nextDouble();
	}
	
	public static double transferNovca(int sourceAccount, int targetAccount, double iznosZaPrebacivanje) throws Exception {
		
		if(!Provjera.sourceAcconutPostoji(sourceAccount) || !Provjera.targetAccountPostoji(targetAccount) ||
		   !Provjera.iznosNaSoruceAccOdgovarajuci(sourceAccount, iznosZaPrebacivanje)) {
			System.out.println("Iznos nije prebacen..");
			return 0;
		}
		
		dodavanjeNovcaNaTargetAcc(targetAccount, iznosZaPrebacivanje);
		uzimanjeNovcaSaSourceAcc(sourceAccount, iznosZaPrebacivanje);
		System.out.print("Novac je uspjesno prebacen! ");
		return iznosZaPrebacivanje;
	}

	public static int sourceAccount(int acc) throws Exception {
		System.out.print("Unesite broj racuna sa kojeg zelite prebaciti novac: ");
		return input.nextInt();
	}
	
	public static int targetAccount(int acc) throws Exception {
		System.out.print("Unesite broj racuna na koji zelite prebaciti novac: ");
		return input.nextInt();
	}
	
	public static double iznosZaPrebacivanje(double iznos) throws Exception {
		System.out.print("Unesite iznos koji zelite prebaciti: ");
		return input.nextDouble();
	}
	
	public static double dodavanjeNovcaNaTargetAcc(int targetAcc, double iznos) throws Exception {
		Racun trenutniRacun = null;
		
		for(Racun r : racuni)
			if(targetAcc == r.getBrojRacuna())
				trenutniRacun = r;
		
		if(trenutniRacun == null)
			return 0;
		
		targetAcc = trenutniRacun.getBrojRacuna();
		trenutniRacun.setIznosNaRacunu(trenutniRacun.getIznosNaRacunu() + iznos);
		return iznos;
	}
	
	public static double uzimanjeNovcaSaSourceAcc(int sourceAcc, double iznos) throws Exception {
		Racun trenutniRacun = null;
				
				for(Racun r : racuni)
					if(sourceAcc == r.getBrojRacuna())
						trenutniRacun = r;
				
				if(trenutniRacun == null)
					return 0;
				
				sourceAcc = trenutniRacun.getBrojRacuna();
				trenutniRacun.setIznosNaRacunu(trenutniRacun.getIznosNaRacunu() - iznos);
				return iznos;
			}
	
	public static String detaljiRacuna(int brojRacuna) throws Exception {
		if(Provjera.racunZaIspisDetaljaPostoji(brojRacuna)) {
			for(Racun r : racuni)
				if(brojRacuna == r.getBrojRacuna()) {
					System.out.println(r.toString());
					return r.toString();
				}
		}
		
		return "";
	}
	
	public static int racunZaIspisDetalja(int racun) throws Exception {
		System.out.print("Unesite broj racuna: ");
		return input.nextInt();
	}
	
	public static void main(String[] args) throws Exception {
			
			try {
				menu();
			} catch (Exception e) {
				input.nextLine();
				menu();
			}


	}

}
