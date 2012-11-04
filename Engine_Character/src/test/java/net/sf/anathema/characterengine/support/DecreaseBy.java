package net.sf.anathema.characterengine.support;

import net.sf.anathema.characterengine.command.Command;
import net.sf.anathema.characterengine.persona.Qualities;
import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;

public class DecreaseBy implements Command {
  private final QualityKey qualityKey;
  private final NumericValue modification;

  public DecreaseBy(QualityKey qualityKey, NumericValue modification) {
    this.qualityKey = qualityKey;
    this.modification = modification;
  }

  @Override
  public void execute(Qualities qualities) {
    qualities.doFor(qualityKey, new QualityClosure() {
      @Override
      public void execute(Quality quality) {
        NumericQuality numericQuality = (NumericQuality) quality;
        numericQuality.changeBy(modification);
      }
    });
  }
}
