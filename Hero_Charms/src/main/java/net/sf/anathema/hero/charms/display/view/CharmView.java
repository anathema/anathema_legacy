package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.TreeView;

public interface CharmView extends SpecialCharmViewContainer {

  TreeView addTreeView();

  ObjectSelectionView<Identifier> addSelectionView(String title, AgnosticUIConfiguration<Identifier> uiConfig);

  ObjectSelectionView<Identifier> addSelectionViewAndSizeItFor(String title,
                                                               AgnosticUIConfiguration<Identifier> uiConfig,
                                                               Identifier[] objects);

  void whenCursorLeavesCharmAreaResetAllPopups();
}