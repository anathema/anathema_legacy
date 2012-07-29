package net.sf.anathema.characterengine;

import java.util.HashMap;
import java.util.Map;

public class DefaultQualities implements Qualities {
  private final Map<QualityKey, Quality> qualityMap = new HashMap<QualityKey, Quality>();

  @Override
  public void addQuality(Type type, Name name) {
    QualityKey key = new QualityKey(type, name);
    qualityMap.put(key, new DefaultQuality());
  }

  @Override
  public void doFor(Type type, Name name, Closure closure) {
    QualityKey key = new QualityKey(type, name);
    if (qualityMap.containsKey(key)) {
      Quality quality = qualityMap.get(key);
      closure.execute(quality);
    }
  }
}