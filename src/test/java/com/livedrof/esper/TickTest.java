package com.livedrof.esper;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.livedrof.esper.vo.Tick;
import org.junit.Test;

public class TickTest {
    @Test
    public void test() {
        //The Configuration is meant only as an initialization-time object.
        Configuration cepConfig = new Configuration();
        // We register Ticks as objects the engine will have to handle
        cepConfig.addEventType("StockTick", Tick.class.getName());

        // We setup the engine
        EPServiceProvider cep = EPServiceProviderManager.getProvider("myCEPEngine", cepConfig);
    }
}
