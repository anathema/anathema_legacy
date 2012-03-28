package net.sf.anathema.cards.reporting;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import net.sf.anathema.cards.types.CharmCard;
import net.sf.anathema.cards.types.ICard;
import net.sf.anathema.cards.types.SpellCard;
import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.description.MagicDescription;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.CharmStats;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.SpellStats;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class CardReport extends AbstractPdfReport {

	private IResources resources;
	private IAnathemaModel model;
	private ICardReportProperties properties;
	
	private static float SCALE = .23f;
	private static final int CARD_WIDTH = (int)(745 * SCALE);
	private static final int CARD_HEIGHT = (int)(1040 * SCALE);
	private static final int STAT_BLOCK_X_OFFSET = (int)(34 * SCALE);
	private static final int STAT_BLOCK_Y_OFFSET = (int)(-86 * SCALE);
	private static final int BODY_BLOCK_X_OFFSET = (int)(32 * SCALE);
	private static final int BODY_BLOCK_Y_OFFSET = (int)(-246 * SCALE);
	private static final int ICONS_Y_OFFSET = (int)(-80 * SCALE);
	private static final int FIRST_ICON_X_OFFSET = (int)(465 * SCALE);
	private static final int SECOND_ICON_X_OFFSET = (int)(596 * SCALE);
	
	private static final int TEXT_MARGIN = (int)(40 * SCALE);
	
	private static final int TITLE_TEXT_Y_OFFSET = (int)(-30 * SCALE);
	private static final int STATS_TEXT_Y_OFFSET = (int)(-95 * SCALE);
	private static final int KEYWORD_TEXT_Y_OFFSET = (int)(-205 * SCALE);
	private static final int BODY_TEXT_Y_OFFSET = (int)(-260 * SCALE);
	private static final int BODY_TEXT_Y_SPAN = (int)(710 * SCALE);
	private static final int SOURCE_TEXT_Y_OFFSET = (int)(-1000 * SCALE);
	
	public CardReport(IResources resources, IAnathemaModel model) {
		this.resources = resources;
		this.model = model;
		this.properties = new CardReportProperties(resources);
	}
	
	@Override
	public String toString() {
	  return resources.getString("CardsReport.CharmsSpells.Name"); //$NON-NLS-1$
	}
	
	@Override
	public void performPrint(IItem item, Document document, PdfWriter writer)
			throws ReportException {
		try {
			PdfContentByte directContent = writer.getDirectContent();
			document.setMargins(20, 20, 10, 10);
	
			ICharacter character = (ICharacter) item.getItemData();
			
			int numCols = (int)((document.right() - document.left()) / CARD_WIDTH);
			int numRows = (int)((document.top() - document.bottom()) / CARD_HEIGHT);
			
			// For now, only one style of report, that includes
			// all spells and charms
			List<ICard> cards = new ArrayList<ICard>();
			for (ICharm charm : getCurrentCharms(character)) {
				cards.add(new CharmCard(charm, createCharmStats(character, charm),
						getCharmDescription(charm),
						properties, resources));
			}
			for (ISpell spell : getCurrentSpells(character)) {
				cards.add(new SpellCard(spell, createSpellStats(spell),
						getCharmDescription(spell),
						properties, resources));
			}
			
			int maxPosition = numRows * numCols - 1;
			int position = 0;
			float upperleftX;
			float upperleftY;
			for (ICard card : cards) {
				upperleftX = document.left() + CARD_WIDTH * (position / numCols);
				upperleftY = document.top() - CARD_HEIGHT * (position % numCols);
				
				drawBaseCard(directContent, upperleftX, upperleftY, card.getLeftIcon() != null);
				
				writeTitle(directContent, upperleftX, upperleftY, card.getTitle());
				writeStats(directContent, upperleftX, upperleftY, card.getStats());
				drawIcons(directContent, upperleftX, upperleftY,
						card.getLeftIcon(), card.getRightIcon());
				writeKeywords(directContent, upperleftX, upperleftY, card.getKeywords());
				writeBody(directContent, upperleftX, upperleftY, card.getBody());
				writeSource(directContent, upperleftX, upperleftY, card.getSource());
				
				if (position == maxPosition) {
					position = 0;
					document.newPage();
				}
				else {
					position++;
				}
			}
		}
		catch (Exception e) {
			throw new ReportException(e);
		}
	}
	
	private void drawBaseCard(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY, boolean twoIcons) {
		//base
		drawImage(directContent, cardUpperleftX, cardUpperleftY, properties.getCardBaseImage());
		//stat block
		drawImage(directContent,
				cardUpperleftX + STAT_BLOCK_X_OFFSET,
				cardUpperleftY + STAT_BLOCK_Y_OFFSET,
				properties.getCardStatBlockImage());
		//body block
		drawImage(directContent,
				cardUpperleftX + BODY_BLOCK_X_OFFSET,
				cardUpperleftY + BODY_BLOCK_Y_OFFSET,
				properties.getCardBodyBlockImage());
		
		// right icon
		drawImage(directContent,
				cardUpperleftX + SECOND_ICON_X_OFFSET,
				cardUpperleftY + ICONS_Y_OFFSET,
				properties.getCardIconBlockImage());
		
		if (twoIcons) {
			// left icon
			drawImage(directContent,
					cardUpperleftX + FIRST_ICON_X_OFFSET,
					cardUpperleftY + ICONS_Y_OFFSET,
					properties.getCardIconBlockImage());
		}
	}
	
	private void writeTitle(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY, String title) throws DocumentException {
		Rectangle rect = new Rectangle(cardUpperleftX + TEXT_MARGIN, // bottom left X
								 	   cardUpperleftY + TITLE_TEXT_Y_OFFSET - properties.getTitleFont().getSize(), // bottom left Y 
								 	   cardUpperleftX + CARD_WIDTH - TEXT_MARGIN, // top right X
								 	   cardUpperleftY + TITLE_TEXT_Y_OFFSET); // top right Y
		writeText(directContent, rect, new Phrase(title, properties.getTitleFont()));
	}
	
	private void drawIcons(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY,
			Image iconLeft, Image iconRight) {
		
		if (iconLeft != null) {
			drawImage(directContent,
					cardUpperleftX + FIRST_ICON_X_OFFSET,
					cardUpperleftY + ICONS_Y_OFFSET,
					iconLeft);
		}
		if (iconRight != null) {
			drawImage(directContent,
					cardUpperleftX + SECOND_ICON_X_OFFSET,
					cardUpperleftY + ICONS_Y_OFFSET,
					iconRight);
		}
	}
	
	private void writeStats(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY, Phrase... statPhrases) throws DocumentException {
		Rectangle rect = new Rectangle(cardUpperleftX + TEXT_MARGIN, // bottom left X
			 	   cardUpperleftY + STATS_TEXT_Y_OFFSET - 2 * properties.getBoldFont().getSize(), // bottom left Y 
			 	   cardUpperleftX + CARD_WIDTH - 2 * TEXT_MARGIN, // top right X
			 	   cardUpperleftY + STATS_TEXT_Y_OFFSET); // top right Y
		writeText(directContent, rect, statPhrases);
	}
	
	private void writeKeywords(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY, String keywords) throws DocumentException {
		Rectangle rect = new Rectangle(cardUpperleftX + TEXT_MARGIN, // bottom left X
			 	   cardUpperleftY + KEYWORD_TEXT_Y_OFFSET - 2 * properties.getKeywordFont().getSize(), // bottom left Y 
			 	   cardUpperleftX + CARD_WIDTH - TEXT_MARGIN, // top right X
			 	   cardUpperleftY + KEYWORD_TEXT_Y_OFFSET); // top right Y
		writeText(directContent, rect, new Phrase(keywords, properties.getKeywordFont()));
	}
	
	private void writeBody(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY, Phrase[] bodyPhrases) throws DocumentException {
		if (bodyPhrases.length == 0) return;
		
		Rectangle rect = new Rectangle(cardUpperleftX + TEXT_MARGIN, // bottom left X
			 	   cardUpperleftY + BODY_TEXT_Y_OFFSET - BODY_TEXT_Y_SPAN, // bottom left Y 
			 	   cardUpperleftX + CARD_WIDTH - 2 * TEXT_MARGIN, // top right X
			 	   cardUpperleftY + BODY_TEXT_Y_OFFSET); // top right Y
		
	    writeText(directContent, rect, bodyPhrases);
	}
	
	private void writeSource(PdfContentByte directContent, float cardUpperleftX, float cardUpperleftY, String source) throws DocumentException {
		Rectangle rect = new Rectangle(cardUpperleftX + TEXT_MARGIN, // bottom left X
			 	   cardUpperleftY + SOURCE_TEXT_Y_OFFSET - properties.getSourceFont().getSize(), // bottom left Y 
			 	   cardUpperleftX + CARD_WIDTH - 2 * TEXT_MARGIN, // top right X
			 	   cardUpperleftY + SOURCE_TEXT_Y_OFFSET); // top right Y
	    writeText(directContent, rect, new Phrase(source, properties.getSourceFont()));
	}
	
	private void writeText(PdfContentByte directContent, Rectangle rect, Phrase... phrases) throws DocumentException {
		ColumnText column = new ColumnText(directContent);
		column.setSimpleColumn(rect);
		column.setLeading(phrases[0].getFont().getSize());
		for (Phrase phrase : phrases) {
			column.addText(phrase);
		}
		column.go();
	}
	
	private void drawImage(PdfContentByte directContent, float x, float y, Image image) {
		try {
			com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(image, null ,false);
			pdfImage.scalePercent(SCALE * 100);
			directContent.addImage(pdfImage, pdfImage.getScaledWidth(), 0, 0, pdfImage.getScaledHeight(), x, y - pdfImage.getScaledHeight());
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean supports(IItem item) {
		if (item == null || !(item.getItemData() instanceof ICharacter)) {
		      return false;
		}
		ICharacter character = (ICharacter) item.getItemData();
		if (!character.hasStatistics()) {
		   return false;
		}
		return getCurrentCharms(character).length > 0;
	}
	
	private ICharm[] getCurrentCharms(ICharacter character) {
	  return character.getStatistics().getCharms().getLearnedCharms(character.getStatistics().isExperienced());
	}
	
	private ISpell[] getCurrentSpells(ICharacter character) {
	  return character.getStatistics().getSpells().getLearnedSpells(character.getStatistics().isExperienced());
	}
	
	private MagicDescription getCharmDescription(IMagic magic) {
	    return CharmDescriptionProviderExtractor.CreateFor(model, resources).getCharmDescription(magic);
	}

	private SpellStats createSpellStats(ISpell spell) {
	  return new SpellStats(spell);
	}
	
	private CharmStats createCharmStats(ICharacter character, ICharm charm) {
		return new CharmStats(charm, createGenericCharacter(character));
	}
	
	private GenericCharacter createGenericCharacter(ICharacter character) {
		return new GenericCharacter(character.getStatistics());
	}
}
