import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import br.ufal.ic.atividades.teste.BooleanArrayAsList;
import br.ufal.ic.atividades.teste.Bools;
import br.ufal.ic.atividades.teste.BoolsMockObj;

class BoolsTest { 
	
	private Bools bools = new Bools();
	
	@Test
	void testBools() {
		assertNotNull(bools, "bools está nula");
	}

	@Test
	void testCompare() {
		assertAll(
				() -> { assertTrue(bools.compare(false, false) == 0, "Houve problemas, não retornou 0 para false/false");},
				() -> { assertTrue(bools.compare(true, true) == 0, "Houve problemas, não retornou 0 para true/true");},
				() -> { assertTrue(bools.compare(true, false) > 0, "Houve problemas, não retornou positivo");},
				() -> { assertTrue(bools.compare(false, true) < 0, "Houve problemas, não retornou negativo");}
				);
		
	}

	@Test
	void testContains() {
		boolean[] b1 = {true, true, true};
		boolean[] b2 = {};
		assertAll(
				() -> { assertTrue( bools.contains(b1, true), "Não achou true no array"); },
				() -> { assertFalse(bools.contains(b1, false), "Achou False no array"); },
				() -> { assertFalse(bools.contains(b2, true), "Achou elementos no array vazio"); }
				);
	}

	@Test
	void testIndexOfBooleanArrayBoolean() {
		boolean[] b1 = {true, true, true, false, false, true};
		boolean[] b2 = {};
		boolean[] b3 = {false, false, false, false, false};
		assertAll(
				() -> { assertEquals(0, bools.indexOf(b1, true), "Não achou true no array"); },
				() -> { assertEquals(3, bools.indexOf(b1, false), "Não achou false no array"); },
				() -> { assertEquals(-1, bools.indexOf(b2, true), "Existe elementos no array vazio"); },
				() -> { assertEquals(-1, bools.indexOf(b3, true), "Existe o elemento"); }
				);
	}


	@Test
	void testIndexOfBooleanArrayBooleanArray() {
		boolean[] b1 = {true, false, false, true,  true};
		boolean[] b2 = {false, false, true};
		boolean[] b3 = {};
		boolean[] b4 = {true, false, false, false};
		
		assertAll(
				() -> { assertEquals(-1, bools.indexOf(b1, b3), "Target nao esta nulo"); },
				() -> { assertEquals(-1, bools.indexOf(b3, b1), "Há elementos semelhantes com o array vazio"); },
				() -> { assertEquals(0, bools.indexOf(b1, b1), "Iguais nao possuem sequencia parecida"); },
				() -> { assertEquals(0, bools.indexOf(b1, b4), "Não achou b4 em b1"); },
				() -> { assertEquals(1, bools.indexOf(b1, b2), "Não achou b2 em b1"); },
				() -> { assertThrows(NullPointerException.class, ()->{bools.indexOf(null, b1);}, "não lançou Exception"); },
				() -> { assertThrows(NullPointerException.class, ()->{bools.indexOf(b1, null);}, "não lançou Exception"); }
				);
	}

	@Test
	void testLastIndexOfBooleanArrayBoolean() {
		boolean[] b1 = {true, true, true, false, false, false, true, true};
		boolean[] b2 = {};
		boolean[] b3 = {false, false, false, false, false};
		assertAll(
				() -> { assertEquals(b1.length-1, bools.lastIndexOf(b1, true), "Não achou true no array"); },
				() -> { assertEquals(b1.length-3, bools.lastIndexOf(b1, false), "Não achou false no array"); },
				() -> { assertEquals(-1, bools.lastIndexOf(b2, true), "Existe elementos no array vazio"); },
				() -> { assertEquals(-1, bools.lastIndexOf(b3, true), "Existe o elemento"); }
				);
	}

