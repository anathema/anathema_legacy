package net.sf.anathema.character.solar.caste;

import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;

public interface ISolarCasteVisitor extends ICasteTypeVisitor {

  public void visitDawn(SolarCaste caste);

  public void visitZenith(SolarCaste caste);

  public void visitTwilight(SolarCaste caste);

  public void visitNight(SolarCaste caste);

  public void visitEclipse(SolarCaste caste);
}