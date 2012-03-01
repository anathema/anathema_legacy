package net.sf.anathema.cascades.presenter.view;

import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.charmtree.presenter.view.ICascadeSelectionView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

import javax.swing.*;
import java.awt.*;

public interface ICascadeView extends ICascadeSelectionView {

  void setCharmVisuals(String id, Color color);

  void setBackgroundColor(Color color);

  void addRuleSetComponent(IExaltedRuleSet[] elements, ListCellRenderer renderer, String borderTitle);

  void addRuleChangeListener(IObjectValueChangedListener<IExaltedRuleSet> rulesChangedListener);

  void unselect();

  void selectRules(IExaltedRuleSet ruleSet);
}