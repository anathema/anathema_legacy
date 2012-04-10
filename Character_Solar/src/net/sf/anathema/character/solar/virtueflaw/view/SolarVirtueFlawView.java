package net.sf.anathema.character.solar.virtueflaw.view;

import net.sf.anathema.framework.value.IIntValueDisplayFactory;
import net.sf.anathema.character.library.virtueflaw.view.VirtueFlawView;
import net.sf.anathema.character.solar.virtueflaw.presenter.ISolarVirtueFlawView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;

import java.util.ArrayList;
import java.util.List;

public class SolarVirtueFlawView extends VirtueFlawView implements ISolarVirtueFlawView {
  private final List<ITextView> textViews = new ArrayList<ITextView>();

  public SolarVirtueFlawView(IIntValueDisplayFactory factory) {
    super(factory);
  }

  @Override
  public ITextView addTextView(final String labelText, int columns, int rows) {
    final ITextView textView = new AreaTextView(rows, columns);
    textViews.add(textView);
    fillIntoVirtueFlawPanel(labelText, textView);
    return textView;
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    for (ITextView textView : textViews) {
      textView.setEnabled(enabled);
    }
  }
}