package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.util.Closure;

public interface AgnosticTree {
  void setUiConfiguration(AgnosticUIConfiguration configuration);

  AgnosticTreeNode createRootNode(String rootText);

  void whenSelectionChangesDo(Closure<Object[]> closure);
}
