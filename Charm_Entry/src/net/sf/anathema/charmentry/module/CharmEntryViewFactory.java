package net.sf.anathema.charmentry.module;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.framework.value.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
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
import net.sf.anathema.charmentry.view.CharmTypeEntryView;
import net.sf.anathema.charmentry.view.CostEntryPageView;
import net.sf.anathema.charmentry.view.DurationEntryView;
import net.sf.anathema.charmentry.view.HeaderDataEntryView;
import net.sf.anathema.charmentry.view.KeywordView;
import net.sf.anathema.charmentry.view.PrerequisiteCharmsEntryView;
import net.sf.anathema.charmentry.view.PrerequisiteEntryView;
import net.sf.anathema.charmentry.view.QualifiedAmountDurationView;
import net.sf.anathema.charmentry.view.ReflexiveSpecialsView;
import net.sf.anathema.charmentry.view.SimpleSpecialsView;
import net.sf.anathema.lib.resources.IResources;

public class CharmEntryViewFactory implements ICharmEntryViewFactory {

  private final IIntValueDisplayFactory factory;

  public CharmEntryViewFactory(IResources resources) {
    this.factory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(resources, CharacterType.MORTAL);
  }

  @Override
  public IHeaderDataEntryView createHeaderDataEntryView() {
    return new HeaderDataEntryView();
  }

  @Override
  public ICharmTypeEntryView getCharmTypeEntryView() {
    return new CharmTypeEntryView();
  }

  @Override
  public IPrerequisitesEntryView createPrerequisiteTraitsView() {
    return new PrerequisiteEntryView(factory);
  }

  @Override
  public IReflexiveSpecialsView createReflexiveSpecialsView() {
    return new ReflexiveSpecialsView();
  }

  @Override
  public ISimpleSpecialsView createSimpleSpecialsView() {
    return new SimpleSpecialsView();
  }

  @Override
  public IDurationEntryView createDurationView() {
    return new DurationEntryView();
  }

  @Override
  public IAmountDurationEntryView createQualifiedAmountDurationView() {
    return new QualifiedAmountDurationView();
  }

  @Override
  public ICostEntryPageView createCostEntryView() {
    return new CostEntryPageView();
  }

  @Override
  public IPrerequisiteCharmsEntryView createPrerequisiteCharmsView() {
    return new PrerequisiteCharmsEntryView();
  }

  @Override
  public IKeywordView createKeywordEntryView() {
    return new KeywordView();
  }
}