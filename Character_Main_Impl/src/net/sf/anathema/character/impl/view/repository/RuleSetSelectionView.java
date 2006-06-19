package net.sf.anathema.character.impl.view.repository;

import javax.swing.JComponent;

import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.view.repository.IRuleSetSelectionView;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.resources.IResources;

public class RuleSetSelectionView implements IRuleSetSelectionView {

  private IGridDialogPanel rulesSelectionPanel;
  private ObjectSelectionView<IExaltedRuleSet> selectionView;
  private final IResources resources;
  private final IExaltedRuleSet preferredRuleSet;

  public RuleSetSelectionView(IResources resources, IExaltedRuleSet preferredRuleSet) {
    this.resources = resources;
    this.preferredRuleSet = preferredRuleSet;
  }

  public void initRulesSelectionView() {
    rulesSelectionPanel = new DefaultGridDialogPanel(false);
    String label = resources.getString("CharacterDialog.Ruleset.Select.Label"); //$NON-NLS-1$
    IdentificateSelectCellRenderer renderer = new IdentificateSelectCellRenderer("Ruleset.", resources); //$NON-NLS-1$
    selectionView = new ObjectSelectionView<IExaltedRuleSet>(label, renderer, ExaltedRuleSet.values(), false);
    selectionView.setSelectedObject(preferredRuleSet);
    selectionView.addComponents(rulesSelectionPanel);
  }

  public JComponent getComponent() {
    return rulesSelectionPanel.getContent();
  }

  public IExaltedRuleSet getSelectedRules() {
    return (IExaltedRuleSet) selectionView.getSelectedObject();
  }

  public void setAvailableRulesets(IExaltedRuleSet[] supportedRuleSets) {
    IExaltedRuleSet selectedRules = getSelectedRules();
    selectionView.setObjects(supportedRuleSets);
    selectionView.setSelectedObject(ExaltedRuleSet.CoreRules);
    selectionView.setSelectedObject(preferredRuleSet);
    selectionView.setSelectedObject(selectedRules);
  }
}