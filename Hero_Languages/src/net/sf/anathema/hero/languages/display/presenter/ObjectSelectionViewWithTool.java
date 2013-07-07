package net.sf.anathema.hero.languages.display.presenter;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

public interface ObjectSelectionViewWithTool<V> extends IObjectSelectionView<V> {

  Tool addTool();
}