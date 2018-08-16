package br.ufal.ic.atividades.teste;

//import static br.ufal.ic.atividades.teste.Bools.checkElementIndex;
//import static br.ufal.ic.atividades.teste.Bools.checkNotNull;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;

public class BooleanArrayAsList extends AbstractList<Boolean>
        implements RandomAccess, Serializable {

    final boolean[] array;
    final int start;
    final int end;
    private BoolsMockObj bmo;

    public BooleanArrayAsList(boolean[] array, BoolsMockObj bmo) { //deixando public
        this(array, 0, array.length, bmo);
    }

    public BooleanArrayAsList(boolean[] array, int start, int end, BoolsMockObj bmo) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.bmo = bmo;
    }

    @Override
    public int size() {
        return end - start;
    }

    @Override
    public boolean isEmpty() { //alterado para o caso do array ser vazio
        if(array.length == 0)
        	return true;
    	return false;
    }

    @Override
    public Boolean get(int index) {
        bmo.checkElementIndex(index, size(), "index");
        return array[start + index];
    }

    @Override
    public boolean contains(Object target) {
        // Overridden to prevent a ton of boxing
        return (target instanceof Boolean)
                && bmo.indexOf(array, (Boolean) target, start, end) != -1; //mudando de Bools para bmo e end-1 tirado 
    }

    @Override
    public int indexOf(Object target) { 
        // Overridden to prevent a ton of boxing
        if (target instanceof Boolean) {
            int i = bmo.indexOf(array, (Boolean) target, start, end); //mudando de Bools para bmo e end-1 tirado
            if (i >= 0) {
                return i - start;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object target) {
        // Overridden to prevent a ton of boxing
        if (target instanceof Boolean) {
            int i = bmo.lastIndexOf(array, (Boolean) target, start, end);
            if (i >= 0) {
                return i - start;
            }
        }
        return -1;
    }

    @Override
    public Boolean set(int index, Boolean element) {
        bmo.checkElementIndex(index, size(), "index");
        boolean oldValue = array[start + index];
        // checkNotNull for GWT (do not optimize)
        array[start + index] = bmo.checkNotNull(element);
        return oldValue;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof BooleanArrayAsList) {
            BooleanArrayAsList that = (BooleanArrayAsList) object;
            int size = size();
            if (that.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (array[start + i] != that.array[that.start + i]) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(object);
    }

    @Override
    public int hashCode() {    	
        int result = 1;
        for (int i = start; i < end; i++) {
            result = 31 * result + Boolean.hashCode(array[i]);
        }
        return result;
    }

    @Override
    public String toString() {
    	if(array.length == 0) return "[]"; //corrigindo para arrays vazios
        StringBuilder builder = new StringBuilder(size() * 7);
        builder.append(array[start] ? "[true" : "[false");
        for (int i = start + 1; i < end; i++) {
            builder.append(array[i] ? ", true" : ", false");
        }
        return builder.append(']').toString();
    }

    boolean[] toBooleanArray() {
        return Arrays.copyOfRange(array, start, end);
    }

    private static final long serialVersionUID = 0;
}
