package net.sf.anathema.character.impl.module.preferences;

import static net.sf.anathema.character.impl.module.preferences.ICharacterPreferencesConstants.DEFAULT_RULESET;
import static net.sf.anathema.character.impl.module.preferences.ICharacterPreferencesConstants.RULESET_PREFERENCE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class RulesetPreferenceElement implements ICharacterPreferencesElement {

  private ExaltedRuleSet ruleset = ExaltedRuleSet.valueOf(CHARACTER_PREFERENCES.get(RULESET_PREFERENCE, DEFAULT_RULESET));
  private boolean dirty;

  public IDialogComponent getComponent(IResources resources) {
    final JLabel rulesLabel = new JLabel(resources.getString("Character.Tools.Preferences.Ruleset") + ":"); //$NON-NLS-1$ //$NON-NLS-2$
    final JComboBox rulesBox = new JComboBox(ExaltedRuleSet.values());
    rulesBox.setRenderer(new IdentificateSelectCellRenderer("Ruleset.", resources)); //$NON-NLS-1$
    rulesBox.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        if (rulesBox.getSelectedItem() != ruleset) {
          ruleset = (ExaltedRuleSet) rulesBox.getSelectedItem();
          dirty = true;
        }
      }
    });
    rulesBox.setSelectedItem(ruleset);
    return new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        panel.add(rulesLabel);
        panel.add(rulesBox);
      }
    };
  }

  public void savePreferences() {
    CHARACTER_PREFERENCES.put(RULESET_PREFERENCE, ruleset.getId());
  }

  public boolean isDirty() {
    return dirty;
  }

  public IIdentificate getCategory() {
    return CHARACTER_CATEGORY;
  }
}