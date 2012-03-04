package net.sf.anathema.character.reporting.pdf.layout.field;

import net.sf.anathema.character.reporting.pdf.layout.Body;

public interface Placement {

  HeightWithoutParent atStartOf(Body body);
  
  Height below(LayoutField field);
  
  Height rightOf(LayoutField field);
}
