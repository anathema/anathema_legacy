package net.sf.anathema.character.lunar.virtueflaw.presenter;

import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ILunarVirtueFlawView extends IVirtueFlawView {

  public ITextView addTextView(String label, int columnCount, int rows);
}