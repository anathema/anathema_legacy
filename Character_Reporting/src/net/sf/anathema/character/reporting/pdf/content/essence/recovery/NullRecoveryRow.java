package net.sf.anathema.character.reporting.pdf.content.essence.recovery;

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
