package com.easy.taifa.easyPwani;

import java.util.ArrayList;
import java.util.List;

public class ReverseList {

    public ReverseList(){

    }

    public List reverseList(List list){
        List reverseList = new ArrayList();
        int lastP= list.size()-1;
        if(!list.isEmpty()) {
            do {
                reverseList.add(list.get(lastP));
                lastP--;

            } while (lastP>-1);
        }

      return reverseList;
    }
}
