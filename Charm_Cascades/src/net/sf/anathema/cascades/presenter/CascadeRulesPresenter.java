package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.charmtree.presenter.CharmRulesPresenter;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.resources.IResources;

import java.util.Map;

public class CascadeRulesPresenter implements CharmRulesPresenter {
  
  private Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules;
  private IResources resources;
  private ICascadeView view;
  private ProxyRuleSet ruleset;
  private AbstractCharmGroupChangeListener selectionListener;

  public CascadeRulesPresenter(IResources resources, ICascadeView view, ProxyRuleSet selectedRuleSet, AbstractCharmGroupChangeListener selectionListener,
                               Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules) {
    this.resources = resources;
    this.view = view;
    this.ruleset = selectedRuleSet;
    this.selectionListener = selectionListener;
    this.charmMapsByRules = charmMapsByRules;
  }

  public void initPresentation() {
    IdentificateSelectCellRenderer renderer = new IdentificateSelectCellRenderer("Ruleset.", resources);
    String title = resources.getString("CharmCascades.RuleSetBox.Title");
    view.addRuleSetComponent(ExaltedRuleSet.values(), renderer, title);
    view.addRuleChangeListener(new RulesChangedListener(ruleset, view, selectionListener, charmMapsByRules));
    view.selectRules(ruleset.getDelegate());
  }
}
