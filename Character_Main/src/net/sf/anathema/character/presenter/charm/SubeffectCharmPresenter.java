package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.model.charm.special.ISubeffect;
import net.sf.anathema.character.model.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.charmtree.batik.intvalue.SVGSubeffectCharmView;
import net.sf.anathema.framework.value.IBooleanValueView;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class SubeffectCharmPresenter implements IPresenter {

  private final IResources resources;
  private final SVGSubeffectCharmView view;
  private final ISubeffectCharmConfiguration model;

  public SubeffectCharmPresenter(
      IResources resources,
      SVGSubeffectCharmView subeffectView,
      ISubeffectCharmConfiguration model) {
    this.resources = resources;
    this.view = subeffectView;
    this.model = model;
  }

  public void initPresentation() {
    for (final ISubeffect subeffect : model.getSubeffects()) {
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
    }
  }
}