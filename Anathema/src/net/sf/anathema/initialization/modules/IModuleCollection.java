package net.sf.anathema.initialization.modules;

import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.lib.control.IClosure;

public interface IModuleCollection {

  public void forAllDo(IClosure<IAnathemaModule> closure);
}