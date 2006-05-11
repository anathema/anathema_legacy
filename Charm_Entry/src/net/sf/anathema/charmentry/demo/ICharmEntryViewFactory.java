package net.sf.anathema.charmentry.demo;

import net.sf.anathema.charmentry.presenter.IKeywordView;

public interface ICharmEntryViewFactory {

  public IHeaderDataEntryView createHeaderDataEntryView();

  public ICharmTypeEntryView getCharmTypeEntryView();

  public IPrerequisitesEntryView createPrerequisiteTraitsView();

  public IReflexiveSpecialsView createReflexiveSpecialsView();

  public ISimpleSpecialsView createSimpleSpecialsView();

  public IDurationEntryView createDurationView();

  public ICostEntryPageView createCostEntryView();

  public IPrerequisiteCharmsEntryView createPrerequisiteCharmsView();

  public IKeywordView createKeywordEntryView();
}