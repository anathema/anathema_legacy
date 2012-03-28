package net.sf.anathema.cards.layout;

import java.awt.Image;
import java.io.IOException;

import net.sf.anathema.cards.types.ICard;
import net.sf.anathema.lib.resources.IResources;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;

public class DemocritusCardLayout extends AbstractCardLayout {

	private static final int CARD_WIDTH = 745;
	private static final int CARD_HEIGHT = 1040;
	private static final float STAT_BLOCK_X_OFFSET = 34;
	private static final float STAT_BLOCK_Y_OFFSET = -86;
	private static final float BODY_BLOCK_X_OFFSET = 32;
	private static final float BODY_BLOCK_Y_OFFSET = -246;
	private static final float ICONS_Y_OFFSET = -80;
	private static final float FIRST_ICON_X_OFFSET = 596;
	private static final float SECOND_ICON_X_OFFSET = 465;
	
	private static final float TEXT_MARGIN = 40;
	private static final float TITLE_TEXT_Y_OFFSET = -30;
	private static final float STATS_TEXT_Y_OFFSET = -98;
	private static final float KEYWORD_TEXT_Y_OFFSET = -205;
	private static final float BODY_TEXT_Y_OFFSET = -260;
	private static final float BODY_TEXT_Y_SPAN = 710;
	private static final float SOURCE_TEXT_Y_OFFSET = -1000;
	
	private final ICardReportResourceProvider provider;
	private final float scale;
	
	public DemocritusCardLayout(IResources resources, float scale) {
		this.scale = scale;
		this.provider = new DemocritusCardResourceProvider(resources);
	}

	@Override
	public void generateCard(ICard card, PdfContentByte directContent, float upperleftX,
			float upperleftY) throws DocumentException {
		drawBaseCard(directContent, upperleftX, upperleftY, card.getSecondaryIcon() != null);
		
		writeTitle(directContent, upperleftX, upperleftY, card.getTitle());
		writeStats(directContent, upperleftX, upperleftY, card.getStats());
		drawIcons(directContent, upperleftX, upperleftY,
				card.getPrimaryIcon(), card.getSecondaryIcon());
		writeKeywords(directContent, upperleftX, upperleftY, card.getKeywords());
		writeBody(directContent, upperleftX, upperleftY, card.getBody());
		writeSource(directContent, upperleftX, upperleftY, card.getSource());
	}
	
	@Override
	public ICardReportResourceProvider getResourceProvider() {
		return provider;
	}

	private void drawBaseCard(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY, boolean twoIcons) {
		//base
		drawImage(directContent, cardUpperleftX, cardUpperleftY, provider.getCardBaseImage());
		//stat block
		drawImage(directContent,
				cardUpperleftX + getStatBlockXOffset(),
				cardUpperleftY + getStatBlockYOffset(),
				provider.getCardStatBlockImage());
		//body block
		drawImage(directContent,
				cardUpperleftX + getBodyBlockXOffset(),
				cardUpperleftY + getBodyBlockYOffset(),
				provider.getCardBodyBlockImage());
		
		// right icon
		drawImage(directContent,
				cardUpperleftX + getFirstIconXOffset(),
				cardUpperleftY + getIconYOffset(),
				provider.getCardIconBlockImage());
		
		if (twoIcons) {
			// left icon
			drawImage(directContent,
					cardUpperleftX + getSecondIconXOffset(),
					cardUpperleftY + getIconYOffset(),
					provider.getCardIconBlockImage());
		}
	}
	
	private void writeTitle(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY, String title) throws DocumentException {
		Rectangle rect = new Rectangle(cardUpperleftX + getTextMargin(), // bottom left X
								 	   cardUpperleftY + getTitleTextYOffset() - provider.getTitleFont().getSize(), // bottom left Y 
								 	   cardUpperleftX + getCardWidth() - getTextMargin(), // top right X
								 	   cardUpperleftY + getTitleTextYOffset()); // top right Y
		writeText(directContent, rect, new Phrase(title, provider.getTitleFont()));
	}
	
	private void drawIcons(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY,
			Image iconRight, Image iconLeft) {
		
		if (iconRight != null) {
			drawImage(directContent,
					cardUpperleftX + getFirstIconXOffset(),
					cardUpperleftY + getIconYOffset(),
					iconRight);
		}
		if (iconLeft != null) {
			drawImage(directContent,
					cardUpperleftX + getSecondIconXOffset(),
					cardUpperleftY + getIconYOffset(),
					iconLeft);
		}
	}
	
