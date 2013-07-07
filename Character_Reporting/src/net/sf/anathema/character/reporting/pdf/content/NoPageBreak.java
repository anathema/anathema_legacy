package net.sf.anathema.character.reporting.pdf.content;

public class NoPageBreak implements PageBreakChecker {
  @Override
  public boolean isRequired() {
    return false;
  }
}
