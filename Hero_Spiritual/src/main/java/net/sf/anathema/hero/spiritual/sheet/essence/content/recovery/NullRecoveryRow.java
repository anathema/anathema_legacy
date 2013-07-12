package net.sf.anathema.hero.spiritual.sheet.essence.content.recovery;

public class NullRecoveryRow implements RecoveryRow {
  @Override
  public String getLabel() {
    return null;
  }

  @Override
  public Integer getAtEase() {
    return null;
  }

  @Override
  public Integer getRelaxed() {
    return null;
  }

  @Override
  public boolean isMarked() {
    return false;
   }
}
