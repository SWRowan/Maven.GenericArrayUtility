package com.zipcodewilmington.arrayutility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<I> {
    private I[] input;


    ArrayUtility(I[] input) {
        this.input = input;
    }


    public Integer countDuplicatesInMerge(I[] arrayToMerge, I valueToEvaluate) {
        Stream<I> concatStream = merge(arrayToMerge);

        Long answer = concatStream.filter(x -> x.equals(valueToEvaluate))
                .count();

        return answer.intValue();
    }


    public I getMostCommonFromMerge(I[] arrayToMerge) {
        Stream<I> merged = merge(arrayToMerge);
        I[] array = arrayConverter(merged.toArray());
        I mostCommon = array[0];
        int counter = 1;
        for (int i = 0; i < array.length - 1; i++){
            I testObj = array[i];
            int numberOfOccur = getNumberOfOccurrences(testObj);
            if (numberOfOccur > counter){
                mostCommon = testObj;
                counter = numberOfOccur;
            }
        }
        return mostCommon;
    }


    public Integer getNumberOfOccurrences(I valueToEvaluate) {
        Long answer = Arrays.stream(input)
                .filter(x -> x.equals(valueToEvaluate))
                .count();
        return answer.intValue();
    }


    public I[] removeValue(I valueToRemove) {
        Object[] arr = Arrays.stream(input)
                .filter(x -> !x.equals(valueToRemove))
                .toArray();
        return arrayConverter(arr);
    }


    public I[] arrayConverter(Object[] array) {
        I[] newArr = Arrays.copyOfRange(input, 0, array.length);
        for (int i = 0; i < array.length; i++) {
            newArr[i] = ((I) array[i]);
        }
        return newArr;
    }


    public Stream<I> merge(I[] merge) {
        Stream<I> stream1 = Stream.of(merge);
        Stream<I> stream2 = Stream.of(input);
        Stream<I> concatStream = Stream.concat(stream1, stream2);
        return concatStream;
    }

    
}
