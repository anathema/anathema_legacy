package net.sf.anathema.cards.data.providers;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import net.sf.anathema.cards.data.EquipmentCardData;
import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.equipment.character.IEquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactAttuneType;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentStringBuilder;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class EquipmentCardDataProvider implements ICardDataProvider {

  private final Resources resources;
  private final IEquipmentStringBuilder stringBuilder;
  // value of black circle character in standard symbol font
  private final static char SYMBOL_BLACK_CIRCLE = (char) 183;

  public EquipmentCardDataProvider(Resources resources) {
    this.resources = resources;
    this.stringBuilder = new EquipmentStringBuilder(resources);
  }

  @Override
  public ICardData[] getCards(Hero hero, ICardReportResourceProvider resourceProvider) {
    EquipmentModel model = EquipmentModelFetcher.fetch(hero);
    List<ICardData> data = new ArrayList<>();
    for (IEquipmentItem item : model.getEquipmentItems()) {
      String title = item.getTitle();
      Paragraph headerText = new Paragraph();
      if (hasCustomTitle(item)) {
        headerText.add(new Phrase(item.getTemplateId(), resourceProvider.getNormalFont()));
      }
      if (item.getMaterialComposition() == MaterialComposition.Variable) {
        String itemMaterial = "";
        if (hasCustomTitle(item)) {
          itemMaterial += " (";
        }
        itemMaterial += item.getMaterial().getId();
        if (hasCustomTitle(item)) {
          itemMaterial += ")";
        }
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

      List<Phrase> bodyText = new ArrayList<>();

      String description = item.getDescription();
      if (description != null && !description.isEmpty()) {
        bodyText.add(new Phrase(description, resourceProvider.getNormalFont()));
        bodyText.add(new Phrase("\n", resourceProvider.getNormalFont()));
      }
      for (IEquipmentStats stats : item.getStats()) {
        Paragraph statsParagraph = new Paragraph();
        if (stats instanceof ArtifactStats) {
          ArtifactStats artifactStats = (ArtifactStats) stats;
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

      data.add(new EquipmentCardData(title, headerText, bodyText.toArray(new Phrase[bodyText.size()]), resourceProvider.getNullIcon()));
    }
    return data.toArray(new ICardData[data.size()]);
  }

  private boolean hasCustomTitle(IEquipmentItem item) {
    return !item.getTemplateId().equals(item.getTitle());
  }

}
