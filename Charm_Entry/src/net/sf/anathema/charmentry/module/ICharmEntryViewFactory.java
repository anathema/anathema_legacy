package net.sf.anathema.charmentry.module;

import net.sf.anathema.charmentry.presenter.view.IAmountDurationEntryView;
import net.sf.anathema.charmentry.presenter.view.ICharmTypeEntryView;
import net.sf.anathema.charmentry.presenter.view.ICostEntryPageView;
import net.sf.anathema.charmentry.presenter.view.IDurationEntryView;
import net.sf.anathema.charmentry.presenter.view.IHeaderDataEntryView;
import net.sf.anathema.charmentry.presenter.view.IKeywordView;
import net.sf.anathema.charmentry.presenter.view.IPrerequisiteCharmsEntryView;
import net.sf.anathema.charmentry.presenter.view.IPrerequisitesEntryView;
import net.sf.anathema.charmentry.presenter.view.IReflexiveSpecialsView;
import net.sf.anathema.charmentry.presenter.view.ISimpleSpecialsView;

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

  public IAmountDurationEntryView createQualifiedAmountDurationView();
}