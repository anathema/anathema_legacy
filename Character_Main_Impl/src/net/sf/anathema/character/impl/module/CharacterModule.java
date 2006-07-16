package net.sf.anathema.character.impl.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public class CharacterModule implements IAnathemaModule {

  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
    new CharacterPerformanceTuner(model, resources).startTuning();
  }
}