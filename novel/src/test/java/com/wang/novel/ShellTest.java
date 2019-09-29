package com.wang.novel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShellTest {
    @Test
    public void Shell(){
        Integer[] integers = {5,2,1,3,9,8,6,7};
        int d = integers.length;
        while(d>1){
            d=d/2;
            for(int i=0;i<d;i++){
                for(int j=i+d;j<integers.length;j=j+d){
                    int t = integers[j];
                    int x;
                    for(x=j-d;x>=0&&integers[x]>t;x=x-d){
                        integers[x+d]= integers[x];
                    }
                    integers[x+d]=t;
                }
            }
        }
        System.out.println(Arrays.toString(integers));
    }
}
