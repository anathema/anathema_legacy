package net.sf.anathema.character.reporting.pdf.rendering.boxes.attributes;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.magic.AttributeFavoringType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.GenericCharmUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.util.MagicLearnUtilities;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AttributesEncoder implements ContentEncoder {

  private final Resources resources;
  private PdfTraitEncoder smallTraitEncoder;
  private final boolean encodeFavored;

  public AttributesEncoder(Resources resources, boolean encodeFavored) {
    this.resources = resources;
    this.encodeFavored = encodeFavored;
    this.smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder();
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    GroupedTraitType[] attributeGroups = reportSession.getCharacter().getTemplate().getAttributeGroups();
    IGenericTraitCollection traitCollection = reportSession.getCharacter().getTraitCollection();
    IMagicStats[] excellencies = getExcellencies(reportSession.getCharacter());
    encodeAttributes(graphics, reportSession.getCharacter(), bounds, attributeGroups, traitCollection, excellencies);
  }

  protected IMagicStats[] getExcellencies(IGenericCharacter character) {
    List<IMagicStats> excellencies = new ArrayList<>();
    FavoringTraitType type = character.getTemplate().getTemplateType().getCharacterType().getFavoringTraitType();
    if (type.equals(new AttributeFavoringType())) {
      for (IMagicStats stats : GenericCharmUtilities.getGenericCharmStats(character)) {
        String genericId = stats.getName().getId();
        if (genericId.endsWith("Excellency")) {
          excellencies.add(stats);
        }
      }
      Collections.sort(excellencies, new Comparator<IMagicStats>() {
        @Override
        public int compare(IMagicStats a, IMagicStats b) {
          String aId = a.getName().getId();
          String bId = b.getName().getId();
          return aId.compareTo(bId);
        }
      });
    }
    return excellencies.toArray(new IMagicStats[excellencies.size()]);
  }

  public final void encodeAttributes(SheetGraphics graphics, IGenericCharacter character, Bounds contentBounds, GroupedTraitType[] attributeGroups, IGenericTraitCollection traitCollection, IMagicStats[] excellencies) {
    int traitMax = getTraitMax(character, attributeGroups);
    float groupSpacing = smallTraitEncoder.getTraitHeight() / 2;
    float y = contentBounds.getMaxY() - groupSpacing;
    String groupId = null;
    List<IMagic> allLearnedMagic = character.getAllLearnedMagic();
    for (GroupedTraitType groupedTraitType : attributeGroups) {
      if (!groupedTraitType.getGroupId().equals(groupId)) {
        groupId = groupedTraitType.getGroupId();
        y -= groupSpacing;
      }
      ITraitType traitType = groupedTraitType.getTraitType();
      String traitLabel = resources.getString("AttributeType.Name." + traitType.getId()); //$NON-NLS-1$
      int value = traitCollection.getTrait(traitType).getCurrentValue();
      Position position = new Position(contentBounds.x, y);
      if (!encodeFavored) {
        y -= smallTraitEncoder.encodeWithText(graphics, traitLabel, position, contentBounds.width, value, traitMax);
      } else {
        boolean favored = traitCollection.getFavorableTrait(traitType).isCasteOrFavored();
        boolean[] excellencyLearned = new boolean[excellencies.length];
        for (int i = 0; i < excellencies.length; i++) {
          String charmId = excellencies[i].getName().getId() + "." + traitType.getId(); //$NON-NLS-1$
          excellencyLearned[i] = MagicLearnUtilities.isCharmLearned(allLearnedMagic, charmId);
        }
        y -= smallTraitEncoder.encodeWithExcellencies(graphics, traitLabel, position, contentBounds.width, value, favored, excellencyLearned, traitMax);
      }
    }
  }

  public int getTraitMax(IGenericCharacter character, GroupedTraitType[] groups) {
    ITraitType traitType = groups[0].getTraitType();
    ITraitTemplate template = character.getTemplate().getTraitTemplateCollection().getTraitTemplate(traitType);
    return template.getLimitation().getAbsoluteLimit(character);
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.Attributes");
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
