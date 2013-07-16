package net.sf.anathema.cards.data;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.main.magic.description.MagicDescription;
import net.sf.anathema.character.main.magic.charmtree.builder.stringbuilder.ScreenDisplayInfoContributor;
import net.sf.anathema.character.main.magic.charmtree.builder.stringbuilder.source.MagicSourceContributor;
import net.sf.anathema.character.main.magic.model.Magic;
import net.sf.anathema.character.main.magic.model.cost.CostImpl;
import net.sf.anathema.character.main.magic.model.cost.HealthCost;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractMagicCardData implements ICardData {

  private Magic magic;
  private Resources resources;
  private MagicDescription description;
  private ICardReportResourceProvider resourceProvider;

  public AbstractMagicCardData(Magic magic, MagicDescription description, ICardReportResourceProvider resourceProvider, Resources resources) {
    this.magic = magic;
    this.resources = resources;
    this.resourceProvider = resourceProvider;
    this.description = description;
  }

  protected Resources getResources() {
    return resources;
  }

  protected ICardReportResourceProvider getResourceProvider() {
    return resourceProvider;
  }

  @Override
  public String getTitle() {
    return getResources().getString(magic.getId());
  }

  @Override
  public String getSource() {
    String source = new MagicSourceContributor<>(resources).createSourceString(magic);
    source = source.replaceAll(resources.getString("CardsReport.MoEP.Long"), resources.getString("CardsReport.MoEP.Short"));
    return source;
  }

  protected Phrase getCostPhrase(boolean semicolon) {
    String cost = new ScreenDisplayInfoContributor(resources).createCostString(magic);
    return new Phrase(cost + (semicolon ? ": " : ""), resourceProvider.getBoldFont());
  }

  protected boolean hasDescription() {
    return !description.isEmpty();
  }

  protected void addDescriptionPhrases(Paragraph phrases) {
    for (String string : description.getParagraphs()) {
      phrases.add(new Paragraph(string, resourceProvider.getNormalFont()));
    }
  }

  protected boolean hasCost(Magic magic) {
    return magic.getTemporaryCost().getEssenceCost() != CostImpl.NULL_COST ||
           magic.getTemporaryCost().getWillpowerCost() != CostImpl.NULL_COST ||
           magic.getTemporaryCost().getHealthCost() != HealthCost.NULL_HEALTH_COST ||
           magic.getTemporaryCost().getXPCost() != CostImpl.NULL_COST;
  }

  @Override
  public boolean wantsNewPage() {
    return false;
  }
}
