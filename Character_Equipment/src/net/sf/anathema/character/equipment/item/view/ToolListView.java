package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.util.Closure;

import java.util.List;

public interface ToolListView<T> {
  void setObjects(List<T> items);

  void addListSelectionListener(Runnable listener);

  void addListSelectionListener(Closure<T> listener);

  List<T> getSelectedItems();

  Tool addTool();

  void refresh();
}