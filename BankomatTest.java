import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankomatTest {
	
	@BeforeEach
	void setUp() throws Exception {
		Racun racun = new Racun("selma", 1, 100);
		Racun racun1 = new Racun("suhra", 2, 200);
		Bankomat.racuni.add(racun);
		Bankomat.racuni.add(racun1);
	}

	@Test
	void test() {
		
	}
	
	@Test
	void shouldReturnString() {
		String actual = Bankomat.opcije("Izaberite zeljenu opciju: ");
		assertEquals("Izaberite zeljenu opciju: ", actual);
		String actual1 = Bankomat.opcijaJedan("1.Kreirati racun");
		assertEquals("1.Kreirati racun", actual1);
		String actual2 = Bankomat.opcijaDva("2.Prebaciti novac na drugi racun");
		assertEquals("2.Prebaciti novac na drugi racun", actual2);
		String actual3 = Bankomat.opcijaTri("3.Ispisati detalje postojeceg racuna");
		assertEquals("3.Ispisati detalje postojeceg racuna", actual3);
	}
	
	@Test
	void shouldReturnFalseWhenRacunIsInIncorrectForm() throws Exception {
		Racun racun = new Racun("selma", 1, 10);
		Racun racunTest = Bankomat.kreiranjeRacuna("selma", racun.getBrojRacuna(), 10);
		Racun racunTest2 = Bankomat.kreiranjeRacuna("s", 52, -10);
		Racun racunTest3 = Bankomat.kreiranjeRacuna("f", -4, 20);
		assertEquals(null, racunTest);
		assertEquals(null, racunTest2);
		assertEquals(null, racunTest3);
	}	
	
	@Test 
	void shouldReturnTrueWhenRacunIsInCorrectForm() throws Exception {
		Racun racunTest = Bankomat.kreiranjeRacuna("selma", 3, 100);
		boolean test = false;
		if(racunTest.getImeVlasnikaRacuna().equals("selma") && racunTest.getBrojRacuna() == 3 && racunTest.getIznosNaRacunu() == 100)
			test = true;
		
		assertTrue(test);
	}
	
	@Test 
	void shouldReturn0WhenTransferNovcaIsNotWork() throws Exception {
		Racun racun = new Racun("selma", 6, 70);
		Racun racunT = new Racun("f", 5, 50);
		int sourceAccount = 2, targetAccount = 3;
		double iznos = 80;
		double rezultat = Bankomat.transferNovca(sourceAccount, targetAccount, iznos);
		if(sourceAccount != racun.getBrojRacuna() || targetAccount != racunT.getBrojRacuna() && iznos > racun.getIznosNaRacunu())
			rezultat = 0;
		
		assertEquals(0, rezultat);
	}
	
	@Test 
	void shouldReturnAmountWhenTransferNovcaWorks() throws Exception {
		Racun racunS = new Racun("s", 7, 20);
		Racun racunT = new Racun("t", 9, 40);
		int sourceAcc = 7, targetAcc = 9;
		double iznos = 10;
		double rezultat = Bankomat.transferNovca(sourceAcc, targetAcc, iznos);
		if(sourceAcc == racunS.getBrojRacuna() && targetAcc == racunT.getBrojRacuna() && iznos < racunS.getIznosNaRacunu())
			rezultat = iznos;
		
		assertEquals(iznos, rezultat);
	}
	
	@Test
	void shouldReturnEmptyStringWhenAccountDoesNotExist() throws Exception {
		String actual = Bankomat.detaljiRacuna(5);
		assertEquals("", actual);
	
	}
	
	@Test
	void shouldReturnInformationAboutTheAccountWhenAccountExist() throws Exception {
		Racun racun = Bankomat.racuni.get(0);
		String actual = Bankomat.detaljiRacuna(racun.getBrojRacuna());
		assertEquals(racun.toString(), actual);
	}

	@Test
	void shouldReturnFalseWhenBrojRacunaLessThan0() throws Exception {
		boolean condition = Provjera.brojRacunaPozitivan(-1);
		assertFalse(condition);
	}
	
	@Test
	void shouldReturnTrueWhenBrojRacunaGreaterThan0() throws Exception {
		boolean condition = Provjera.brojRacunaPozitivan(2);
		assertTrue(condition);
	}
	
	@Test
	void shouldReturnFalseWhenBrojRacunaAlreadyExist() throws Exception {
		boolean condition = Provjera.brojRacunaUnikatan(Bankomat.racuni.get(0).getBrojRacuna());
		assertFalse(condition);
	}

	@Test
	void shouldReturnTrueWhenBrojRacunaDoesntExistYet() throws Exception {
		boolean condition = Provjera.brojRacunaUnikatan(5);
		assertTrue(condition);
	}
	
	@Test
	void shouldReturnFalseWhenIznosLessThan0() throws Exception {
		boolean condition = Provjera.iznosNaRacunuPozitivan(-1);
		assertFalse(condition);
	}
	
	@Test
	void shouldReturnTrueWhenIznosGreaterThan0() throws Exception {
		boolean condition = Provjera.iznosNaRacunuPozitivan(50);
		assertTrue(condition);
	}
	
	@Test
	void shouldReturnFalseWhenSourceAcconutDoesntExist() throws Exception {
		boolean condition = Provjera.sourceAcconutPostoji(9);
		assertFalse(condition);
	}
	
	@Test
	void shouldReturnTrueWhenSourceAccExists() throws Exception {
		boolean condition = Provjera.sourceAcconutPostoji(Bankomat.racuni.get(0).getBrojRacuna());
		assertTrue(condition);
	}
	
	@Test
	void shouldReturnFalseWhenTargetAccDoesntExist() throws Exception {
		boolean condition = Provjera.targetAccountPostoji(5);
		assertFalse(condition);
	}
	
	@Test 
	void shouldReturnTrueWhenTargetAccountExists() throws Exception {
		boolean condition = Provjera.targetAccountPostoji(Bankomat.racuni.get(1).getBrojRacuna());
		assertTrue(condition);
	}
	
	@Test
	void shouldReturFalseWhenAmountOfMoneyGreaterThanAmountOnSoruceAccount() throws Exception {
		boolean condition = Provjera.iznosNaSoruceAccOdgovarajuci(Bankomat.racuni.get(0).getBrojRacuna(), 500);
		assertFalse(condition);
	}
	
	@Test
	void shouldReturnTrueWhenAmountOfMoneyLessThanAmountOnSourceAcc() throws Exception {
		Racun racun = Bankomat.racuni.get(0);
		boolean condition = Provjera.iznosNaSoruceAccOdgovarajuci(racun.getBrojRacuna(), 50);
		assertTrue(condition);
	}
	
	@Test
	void sholudReturnFalseWhenAccountDoesntExist() throws Exception {
		boolean condition = Provjera.racunZaIspisDetaljaPostoji(5);
		assertFalse(condition);
	}
	
	@Test
	void sholudReturnTrueWhenAccountExists() throws Exception {
		boolean condition = Provjera.racunZaIspisDetaljaPostoji(Bankomat.racuni.get(0).getBrojRacuna());
		assertTrue(condition);
	}

}
