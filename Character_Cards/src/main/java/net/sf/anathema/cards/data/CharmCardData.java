package net.sf.anathema.cards.data;

import com.google.common.base.Joiner;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.main.magic.description.MagicDescription;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charmtree.builder.stringbuilder.type.VerboseCharmTypeContributor;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.hero.charms.sheet.content.stats.CharmStats;
import net.sf.anathema.charms.MartialArtsUtilities;
import net.sf.anathema.lib.resources.Resources;

public class CharmCardData extends AbstractMagicCardData {
  private Charm charm;
  private CharmStats stats;

  public CharmCardData(Charm charm, CharmStats stats, MagicDescription description, ICardReportResourceProvider fontProvider, Resources resources) {
    super(charm, description, fontProvider, resources);
    this.charm = charm;
    this.stats = stats;
  }

  @Override
  public Image getPrimaryIcon() {
    if (MartialArtsUtilities.isMartialArts(charm) && !charm.isInstanceOfGenericCharm()) {
      Image image = getResourceProvider().getMartialArtIcon(charm.getGroupId());
      return image != null ? image : getResourceProvider().getTraitIcon(AbilityType.MartialArts);
    } else {
      return getResourceProvider().getTraitIcon(charm.getPrimaryTraitType());
    }

  }

  @Override
  public Image getSecondaryIcon() {
    if (MartialArtsUtilities.isMartialArts(charm) && !charm.isInstanceOfGenericCharm()) {
      return getResourceProvider().getMartialArtLevelIcon(MartialArtsUtilities.getLevel(charm));
    } else {
      return getResourceProvider().getCharacterIcon(charm.getCharacterType());
    }
  }

  @Override
  public Paragraph getStats() {
    Paragraph stats = new Paragraph();
    stats.add(getCharmType(charm));
    stats.add("\n");
    stats.add(getCharmDuration(charm));
    return stats;
  }

  @Override
  public Element[] getBody(int contentHeight) {
    Paragraph phrases = new Paragraph();
    if (hasCost(charm)) {
      phrases.add(getCostPhrase(hasDescription()));
    }
    if (hasDescription()) {
      addDescriptionPhrases(phrases);
    }
    return new Element[]{phrases};
  }

  private Phrase getCharmType(Charm charm) {
    String type = new VerboseCharmTypeContributor(getResources()).createTypeString(charm.getCharmTypeModel());
    return new Phrase(8, type, getResourceProvider().getBoldFont());
  }

  private Phrase getCharmDuration(Charm charm) {
    String duration = charm.getDuration().getText(getResources());
    return new Phrase(duration, getResourceProvider().getNormalFont());
  }

  @Override
  public String getKeywords() {
    return Joiner.on(", ").join(stats.getDetailStrings(getResources()));
  }
}
