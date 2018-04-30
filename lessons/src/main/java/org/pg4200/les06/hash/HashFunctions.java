package org.pg4200.les06.hash;

/**
 * A "hash" function "h" is used to map data of arbitrary size (eg string text) to
 * to a value of fixed size (eg, a 32-bit integer).
 * <p>
 * Main properties:
 * <p>
 * 1) deterministic: h(x) should always return same value
 * <p>
 * 2) uniform: given the domain X, values x in X should be spread
 * as uniform as possible when hashed.
 * In other words, if x!=y, we would like h(x)!=h(y).
 * This in general not possible as the size of the domain
 * X would be (much, much) larger than the one of hashed
 * values Z. However, we want the number of "collision" to
 * be as uniform as possible, ie for each z in Z, we would
 * like on average just |X|/|Z| values in X mapping to z.
 * <p>
 * 3) performance: either the h() should be very FAST (eg when used in
 * data structures), or very SLOW (eg in security).
 * We will not cover security in this course, but it
 * is something we will see when dealing with web/enterprise
 * programming in the next semesters.
 * <p>
 * <p>
 * Considerations:
 * <p>
 * - when computing h(x)=z, in general from the z value we cannot recompute
 * x, as there are many different values in X that maps to the same z.
 * This is because |X|>>|Z|, ie the cardinality of the input space is
 * usually much larger than the the cardinality of the set of hashed
 * values.
 * <p>
 * - the input x can be considered as a sequence of bits. When computing
 * an hash, such should be based on all the bits of the inputs, and not
 * just a subset. This help in avoiding collisions.
 * <p>
 * Created by arcuri82 on 07-Sep-17.
 */
public class HashFunctions {

    /*
        Let's consider mapping functions that do map to 32-bit integers.
        The input could be anything, even custom objects.
     */

    public static int nonUniformHash(int x) {
        /*
            Returning a constant is still a valid hash, because
            deterministic.
            However, it is the worst possible case for uniformity,
            as all input values map to the exactly same hash values.
         */
        return 1;
    }

    public static int identityHash(int x) {
        /*
            The input and the output is of the same size (in their bits representation).
            So perfectly fine to just return it.
         */
        return x;
    }

    public static int shiftedHash(int x) {

        /*
            still perfectly fine for a hash function.
            The hashed values doesn't need to have any resemblance
            with the input.
         */

        return x + 100;
    }

    public static int hashLongToInt(long x) {

        /*
            int are 32 bits.
            long are 64 bits.

            So, an approach is to only look at the last 32 bits of the long.
            This can be achieved by doing a bitwise AND operation (&)
            on a 32 bit values with only 1s (ie 0xFF_FF_FF_FF).
            Note the "0x", this means representing a value in hexadecimal notations,
            where each symbol has 16 values: {0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F}.
            Being 16 values, can be represented with 16=2pow4 -> 4 bits, where F (15
            in decimal notation) is 1111 in binary, ie 8+4+2+1=15
            So, 32 bits of 1s is F 8 times, ie, 0xFFFFFFFF.
            When doing computations at bit/byte level, using hexadecimal is
            easier than decimal.

            A further thing to consider: in a int, if you have 32 bits of 1s, because
            the leftmost bit is a 1, then the value is negative, in particular:
            0xFFFFFFFF == -1
            however, in a long, that would be a:
            0xFFFFFFFFl == 4294967295
            ie (2pow32 - 1)

            so we could return

            (x & 0xFF_FF_FF_FFl)

            but that would be a long value, and we need an int.
            So we can cast it to an int with (int)

            (int) (x & 0xFF_FF_FF_FFl)

             but what does it mean to cast a long to int?
             In java, that just takes the rightmost 32-bits...
             so actually we do not need to do & bitmask
         */

        return (int) x;
    }

    public static int hashLongToIntRevised(long x) {
        /*
            This is actually what done in the Java API to
            calculate Long.hashCode().

            All bits of the inputs are used to calculate the hash.

            x >>> 32 does shift all the bits in x by 32 positions to the right.
            Once that is executed, the results will have the 32 leftmost bits
            into the 32 rightmost (which are now discarded).

            With the exclusive OR operation (^), we compare each bit between
            "x" and "x >>> 32", and create a new value based on whether they
            disagree on the 1s (only one of the two values has a 1).

            Recall that the (int) will ignore the 32 leftmost bits.
            So, the resulting integer from the 32 bits is based on a XOR
            between the first and last 32 bits of the original input.

            Why using a XOR? reason is, given any input value x, if we just
            change one single bit, then necessarily we will end up with
            a different hash value.
            Changing 2 or more bits might result in same hash though.
         */
        return (int) (x ^ (x >>> 32));
    }

    public static int hashStringSum(String x) {

       /*
            A string is a sequence of characters.
            This is usually represented as an array of char values.
            In Java, chars are represented with 16-bit numbers in UTF-16 format.
            UTF: Unicode Transformation Format.
            Each number is mapped to a specific character.
            For example the number 65 represents the character 'A'.
        */

        int sum = 0;
        for (int i = 0; i < x.length(); i++) {
            sum += x.charAt(i);
        }
        return sum;
    }


    public static int hashStringSumRevised(String x) {

       /*
           This is equivalent to the actual code in
           String#hashCode()

           Here, before adding, we multiply by a constant delta.
           This is used to avoid (but not prevent) collisions on
           common words.
           The actual value 31 is not random, but based on some properties,
           like being odd (this helps in avoiding some issues when the sum
           overflow).
           It is also efficient to compute. Multiplying by 31 is equivalent
           to multiplying by 32 and then subtract 1 time, ie

           31 * y == (32 * y) -y

           multiplying by a multiple of 2 (note 32==2^5) is equivalent to shift
           its bits to the left, ie

           31 * y = (y << 5) - y

           Compilers can do this kind of optimization.
        */

        final int delta = 31;

        int sum = 0;
        for (int i = 0; i < x.length(); i++) {
            sum = (delta * sum) + x.charAt(i);
        }
        return sum;
    }
}


