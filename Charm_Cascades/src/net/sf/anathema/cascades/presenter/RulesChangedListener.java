package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.Map;
import java.util.Set;

import static java.util.Arrays.sort;

public class RulesChangedListener implements IObjectValueChangedListener<IExaltedRuleSet> {

  private AbstractCharmGroupChangeListener listener;
  private ProxyRuleSet selectedRuleSet;
  private ICascadeView view;
  private Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules;

  public RulesChangedListener(ProxyRuleSet selectedRuleSet, ICascadeView view, AbstractCharmGroupChangeListener listener,
                              Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules) {
    this.selectedRuleSet = selectedRuleSet;
    this.view = view;
    this.listener = listener;
    this.charmMapsByRules = charmMapsByRules;
  }

  @Override
  public void valueChanged(IExaltedRuleSet newValue) {
    IExaltedEdition currentEdition = getCurrentEdition();
    exchangeRules(newValue);
    if (editionIsStill(currentEdition)) {
      return;
    }
    handleEditionChange();
  }

  private boolean editionIsStill(IExaltedEdition currentEdition) {
    return selectedRuleSet.getEdition() == currentEdition;
  }

  private IExaltedEdition getCurrentEdition() {
    if (selectedRuleSet.hasDelegate()) {
      return selectedRuleSet.getEdition();
    }
    return null;
  }

  private void exchangeRules(IExaltedRuleSet newValue) {
    selectedRuleSet.setDelegate(newValue);
  }

  private void handleEditionChange() {
    listener.setEdition(selectedRuleSet.getEdition());
    Set<IIdentificate> typeSet = charmMapsByRules.get(selectedRuleSet.getDelegate()).keySet();
    IIdentificate[] cascadeTypes = typeSet.toArray(new IIdentificate[typeSet.size()]);
    sort(cascadeTypes, new ByCharacterType());
    view.fillCharmTypeBox(cascadeTypes);
    view.unselect();
    view.fillCharmGroupBox(new IIdentificate[0]);
  }
}
