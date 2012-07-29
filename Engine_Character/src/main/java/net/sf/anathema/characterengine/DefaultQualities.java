package net.sf.anathema.characterengine;

import java.util.HashMap;
import java.util.Map;

public class DefaultQualities implements Qualities {
  private final Map<QualityKey, Quality> qualityMap = new HashMap<QualityKey, Quality>();

  @Override
  public void addQuality(QualityKey qualityKey) {
    qualityMap.put(qualityKey, new DefaultQuality());
  }

  @Override
  public void doFor(QualityKey qualityKey, Closure closure) {
    if (qualityMap.containsKey(qualityKey)) {
      Quality quality = qualityMap.get(qualityKey);
      closure.execute(quality);
    }
  }
}