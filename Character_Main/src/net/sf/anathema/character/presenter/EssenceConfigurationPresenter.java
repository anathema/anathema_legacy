package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.model.traits.essence.IEssencePoolConfiguration;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.lib.workflow.labelledvalue.NullValueView;

public class EssenceConfigurationPresenter implements IPresenter {

  private final IBasicAdvantageView view;
  private final IEssencePoolConfiguration essence;
  private final IResources resources;
  private final ICoreTraitConfiguration traitConfiguration;

  public EssenceConfigurationPresenter(
          IResources resources,
          final IEssencePoolConfiguration essence,
          final ICoreTraitConfiguration traitConfiguration,
          final IBasicAdvantageView view) {
    this.resources = resources;
    this.essence = essence;
    this.traitConfiguration = traitConfiguration;
    this.view = view;
  }

  public void initPresentation() {
    ITrait essenceTrait = traitConfiguration.getTrait(OtherTraitType.Essence);
    IIntValueView essenceView = view.addEssenceView(resources.getString("Essence.Name"), //$NON-NLS-1$
            essenceTrait.getCurrentValue(),
            essenceTrait.getMaximalValue(),
            (IModifiableCapTrait) essenceTrait);
    if (essence.isEssenceUser()) {
      String key = "EssencePool.Name.Personal";
      String personalPool = essence.getPersonalPool();
      final IValueView<String> personalView = addPool(key, personalPool);
      final IValueView<String> peripheralView = createPeripheralPoolView();
      final IValueView<String> attunementView = addPool("EssencePool.Name.Attunement", essence.getAttunedPool());
      essence.addPoolChangeListener(new IChangeListener() {
        public void changeOccured() {
          personalView.setValue(essence.getPersonalPool());
          listenToPeripheralChanges(peripheralView);
          attunementView.setValue(essence.getAttunedPool());
        }
      });
    }
    new TraitPresenter(essenceTrait, essenceView).initPresentation();
  }

  private void listenToPeripheralChanges(IValueView<String> peripheralView) {
    if (essence.hasPeripheralPool()) {
      peripheralView.setValue(essence.getPeripheralPool());
    }
  }

  private IValueView<String> createPeripheralPoolView() {
    if (essence.hasPeripheralPool()) {
      return addPool("EssencePool.Name.Peripheral", essence.getPeripheralPool());
    }
    return new NullValueView<String>();
  }

  private IValueView<String> addPool(String labelKey, String pool) {
    return view.addPoolView(resources.getString(labelKey), pool);
  }
}