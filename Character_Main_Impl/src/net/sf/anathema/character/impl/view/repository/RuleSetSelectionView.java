package net.sf.anathema.character.impl.view.repository;

import javax.swing.JComponent;

import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.view.repository.IRuleSetSelectionView;
import net.sf.anathema.framework.presenter.view.ObjectSelectionView;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.resources.IResources;

public class RuleSetSelectionView implements IRuleSetSelectionView {

  private IGridDialogPanel rulesSelectionPanel;
  private ObjectSelectionView selectionView;
  private final IResources resources;
  private final ExaltedRuleSet preferredRuleSet;

  public RuleSetSelectionView(IResources resources, ExaltedRuleSet preferredRuleSet) {
    this.resources = resources;
    this.preferredRuleSet = preferredRuleSet;
  }

  public void initRulesSelectionView() {
    rulesSelectionPanel = new DefaultGridDialogPanel(false);
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