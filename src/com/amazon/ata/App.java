package com.amazon.ata;

import com.amazon.ata.cost.CarbonCostStrategy;
import com.amazon.ata.cost.CostStrategy;
import com.amazon.ata.cost.MonetaryCostStrategy;
import com.amazon.ata.cost.WeightedCostStrategy;
import com.amazon.ata.dao.PackagingDAO;
import com.amazon.ata.datastore.PackagingDatastore;
import com.amazon.ata.service.ShipmentService;

public class App {
    /* don't instantiate me */
    private App() {}

    private static PackagingDatastore getPackagingDatastore() {
        return new PackagingDatastore();
    }

    private static PackagingDAO getPackagingDAO() {
        return new PackagingDAO(getPackagingDatastore());
    }

    /**
     * This will create WeightedCostStrategy composed of MonetaryCost and CarbonCost
     * with reasonable default weights.
     *
     * Use the {@link WeightedCostStrategy#builder()} to build with custom weights, or if we add
     * additional {@link CostStrategy}s.
     *
     * @return A WeightedCostStrategy composed of MonetaryCost and CarbonCost
     *         with default weights.
     */
    private static CostStrategy getCostStrategy() {
        return new WeightedCostStrategy(new MonetaryCostStrategy(), new CarbonCostStrategy());
    }

    public static ShipmentService getShipmentService() {
        return new ShipmentService(getPackagingDAO(), getCostStrategy());
    }
}