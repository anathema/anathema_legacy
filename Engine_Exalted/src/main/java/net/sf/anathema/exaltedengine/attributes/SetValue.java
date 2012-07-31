package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.command.Command;
import net.sf.anathema.characterengine.persona.Qualities;
import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.exaltedengine.NumericValue;

public class SetValue implements Command {
  private final NumericValue newValue;
  private final QualityKey qualityKey;

  public SetValue(QualityKey qualityKey, NumericValue newValue) {
    this.qualityKey = qualityKey;
    this.newValue = newValue;
  }

  @Override
  public void execute(Qualities qualities) {
    qualities.doFor(qualityKey, new QualityClosure() {
      @Override
      public void execute(Quality quality) {
        ((Attribute) quality).changeValueTo(newValue);
      }
    });
  }
}
