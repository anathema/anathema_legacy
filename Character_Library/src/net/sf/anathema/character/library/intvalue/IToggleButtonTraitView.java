package net.sf.anathema.character.library.intvalue;

import net.sf.anathema.character.library.trait.view.ITraitView;
import net.sf.anathema.framework.value.IIntValueView;

public interface IToggleButtonTraitView<K extends ITraitView< ? >> extends
    IIntValueView,
    IToggleButtonView,
    ITraitView<K> {
  // Nothing to do
}