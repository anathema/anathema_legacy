package net.sf.anathema.character.impl.module.preferences;

import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.DEFAULT_RULESET;
import static net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants.RULESET_PREFERENCE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.resources.IResources;

public class RulesetPreferenceElement extends AbstractCharacterPreferencesElement {

  private ExaltedRuleSet ruleset = ExaltedRuleSet.valueOf(CHARACTER_PREFERENCES.get(RULESET_PREFERENCE, DEFAULT_RULESET));
  private boolean dirty;
  private LabelledPreferenceComboBox box;

  public void addCompoment(GridDialogPanel panel, IResources resources) {
    panel.add(getComponent(resources));
  }

  private IDialogComponent getComponent(IResources resources) {
    String labelText = resources.getString("Character.Tools.Preferences.Ruleset") + ":"; //$NON-NLS-1$ //$NON-NLS-2$
    IdentificateSelectCellRenderer renderer = new IdentificateSelectCellRenderer("Ruleset.", resources); //$NON-NLS-1$
    ExaltedRuleSet[] values = ExaltedRuleSet.values();
    box = new LabelledPreferenceComboBox(labelText, renderer, values);
    box.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Object selectedItem = box.getSelectedItem();
        if (selectedItem != ruleset) {
          ruleset = (ExaltedRuleSet) selectedItem;
          dirty = true;
        }
      }
    });
    box.setSelectedItem(ruleset);
    return box.getDialogComponent();
  }

  public void savePreferences() {
    CHARACTER_PREFERENCES.put(RULESET_PREFERENCE, ruleset.getId());
  }

  public boolean isDirty() {
    return dirty;
  }

  public void reset() {
    ruleset = ExaltedRuleSet.valueOf(CHARACTER_PREFERENCES.get(RULESET_PREFERENCE, DEFAULT_RULESET));
    box.setSelectedItem(ruleset);
    dirty = false;
  }
}