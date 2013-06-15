package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class BasicAdvantagePresenter {
  private final List<Presenter> subPresenters = new ArrayList<>();
  private final IBasicAdvantageView view;
  private final Resources resources;

  public BasicAdvantagePresenter(Resources resources, ICharacter character, IBasicAdvantageView view) {
    this.resources = resources;
    this.view = view;
    ICoreTraitConfiguration traitConfiguration = character.getTraitConfiguration();
    subPresenters.add(new VirtueConfigurationPresenter(resources, traitConfiguration, view));
    subPresenters.add(new WillpowerConfigurationPresenter(resources, traitConfiguration.getTrait(OtherTraitType.Willpower), view));
    subPresenters.add(new EssenceConfigurationPresenter(resources, character.getEssencePool(), traitConfiguration, view));
  }

  public void initPresentation() {
    for (Presenter presenter : subPresenters) {
      presenter.initPresentation();
    }
    view.initGui(new BasicAdvantageViewProperties(resources));
  }
}