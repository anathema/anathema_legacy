package net.sf.anathema.initialization.modules;

import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.lib.control.IClosure;

public abstract class AbstractModuleInitializer implements IModuleInitializer {

  private final IModuleCollection moduleCollection;

  public AbstractModuleInitializer(IModuleCollection moduleCollection) {
    this.moduleCollection = moduleCollection;
  }

  public final void initialize() {
    moduleCollection.forAllDo(new IClosure<IAnathemaModule>() {
      public void execute(IAnathemaModule input) {
        initialize(input);
      }
    });
  }

  protected abstract void initialize(IAnathemaModule module);
}