package org.pg4200.les09.search;

// WARNING: this is one of the 12 classes you need to study and know by heart


/**
 * Knuth-Morris-Pratt substring search
 * <p>
 * Created by arcuri82 on 04-May-18.
 */
public class TextSearchKMP implements TextSearch {

    /*
        To make the search more efficient, we create a supporting
        data structure based on the defaultTarget.
        We build a "deterministic finite-state automaton" (DFA),
        which can be represented by a matrix.

        The matrix is in the form [c][j]:
        c -> is a character (there are 2^16 due to UTF-16)
        j -> the position in the target we are evaluating (so there are target.length())
        [c][j] -> what element in the target we should compare next when scanning
                  the next char in the text.

        Note: this could be optimized, as there is a LOT of wasted space for the chars
        not in the target, eg by using a Map instead of a matrix.
     */
    private final int[][] nextCheck;

    protected final String defaultTarget;

    public TextSearchKMP() {
        nextCheck = null;
        defaultTarget = null;
    }

    public TextSearchKMP(String target) {

        if (target == null || target.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        defaultTarget = target;

        /*
            initialize the nextCheck matrix.

            Note: 65_536 == 2^16 (2 at the power of 16)
            Also note that in Java (and in other programming languages)
            the ^ operator is not an exponent, but rather a XOR.

            Note: when created, the matrix will contain all 0s
         */
        nextCheck = new int[65_536][target.length()];

        /*
            nextCheck[target.charAt(j)][j] = j + 1;
            should always hold, also for j=0.
            As in the loop we start from j=1, we need
            to have this assignment here before the loop.

            Point is, for j==0 (ie when we are trying to match the first
            char in the target), in case of mismatch we should always stay
            on the first position j==0, so we keep the default
            [c][0] = 0
            but for the case c==target.chartAt(0)
            And this also why the loop starts from j=1
         */
        nextCheck[target.charAt(0)][0] = 1;

        for (int x = 0, j = 1; j < target.length(); j++) {

            /*
                initialize all possible chars for the current analysed position j
                in the target.

                x<j represents the "restart" index in the target.
                so when we have a mismatch, the next j to look at would be the
                one after the restart, which is nextCheck[c][x].
                In other words, even if c at position j is a mismatch, it could be
                a valid char for a match starting between 1 and j-1.

                Note: in the very first loop (ie, j==1) we have x==0, and so
                we get [c][1]=0 but in one case where [c][1]=1 for c==target.chartAt(0)
             */
            assert x < j;

            for (int c = 0; c < nextCheck.length; c++) {

                nextCheck[c][j] = nextCheck[c][x];
            }

            /*
                should always hold.
                When we are looking at position j in the target and
                we have a match with the character in the text (i.e, c==target.charAt(j)),
                then we move to the next position in the target, which is j+1
             */
            nextCheck[target.charAt(j)][j] = j + 1;

            /*
                The current char at j could be a valid char for a partial
                match starting after 0. So, for the next loop, we update
                x by looking at what would be next in that partial match
             */
            x = nextCheck[target.charAt(j)][x];
        }
    }


    @Override
    public int findFirst(String text) throws IllegalStateException {

        if (defaultTarget == null || nextCheck == null) {
            throw new IllegalStateException("No default target specified");
        }

        int i, j;

        for (i = 0, j = 0; i < text.length() && j < defaultTarget.length(); i++) {

            int before = j;  //only needed for the assertion

            j = nextCheck[text.charAt(i)][j];

            //go backward, or move forward by at most 1
            assert j >= 0 && j <= before + 1;

            /*
                Note, this loop ends in two cases:
                1) we scan the whole text with no match, and we get i==text.length()
                2) nextCheck[text.charAt(i)][j] == defaultTarget.length()
                   which means we are looking at the last position j==defaultTarget.length()-1
                   and there is a match (recall property nextCheck[target.charAt(j)][j] = j + 1).
                   Also note that j, in contrast to i, is not incremented with j++ at each iteration
             */
        }

        if (j == defaultTarget.length()) {
             /*
                if true, it means the loop ended with a match before reaching the end of the text.
                But the variable i would point to the position of the last char of the target
                in the text. So we need to subtract the length of the target itself to get
                its starting point in the text.
              */
            return i - defaultTarget.length();
        }

        return -1;
    }

    @Override
    public int findFirst(String text, String target) {

        /*
         * Note: this can be quite inefficient for small texts/targets...
         * building a "nextCheck" data structure has an overhead... if we throw it
         * away at the first search, then it could be faster to just do a brute
         * force search.
         *
         * Note: we will see in the exercises how this can be improved.
         */
        TextSearchKMP textSearchKMP = new TextSearchKMP(target);

        return textSearchKMP.findFirst(text);
    }
}
