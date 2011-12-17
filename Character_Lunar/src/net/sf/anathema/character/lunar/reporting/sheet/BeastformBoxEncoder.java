package net.sf.anathema.character.lunar.reporting.sheet;

import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.BoxEncodingUtils;
import net.sf.anathema.character.reporting.encoder.IPdfBoxEncoder;
import net.sf.anathema.character.reporting.util.*;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class BeastformBoxEncoder extends AbstractPdfEncoder implements IPdfBoxEncoder
{
	  private final BaseFont baseFont;
	  private final float smallWidth;
	  private float smallHeight;
	  
	  public BeastformBoxEncoder(BaseFont baseFont, float smallWidth, float smallHeight) {
		    this.baseFont = baseFont;
		    this.smallWidth = smallWidth;
		    this.smallHeight = smallHeight - (HEADER_HEIGHT / 2);
		  }

		  @Override
		  protected BaseFont getBaseFont() {
		    return baseFont;
		  }

		  public void encodeContentBox(PdfContentByte directContent, Bounds contentBounds) {
		    float smallMaxX = contentBounds.x + smallWidth;
		    setFillColorBlack(directContent);
		    directContent.setLineWidth(0.5f);
		    directContent.moveTo(contentBounds.x, contentBounds.y + ARCSPACE);
		    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x, contentBounds.y, 180);
		    directContent.moveTo(contentBounds.x + ARCSPACE, contentBounds.y);
		    directContent.lineTo(smallMaxX - ARCSPACE, contentBounds.y);
		    BoxEncodingUtils.add90DegreeArc(directContent, smallMaxX - ARC_SIZE, contentBounds.y, 270);
		    directContent.moveTo(smallMaxX, contentBounds.y + ARCSPACE);
		    directContent.lineTo(smallMaxX, contentBounds.getMaxY() - ARCSPACE - smallHeight);
		    BoxEncodingUtils.add90DegreeArc(directContent, smallMaxX, contentBounds.getMaxY() - smallHeight - ARC_SIZE, 90);
		    directContent.moveTo(smallMaxX + ARCSPACE, contentBounds.getMaxY() - smallHeight);
		    directContent.lineTo(contentBounds.getMaxX() - ARCSPACE, contentBounds.getMaxY() - smallHeight);
		    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.getMaxX() - ARC_SIZE, contentBounds.getMaxY()
		        - smallHeight, 270);
		    directContent.moveTo(contentBounds.getMaxX(), contentBounds.getMaxY() + ARCSPACE - smallHeight);
		    directContent.lineTo(contentBounds.getMaxX(), contentBounds.getMaxY() - ARCSPACE);
		    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.getMaxX() - ARC_SIZE, contentBounds.getMaxY()
		        - ARC_SIZE, 0);
		    directContent.moveTo(contentBounds.getMinX() + ARCSPACE, contentBounds.getMaxY());
		    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x, contentBounds.getMaxY() - ARC_SIZE, 90);
		    directContent.moveTo(contentBounds.x, contentBounds.getMaxY() - ARCSPACE);
		    directContent.lineTo(contentBounds.x, contentBounds.y + ARCSPACE);
		    directContent.stroke();
		  }
}
