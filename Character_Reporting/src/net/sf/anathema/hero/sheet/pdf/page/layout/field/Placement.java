package net.sf.anathema.hero.sheet.pdf.page.layout.field;

import net.sf.anathema.hero.sheet.pdf.page.layout.SheetPage;

public interface Placement {

  HeightWithoutParent atStartOf(SheetPage body);
  
  Height below(LayoutField field);
  
  Height rightOf(LayoutField field);
}
