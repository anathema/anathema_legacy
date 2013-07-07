package net.sf.anathema.hero.sheet.pdf.session;

public class NoPageBreak implements PageBreakChecker {
  @Override
  public boolean isRequired() {
    return false;
  }
}
