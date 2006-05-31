package net.sf.anathema.character.generic.framework.magic.view;

import javax.swing.event.ListSelectionListener;

public interface IMagicLearnView {

  public void setMagicOptions(Object[] magics);

  public void setLearnedMagic(Object[] magics);

  public void clearSelection();

  public void addMagicViewListener(IMagicViewListener listener);

  public void addSelectionListListener(ListSelectionListener listener);

  public void addOptionListListener(ListSelectionListener listener);
}