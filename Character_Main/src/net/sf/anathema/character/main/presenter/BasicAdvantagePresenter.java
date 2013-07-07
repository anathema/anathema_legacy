package net.sf.anathema.character.main.presenter;

import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.hero.essencepool.EssencePoolModelFetcher;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.character.main.view.AdvantageView;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class BasicAdvantagePresenter {
  private final List<Presenter> subPresenters = new ArrayList<>();
  private final AdvantageView view;
  private final Resources resources;

  public BasicAdvantagePresenter(Resources resources, Hero hero, AdvantageView view) {
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
    view.initGui(new DefaultAdvantageViewProperties(resources));
  }
}