package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.Graphics;

public interface IBoxContentEncoder {

  public void encode(Graphics graphics, ReportContent reportContent) throws DocumentException;

  public boolean hasContent(ReportContent content);

  public String getHeaderKey(ReportContent reportContent);
}
