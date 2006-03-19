package net.sf.anathema.character.impl.view.repository;

import javax.swing.JComponent;

import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.framework.presenter.view.ObjectSelectionView;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.resources.IResources;

public class RuleSetSelectionView {

  private GridDialogPanel rulesSelectionPanel;
  private ObjectSelectionView selectionView;
  private final IResources resources;
  private final ExaltedRuleSet preferredRuleSet;

  public RuleSetSelectionView(IResources resources, ExaltedRuleSet preferredRuleSet) {
    this.resources = resources;
    this.preferredRuleSet = preferredRuleSet;
  }

  public void initRulesSelectionView() {
    rulesSelectionPanel = new GridDialogPanel(false);
    selectionView = new ObjectSelectionView(ExaltedRuleSet.values(), false);
    selectionView.setSelectedObject(preferredRuleSet);
    selectionView.addTo(resources.getString("CharacterDialog.Ruleset.Select.Label"), //$NON-NLS-1$
        new IdentificateSelectCellRenderer("Ruleset.", resources), rulesSelectionPanel); //$NON-NLS-1$
  }

  public JComponent getComponent() {
    return rulesSelectionPanel.getContent();
  }

  public IExaltedRuleSet getSelectedRules() {
    return (IExaltedRuleSet) selectionView.getComboBox().getSelectedItem();
  }

  public void setAvailableRulesets(IExaltedRuleSet[] supportedRuleSets) {
    IExaltedRuleSet selectedRules = getSelectedRules();
    selectionView.setObjects(supportedRuleSets);
    selectionView.setSelectedObject(ExaltedRuleSet.CoreRules);
    selectionView.setSelectedObject(preferredRuleSet);
    selectionView.setSelectedObject(selectedRules);
  }
}