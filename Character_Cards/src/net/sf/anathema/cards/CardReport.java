package net.sf.anathema.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import net.sf.anathema.cards.layout.ICardLayout;
import net.sf.anathema.cards.providers.ICardProvider;
import net.sf.anathema.cards.types.ICard;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class CardReport extends AbstractPdfReport {

	private IResources resources;
	private ICardLayout layout;
	private ICardProvider[] cardProviders;
	
	public CardReport(IResources resources, ICardLayout layout, ICardProvider... cardProviders) {
		this.resources = resources;
		this.cardProviders = cardProviders;
		this.layout = layout;
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
			//document.setMargins(20, 20, 10, 10);

			// For now, only one style of report, that includes
			// all spells and charms
			List<ICard> cards = new ArrayList<ICard>();
			for (ICardProvider provider : cardProviders) {
				Collections.addAll(cards, provider.getCards((ICharacter) item.getItemData(),
						layout.getResourceProvider()));
			}

			float documentWidth = document.right() - document.left();
			float documentHeight = document.top() - document.bottom();
			int numCols = (int)(documentWidth / layout.getCardWidth());
			int numRows = (int)(documentHeight / layout.getCardHeight());
			float horizontalGutter = (documentWidth - numCols * layout.getCardWidth()) / (numCols - 1);
			float verticalGutter = (documentHeight - numRows * layout.getCardHeight()) / (numRows - 1);
			int maxPosition = numRows * numCols - 1;
			int position = 0;
			float upperleftX;
			float upperleftY;
			for (ICard card : cards) {
				upperleftX = document.left() + (layout.getCardWidth() + horizontalGutter) * (position / numCols);
				upperleftY = document.top() - (layout.getCardHeight() + verticalGutter) * (position % numCols);
				
				layout.generateCard(card, directContent, upperleftX, upperleftY);
				
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
}
