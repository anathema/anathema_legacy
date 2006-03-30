package net.sf.anathema.character.view.repository;

import javax.swing.JComponent;

import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public interface IRuleSetSelectionView {

  public JComponent getComponent();

  public IExaltedRuleSet getSelectedRules();

  public void setAvailableRulesets(IExaltedRuleSet[] supportedRuleSets);

  public void initRulesSelectionView();

}