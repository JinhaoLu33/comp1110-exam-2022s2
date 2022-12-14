package comp1110.exam;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The class StringPattern represents a string pattern, made up of constants
 * (strings) and two binary operators: concatenation (abbreviated +) and
 * choice (abbreviated |). The class is abstract: any StringPattern instance
 * must be an instance of one of the two subclasses Constant or Operator,
 * defined below.
 * <p>
 * You must implement the `equals` and `hashCode` methods of the two
 * subclasses.
 * <p>
 * Two StringPatterns are equal if they represent the exact same expression,
 * including ordering and grouping of terms. For example, "a" | "b" and
 * "b" | "a" are not considered equal, even if they generate the same
 * set of strings.
 * <p>
 * Your hash functions do not have to be perfect, but they should be
 * non-trivial (hashing a collection of different StringPatterns should yield
 * a number of different hash values, although not necessarily as many
 * as the number of StringPatterns hashed).
 * <p>
 * Remember that two objects that are equal must have the same hash
 * value.
 */
public abstract class Q6StringPattern {

    /*
     * Returns the list of strings generated by the pattern.
     */
    public abstract List<String> generate();

}

class StringConstant extends Q6StringPattern {
    String value;

    StringConstant(String value) {
        this.value = value;
    }

    public List<String> generate() {
        List<String> set = new ArrayList<String>();
        set.add(value);
        return set;
    }

    public boolean equals(Object other) {
        // FIXME
        if (other instanceof StringConstant){
            return Objects.equals(this.value,((StringConstant) other).value);
        }
        return false;
    }

    public int hashCode() {
        int code = 17;
        char[] cArray;
        if (value != null) {
            cArray = value.toCharArray();
            if (cArray.length > 0) {
                for (char c : cArray) {
                    code = 33 * code + c;
                }
            }
        }

        return code;
        // FIXME
    }
}

enum StringOperatorType {
    CONCATENATION, CHOICE;

    public String toString() {
        switch (this) {
            case CONCATENATION:
                return "+";
            case CHOICE:
                return "|";
        }
        return "?";
    }
}

class StringOperator extends Q6StringPattern {
    StringOperatorType type;
    Q6StringPattern first;
    Q6StringPattern second;

    StringOperator(StringOperatorType type, Q6StringPattern first, Q6StringPattern second) {
        this.type = type;
        this.first = first;
        this.second = second;
    }

    public List<String> generate() {
        List<String> set1 = first.generate();
        List<String> set2 = second.generate();
        List<String> set = new ArrayList<String>();
        switch (type) {
            case CONCATENATION:
                for (var s1 : set1)
                    for (var s2 : set2)
                        set.add(s1 + s2);
                break;
            case CHOICE:
                for (var s1 : set1)
                    set.add(s1);
                for (var s2 : set2)
                    set.add(s2);
        }
        return set;
    }

    public boolean equals(Object other) {
        if (other instanceof StringOperator){
            return Objects.equals(this.type,((StringOperator) other).type)&&Objects.equals(this.first,((StringOperator) other).first)&&Objects.equals(this.second,((StringOperator) other).second);
        }
        // FIXME
        return false;
    }

    public int hashCode() {
        int code = 17;
        char[] cArray;
        if (type!=null){
            cArray=type.toString().toCharArray();
            if (cArray.length>0){
                for (char c : cArray) {
                    code = 33 * code + c;
                }
            }
        }
        char[] cArray2;
        if (first!=null){
            cArray2=first.toString().toCharArray();
            if (cArray2.length>0){
                for (char c : cArray2) {
                    code = 33 * code + c;
                }
            }
        }
        char[] cArray3;
        if (second!=null){
            cArray3=second.toString().toCharArray();
            if (cArray3.length>0){
                for (char c : cArray3) {
                    code = 33 * code + c;
                }
            }
        }
        return code;
        // FIXME

    }
}
