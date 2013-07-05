package net.sf.anathema.hero.concept.display.description.presenter;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ICharacterDescriptionView {

  ITextView addLineView(String labelText);

  ITextView addAreaView(String labelText);

  Tool addEditAction();

  IMultiComponentLine addMultiComponentLine();
}