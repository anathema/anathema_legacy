package net.sf.anathema.character.reporting.pdf.content.essence.recovery;

public interface RecoveryRow {
  
  String getLabel();
  
  Integer getAtEase();
  
  Integer getRelaxed();

  boolean isMarked();
}