	private void writeStats(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY, Phrase... statPhrases) throws DocumentException {
		Rectangle rect = new Rectangle(cardUpperleftX + getTextMargin(), // bottom left X
			 	   cardUpperleftY + getStatsTextYOffset() - 2 * provider.getBoldFont().getSize(), // bottom left Y 
			 	   cardUpperleftX + getCardWidth() - 2 * getTextMargin(), // top right X
			 	   cardUpperleftY + getStatsTextYOffset()); // top right Y
		writeText(directContent, rect, statPhrases);
	}
	
	private void writeKeywords(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY, String keywords) throws DocumentException {
		Rectangle rect = new Rectangle(cardUpperleftX + getTextMargin(), // bottom left X
			 	   cardUpperleftY + getKeywordYOffset() - 2 * provider.getKeywordFont().getSize(), // bottom left Y 
			 	   cardUpperleftX + getCardWidth() - getTextMargin(), // top right X
			 	   cardUpperleftY + getKeywordYOffset()); // top right Y
		writeText(directContent, rect, new Phrase(keywords, provider.getKeywordFont()));
	}
	
	private void writeBody(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY, Phrase[] bodyPhrases) throws DocumentException {
		if (bodyPhrases.length == 0) return;
		
		Rectangle rect = new Rectangle(cardUpperleftX + getTextMargin(), // bottom left X
			 	   cardUpperleftY + getBodyTextYOffset() - getBodyTextYSpan(), // bottom left Y 
			 	   cardUpperleftX + getCardWidth() - 2 * getTextMargin(), // top right X
			 	   cardUpperleftY + getBodyTextYOffset()); // top right Y
		
	    writeText(directContent, rect, bodyPhrases);
	}
	
	private void writeSource(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY, String source) throws DocumentException {
		Rectangle rect = new Rectangle(cardUpperleftX + getTextMargin(), // bottom left X
			 	   cardUpperleftY + getSourceTextYOffset() - provider.getSourceFont().getSize(), // bottom left Y 
			 	   cardUpperleftX + getCardWidth() - 2 * getTextMargin(), // top right X
			 	   cardUpperleftY + getSourceTextYOffset()); // top right Y
	    writeText(directContent, rect, new Phrase(source, provider.getSourceFont()));
	}
	
	private void drawImage(PdfContentByte directContent, float x, float y, Image image) {
		try {
			com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(image, null ,false);
			pdfImage.scalePercent(scale * 100);
			drawImage(directContent, x, y, pdfImage);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getCardWidth() {
		return (int) (CARD_WIDTH * scale);
	}

	@Override
	public int getCardHeight() {
		return (int) (CARD_HEIGHT * scale);
	}
	
	private float getTitleTextYOffset() {
		return TITLE_TEXT_Y_OFFSET * scale;
	}
	
	private float getStatBlockXOffset() {
		return STAT_BLOCK_X_OFFSET * scale;
	}
	
	private float getStatBlockYOffset() {
		return STAT_BLOCK_Y_OFFSET * scale;
	}
	
	private float getStatsTextYOffset() {
		return STATS_TEXT_Y_OFFSET * scale;
	}
	
	private float getBodyBlockXOffset() {
		return BODY_BLOCK_X_OFFSET * scale;
	}
	
	private float getBodyBlockYOffset() {
		return BODY_BLOCK_Y_OFFSET * scale;
	}
	
	private float getBodyTextYOffset() {
		return BODY_TEXT_Y_OFFSET * scale;
	}
	
	private float getBodyTextYSpan() {
		return BODY_TEXT_Y_SPAN * scale;
	}
	
	private float getFirstIconXOffset() {
		return FIRST_ICON_X_OFFSET * scale;
	}
	
	private float getSecondIconXOffset() {
		return SECOND_ICON_X_OFFSET * scale;
	}
	
	private float getIconYOffset() {
		return ICONS_Y_OFFSET * scale;
	}
	
	private float getKeywordYOffset() {
		return KEYWORD_TEXT_Y_OFFSET * scale;
	}
	
	private float getSourceTextYOffset() {
		return SOURCE_TEXT_Y_OFFSET * scale;
	}
	
	private float getTextMargin() {
		return TEXT_MARGIN * scale;
	}
}
