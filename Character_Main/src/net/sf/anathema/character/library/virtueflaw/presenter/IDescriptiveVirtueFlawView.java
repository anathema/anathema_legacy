package net.sf.anathema.character.library.virtueflaw.presenter;

import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IDescriptiveVirtueFlawView extends IVirtueFlawView {

  ITextView addTextView(String label, int columnCount, int rows);
}