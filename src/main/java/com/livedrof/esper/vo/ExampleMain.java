package com.livedrof.esper.vo;

import java.util.Random;

import com.espertech.esper.client.*;

public class ExampleMain {
    private static Random generator = new Random();

    public static void GenerateRandomTick(EPRuntime cepRT) {
        double price = (double) generator.nextInt(10);
        long timeStamp = System.currentTimeMillis();
        String symbol = "AAPL";
        Tick tick = new Tick(symbol, price, timeStamp);
        System.out.println("Sending tick:" + tick);
        cepRT.sendEvent(tick);
    }

    public static void main(String[] args) {
        //The Configuration is meant only as an initialization-time object.
        Configuration cepConfig = new Configuration();
        cepConfig.addEventType("StockTick", Tick.class.getName());

        EPServiceProvider cep = EPServiceProviderManager.getProvider("myCEPEngine", cepConfig);

        EPRuntime cepRT = cep.getEPRuntime();
    }
}