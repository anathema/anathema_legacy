package net.sf.anathema.character.impl.module.preferences;

import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.CHARMORDER_PREFERENCE;
import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.DEFAULT_CHARMORDER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.generic.framework.reporting.datasource.CharmOrderType;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.resources.IResources;

public class CharmOrderPreferenceElement extends AbstractCharacterPreferencesElement {

  private CharmOrderType charmorder = CharmOrderType.valueOf(CHARACTER_PREFERENCES.get(
      CHARMORDER_PREFERENCE,
      DEFAULT_CHARMORDER));
  private boolean dirty;
  private LabelledPreferenceComboBox box;

  public IDialogComponent getComponent(IResources resources) {
    String labelText = resources.getString("Character.Tools.Preferences.CharmOrder") + ":"; //$NON-NLS-1$ //$NON-NLS-2$
    IdentificateSelectCellRenderer renderer = new IdentificateSelectCellRenderer("CharmOrder.", resources); //$NON-NLS-1$
    CharmOrderType[] values = CharmOrderType.values();
    box = new LabelledPreferenceComboBox(labelText, renderer, values);
    box.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Object selectedItem = box.getSelectedItem();
        if (selectedItem != charmorder) {
          charmorder = (CharmOrderType) selectedItem;
          dirty = true;
        }
      }
    });
    box.setSelectedItem(charmorder);
    return box.getDialogComponent();
  }

  public void savePreferences() {
    CHARACTER_PREFERENCES.put(CHARMORDER_PREFERENCE, charmorder.getId());
  }

  public boolean isDirty() {
    return dirty;
  }

  public void reset() {
    charmorder = CharmOrderType.valueOf(CHARACTER_PREFERENCES.get(CHARMORDER_PREFERENCE, DEFAULT_CHARMORDER));
    box.setSelectedItem(charmorder);
    dirty = false;
  }
}