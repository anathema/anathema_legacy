package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.interaction.Tool;

import java.util.List;

public interface ToolListView<T> {
  void setObjects(T[] items);

  void addListSelectionListener(Runnable listener);

  List<T> getSelectedItems();

  Tool addTool();
}