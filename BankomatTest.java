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
	void shouldReturnOneWhenOdabirEqualsOne() throws Exception {
		int actual = Bankomat.odabirOpcije(1);
		assertEquals(1, actual);
	}
	
	@Test
	void shouldReturnZeroWhenOdabirIsLessThanOne() throws Exception {
		int actual = Bankomat.odabirOpcije(-1);
		assertEquals(0, actual);
	}
	
	@Test
	void shouldReturnZeroWhenOdabirIsGreaterThan3() throws Exception {
		int actual = Bankomat.odabirOpcije(4);
		assertEquals(0, actual);
	}
	
	@Test
	void shouldReturnFalseWhenRacunIsInIncorrectForm() throws Exception {
		Racun racunTest = Bankomat.kreiranjeRacuna("selma", -1, -10);
		Racun racunTest1 = Bankomat.kreiranjeRacuna("selma", 1, -10);
		boolean test = true;
		if(racunTest.getBrojRacuna() == -1)
			test = false;
		assertFalse(test);
		
		if(racunTest.getIznosNaRacunu() == -10)
			test = false;
		assertFalse(test);
		
		if(racunTest1.getBrojRacuna() == Bankomat.racuni.get(0).getBrojRacuna())
			test = false;
		assertFalse(test);
	}
	
	@Test 
	void shouldReturnTrueWhenRacunIsInCorrectForm() throws Exception {
		Racun racunTest = Bankomat.kreiranjeRacuna("selma", 1, 100);
		boolean test = false;
		if(racunTest.getBrojRacuna() == 1 && racunTest.getImeVlasnikaRacuna().equals("selma") 
				&&  racunTest.getIznosNaRacunu() == 100)
			test = true;
		assertTrue(test);
	}
	
	@Test 
	void shouldReturnStringImeVlasnika()  throws Exception {
		String actual = Bankomat.imeVlasnikaRacuna("ImeVlasnika");
		assertEquals("ImeVlasnika", actual);
	}
	
	@Test 
	void shouldReturnIntBrojRacuna() throws Exception {
		int actual = Bankomat.brojRacuna(5);
		assertEquals(5, actual);
	}
	
	@Test 
	void shouldReturnDoubleIznosNaRacunu() throws Exception {
		double actual = Bankomat.iznosNaRacunu(10.0);
		assertEquals(10.0, actual);
	}
	
	@Test 
	void shouldReturnFalseWhenTransferNovcaIsNotWork() throws Exception {
		
	}
	
	@Test 
	void shouldReturnTrueWhenTransferNovcaWorks() throws Exception {
		
	}
	
	@Test
	void shouldReturnSourceAccountNumber() throws Exception {
		int actual = Bankomat.sourceAccount(5);
		assertEquals(5, actual);
	}
	
	@Test
	void shouldReturnTargetAccountNumber() throws Exception {
		int actual = Bankomat.targetAccount(4);
		assertEquals(4, actual);
	}
	
	@Test
	void shouldReturnAmount() throws Exception {
		double actual = Bankomat.iznosZaPrebacivanje(10.0);
		assertEquals(10.0, actual);
	}
	
	@Test 
	void shouldReturnAmountWhenMoneyIsTransferred() throws Exception {
		
	}
	
	@Test
	void shouldReturnZeroWhenMoneyIsNotTransferred() throws Exception {
		
	}
	
	@Test
	void shouldReturnEmptyStringWhenAccountDoesNotExist() throws Exception {
	
	}
	
	@Test
	void shouldReturnInformationAboutTheAccountWhenAccountExist() throws Exception {
		
		
	}
	
	@Test
	void shouldReturnInt() throws Exception {
		int actual = Bankomat.racunZaIspisDetalja(1);
		assertEquals(1, actual);
		
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
		double iznos = Bankomat.racuni.get(0).getIznosNaRacunu()+500;
		boolean condition = Provjera.iznosNaSoruceAccOdgovarajuci(Bankomat.racuni.get(0).getBrojRacuna(), iznos);
		assertFalse(condition);
	}
	
	@Test
	void shouldReturnTrueWhenAmountOfMoneyLessThanAmountOnSourceAcc() throws Exception {
		double iznos = Bankomat.racuni.get(0).getIznosNaRacunu()-50;
		boolean condition = Provjera.iznosNaSoruceAccOdgovarajuci(Bankomat.racuni.get(0).getBrojRacuna(), iznos);
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
