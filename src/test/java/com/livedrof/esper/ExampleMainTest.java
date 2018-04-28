package com.livedrof.esper;

import com.espertech.esper.client.*;
import com.livedrof.esper.listener.CEPListener;
import com.livedrof.esper.vo.Tick;
import org.junit.Test;

import static com.livedrof.esper.vo.ExampleMain.GenerateRandomTick;

public class ExampleMainTest {
    @Test
    public void test() {
        Configuration cepConfig = new Configuration();
        cepConfig.addEventType("StockTick", Tick.class.getName());
        EPServiceProvider cep = EPServiceProviderManager.getProvider("myCEPEngine", cepConfig);
        EPRuntime cepRT = cep.getEPRuntime();

        // We register an EPL statement
        EPAdministrator cepAdm = cep.getEPAdministrator();
        EPStatement cepStatement = cepAdm.createEPL("select * from " +
                "StockTick(symbol='AAPL').win:length(2) " +
                "having avg(price) > 6.0");
        cepStatement.addListener(new CEPListener());

        for (int i = 0; i < 5; i++)
            GenerateRandomTick(cepRT);
    }
}
