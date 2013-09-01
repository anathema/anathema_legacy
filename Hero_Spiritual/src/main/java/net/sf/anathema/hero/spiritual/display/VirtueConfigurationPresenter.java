package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.framework.environment.Resources;

public class VirtueConfigurationPresenter implements Presenter {

  private final Trait[] virtues;
  private final SpiritualTraitsView view;
  private final Resources resources;

  public VirtueConfigurationPresenter(Resources resources, TraitMap traits, SpiritualTraitsView view) {
    this.resources = resources;
    this.virtues = traits.getTraits(VirtueType.values());
    this.view = view;
  }

  @Override
  public void initPresentation() {
    for (Trait virtue : virtues) {
      String labelText = resources.getString("VirtueType.Name." + virtue.getType().getId());
      IntValueView virtueView = view.addVirtue(labelText, virtue.getMaximalValue());
      new TraitPresenter(virtue, virtueView).initPresentation();
    }
  }
}