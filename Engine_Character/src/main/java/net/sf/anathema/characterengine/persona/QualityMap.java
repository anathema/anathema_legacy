package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.characterengine.quality.TypeClosure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QualityMap {
  private final Map<QualityKey, Quality> qualityMap = new HashMap<>();


  public void put(QualityKey qualityKey, Quality quality) {
    qualityMap.put(qualityKey, quality);
  }

  public List<Quality> getAllWithType(final Type queryType) {
    final List<Quality> allWithType = new ArrayList<>();
    for (final Map.Entry<QualityKey, Quality> entry : qualityMap.entrySet()) {
      QualityKey key = entry.getKey();
      key.withTypeDo(new TypeClosure() {
        @Override
        public void execute(Type type) {
          if (type == queryType) {
            allWithType.add(entry.getValue());
          }
        }
      });
    }
    return allWithType;
  }

  public Quality get(QualityKey qualityKey) {
    return qualityMap.get(qualityKey);
  }

  public boolean contains(QualityKey qualityKey) {
    return qualityMap.containsKey(qualityKey);
  }
}
