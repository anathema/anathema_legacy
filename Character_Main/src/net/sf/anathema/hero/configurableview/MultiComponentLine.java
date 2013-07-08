package net.sf.anathema.hero.configurableview;

import net.sf.anathema.character.main.IIntegerDescription;
import net.sf.anathema.lib.gui.widgets.IIntegerView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface MultiComponentLine {

  ITextView addFieldsView(String labelText);

  IIntegerView addIntegerView(String labelText, IIntegerDescription integerDescription);
}
