package net.sf.anathema.cards.layout;

import net.sf.anathema.cards.ICard;
import net.sf.anathema.lib.resources.IResources;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
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
	private static final float TEXT_TITLE_MARGIN = 30;
	private static final float TITLE_TEXT_Y_OFFSET = -22;
	private static final float STATS_TEXT_Y_OFFSET = -98;
	private static final float KEYWORD_TEXT_Y_OFFSET = -205;
	private static final float BODY_TEXT_Y_OFFSET = -254;
	private static final float BODY_TEXT_Y_SPAN = 710;
	private static final float SOURCE_TEXT_Y_OFFSET = -1000;
	
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
		drawScaledImage(card.getPdfContent(), card.getX(), card.getY(),
				provider.getCardBaseImage());
		//stat block
		drawScaledImage(card.getPdfContent(),
				card.getX() + scale(STAT_BLOCK_X_OFFSET),
				card.getY() + scale(STAT_BLOCK_Y_OFFSET),
				provider.getCardStatBlockImage());
		//body block
		drawScaledImage(card.getPdfContent(),
				card.getX() + scale(BODY_BLOCK_X_OFFSET),
				card.getY() + scale(BODY_BLOCK_Y_OFFSET),
				provider.getCardBodyBlockImage());
		
		// right icon
		drawScaledImage(card.getPdfContent(),
				card.getX() + scale(FIRST_ICON_X_OFFSET),
				card.getY() + scale(ICONS_Y_OFFSET),
				provider.getCardIconBlockImage());
		
		if (card.getData().getSecondaryIcon() != null) {
			// left icon, first place the shadow
			drawScaledImage(card.getPdfContent(),
					card.getX() + scale(SECOND_ICON_X_OFFSET),
					card.getY() + scale(ICON_SHADOW_Y_OFFSET),
					provider.getCardIconShadowImage());
			drawScaledImage(card.getPdfContent(),
					card.getX() + scale(SECOND_ICON_X_OFFSET),
					card.getY() + scale(ICONS_Y_OFFSET),
					provider.getCardIconBlockImage());
		}
	}
	
	private void writeTitle(ICard card) throws DocumentException {
		Rectangle rect = new Rectangle(card.getX() + scale(TEXT_TITLE_MARGIN), // bottom left X
								 	   card.getY() + scale(TITLE_TEXT_Y_OFFSET) - provider.getTitleFont().getSize(), // bottom left Y 
								 	   card.getX() + getCardWidth() - scale(TEXT_TITLE_MARGIN), // top right X
								 	   card.getY() + scale(TITLE_TEXT_Y_OFFSET)); // top right Y
		writeText(card.getPdfContent(), rect, new Phrase(card.getData().getTitle(), provider.getTitleFont()));
	}
	
	private void drawIcons(ICard card) {
		
		if (card.getData().getPrimaryIcon() != null) {
			drawScaledImage(card.getPdfContent(),
					card.getX() + scale(FIRST_ICON_X_OFFSET),
					card.getY() + scale(ICONS_Y_OFFSET),
					card.getData().getPrimaryIcon());
		}
		if (card.getData().getSecondaryIcon() != null) {
			drawScaledImage(card.getPdfContent(),
					card.getX() + scale(SECOND_ICON_X_OFFSET),
					card.getY() + scale(ICONS_Y_OFFSET),
					card.getData().getSecondaryIcon());
		}
	}
	
	private void writeStats(ICard card) throws DocumentException {
		float width = scale(card.getData().getSecondaryIcon() != null ? SECOND_ICON_X_OFFSET : FIRST_ICON_X_OFFSET);
		Rectangle rect = new Rectangle(card.getX() + scale(TEXT_MARGIN), // bottom left X
			 	   card.getY() + scale(STATS_TEXT_Y_OFFSET) - 2 * provider.getBoldFont().getSize(), // bottom left Y 
			 	   card.getX() + width, // top right X
			 	   card.getY() + scale(STATS_TEXT_Y_OFFSET)); // top right Y
		writeText(card.getPdfContent(), rect, card.getData().getStats());
	}
	
	private void writeKeywords(ICard card) throws DocumentException {
		Rectangle rect = new Rectangle(card.getX() + scale(TEXT_MARGIN), // bottom left X
			 	   card.getY() + scale(KEYWORD_TEXT_Y_OFFSET) - 2 * provider.getKeywordFont().getSize(), // bottom left Y 
			 	   card.getX() + getCardWidth() - scale(TEXT_MARGIN), // top right X
			 	   card.getY() + scale(KEYWORD_TEXT_Y_OFFSET)); // top right Y
		writeText(card.getPdfContent(), rect, new Phrase(card.getData().getKeywords(), provider.getKeywordFont()));
	}
	
	private void writeBody(ICard card) throws DocumentException {
		Element[] body = card.getData().getBody((int) scale(BODY_TEXT_Y_SPAN));
		if (body.length == 0) return;
		
		Rectangle rect = new Rectangle(card.getX() + scale(TEXT_MARGIN), // bottom left X
			 	   card.getY() + scale(BODY_TEXT_Y_OFFSET) - scale(BODY_TEXT_Y_SPAN), // bottom left Y 
			 	   card.getX() + getCardWidth() - 2 * scale(TEXT_MARGIN), // top right X
			 	   card.getY() + scale(BODY_TEXT_Y_OFFSET)); // top right Y
		
	    writeText(card.getPdfContent(), rect, body);
	}
	
	private void writeSource(ICard card) throws DocumentException {
		Rectangle rect = new Rectangle(card.getX() + scale(TEXT_MARGIN), // bottom left X
			 	   card.getY() + scale(SOURCE_TEXT_Y_OFFSET) - provider.getSourceFont().getSize(), // bottom left Y 
			 	   card.getX() + getCardWidth() - 2 * scale(TEXT_MARGIN), // top right X
			 	   card.getY() + scale(SOURCE_TEXT_Y_OFFSET)); // top right Y
	    writeText(card.getPdfContent(), rect, new Phrase(card.getData().getSource(), provider.getSourceFont()));
	}
	
	protected void adjustContentImage(Image image) {
		image.scalePercent(scale * 100);
	}
	
	private void drawScaledImage(PdfContentByte directContent, float x, float y, Image image) {
		adjustContentImage(image);
		drawImage(directContent, x, y, image);
	}
	
	@Override
	public int getCardWidth() {
		return (int) (scale(CARD_WIDTH));
	}

	@Override
	public int getCardHeight() {
		return (int) (scale(CARD_HEIGHT));
	}
	
	private float scale(float value) {
		return scale * value;
	}
}