	@Test
	void testConcat() {
		boolean[] b1 = {true, false};
		boolean[] b2 = {false, true, true};
		boolean[] b3 = {};
		boolean[] conc1 = {true, false, false, true, true};
		boolean[] conc2 = {false, true, true, true, false};
		assertAll(
				() -> { assertArrayEquals(conc1, bools.concat(b1, b2), "Nao uniu certo b1 e b2"); },
				() -> { assertArrayEquals(conc2, bools.concat(b2, b1), "Nao uniu certo b2 e b1"); },
				() -> { assertArrayEquals(b1, bools.concat(b1, b3), "O vazio alterou b1"); },
				() -> { assertArrayEquals(b3, bools.concat(b3, b3), "vazio nao é mais vazio"); }
				);
	}

	@Test
	void testToArray() {
		ArrayList<Boolean> b = new ArrayList<Boolean>();
		b.add(true);
		b.add(false);
		b.add(true);
		b.add(true);
		b.add(false);
		b.add(false);
		boolean[] b2 = {true, false, true, true, false, false};
		BooleanArrayAsList baal = new BooleanArrayAsList(b2, new BoolsMockObj());
		
		assertAll(
				() -> { assertArrayEquals(b2, bools.toArray(b), "Não sairam iguais"); },
				() -> { assertThrows(NullPointerException.class, () -> { bools.toArray(null); }, "Não lançou NullPointerException"); },
				() -> { assertArrayEquals(b2, bools.toArray(baal), "Não estao igauais para o BooleanArrayAsList"); }
				);	
	}

	@Test
	void testAsList() {
		ArrayList<Boolean> b = new ArrayList<Boolean>();
		b.add(false);
		b.add(false);
		b.add(false);
		b.add(true);
		ArrayList<Boolean> b2 = new ArrayList<Boolean>();
		b2.add(true);
		assertAll(
				() -> { assertEquals(b, bools.asList(false, false, false, true), "Não retornou igual"); },
				() -> { assertNotEquals(b, bools.asList(false, false, true, true), "Retornou igual"); },
				() -> { assertEquals(new ArrayList<Boolean>(), bools.asList(), "Retornou não vazio"); },
				() -> { assertEquals(b2, bools.asList(true), "Retornou vazio"); }, //testando possivel bug para length == 1
				() -> { assertThrows(NullPointerException.class, () -> { bools.asList(null); }, "Não lançou NullPointerException"); }
				);
	}

	@Test
	void testCountTrue() {
		boolean[] b = {true, true, true, true, true, true, true, true, true, true, true, true}; //12 trues
		assertAll(
				() -> { assertEquals(4, bools.countTrue(true, false, false, true, true, true), "Não contou corretamente"); },
				() -> { assertEquals(0, bools.countTrue(false, false, false), "Não contou corretamente"); },
				() -> { assertEquals(0, bools.countTrue(), "Não contou corretamente"); },
				() -> { assertEquals(12, bools.countTrue(b), "Não contou corretamente"); }
				);		
	}

	@Test
	void testCheckNotNull() {
		Object o = new Object();
		assertAll(
				() -> { assertEquals(bools, bools.checkNotNull(bools), "Não são iguais"); },
				() -> { assertThrows(NullPointerException.class, ()->{bools.checkNotNull(null);}, "não lançou Exception"); }
				);
	}

	@Test
	void testCheckElementIndex() {
		assertAll(
				() -> { assertThrows(IndexOutOfBoundsException.class, () -> {bools.checkElementIndex(-5, 10, "Array");}, "Não lançou Exception"); },
				() -> { assertThrows(IllegalArgumentException.class, () -> {bools.checkElementIndex(1, -10, "Array");}, "Não lançou Exception"); },
				() -> { assertThrows(IndexOutOfBoundsException.class, () -> {bools.checkElementIndex(50, 10, "Array");}, "Não lançou Exception"); },
				() -> { assertEquals(3, bools.checkElementIndex(3, 10, "Array"));}
				);
	}
}
