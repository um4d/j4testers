package ru.stqa.pft.mantis.tests;

import org.subethamail.wiser.Wiser;

import java.util.stream.Collectors;

public class sandbox {
    public static void main(String[] args) throws InterruptedException {
        Wiser wiser = new Wiser();
        wiser.setPort(1024);
        wiser.start();
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + 20000) {
            System.out.println(wiser.getMessages().size());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        wiser.stop();
    }
}

