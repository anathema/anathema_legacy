package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.quality.QualityKey;
import org.junit.Test;

public class ListenerMapTest {

  @Test
  public void triggersOnlyIfContained() throws Exception {
    QualityKey qualityKey = QualityKey.ForTypeAndName("unknownType", "unknownName");
    new ListenerMap().triggerFor(qualityKey);
  }
}
