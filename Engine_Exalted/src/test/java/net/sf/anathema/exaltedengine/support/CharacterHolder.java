package net.sf.anathema.exaltedengine.support;

import com.google.inject.Singleton;
import net.sf.anathema.characterengine.command.Command;
import net.sf.anathema.characterengine.persona.Persona;
import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.QualityListener;
import net.sf.anathema.characterengine.quality.Type;

@Singleton
public class CharacterHolder implements Persona{
  private Persona persona;

  public void hold(Persona persona) {
    this.persona = persona;
  }

  @Override
  public void execute(Command command) {
    persona.execute(command);
  }

  @Override
  public void doFor(QualityKey qualityKey, QualityClosure closure) {
    persona.doFor(qualityKey, closure);
  }

  @Override
  public void doForEach(Type type, QualityClosure closure) {
    persona.doForEach(type, closure);
  }

  @Override
  public void doForEachDisregardingRules(Type type, QualityClosure closure) {
    persona.doForEachDisregardingRules(type, closure);
  }

  @Override
  public void observe(QualityKey qualityKey, QualityListener listener) {
    persona.observe(qualityKey, listener);
  }

  @Override
  public void stopObservation(QualityKey qualityKey, QualityListener listener) {
    persona.stopObservation(qualityKey, listener);
  }
}