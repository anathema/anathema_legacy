package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

public interface ISourceEntryModel {

  void setSourceBook(IExaltedSourceBook identificate);

  boolean enablePageSelection();

  void setSourcePage(int newValue);

  IExaltedSourceBook[] getLegalSources();
}