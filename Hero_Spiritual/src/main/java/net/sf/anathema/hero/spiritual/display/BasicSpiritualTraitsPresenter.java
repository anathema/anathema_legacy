package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spiritual.model.pool.EssencePoolModelFetcher;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.framework.environment.Resources;

import java.util.ArrayList;
import java.util.List;

public class BasicSpiritualTraitsPresenter {
  private final List<Presenter> subPresenters = new ArrayList<>();
  private final SpiritualTraitsView view;
  private final Resources resources;

  public BasicSpiritualTraitsPresenter(Resources resources, Hero hero, SpiritualTraitsView view) {
    this.resources = resources;
    this.view = view;
    TraitMap traitMap = TraitModelFetcher.fetch(hero);
    subPresenters.add(new VirtueConfigurationPresenter(resources, traitMap, view));
    subPresenters.add(new WillpowerConfigurationPresenter(resources, traitMap.getTrait(OtherTraitType.Willpower), view));
    subPresenters.add(new EssenceConfigurationPresenter(resources, EssencePoolModelFetcher.fetch(hero), traitMap, view));
  }

  public void initPresentation() {
    for (Presenter presenter : subPresenters) {
      presenter.initPresentation();
    }
    view.initGui(new DefaultSpiritualTraitsViewProperties(resources));
  }
}