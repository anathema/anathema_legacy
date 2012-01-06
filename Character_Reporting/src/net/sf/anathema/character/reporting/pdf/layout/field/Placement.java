package net.sf.anathema.character.reporting.pdf.layout.field;

import net.sf.anathema.character.reporting.pdf.layout.Body;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;

public interface Placement {

  HeightWithoutParent atStartOf(Body body);
  
  Height below(LayoutField field);
  
  Height rightOf(LayoutField field);
}
