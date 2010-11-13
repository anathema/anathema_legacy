package net.sf.anathema.character.generic.framework.magic.view;

import java.util.Comparator;

import javax.swing.event.ListSelectionListener;

import net.sf.anathema.lib.util.IIdentificate;

public interface IMagicLearnView {

  public void setMagicOptions(Object[] magics);

  public void setLearnedMagic(Object[] magics);

  public void addMagicOptions(IIdentificate[] magics, Comparator<IIdentificate> comparator);

  public void addLearnedMagic(Object[] magics);

  public void removeMagicOptions(Object[] magics);

  public void removeLearnedMagic(Object[] magics);

  public void clearSelection();

  public void addMagicViewListener(IMagicViewListener listener);

  public void addSelectionListListener(ListSelectionListener listener);

  public void addOptionListListener(ListSelectionListener listener);
}