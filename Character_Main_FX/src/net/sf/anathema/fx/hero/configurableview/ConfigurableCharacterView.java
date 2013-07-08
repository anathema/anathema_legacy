package net.sf.anathema.fx.hero.configurableview;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ConfigurableCharacterView {

  ITextView addLineView(String labelText);

  ITextView addAreaView(String labelText);

  Tool addEditAction();

  MultiComponentLine addMultiComponentLine();

  <T> IObjectSelectionView<T> addSelectionView(String label, AgnosticUIConfiguration<T> uiConfiguration);

  IIntValueView addDotSelector(String label, int maxValue);
}