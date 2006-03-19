package net.sf.anathema.character.lunar.beastform.view;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnProperties;

public interface IGiftLearnViewProperties extends IMagicLearnProperties {

  public ListSelectionListener getRemoveButtonEnabledListener(JButton button, JList list);

}
