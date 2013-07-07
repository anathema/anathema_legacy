package net.sf.anathema.character.main.presenter;

import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.presenter.TraitPresenter;
import net.sf.anathema.hero.essencepool.EssencePoolModel;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.character.main.view.AdvantageView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.lib.workflow.labelledvalue.NullValueView;

public class EssenceConfigurationPresenter implements Presenter {

  private final AdvantageView view;
  private final EssencePoolModel essencePool;
  private final Resources resources;
  private final TraitMap traitMap;

  public EssenceConfigurationPresenter(Resources resources, EssencePoolModel essencePool, TraitMap traitMap, AdvantageView view) {
    this.resources = resources;
    this.essencePool = essencePool;
    this.traitMap = traitMap;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    Trait essenceTrait = traitMap.getTrait(OtherTraitType.Essence);
    IIntValueView essenceView =
            view.addEssenceView(resources.getString("Essence.Name"), essenceTrait.getCurrentValue(), essenceTrait.getMaximalValue(), essenceTrait);
    if (essencePool.isEssenceUser()) {
      String key = "EssencePool.Name.Personal";
      String personalPool = essencePool.getPersonalPool();
      final IValueView<String> personalView = addPool(key, personalPool);
      final IValueView<String> peripheralView = createPeripheralPoolView();
      final IValueView<String> attunementView = addPool("EssencePool.Name.Attunement", essencePool.getAttunedPool());
      essencePool.addPoolChangeListener(new ChangeListener() {
        @Override
        public void changeOccurred() {
          personalView.setValue(essencePool.getPersonalPool());
          listenToPeripheralChanges(peripheralView);
          attunementView.setValue(essencePool.getAttunedPool());
        }
      });
    }
    new TraitPresenter(essenceTrait, essenceView).initPresentation();
  }

  private void listenToPeripheralChanges(IValueView<String> peripheralView) {
    if (essencePool.hasPeripheralPool()) {
      peripheralView.setValue(essencePool.getPeripheralPool());
    }
  }

  private IValueView<String> createPeripheralPoolView() {
    if (essencePool.hasPeripheralPool()) {
      return addPool("EssencePool.Name.Peripheral", essencePool.getPeripheralPool());
    }
    return new NullValueView<>();
  }

  private IValueView<String> addPool(String labelKey, String pool) {
    return view.addPoolView(resources.getString(labelKey), pool);
  }
}
