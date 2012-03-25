package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public interface ContentEncoder {

  void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException;

  boolean hasContent(ReportSession session);

  String getHeader(ReportSession session);
}
