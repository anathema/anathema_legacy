package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.model.charm.special.ISubeffect;
import net.sf.anathema.charmtree.batik.intvalue.SVGSubeffectCharmView;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;

public class MultipleEffectCharmPresenter implements IPresenter {

  private final IResources resources;
  private final SVGSubeffectCharmView view;
  private final IMultipleEffectCharmConfiguration model;

  public MultipleEffectCharmPresenter(
      IResources resources,
      SVGSubeffectCharmView subeffectView,
      IMultipleEffectCharmConfiguration model) {
    this.resources = resources;
    this.view = subeffectView;
    this.model = model;
  }

  public void initPresentation() {
    for (final ISubeffect subeffect : model.getEffects()) {
      String label = resources.getString(model.getCharm().getId() + ".Subeffects." + subeffect.getId()); //$NON-NLS-1$
      final IBooleanValueView display = view.addSubeffect(label);
      subeffect.addChangeListener(new IChangeListener() {
        public void changeOccured() {
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