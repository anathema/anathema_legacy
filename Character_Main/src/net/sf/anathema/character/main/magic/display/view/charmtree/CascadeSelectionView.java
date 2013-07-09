package net.sf.anathema.character.main.magic.display.view.charmtree;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.presenter.view.CascadeLoadedListener;
import net.sf.anathema.platform.tree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.presenter.view.ToolTipProperties;

public interface CascadeSelectionView {

  CharmTreeRenderer getCharmTreeRenderer();

  void addCascadeLoadedListener(CascadeLoadedListener listener);

  void addCharmTypeSelector(String title, Identifier[] types, AgnosticUIConfiguration uiConfig);

  void addCharmTypeSelectionListener(ObjectValueListener<Identifier> selectionListener);

  void fillCharmGroupBox(Identifier[] charmGroups);

  void fillCharmTypeBox(Identifier[] cascadeTypes);

  void addCharmGroupSelector(String title, AgnosticUIConfiguration uiConfig, ICharmGroupChangeListener selectionListener, Identifier[] allPotentialGroups);

  void addCharmCascadeHelp(String helpText);

  void initGui(ToolTipProperties treeProperties, NodeProperties properties);

  void whenCursorLeavesCharmAreaResetAllPopups();
}