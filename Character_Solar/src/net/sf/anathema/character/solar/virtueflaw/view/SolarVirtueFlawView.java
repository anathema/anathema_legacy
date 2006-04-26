package net.sf.anathema.character.solar.virtueflaw.view;

import net.sf.anathema.character.library.virtueflaw.view.VirtueFlawView;
import net.sf.anathema.character.solar.virtueflaw.presenter.ISolarVirtueFlawView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;

public class SolarVirtueFlawView extends VirtueFlawView implements ISolarVirtueFlawView {

  public ITextView addTextView(final String labelText, int columns, int rows) {
    final ITextView textView = new AreaTextView(rows, columns);
    getTextViews().add(textView);
    fillIntoVirtueFlawPanel(labelText, textView);
    return textView;
  }
}