package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;

public interface IVariableBoxContentEncoder extends IBoxContentEncoder {

  public float getRequestedHeight(ReportContent content, float width);
}
