package net.sf.anathema.characterengine.command;

import net.sf.anathema.characterengine.persona.Qualities;
import net.sf.anathema.characterengine.quality.QualityKey;

public class AddQuality implements Command {
  private final QualityKey qualityKey;

  public AddQuality(QualityKey qualityKey) {
    this.qualityKey = qualityKey;
  }

  @Override
  public void execute(Qualities qualities) {
    qualities.addQuality(qualityKey);
  }
}