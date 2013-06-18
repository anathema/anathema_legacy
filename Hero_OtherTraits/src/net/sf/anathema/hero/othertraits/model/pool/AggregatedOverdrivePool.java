package net.sf.anathema.hero.othertraits.model.pool;

import net.sf.anathema.character.main.model.essencepool.OverdrivePool;

import java.util.ArrayList;
import java.util.List;

public class AggregatedOverdrivePool implements OverdrivePool {

  private final List<OverdrivePool> allPools = new ArrayList<>();

  public void addOverdrivePool(OverdrivePool pool) {
    allPools.add(pool);
  }

  @Override
  public int getPool() {
    int poolValue = 0;
    for (OverdrivePool pool : allPools) {
      poolValue += pool.getPool();
    }
    return Math.min(poolValue, 25);
  }
}
