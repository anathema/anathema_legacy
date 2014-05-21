package net.sf.anathema.hero.display.configurableview;

import net.sf.anathema.character.framework.IIntegerDescription;
import net.sf.anathema.lib.gui.widgets.IIntegerView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface MultiComponentLine {

  ITextView addFieldsView(String labelText);

  IIntegerView addIntegerView(String labelText, IIntegerDescription integerDescription);
}
