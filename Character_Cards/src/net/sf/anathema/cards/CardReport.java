package net.sf.anathema.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.data.providers.ICardDataProvider;
import net.sf.anathema.cards.layout.ICardLayout;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class CardReport extends AbstractPdfReport {

	private IResources resources;
	private ICardLayout layout;
	private ICardDataProvider[] cardDataProviders;
	
	public CardReport(IResources resources, ICardLayout layout, ICardDataProvider... cardProviders) {
		this.resources = resources;
		this.cardDataProviders = cardProviders;
		this.layout = layout;
	}
	
	@Override
	public String toString() {
	  return resources.getString("CardsReport.Name"); //$NON-NLS-1$
	}
	
	@Override
	public void performPrint(IItem item, Document document, PdfWriter writer)
			throws ReportException {
		try {
			PdfContentByte directContent = writer.getDirectContent();
			document.setMargins(
					20,
					20,
					document.topMargin(),
					document.bottomMargin());

			// For now, only one style of report, that includes
			// all spells and charms
			List<ICardData> cardDataSet = new ArrayList<ICardData>();
			for (ICardDataProvider provider : cardDataProviders) {
				Collections.addAll(cardDataSet, provider.getCards((ICharacter) item.getItemData(),
						layout.getResourceProvider()));
			}

			float documentWidth = document.right() - document.left();
			float documentHeight = document.top() - document.bottom();
			int numCols = (int)(documentWidth / layout.getCardWidth());
			int numRows = (int)(documentHeight / layout.getCardHeight());
			float horizontalGutter = (documentWidth - numCols * layout.getCardWidth()) / (numCols - 1);
			float verticalGutter = (documentHeight - numRows * layout.getCardHeight()) / (numRows - 1);
			int maxPosition = numRows * numCols;
			int position = 0;
			for (ICardData cardData : cardDataSet) {
				if (position == maxPosition ||
					(cardData.wantsNewPage() && position > 0)) {
					position = 0;
					document.newPage();
				}
				
				float upperleftX = document.left() + (layout.getCardWidth() + horizontalGutter) * (position % numCols);
				float upperleftY = document.top() - (layout.getCardHeight() + verticalGutter) * (position / numCols);
				
				Card card = new Card(directContent, upperleftX, upperleftY, cardData);
				layout.drawCard(card);
				
				position++;
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
