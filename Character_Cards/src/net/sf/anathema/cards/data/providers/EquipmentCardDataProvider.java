package net.sf.anathema.cards.data.providers;

import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import net.sf.anathema.cards.data.EquipmentCardData;
import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.EquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.IEquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentCardDataProvider implements ICardDataProvider {

	private final IResources resources;
	private final IEquipmentStringBuilder stringBuilder;
	// value of black circle character in standard symbol font
	private final static char SYMBOL_BLACK_CIRCLE = (char)183; 
	
	public EquipmentCardDataProvider(IResources resources) {
		this.resources = resources;
		this.stringBuilder = new EquipmentStringBuilder(resources);
	}
	
	@Override
	public ICardData[] getCards(ICharacter character,
			ICardReportResourceProvider resourceProvider) {
		IEquipmentAdditionalModel model = (IEquipmentAdditionalModel) character.getStatistics().
				getCharacterContext().getAdditionalModel(IEquipmentAdditionalModelTemplate.ID);
		List<ICardData> data = new ArrayList<ICardData>();
		for (IEquipmentItem item : model.getEquipmentItems()) {
			String title = item.getTitle();
			Paragraph headerText = new Paragraph();
			if (hasCustomTitle(item)) {
				headerText.add(new Phrase(item.getTemplateId(), resourceProvider.getNormalFont()));
			}
			if (item.getMaterialComposition() == MaterialComposition.Variable) {
				String itemMaterial = "";
				if (hasCustomTitle(item)) itemMaterial += " (";
				itemMaterial += item.getMaterial().getId();
				if (hasCustomTitle(item)) itemMaterial += ")";
				headerText.add(new Phrase(itemMaterial, resourceProvider.getNormalFont()));
			}
			if (!headerText.isEmpty()) {
				headerText.add(new Phrase("\n"));
			}
			if (item.getCost() != null) {
				String[] costSegments = item.getCost().toString().split(" ");
				costSegments[1] = costSegments[1].replace('*', SYMBOL_BLACK_CIRCLE);
				
				headerText.add(new Phrase(costSegments[0] + " ", resourceProvider.getNormalFont()));
				headerText.add(new Phrase(costSegments[1], resourceProvider.getSymbolFont()));
			}
			
			List<Phrase> bodyText = new ArrayList<Phrase>();
			
			String description = item.getDescription();
			if (description != null && !description.isEmpty()) {
				bodyText.add(new Phrase(description, resourceProvider.getNormalFont()));
				bodyText.add(new Phrase("\n", resourceProvider.getNormalFont()));
			}
			for (IEquipmentStats stats : item.getStats()) {
				Paragraph statsParagraph = new Paragraph();
				if (stats instanceof IArtifactStats) {
					IArtifactStats artifactStats = (IArtifactStats) stats;
					if (artifactStats.getAttuneType() != ArtifactAttuneType.FullyAttuned) {
						continue;
					}
					statsParagraph.add(new Phrase(resources.getString("Equipment.Stats.Short.AttuneCost").trim() + ": ", resourceProvider.getBoldFont()));
					statsParagraph.add(new Phrase(artifactStats.getAttuneCost() + "m", resourceProvider.getNormalFont()));
				} else {
					String statsString = stringBuilder.createString(item, stats);
					statsParagraph.add(new Phrase(stats.getId() + ": ", resourceProvider.getBoldFont()));
					statsParagraph.add(new Phrase(statsString.substring(statsString.indexOf(':') + 2), resourceProvider.getNormalFont()));
				}
				
				bodyText.add(statsParagraph);
			}
			
			data.add(new EquipmentCardData(title, headerText, bodyText.toArray(new Phrase[0]),
					resourceProvider.getNullIcon()));
		}
		return data.toArray(new ICardData[0]);
	}
	
	private boolean hasCustomTitle(IEquipmentItem item) {
		return !item.getTemplateId().equals(item.getTitle());
	}

}
