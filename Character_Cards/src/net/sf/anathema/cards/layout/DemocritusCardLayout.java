package net.sf.anathema.cards.layout;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.cards.ICard;
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
	private static final float ICON_SHADOW_Y_OFFSET = -70;
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
	
	Map<Image, com.itextpdf.text.Image> imageMap = new HashMap<Image, com.itextpdf.text.Image>();
	private final ICardReportResourceProvider provider;
	private final float scale;
	
	public DemocritusCardLayout(IResources resources, float scale) {
		this.scale = scale;
		this.provider = new DemocritusCardResourceProvider(resources);
	}

	@Override
	public void drawCard(ICard card) throws DocumentException {
		drawBaseCard(card);
		
		writeTitle(card);
		writeStats(card);
		drawIcons(card);
		writeKeywords(card);
		writeBody(card);
		writeSource(card);
	}
	
	@Override
	public ICardReportResourceProvider getResourceProvider() {
		return provider;
	}

	private void drawBaseCard(ICard card) {
		//base
		drawImage(card.getPdfContent(), card.getX(), card.getY(),
				provider.getCardBaseImage());
		//stat block
		drawImage(card.getPdfContent(),
				card.getX() + getStatBlockXOffset(),
				card.getY() + getStatBlockYOffset(),
				provider.getCardStatBlockImage());
		//body block
		drawImage(card.getPdfContent(),
				card.getX() + getBodyBlockXOffset(),
				card.getY() + getBodyBlockYOffset(),
				provider.getCardBodyBlockImage());
		
		// right icon
		drawImage(card.getPdfContent(),
				card.getX() + getFirstIconXOffset(),
				card.getY() + getIconYOffset(),
				provider.getCardIconBlockImage());
		
		if (card.getData().getSecondaryIcon() != null) {
			// left icon, first place the shadow
			drawImage(card.getPdfContent(),
					card.getX() + getSecondIconXOffset(),
					card.getY() + getIconShadowYOffset(),
					provider.getCardIconShadowImage());
			drawImage(card.getPdfContent(),
					card.getX() + getSecondIconXOffset(),
					card.getY() + getIconYOffset(),
					provider.getCardIconBlockImage());
		}
	}
	
	private void writeTitle(ICard card) throws DocumentException {
		Rectangle rect = new Rectangle(card.getX() + getTextMargin(), // bottom left X
								 	   card.getY() + getTitleTextYOffset() - provider.getTitleFont().getSize(), // bottom left Y 
								 	   card.getX() + getCardWidth() - getTextMargin(), // top right X
								 	   card.getY() + getTitleTextYOffset()); // top right Y
		writeText(card.getPdfContent(), rect, new Phrase(card.getData().getTitle(), provider.getTitleFont()));
	}
	
	private void drawIcons(ICard card) {
		
		if (card.getData().getPrimaryIcon() != null) {
			drawImage(card.getPdfContent(),
					card.getX() + getFirstIconXOffset(),
					card.getY() + getIconYOffset(),
					card.getData().getPrimaryIcon());
		}
		if (card.getData().getSecondaryIcon() != null) {
			drawImage(card.getPdfContent(),
					card.getX() + getSecondIconXOffset(),
					card.getY() + getIconYOffset(),
					card.getData().getSecondaryIcon());
		}
	}
	
	private void writeStats(ICard card) throws DocumentException {
		Rectangle rect = new Rectangle(card.getX() + getTextMargin(), // bottom left X
			 	   card.getY() + getStatsTextYOffset() - 2 * provider.getBoldFont().getSize(), // bottom left Y 
			 	   card.getX() + getCardWidth() - 2 * getTextMargin(), // top right X
			 	   card.getY() + getStatsTextYOffset()); // top right Y
		writeText(card.getPdfContent(), rect, card.getData().getStats());
	}
	
	private void writeKeywords(ICard card) throws DocumentException {
		Rectangle rect = new Rectangle(card.getX() + getTextMargin(), // bottom left X
			 	   card.getY() + getKeywordYOffset() - 2 * provider.getKeywordFont().getSize(), // bottom left Y 
			 	   card.getX() + getCardWidth() - getTextMargin(), // top right X
			 	   card.getY() + getKeywordYOffset()); // top right Y
		writeText(card.getPdfContent(), rect, new Phrase(card.getData().getKeywords(), provider.getKeywordFont()));
	}
	
	private void writeBody(ICard card) throws DocumentException {
		if (card.getData().getBody().length == 0) return;
		
		Rectangle rect = new Rectangle(card.getX() + getTextMargin(), // bottom left X
			 	   card.getY() + getBodyTextYOffset() - getBodyTextYSpan(), // bottom left Y 
			 	   card.getX() + getCardWidth() - 2 * getTextMargin(), // top right X
			 	   card.getY() + getBodyTextYOffset()); // top right Y
		
	    writeText(card.getPdfContent(), rect, card.getData().getBody());
	}
	
	private void writeSource(ICard card) throws DocumentException {
		Rectangle rect = new Rectangle(card.getX() + getTextMargin(), // bottom left X
			 	   card.getY() + getSourceTextYOffset() - provider.getSourceFont().getSize(), // bottom left Y 
			 	   card.getX() + getCardWidth() - 2 * getTextMargin(), // top right X
			 	   card.getY() + getSourceTextYOffset()); // top right Y
	    writeText(card.getPdfContent(), rect, new Phrase(card.getData().getSource(), provider.getSourceFont()));
	}
	
	private void drawImage(PdfContentByte directContent, float x, float y, Image image) {
		try {
			com.itextpdf.text.Image pdfImage;
			pdfImage = imageMap.get(image);
			if (pdfImage == null) {
				pdfImage = com.itextpdf.text.Image.getInstance(image, null ,false);
				pdfImage.scalePercent(scale * 100);
				imageMap.put(image, pdfImage);
			}
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
	
	private float getIconShadowYOffset() {
		return ICON_SHADOW_Y_OFFSET * scale;
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
