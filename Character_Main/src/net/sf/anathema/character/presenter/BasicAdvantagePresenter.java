package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.main.essencepool.model.EssencePoolModelFetcher;
import net.sf.anathema.character.main.traits.model.TraitMap;
import net.sf.anathema.character.main.traits.model.TraitModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.view.AdvantageView;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class BasicAdvantagePresenter {
  private final List<Presenter> subPresenters = new ArrayList<>();
  private final AdvantageView view;
  private final Resources resources;

  public BasicAdvantagePresenter(Resources resources, ICharacter character, AdvantageView view) {
    this.resources = resources;
    this.view = view;
    TraitMap traitMap = TraitModelFetcher.fetch(character);
    subPresenters.add(new VirtueConfigurationPresenter(resources, traitMap, view));
    subPresenters.add(new WillpowerConfigurationPresenter(resources, traitMap.getTrait(OtherTraitType.Willpower), view));
    subPresenters.add(new EssenceConfigurationPresenter(resources, EssencePoolModelFetcher.fetch(character), traitMap, view));
  }

  public void initPresentation() {
    for (Presenter presenter : subPresenters) {
      presenter.initPresentation();
    }
    view.initGui(new DefaultAdvantageViewProperties(resources));
  }
}