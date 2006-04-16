package net.sf.anathema.framework.reporting.itext;

import java.awt.Color;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.framework.itemdata.IBasicItemData;
import net.sf.anathema.framework.itemdata.IItemDescription;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.styledtext.model.ITextFormat;
import net.sf.anathema.framework.styledtext.model.ITextPart;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.MultiColumnText;

public class NoteReport implements IITextReport {

  public void performPrint(IItem item, Document document) throws ReportException {
    if (!supports(item)) {
      throw new IllegalArgumentException("Item not supported: " + item.getDisplayName()); //$NON-NLS-1$
    }
    IItemDescription noteDescription = ((IBasicItemData) item.getItemData()).getDescription();
    ITextPart[] text = noteDescription.getContent().getText();
    try {
      MultiColumnText columnText = new MultiColumnText();
      columnText.addRegularColumns(document.left(), document.right(), 10f, 2);
      columnText.addElement(createNewParagraph(noteDescription.getName().getText(), Element.ALIGN_CENTER, Font.BOLD));
      Paragraph paragraph = new Paragraph();
      for (ITextPart textpart : text) {
        Font font = createDefaultFont(getStyle(textpart.getFormat()));
        paragraph.add(new Chunk(textpart.getText(), font));
      }
      columnText.addElement(paragraph);
      document.add(columnText);
    }
    catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private int getStyle(ITextFormat format) {
    if (!format.isStyled()) {
      return Font.NORMAL;
    }
    int style = Font.NORMAL;
    if (format.isUnderline()) {
      style = style | Font.UNDERLINE;
    }
    FontStyle fontStyle = format.getFontStyle();
    if (fontStyle.isBold()) {
      style = style | Font.BOLD;
    }
    if (fontStyle.isItalic()) {
      style = style | Font.ITALIC;
    }
    return style;
  }

  public boolean supports(IItem item) {
    if (item == null) {
      return false;
    }
    return item.getItemData() instanceof IBasicItemData;
  }

  private static Element createNewParagraph(String text, int alignment, int style) {
    Font font = createDefaultFont(style);
    Paragraph paragraph = new Paragraph(text, font);
    paragraph.setAlignment(alignment);
    paragraph.setLeading(font.size() * 1.2f);
    return paragraph;
  }

  private static Font createDefaultFont(int style) {
    return FontFactory.getFont("Helvetica", 10, style, Color.BLACK);
  }
}