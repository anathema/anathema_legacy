package net.sf.anathema.charmentry.demo;

import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactory;
import net.sf.anathema.charmentry.demo.view.CharmTypeEntryView;
import net.sf.anathema.charmentry.demo.view.DurationEntryView;
import net.sf.anathema.charmentry.demo.view.HeaderDataEntryView;
import net.sf.anathema.charmentry.demo.view.PrerequisiteEntryView;
import net.sf.anathema.charmentry.demo.view.ReflexiveSpecialsView;
import net.sf.anathema.charmentry.demo.view.SimpleSpecialsView;
import net.sf.anathema.lib.resources.IResources;

public class CharmEntryViewFactory implements ICharmEntryViewFactory {

  private final IIntValueDisplayFactory factory;

  public CharmEntryViewFactory(IResources resources) {
    this.factory = new IntValueDisplayFactory(resources, resources.getImageIcon(IIconConstants.MORTAL_BALL));
  }

  public IHeaderDataEntryView createHeaderDataEntryView() {
    return new HeaderDataEntryView();
  }

  public ICharmTypeEntryView getCharmTypeEntryView() {
    return new CharmTypeEntryView();
  }

  public IPrerequisitesEntryView createPrerequisitesView() {
    return new PrerequisiteEntryView(factory);
  }

  public IReflexiveSpecialsView createReflexiveSpecialsView() {
    return new ReflexiveSpecialsView();
  }

  public ISimpleSpecialsView createSimpleSpecialsView() {
    return new SimpleSpecialsView();
  }

  public IDurationEntryView createDurationView() {
    return new DurationEntryView();
  }

  public ICostEntryPageView createCostEntryView() {
    return new CostEntryPageView();
  }
}