package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.command.Command;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.QualityListener;
import net.sf.anathema.characterengine.quality.Type;

public class DefaultPersona implements Persona {
  private final Qualities qualities;

  public DefaultPersona(Qualities qualities) {
    this.qualities = qualities;
  }

  @Override
  public void execute(Command command) {
    command.execute(qualities);
  }

  @Override
  public void doFor(QualityKey qualityKey, QualityClosure closure) {
    qualities.doFor(qualityKey, closure);
  }

  @Override
  public void doForEach(Type type, QualityClosure closure) {
    qualities.doForEach(type, closure);
  }

  @Override
  public void observe(QualityKey qualityKey, QualityListener listener) {
    qualities.observe(qualityKey, listener);
  }
}