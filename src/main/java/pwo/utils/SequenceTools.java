package pwo.utils;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SequenceTools {

    private static String getTerms(SequenceGenerator sg, int from, int to, String sep){
        boolean reversed = from > to ? true : false;
        if (reversed) {
            int temp = from;
            from = to;
            to = temp;
        }
        int i = from, stop = to;
        ArrayList<String> terms = new ArrayList<String>();
        while (true) {
            terms.add(sg.getTerm(i).toString());
            if (i == stop){
                if (reversed) {
                    Collections.reverse(terms);
                }
                return String.join(sep, terms);
            }
            i += 1;
        }
    }

    public static String getTermsAsColumn(SequenceGenerator sg, int from, int to){
        return getTerms(sg, from, to, "\n");
    }

    public static String getTermsAsLine(SequenceGenerator sg, int from, int to){
        return getTerms(sg, from, to, " ");
    }

    public static boolean writeToFile(SequenceGenerator sg,int from, int to, String fileName){
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(getTermsAsColumn(sg, from, to));
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
}