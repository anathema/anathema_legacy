package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.framework.environment.Resources;

public class WillpowerConfigurationPresenter implements Presenter {

  private final Trait willpower;
  private final SpiritualTraitsView view;
  private final Resources resources;

  public WillpowerConfigurationPresenter(Resources resources, Trait willpower, SpiritualTraitsView view) {
    this.resources = resources;
    this.willpower = willpower;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    String labelText = resources.getString("WillpowerType.Name");
    IntValueView willpowerView = view.addWillpower(labelText, willpower.getMaximalValue());
    new TraitPresenter(willpower, willpowerView).initPresentation();
  }
}
