package com.url.shortening.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class IDConverter {

    public static final IDConverter INSTANCE = new IDConverter();
    private static HashMap<Character,Integer> charToIndexTable;
    private static List<Character> indexToCharTable;

    private IDConverter(){
        initializeCharToIndexTable();
        initializeIndexToCharTable();
    }

    public static String createUniqueID(Long id){
      LinkedList<Integer> base62ID = convertBase10ToBase62ID(id);
      StringBuilder uniqueURLID = new StringBuilder();
      for(int digit : base62ID){
          uniqueURLID.append(indexToCharTable.get(digit));
      }
      return uniqueURLID.toString();
    }

    public static Long getDictionaryKeyFromUniqueID(String uniqueID){
        List<Character> base62ID = new ArrayList<>();
        for(int i = 0; i < uniqueID.length(); i++){
            base62ID.add(uniqueID.charAt(i));
        }
        Long dictionaryKey = convertBase62ToBase10ID(base62ID);
        return dictionaryKey;
    }

    private static Long convertBase62ToBase10ID(List<Character> ids){
       long id = 0L;
       for(int i = 0,exp = ids.size() - 1; i < ids.size(); i++,exp--){
           int base10 = charToIndexTable.get(ids.get(i));
           id += (base10 * Math.pow(62.0,exp));
       }
       return id;
    }

    private static LinkedList<Integer> convertBase10ToBase62ID(Long id){
        LinkedList<Integer> digits = new LinkedList<>();
        while(id > 0){
            int remainder = (int)(id % 62);
            digits.addFirst(remainder);
            id /= 62;
        }
        return digits;
    }

    private void initializeCharToIndexTable(){
        // 0->a, 1->b, ..., 25->z, ..., 52->0, 61->9
        charToIndexTable = new HashMap<>();
        for(int i = 0; i < 26; i++){
            char c = 'a';
            c += i;
            charToIndexTable.put(c,i);
        }
        for(int i = 26; i < 52; i++){
            char c = 'A';
            c += (i-26);
            charToIndexTable.put(c,i);
        }
        for(int i = 52; i < 62; i++){
            char c = '0';
            c += (i-52);
            charToIndexTable.put(c,i);
        }
    }

    private void initializeIndexToCharTable(){
        // 0->a, 1->b, ..., 25->z, ..., 52->0, 61->9
        indexToCharTable = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            char c = 'a';
            c += i;
            indexToCharTable.add(c);
        }
        for(int i = 26; i < 52; i++){
            char c = 'A';
            c += (i-26);
            indexToCharTable.add(c);
        }
        for(int i = 52; i < 62; i++){
            char c = '0';
            c += (i-52);
            indexToCharTable.add(c);
        }

    }
}
