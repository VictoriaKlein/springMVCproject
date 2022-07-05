package com.victoriaKlein.spring.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victoriaKlein.spring.model.MagicSquare;
import org.springframework.util.MultiValueMap;

import java.util.*;

public class MagicSquareService {
 static Gson gson = new GsonBuilder().create();

    //iterating through list of magicSquares, adding result of comparison with given array to a list
    // to sort the collection and find the min (for return corresponding array was used map with key-value pair)
    public static Multimap<String, int [][]> findSquareWithMinCost (List<int[][]>arraysList, int[][]ms){
//        List <int[][]> resultList = new ArrayList<>();
//        //let's create a variable which could contain the latest result of comparison of existing and incoming array
//        //now assign the result of the first iteration through existing array
//       int result = getMagicSquareBest(arraysList.get(0), ms);
//       int j=0;
//        Map<Integer,int[][]> wow = new HashMap<>();
//        System.out.println("incoming ms " + Arrays.deepToString(ms));
//        for (int i =0; i<arraysList.size(); i++) {
//            System.out.println(" at start " + Arrays.deepToString(resultList.toArray()));
//            //    result = getMagicSquareBest(arraysList.get(0), ms);
//                System.out.println("result at start " + result);
//                //if result coming from the function getMagicSquareBest equal to result (for the iteration it will always be like that)
//                if (getMagicSquareBest(arraysList.get(i), ms) == result) {
//                    //add array with min sumnumber to resultList
//                    resultList.add(arraysList.get(i));
//                    //variable for counting elements in resultList
//                    j=resultList.size()-1;
//                } else if (getMagicSquareBest(arraysList.get(i), ms) < result) {
//                    //if result coming from the function getMagicSquareBest less than result let's reassign new value to result
//                    result = getMagicSquareBest(arraysList.get(i), ms);
//                    //remove previous array from resultList and add new one
//                  do {resultList.remove(j);}
//                          while(j>=0&&getMagicSquareBest(arraysList.get(i), ms) < result);
//                    resultList.add(arraysList.get(i));
//                }
//                //if result coming from the function getMagicSquareBest greater than result do nothing and go to the next iteration
//            }
//        System.out.println("result: " + Arrays.deepToString(resultList.toArray()) + "with min number " + result);
//another way to find all possible best matches using Guava Multimap
        Multimap<String,int [][]> resultMap = ArrayListMultimap.create();
        for (int [][] array : arraysList) {resultMap.put(String.valueOf(getMagicSquareBest(array, ms)), array);}
        //keys are already sorted, just find first same min numbers and remove others that differ from the first one
        String firstkey = resultMap.keys().stream().findFirst().get();
        resultMap.keys().removeIf(key->(!key.equals(firstkey)));
        return resultMap;
    }
    //function to compare each value of two-dimensional array: from input and from given int arraysList of magicSquares
    public static int getMagicSquareBest (int[][] arr, int[][]ms){
        int r;
        int c;
        int sumTotal = 0;
        for (r = 0; r < ms.length; r++) {
            for (c = 0; c < ms[r].length; c++) {
                int difference = Math.abs(ms[r][c] - arr[r][c]);
                sumTotal += difference;
                System.out.println("sum " + sumTotal);
            }
        }
        return sumTotal;
    }
    //converting list of strings from DB
    public static Multimap<String, int[][]> convertFromStringToArray(List<MagicSquare> magicSquares, int[][]inputArray){
        List<int[][]> arraysList = new ArrayList<>();
        for(MagicSquare square: magicSquares){
            int[][] magicSquareVariantArray = gson.fromJson(square.getMagicSquareVariant(), int[][].class);
            arraysList.add(magicSquareVariantArray);
        }
        //let's execute the logic by calling method that compares incoming array and arrayList og magicSquares got from DB
      return   findSquareWithMinCost(arraysList,inputArray);
    }
    //method for returning list of magic squares presented as list of two-dimensional arrays

}
