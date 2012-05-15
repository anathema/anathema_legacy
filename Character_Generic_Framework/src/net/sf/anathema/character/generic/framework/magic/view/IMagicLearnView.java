package net.sf.anathema.character.generic.framework.magic.view;

import net.sf.anathema.lib.util.IIdentificate;

import javax.swing.event.ListSelectionListener;
import java.util.Comparator;

public interface IMagicLearnView {

  void setMagicOptions(Object[] magics);

  void setLearnedMagic(Object[] magics);

  void addMagicOptions(IIdentificate[] magics, Comparator<IIdentificate> comparator);

  void addLearnedMagic(Object[] magics);

  void removeMagicOptions(Object[] magics);

  void removeLearnedMagic(Object[] magics);

  void clearSelection();

  void addMagicViewListener(IMagicViewListener listener);

  void addSelectionListListener(ListSelectionListener listener);

  void addOptionListListener(ListSelectionListener listener);
}