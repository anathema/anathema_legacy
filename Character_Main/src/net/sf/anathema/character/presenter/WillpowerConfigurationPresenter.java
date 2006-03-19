package net.sf.anathema.character.presenter;

import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.presenter.AbstractTraitPresenter;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.resources.IResources;

public class WillpowerConfigurationPresenter extends AbstractTraitPresenter implements IAdvantageSubPresenter {

  private final ITrait willpower;
  private final IBasicAdvantageView view;
  private final IResources resources;

  public WillpowerConfigurationPresenter(IResources resources, ITrait willpower, IBasicAdvantageView view) {
    this.resources = resources;
    this.willpower = willpower;
    this.view = view;
  }

  public void init() {
    String labelText = resources.getString("WillpowerType.Name"); //$NON-NLS-1$
    IIntValueView willpowerView = view.addWillpower(labelText, willpower.getCurrentValue(), willpower.getMaximalValue());
    addModelValueListener(willpower, willpowerView);
    addViewValueListener(willpowerView, willpower);
  }
}