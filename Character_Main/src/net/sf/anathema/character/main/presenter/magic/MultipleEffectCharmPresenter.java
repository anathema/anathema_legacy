package net.sf.anathema.character.main.presenter.magic;

import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.main.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;

public class MultipleEffectCharmPresenter implements Presenter {

  private final Resources resources;
  private final ToggleButtonSpecialNodeView view;
  private final IMultipleEffectCharmConfiguration model;

  public MultipleEffectCharmPresenter(Resources resources, ToggleButtonSpecialNodeView subeffectView, IMultipleEffectCharmConfiguration model) {
    this.resources = resources;
    this.view = subeffectView;
    this.model = model;
  }

  @Override
  public void initPresentation() {
    for (final ISubeffect subeffect : model.getEffects()) {
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