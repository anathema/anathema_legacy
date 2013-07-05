package net.sf.anathema.hero.concept.display.description.presenter;

import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.lib.gui.widgets.IIntegerView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface MultiComponentLine {

  ITextView addFieldsView(String labelText);

  IIntegerView addIntegerView(String labelText, IIntegerDescription integerDescription);
}
