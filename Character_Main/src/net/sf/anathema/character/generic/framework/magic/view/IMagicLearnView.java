package net.sf.anathema.character.generic.framework.magic.view;

import net.sf.anathema.lib.util.Identified;

import javax.swing.event.ListSelectionListener;
import java.util.Comparator;

public interface IMagicLearnView {

  void addMagicOptions(Identified[] magics, Comparator<Identified> comparator);

  void setMagicOptions(Object[] magics);

  void removeMagicOptions(Object[] magics);

  void addLearnedMagic(Object[] magics);

  void setLearnedMagic(Object[] magics);

  void removeLearnedMagic(Object[] magics);

  void clearSelection();

  void addMagicViewListener(IMagicViewListener listener);

  void addSelectionListListener(ListSelectionListener listener);

  void addOptionListListener(ListSelectionListener listener);
}