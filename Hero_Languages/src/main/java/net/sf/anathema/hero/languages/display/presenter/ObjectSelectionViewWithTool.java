package net.sf.anathema.hero.languages.display.presenter;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;

public interface ObjectSelectionViewWithTool<V> extends ObjectSelectionView<V> {

  Tool addTool();
}