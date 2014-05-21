package net.sf.anathema.character.framework.reporting;

import net.sf.anathema.hero.model.Hero;

import java.io.OutputStream;

public interface Report {

  boolean supports(Hero hero);

  void print(Hero hero, OutputStream stream) throws ReportException;
}
