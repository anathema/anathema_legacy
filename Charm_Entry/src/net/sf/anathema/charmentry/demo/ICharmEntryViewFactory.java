package net.sf.anathema.charmentry.demo;

public interface ICharmEntryViewFactory {

  public IHeaderDataEntryView createHeaderDataEntryView();

  public ICharmTypeEntryView getCharmTypeEntryView();

  public IPrerequisitesEntryView createPrerequisitesView();

  public IReflexiveSpecialsView createReflexiveSpecialsView();

  public ISimpleSpecialsView createSimpleSpecialsView();

  public IDurationEntryView createDurationView();

  public ICostEntryPageView createCostEntryView();
}