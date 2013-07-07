package net.sf.anathema.herotype.solar.display.curse;

import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface DescriptiveVirtueFlawView extends VirtueFlawView {

  ITextView addTextView(String label, int columnCount, int rows);
}