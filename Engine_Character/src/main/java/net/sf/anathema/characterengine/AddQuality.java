package net.sf.anathema.characterengine;

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