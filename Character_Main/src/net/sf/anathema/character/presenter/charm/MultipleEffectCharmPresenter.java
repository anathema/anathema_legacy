package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGToggleButtonSpecialNodeView;

public class MultipleEffectCharmPresenter implements IPresenter {

  private final IResources resources;
  private final SVGToggleButtonSpecialNodeView view;
  private final IMultipleEffectCharmConfiguration model;

  public MultipleEffectCharmPresenter(
      IResources resources,
      SVGToggleButtonSpecialNodeView subeffectView,
      IMultipleEffectCharmConfiguration model) {
    this.resources = resources;
    this.view = subeffectView;
    this.model = model;
  }

  public void initPresentation() {
    for (final ISubeffect subeffect : model.getEffects()) {
      String key = model.getCharm().getId() + ".Subeffects." + subeffect.getId();
      String label = resources.getString(key);
      final IBooleanValueView display = view.addSubeffect(label);
      subeffect.addChangeListener(new IChangeListener() {
        public void changeOccurred() {
          display.setSelected(subeffect.isLearned());
        }
      });
      display.addChangeListener(new IBooleanValueChangedListener() {
        public void valueChanged(boolean newValue) {
          subeffect.setLearned(newValue);
        }
      });
      display.setSelected(subeffect.isLearned());
    }
  }
}