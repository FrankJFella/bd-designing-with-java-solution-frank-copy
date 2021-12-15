package com.amazon.ata.datastore;

import com.amazon.ata.types.Box;
import com.amazon.ata.types.FcPackagingOption;
import com.amazon.ata.types.FulfillmentCenter;
import com.amazon.ata.types.Packaging;
import com.amazon.ata.types.PolyBag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PackagingDatastoreTest {

    FulfillmentCenter ind1 = new FulfillmentCenter("IND1");
    FulfillmentCenter abe2 = new FulfillmentCenter("ABE2");
    FulfillmentCenter yow4 = new FulfillmentCenter("YOW4");
    FulfillmentCenter iad2 = new FulfillmentCenter("IAD2");
    FulfillmentCenter pdx1 = new FulfillmentCenter("PDX1");

    Packaging package10Cm = new Box(BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(10));
    Packaging package20Cm = new Box(BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20));
    Packaging package40Cm = new Box(BigDecimal.valueOf(40), BigDecimal.valueOf(40), BigDecimal.valueOf(40));
    Packaging package60Cm = new Box(BigDecimal.valueOf(60), BigDecimal.valueOf(60), BigDecimal.valueOf(60));
    Packaging package2000Cc = new PolyBag(BigDecimal.valueOf(2000));
    Packaging package5000Cc = new PolyBag(BigDecimal.valueOf(5000));
    Packaging package6000Cc = new PolyBag(BigDecimal.valueOf(6000));
    Packaging package10000Cc = new PolyBag(BigDecimal.valueOf(10000));

    FcPackagingOption ind1_10Cm = new FcPackagingOption(ind1, package10Cm);
    FcPackagingOption abe2_20Cm = new FcPackagingOption(abe2, package20Cm);
    FcPackagingOption abe2_40Cm = new FcPackagingOption(abe2, package40Cm);
    FcPackagingOption yow4_10Cm = new FcPackagingOption(yow4, package10Cm);
    FcPackagingOption yow4_20Cm = new FcPackagingOption(yow4, package20Cm);
    FcPackagingOption yow4_60Cm = new FcPackagingOption(yow4, package60Cm);
    FcPackagingOption iad2_20Cm = new FcPackagingOption(iad2, package20Cm);
    FcPackagingOption pdx1_40Cm = new FcPackagingOption(pdx1, package40Cm);
    FcPackagingOption pdx1_60Cm = new FcPackagingOption(pdx1, package60Cm);
    FcPackagingOption iad2_2000Cc = new FcPackagingOption(iad2, package2000Cc);
    FcPackagingOption iad2_10000Cc = new FcPackagingOption(iad2, package10000Cc);
    FcPackagingOption iad_5000cc = new FcPackagingOption(iad2, package5000Cc);
    FcPackagingOption yow4_2000cc = new FcPackagingOption(yow4, package2000Cc);
    FcPackagingOption yow4_5000cc = new FcPackagingOption(yow4, package5000Cc);
    FcPackagingOption yow4_10000cc = new FcPackagingOption(yow4, package10000Cc);
    FcPackagingOption ind1_2000cc = new FcPackagingOption(ind1, package2000Cc);
    FcPackagingOption ind1_5000cc = new FcPackagingOption(ind1, package5000Cc);
    FcPackagingOption abe2_2000cc = new FcPackagingOption(abe2, package2000Cc);
    FcPackagingOption abe2_6000cc = new FcPackagingOption(abe2, package6000Cc);
    FcPackagingOption pdx1_5000cc = new FcPackagingOption(pdx1, package5000Cc);
    FcPackagingOption pdx1_10000cc = new FcPackagingOption(pdx1, package10000Cc);


    @Test
    public void getFcPackagingOptions_get_returnAllOptions() {
        // GIVEN
        PackagingDatastore packagingDatastore = new PackagingDatastore();
        List<FcPackagingOption> expectedPackagingOptions = Arrays.asList(ind1_10Cm, abe2_20Cm, abe2_40Cm, yow4_10Cm,
            yow4_20Cm, yow4_60Cm, iad2_20Cm, iad2_20Cm, pdx1_40Cm, pdx1_60Cm, pdx1_60Cm, iad2_2000Cc, iad2_10000Cc,
            iad_5000cc, yow4_2000cc, yow4_5000cc, yow4_10000cc, ind1_2000cc, ind1_5000cc, abe2_2000cc, abe2_6000cc,
            pdx1_5000cc, pdx1_10000cc, yow4_5000cc);

        // WHEN
        List<FcPackagingOption> fcPackagingOptions = packagingDatastore.getFcPackagingOptions();

        // THEN
        assertEquals(expectedPackagingOptions.size(), fcPackagingOptions.size(),
            String.format("There should be %s FC/Packaging pairs.", expectedPackagingOptions.size()));
        for (FcPackagingOption expectedPackagingOption : expectedPackagingOptions) {
            assertTrue(fcPackagingOptions.contains(expectedPackagingOption), String.format("expected packaging " +
                "options from PackagingDatastore to contain %s package in fc %s",
                expectedPackagingOption.getPackaging(),
                expectedPackagingOption.getFulfillmentCenter().getFcCode()));
        }
        assertTrue(true, "getFcPackagingOptions contained all of the expected options.");
    }
}
