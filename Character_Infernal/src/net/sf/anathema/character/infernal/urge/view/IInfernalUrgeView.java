package net.sf.anathema.character.infernal.urge.view;

import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IInfernalUrgeView extends IVirtueFlawView {

  public ITextView addTextView(String label, int columnCount, int rows);
}