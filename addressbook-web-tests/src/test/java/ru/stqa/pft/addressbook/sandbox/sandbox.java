package ru.stqa.pft.addressbook.sandbox;

import com.google.gson.reflect.TypeToken;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class sandbox {

    static List<GroupData> list;

    public static void main(String[] args) {
        System.out.println(new TypeToken<List<GroupData>>(){}.getType());
    }

}
