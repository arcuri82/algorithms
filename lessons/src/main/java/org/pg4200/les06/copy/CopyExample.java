package org.pg4200.les06.copy;

public class CopyExample {

    public static String[] copyWrong(String[] input){
        if(input == null){
            return null;
        }
        /*
            We have a new variable, pointing to same array.
            Any change in this array will be seen by the input one as well.
         */
        String[] output = input;
        return output;
    }

    public static String[] copy(String[] input){
        if(input == null){
            return null;
        }

        //create a new array
        String[] output = new String[input.length];
        for(int i=0; i<output.length; i++){
            /*
                Strings are immutable. So it is fine that both
                arrays points to exactly the same String objects
             */
            output[i] = input[i];
        }

        return output;
    }


    public static String[][] copyWrong(String[][] input){
        if(input == null){
            return null;
        }

        String[][] output = new String[input.length][];
        for(int i=0; i<output.length; i++){
            /*
                Although Strings are immutable, an array of String
                is NOT immutable
             */
            output[i] = input[i];
        }
        return output;
    }

    public static String[][] copy(String[][] input){
        if(input == null){
            return null;
        }

        String[][] output = new String[input.length][];
        for(int i=0; i<output.length; i++){
            /*
                For each row in the matrix, we need to make
                a copy.
             */
            output[i] = copy(input[i]);
        }
        return output;
    }

}
