package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

public interface ISourceEntryModel {
  public IExaltedSourceBook[] getPredefinedSources();

  public void setSourceBook(IExaltedSourceBook identificate);

  public boolean enablePageSelection();

  public void setSourcePage(int newValue);
}
