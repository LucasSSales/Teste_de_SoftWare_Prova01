package br.ufal.ic.atividades.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

public class BoolsMockObj extends Bools {
	
	//APENAS AS FUNÇÕES ONDE FORAM ENCONTRADAS BUGS QUE SÃO CHAMADAS EM BoolsArrayAsList
	
	private boolean[] b1 = {true, false, false, true, false, true};
	
	@Override
	public int lastIndexOf(boolean[] array, boolean target, int start, int end) {
		boolean[] b2 = {true, false, false};
		
		if(Arrays.equals(array, b1) && start == 0) {
			if(target == false)
				return 4;
			return 5;
		}
		
		if(Arrays.equals(array, b1)) {
			if(target == false)
				return 1 + start;
			return 2 + start;
		}
		
		if(Arrays.equals(array, b2)) {
			if(target == false)
				return 2;
			return 0;
		}
		
		return -1;
	}
	
	@Override
	int indexOf(boolean[] array, boolean target, int start, int end) {
		if(Arrays.equals(array, b1) && start == 0) {
			if(target == false)
				return 1;
			return 0;
		}
		
		if(Arrays.equals(array, b1)) {
			if(target == false)
				return 0 + start;
			return 2 + start;
		}
				
		return -1;
	}


}
