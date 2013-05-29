package net.sf.anathema.character.view;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ICharacterDescriptionView extends IView {

  ITextView addLineView(String labelText);

  ITextView addAreaView(String labelText, int rowCount);

  Tool addEditAction();

  IMultiComponentLine addMultiComponentLine();
}