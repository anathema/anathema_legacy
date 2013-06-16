package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.main.essencepool.model.EssencePoolModel;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.lib.workflow.labelledvalue.NullValueView;

public class EssenceConfigurationPresenter implements Presenter {

  private final IBasicAdvantageView view;
  private final EssencePoolModel essence;
  private final Resources resources;
  private final ICoreTraitConfiguration traitConfiguration;

  public EssenceConfigurationPresenter(Resources resources, EssencePoolModel essence, ICoreTraitConfiguration traitConfiguration,
                                       IBasicAdvantageView view) {
    this.resources = resources;
    this.essence = essence;
    this.traitConfiguration = traitConfiguration;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    Trait essenceTrait = traitConfiguration.getTrait(OtherTraitType.Essence);
    IIntValueView essenceView =
            view.addEssenceView(resources.getString("Essence.Name"), essenceTrait.getCurrentValue(), essenceTrait.getMaximalValue(), essenceTrait);
    if (essence.isEssenceUser()) {
      String key = "EssencePool.Name.Personal";
      String personalPool = essence.getPersonalPool();
      final IValueView<String> personalView = addPool(key, personalPool);
      final IValueView<String> peripheralView = createPeripheralPoolView();
      final IValueView<String> attunementView = addPool("EssencePool.Name.Attunement", essence.getAttunedPool());
      essence.addPoolChangeListener(new IChangeListener() {
        @Override
        public void changeOccurred() {
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
    return new NullValueView<>();
  }

  private IValueView<String> addPool(String labelKey, String pool) {
    return view.addPoolView(resources.getString(labelKey), pool);
  }
}
