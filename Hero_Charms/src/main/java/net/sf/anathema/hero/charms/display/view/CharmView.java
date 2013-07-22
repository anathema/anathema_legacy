package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.CascadeLoadedListener;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;
import net.sf.anathema.platform.tree.display.TreeRenderer;

public interface CharmView extends SpecialCharmViewContainer {

  TreeRenderer getCharmTreeRenderer();

  void addCascadeLoadedListener(CascadeLoadedListener listener);

  void addCharmTypeSelector(String title, Identifier[] types, AgnosticUIConfiguration uiConfig);

  void addCharmTypeSelectionListener(ObjectValueListener<Identifier> selectionListener);

  void fillCharmGroupBox(Identifier[] charmGroups);

  void fillCharmTypeBox(Identifier[] cascadeTypes);

  void addCharmGroupSelector(String title, AgnosticUIConfiguration uiConfig, ICharmGroupChangeListener selectionListener, Identifier[] allPotentialGroups);

  void addCharmCascadeHelp(String helpText);

  void initGui(ToolTipProperties treeProperties, NodeProperties properties);

  void whenCursorLeavesCharmAreaResetAllPopups();

  void colorNode(String charmId, RGBColor color);

  void setBackgroundColor(RGBColor color);

  void addCharmInteractionListener(NodeInteractionListener listener);
}