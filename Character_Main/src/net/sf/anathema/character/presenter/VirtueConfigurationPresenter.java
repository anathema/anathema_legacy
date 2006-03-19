package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.presenter.AbstractTraitPresenter;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.resources.IResources;

public class VirtueConfigurationPresenter extends AbstractTraitPresenter implements IAdvantageSubPresenter {

  private final ITrait[] virtues;
  private final IBasicAdvantageView view;
  private final IResources resources;

  public VirtueConfigurationPresenter(IResources resources, ICoreTraitConfiguration traits, IBasicAdvantageView view) {
    this.resources = resources;
    this.virtues = traits.getTraits(VirtueType.values());
    this.view = view;
  }

  public void init() {
    for (ITrait virtue : virtues) {
      String labelText = resources.getString("VirtueType.Name." + virtue.getType().getId()); //$NON-NLS-1$
      IIntValueView virtueView = view.addVirtue(labelText, virtue.getCurrentValue(), virtue.getMaximalValue());
      addModelValueListener(virtue, virtueView);
      addViewValueListener(virtueView, virtue);
    }
  }
}