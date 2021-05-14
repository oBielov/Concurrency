package builder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class to build input string for WaterBuilder realisation.
 * {@link #buildChain(int)} produce String filled with "O" and "H" chars.
 * @see WaterBuilder
 */
public class InputTank {


    /**
     * Builds two lists of "H" and "O". Concats them. Number of "H" elements
     * will always be twice as "O" elements.
     * @param molecules number of chains "HHO" to produce
     * @return String of "H" and "O" chars with condition that n(H) will be n(O)*2
     */
    public static String buildChain(int molecules){
        List<String> oxygen = Collections.nCopies(molecules, "O");
        List<String> hydrogen = Collections.nCopies(molecules*2, "H");
        return Stream.of(oxygen, hydrogen )
                .flatMap(Collection::stream)
                .collect(Collectors.joining());
    }



}
