package net.sf.anathema.character.generic.impl.additional;

import java.util.HashMap;
import java.util.Map;

public class AdditionalEssencePool {

  private final int multiplier;
  private final Map<Integer, Integer> override = new HashMap<Integer, Integer>();

  public AdditionalEssencePool(int multiplier) {
    this.multiplier = multiplier;
  }

  public void setFixedValue(int value, int pool) {
    override.put(value, pool);
  }

  public int getPool(int value) {
    Integer overridePool = override.get(value);
    if (overridePool != null) {
      return overridePool;
    }
    return value * multiplier;
  }
}