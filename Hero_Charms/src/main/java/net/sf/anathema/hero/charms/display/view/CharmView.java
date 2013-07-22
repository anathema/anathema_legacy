package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.CascadeLoadedListener;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.display.NodePresentationProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;
import net.sf.anathema.platform.tree.display.TreeRenderer;
import net.sf.anathema.platform.tree.display.TreeView;

public interface CharmView extends SpecialCharmViewContainer {

  TreeRenderer getCharmTreeRenderer();

  void addCascadeLoadedListener(CascadeLoadedListener listener);

  ObjectSelectionView<Identifier> addSelectionView(String title, AgnosticUIConfiguration<Identifier> uiConfig);

  ObjectSelectionView<Identifier> addSelectionViewAndSizeItFor(String title, AgnosticUIConfiguration<Identifier> uiConfig,
                                                               Identifier[] objects);


  void addCharmCascadeHelp(String helpText);

  TreeView addTreeView(ToolTipProperties treeProperties, NodePresentationProperties properties);

  void whenCursorLeavesCharmAreaResetAllPopups();

  void colorNode(String charmId, RGBColor color);

  void setBackgroundColor(RGBColor color);

  void addCharmInteractionListener(NodeInteractionListener listener);
}