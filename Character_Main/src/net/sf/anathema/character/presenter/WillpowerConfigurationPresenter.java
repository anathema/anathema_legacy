package net.sf.anathema.character.presenter;

import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class WillpowerConfigurationPresenter implements IPresenter {

  private final ITrait willpower;
  private final IBasicAdvantageView view;
  private final IResources resources;

  public WillpowerConfigurationPresenter(IResources resources, ITrait willpower, IBasicAdvantageView view) {
    this.resources = resources;
    this.willpower = willpower;
    this.view = view;
  }

  public void initPresentation() {
    String labelText = resources.getString("WillpowerType.Name"); //$NON-NLS-1$
    IIntValueView willpowerView = view.addWillpower(labelText, willpower.getCurrentValue(), willpower.getMaximalValue());
    new TraitPresenter(willpower, willpowerView).initPresentation();
  }
}