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

  IHeaderDataEntryView createHeaderDataEntryView();

  ICharmTypeEntryView getCharmTypeEntryView();

  IPrerequisitesEntryView createPrerequisiteTraitsView();

  IReflexiveSpecialsView createReflexiveSpecialsView();

  ISimpleSpecialsView createSimpleSpecialsView();

  IDurationEntryView createDurationView();

  ICostEntryPageView createCostEntryView();

  IPrerequisiteCharmsEntryView createPrerequisiteCharmsView();

  IKeywordView createKeywordEntryView();

  IAmountDurationEntryView createQualifiedAmountDurationView();
}