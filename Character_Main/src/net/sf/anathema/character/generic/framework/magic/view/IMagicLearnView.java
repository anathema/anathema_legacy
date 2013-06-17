package net.sf.anathema.character.generic.framework.magic.view;

import net.sf.anathema.lib.util.Identifier;

import javax.swing.event.ListSelectionListener;
import java.util.Comparator;

public interface IMagicLearnView {

  void addMagicOptions(Identifier[] magics, Comparator<Identifier> comparator);

  void setMagicOptions(Object[] magics);

  void removeMagicOptions(Object[] magics);

  void addLearnedMagic(Object[] magics);

  void setLearnedMagic(Object[] magics);

  void removeLearnedMagic(Object[] magics);

  void clearSelection();

  void addMagicViewListener(IMagicViewListener listener);

  //todo: Listener
  void addSelectionListListener(ListSelectionListener listener);

  //todo: Listener
  void addOptionListListener(ListSelectionListener listener);
}