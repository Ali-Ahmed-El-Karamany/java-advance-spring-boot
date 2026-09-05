package com.pioneers.dsa.datastructures.MyList;

public class MyArrayListDemo {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(null);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        System.out.println(list);

        list.remove(null);
        list.remove(Integer.valueOf(7));
        System.out.println(list);

        list.remove(1);
        System.out.println(list);


        System.out.println("list.get(4) = " + list.get(4));

        list.set(0, 0);

        System.out.println(list);

        if (!list.isEmpty()) {
            list.clear();
        }

        System.out.println(list);
    }
}
