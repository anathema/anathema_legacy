package net.sf.anathema.characterengine;

import java.util.HashMap;
import java.util.Map;

public class DefaultQualities implements Qualities {
  private final Map<QualityKey, Quality> qualityMap = new HashMap<QualityKey, Quality>();
  private final Engine engine;

  public DefaultQualities(Engine engine) {
    this.engine = engine;
  }

  @Override
  public void addQuality(final QualityKey qualityKey) {
    qualityKey.withTypeDo(new CreateQuality(qualityKey));
  }

  @Override
  public void doFor(QualityKey qualityKey, QualityClosure closure) {
    if (qualityMap.containsKey(qualityKey)) {
      Quality quality = qualityMap.get(qualityKey);
      closure.execute(quality);
    }
  }

  private class CreateQuality implements TypeClosure {

    private final QualityKey qualityKey;

    public CreateQuality(QualityKey qualityKey) {
      this.qualityKey = qualityKey;
    }

    @Override
    public void execute(Type type) {
      Quality quality = engine.createQuality(type);
      qualityMap.put(qualityKey, quality);
    }
  }
}