package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ObjectValueListener;

public interface StringEntryView {

  void addTextChangeListener(ObjectValueListener<String> listener);

  void addAddButtonListener(Command listener);

  void setAddButtonEnabled(boolean enabled);

  void clear();
}