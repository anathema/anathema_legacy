package net.sf.anathema.charmentry.demo;

public interface ICharmEntryViewFactory {

  public IHeaderDataEntryView createHeaderDataEntryView();

  public ICharmTypeEntryView getCharmTypeEntryView();

  public IPrerequisitesEntryView createPrerequisitesView();
}