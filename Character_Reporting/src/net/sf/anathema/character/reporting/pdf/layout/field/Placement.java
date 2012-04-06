package net.sf.anathema.character.reporting.pdf.layout.field;

import net.sf.anathema.character.reporting.pdf.layout.SheetPage;

public interface Placement {

  HeightWithoutParent atStartOf(SheetPage body);
  
  Height below(LayoutField field);
  
  Height rightOf(LayoutField field);
}
