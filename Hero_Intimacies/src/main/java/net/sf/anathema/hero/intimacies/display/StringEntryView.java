package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ObjectValueListener;

public interface StringEntryView {

  void addTextChangeListener(ObjectValueListener<String> listener);

  Tool addTool();

  void clear();
}