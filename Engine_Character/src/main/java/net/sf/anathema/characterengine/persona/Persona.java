package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.command.Command;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.QualityListener;
import net.sf.anathema.characterengine.quality.Type;

public interface Persona {
  void execute(Command command);

  void doFor(QualityKey qualityKey, QualityClosure closure);

  void doForEach(Type type, QualityClosure closure);

  void observe(QualityKey qualityKey, QualityListener listener);
}