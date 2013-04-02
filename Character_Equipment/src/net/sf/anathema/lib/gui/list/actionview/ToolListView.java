package net.sf.anathema.lib.gui.list.actionview;

import net.sf.anathema.interaction.Tool;

public interface ToolListView<T> {
  void setObjects(T[] items);

  void addListSelectionListener(Runnable listener);

  T[] getSelectedItems();

  Tool addTool();
}