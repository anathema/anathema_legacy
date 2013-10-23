package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.model.special.subeffects.MultipleEffectCharmSpecials;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffect;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;

public class MultipleEffectCharmPresenter {

  private final Resources resources;
  private final ToggleButtonSpecialNodeView view;
  private final MultipleEffectCharmSpecials model;

  public MultipleEffectCharmPresenter(Resources resources, ToggleButtonSpecialNodeView subeffectView, MultipleEffectCharmSpecials model) {
    this.resources = resources;
    this.view = subeffectView;
    this.model = model;
  }

  public void initPresentation() {
    for (final SubEffect subeffect : model.getEffects()) {
      String key = model.getCharm().getId() + ".Subeffects." + subeffect.getId();
      String label = resources.getString(key);
      final IBooleanValueView display = view.addSubeffect(label);
      subeffect.addChangeListener(new ChangeListener() {
        @Override
        public void changeOccurred() {
          display.setSelected(subeffect.isLearned());
        }
      });
      display.addChangeListener(new IBooleanValueChangedListener() {
        @Override
        public void valueChanged(boolean newValue) {
          subeffect.setLearned(newValue);
          display.setSelected(subeffect.isLearned());
        }
      });
      display.setSelected(subeffect.isLearned());
    }
  }
}