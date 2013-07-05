package net.sf.anathema.character.view;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ICharacterDescriptionView {

  ITextView addLineView(String labelText);

  ITextView addAreaView(String labelText, int rowCount);

  Tool addEditAction();

  IMultiComponentLine addMultiComponentLine();
}