package net.sf.anathema.character.lunar.virtueflaw.view;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.virtueflaw.view.VirtueFlawView;
import net.sf.anathema.character.lunar.virtueflaw.presenter.ILunarVirtueFlawView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;

public class LunarVirtueFlawView extends VirtueFlawView implements ILunarVirtueFlawView {
  private final List<ITextView> textViews = new ArrayList<ITextView>();
  
  public LunarVirtueFlawView(IIntValueDisplayFactory factory)
  {
	  super(factory);
  }

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