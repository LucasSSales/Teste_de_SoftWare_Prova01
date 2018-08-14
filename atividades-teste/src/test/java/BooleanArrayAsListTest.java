import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ufal.ic.atividades.teste.BooleanArrayAsList;
import br.ufal.ic.atividades.teste.BoolsMockObj;

class BooleanArrayAsListTest {
	
	boolean[] array = {true, false, false, true, false, true};
	BooleanArrayAsList baal1 = new BooleanArrayAsList(array, new BoolsMockObj());
	BooleanArrayAsList baal2 = new BooleanArrayAsList(array, 1, 4, new BoolsMockObj());
	
	@Test
	void testBooleanArrayAsListBooleanArrayBoolsMockObj() {
		assertNotNull(baal1, "baal1 é nulo");
	}

	@Test
	void testBooleanArrayAsListBooleanArrayIntIntBoolsMockObj() {
		assertNotNull(baal2, "baal2 é nulo");
	}

	@Test
	void testHashCode() {
		System.out.println(baal1.hashCode());
		System.out.println(baal2.hashCode());
	}

	@Test
	void testSize() {
		
		assertAll(
				() -> { assertEquals(6, baal1.size(), "tamanho incorreto"); },
				() -> { assertEquals(3, baal2.size(), "tamanho incorreto"); }//
				);
		
	}

	@Test
	void testIsEmpty() {
		boolean[] b = {};
		BooleanArrayAsList baal3 = new BooleanArrayAsList( b , new BoolsMockObj());
		assertAll(
				() -> { assertFalse(baal1.isEmpty(), "o array esta vazio"); },
				() -> { assertTrue(baal3.isEmpty(), "o array nao esta vazio"); }
				);
	}

	@Test
	void testGetInt() {
		assertAll(
				() -> { assertEquals( array[2], baal1.get(2) ); },
				() -> { assertEquals( array[3], baal2.get(2) ); },
				() -> { assertThrows(IndexOutOfBoundsException.class , () -> {	baal1.get(100);}  ); },
				() -> { assertThrows(IndexOutOfBoundsException.class , () -> {	baal1.get(-10);}  ); }
				);
	}

	@Test
	void testContainsObject() {
		boolean[] b = {true, true, true};
		BooleanArrayAsList baal3 = new BooleanArrayAsList( b , new BoolsMockObj());
		assertAll(
				() -> { assertTrue(baal1.contains(false), "Contem false"); },
				() -> { assertTrue(baal1.contains(true), "Contem true"); },
				() -> { assertTrue(baal2.contains(false), "Contem false"); },
				() -> { assertTrue(baal2.contains(true), "Contem true"); },
				() -> { assertFalse(baal1.contains( new Object() ), "Object é instancia de Boolean"); },
				() -> { assertFalse( baal3.contains(false), "Contém False"); }
				);
	}

	@Test
	void testIndexOfObject() {
		boolean[] b = {true, true, true};
		BooleanArrayAsList baal3 = new BooleanArrayAsList( b , new BoolsMockObj());
		assertAll(
				() -> { assertEquals(1, baal1.indexOf(false), "Index errado 1"); },
				() -> { assertEquals(0, baal1.indexOf(true), "Index errado 2"); },
				() -> { assertEquals(0, baal2.indexOf(false), "Index errado 3"); },
				() -> { assertEquals(2, baal2.indexOf(true), "Index errado 4"); },
				() -> { assertEquals(-1, baal1.indexOf(new Object()), "Object é instancia de Boolean"); },
				() -> { assertEquals(-1, baal3.indexOf(false), "Contém False"); }
				);
	}

	@Test
	void testLastIndexOfObject() {
		boolean[] b = {true, true, true};
		BooleanArrayAsList baal3 = new BooleanArrayAsList( b , new BoolsMockObj());
		boolean[] b2 = {true, false, false};
		BooleanArrayAsList baal4 = new BooleanArrayAsList( b2 , new BoolsMockObj());
		assertAll(
				() -> { assertEquals(4, baal1.lastIndexOf(false), "Index errado 1"); },
				() -> { assertEquals(5, baal1.lastIndexOf(true), "Index errado 2"); },
				() -> { assertEquals(1, baal2.lastIndexOf(false), "Index errado 3"); },
				() -> { assertEquals(2, baal2.lastIndexOf(true), "Index errado 4"); },
				() -> { assertEquals(2, baal4.lastIndexOf(false), "Index errado 5"); },
				() -> { assertEquals(0, baal4.lastIndexOf(true), "Index errado 6"); }, //testando qnd a ultima eh so no começo
				() -> { assertEquals(-1, baal1.lastIndexOf(new Object()), "Object é instancia de Boolean"); },
				() -> { assertEquals(-1, baal3.lastIndexOf(false), "Contém False"); }
				);
	}

	@Test
	void testSetIntBoolean() {
		boolean[] b1 = {true, false, true, true, false, true};
		boolean[] b2 = {true, false, false, false, false, true};
		assertAll(
				() -> { assertEquals( array[2], baal1.set(2, true) ); },
				() -> { assertEquals( array[3], baal2.set(2, false) ); },
				() -> { assertThrows(IndexOutOfBoundsException.class , () -> {	baal1.set(100, false);}  ); },
				() -> { assertThrows(IndexOutOfBoundsException.class , () -> {	baal1.set(-10, true);}  ); },
				() -> { assertThrows(NullPointerException.class , () -> {	baal1.set(2, null);}  ); }
				);
	}

	@Test
	void testEqualsObject() {
		BooleanArrayAsList bb = new BooleanArrayAsList(array, new BoolsMockObj());
		assertAll(
				() -> { assertTrue(baal1.equals(baal1), "Não sao igauis"); },
				() -> { assertFalse(baal1.equals(baal2), "Sao igauis"); },
				() -> { assertFalse(baal1.equals(new Object()), "Sao igauis"); },
				() -> { assertTrue(baal1.equals(bb), "Não sao igauis"); }
				);
	}


}
