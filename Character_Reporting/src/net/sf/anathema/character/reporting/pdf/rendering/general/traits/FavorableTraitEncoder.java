package net.sf.anathema.character.reporting.pdf.rendering.general.traits;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.CollectionUtilities;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class FavorableTraitEncoder implements IBoxContentEncoder {

  private final List<ITraitType> markedTraitTypes = new ArrayList<ITraitType>();
  private final List<INamedTraitEncoder> namedTraitEncoders = new ArrayList<INamedTraitEncoder>();
  private final PdfTraitEncoder traitEncoder;
  private final IResources resources;
  private final int essenceMax;
  private final BaseFont baseFont;

  public FavorableTraitEncoder(BaseFont baseFont, IResources resources, int essenceMax, ITraitType... markedTypes) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.essenceMax = essenceMax;
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder();
    Collections.addAll(markedTraitTypes, markedTypes);
  }

  protected final void addNamedTraitEncoder(INamedTraitEncoder encoder) {
    namedTraitEncoders.add(encoder);
  }

  protected PdfTraitEncoder getTraitEncoder() {
    return traitEncoder;
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    Position position = new Position(bounds.getMinX(), bounds.getMaxY());
    float width = bounds.width;

    float bottom = bounds.getMinY() + IVoidStateFormatConstants.TEXT_PADDING;
    int nExcellencies = getExcellencies(reportContent.getCharacter()).length;
    if (nExcellencies > 0) {
      bottom += encodeExcellencyCommentText(graphics, nExcellencies, position, bottom);
    }
    if (!markedTraitTypes.isEmpty()) {
      bottom += encodeMarkerCommentText(graphics, position, bottom);
    }

    float yPosition = encodeTraitGroups(graphics, reportContent.getCharacter(), position, width);
    float height = yPosition - bottom;
    for (INamedTraitEncoder encoder : namedTraitEncoders) {
      yPosition -= IVoidStateFormatConstants.LINE_HEIGHT;
      yPosition -= encoder.encode(graphics, reportContent.getCharacter(), new Position(position.x, yPosition), width, height);
      height -= IVoidStateFormatConstants.LINE_HEIGHT;
    }
  }

  private float encodeTraitGroups(SheetGraphics graphics, IGenericCharacter character, Position position, float width) {
    IIdentifiedTraitTypeGroup[] groups = getIdentifiedTraitTypeGroups(character);
    IGenericTraitCollection traitCollection = getTraitCollection(character);
    IMagicStats[] excellencies = getExcellencies(character);

    float yPosition = position.y;
    for (IIdentifiedTraitTypeGroup group : groups) {
      Position groupPosition = new Position(position.x, yPosition);
      yPosition -= encodeTraitGroup(graphics, character, excellencies, traitCollection, group, groupPosition, width);
      yPosition -= IVoidStateFormatConstants.TEXT_PADDING;
    }
    return yPosition;
  }

  protected IMagicStats[] getExcellencies(IGenericCharacter character) {
    List<IMagicStats> excellencies = new ArrayList<IMagicStats>();
    if (shouldShowExcellencies(character)) {
      for (IMagicStats stats : character.getGenericCharmStats()) {
        String genericId = stats.getName().getId();
        if (genericId.endsWith("Excellency")) {
          excellencies.add(stats);
        }
      }
      Collections.sort(excellencies, new Comparator<IMagicStats>() {
        public int compare(IMagicStats a, IMagicStats b) {
          String aId = a.getName().getId();
          String bId = b.getName().getId();

          Integer aIndex = new Integer(aId.substring(aId.lastIndexOf('.') + 1, aId.indexOf("Excellency") - 2));
          Integer bIndex = new Integer(bId.substring(bId.lastIndexOf('.') + 1, bId.indexOf("Excellency") - 2));
          return aIndex.compareTo(bIndex);
        }
      });
    }
    return excellencies.toArray(new IMagicStats[excellencies.size()]);
  }

  protected IGenericTraitCollection getTraitCollection(IGenericCharacter character) {
    return character.getTraitCollection();
  }

  protected abstract IIdentifiedTraitTypeGroup[] getIdentifiedTraitTypeGroups(IGenericCharacter character);

  private float encodeTraitGroup(SheetGraphics graphics, IGenericCharacter character, IMagicStats[] excellencies,
    IGenericTraitCollection traitCollection, IIdentifiedTraitTypeGroup group, Position position, float width) {
    float height = 0;
    float groupLabelWidth = IVoidStateFormatConstants.LINE_HEIGHT + IVoidStateFormatConstants.TEXT_PADDING;
    float traitX = position.x + groupLabelWidth;
    ITraitType[] traitTypes = group.getAllGroupTypes();
    float groupLabelX = position.x + 4;
    float markerX = groupLabelX + IVoidStateFormatConstants.TEXT_PADDING;
    List<IMagic> allLearnedMagic = character.getAllLearnedMagic();
    for (int index = 0; index < traitTypes.length; index++) {
      ITraitType traitType = traitTypes[index];
      float yPosition = position.y - (index + 1) * traitEncoder.getTraitHeight();
      if (markedTraitTypes.contains(traitType)) {
        encodeMarker(graphics, new Position(markerX, yPosition + 1));
      }
      IFavorableGenericTrait trait = traitCollection.getFavorableTrait(traitType);
      String label = resources.getString(getTraitTypePrefix() + traitType.getId());

      if (shouldShowExcellencies(character)) {
        boolean[] excellencyLearned = new boolean[excellencies.length];
        for (int i = 0; i < excellencies.length; i++) {
          final String charmId = excellencies[i].getName().getId() + "." + traitType.getId(); //$NON-NLS-1$
          excellencyLearned[i] = CollectionUtilities.find(allLearnedMagic, new IPredicate<IMagic>() {
            public boolean evaluate(IMagic value) {
              return charmId.equals(value.getId());
            }
          }) != null;
        }

        height += encodeFavorableTrait(graphics, label, trait, excellencyLearned, new Position(traitX, yPosition), width - groupLabelWidth);
      }
      else {
        height += encodeFavorableTrait(graphics, label, trait, new Position(traitX, yPosition), width - groupLabelWidth);
      }
    }
    Position groupLabelPosition = new Position(groupLabelX, position.y - height / 2f);
    addGroupLabel(graphics, group, groupLabelPosition);
    return height;
  }

  private void addGroupLabel(SheetGraphics graphics, IIdentifiedTraitTypeGroup group, Position position) {
    if (getGroupNamePrefix() == null) {
      return;
    }
    String groupId = group.getGroupId().getId();
    String resourceKey = group.getGroupId() instanceof ICasteType ? "Caste." + groupId : getGroupNamePrefix() + groupId; //$NON-NLS-1$
    String groupLabel = resources.getString(resourceKey);
    graphics.drawVerticalText(groupLabel, position, PdfContentByte.ALIGN_CENTER);
  }

  protected abstract String getGroupNamePrefix();

  protected String getTraitTypePrefix() {
    return ""; //$NON-NLS-1$
  }

  private float encodeFavorableTrait(SheetGraphics graphics, String label, IFavorableGenericTrait trait, Position position, float width) {
    int value = trait.getCurrentValue();
    boolean favored = trait.isCasteOrFavored();
    return traitEncoder.encodeWithTextAndRectangle(graphics, label, position, width, value, favored, essenceMax);
  }

  private float encodeFavorableTrait(SheetGraphics graphics, String label, IFavorableGenericTrait trait, boolean[] excellencyLearned,
    Position position, float width) {
    int value = trait.getCurrentValue();
    boolean favored = trait.isCasteOrFavored();
    return traitEncoder.encodeWithExcellencies(graphics, label, position, width, value, favored, excellencyLearned, essenceMax);
  }

  private void encodeMarker(SheetGraphics graphics, Position markerPosition) {
    PdfContentByte directContent = graphics.getDirectContent();
    directContent.setLineWidth(1.0f);
    directContent.moveTo(markerPosition.x, markerPosition.y + 2);
    directContent.lineTo(markerPosition.x + 4, markerPosition.y + 2);
    directContent.moveTo(markerPosition.x + 2, markerPosition.y);
    directContent.lineTo(markerPosition.x + 2, markerPosition.y + 4);
    directContent.stroke();
  }

  private float encodeMarkerCommentText(SheetGraphics graphics, Position position, float yPosition) {
    encodeMarker(graphics, new Position(position.x, yPosition));
    String mobilityPenaltyText = " : " + resources.getString(getMarkerCommentKey()); //$NON-NLS-1$
    Position commentPosition = new Position(position.x + 5, yPosition);
    graphics.drawComment(mobilityPenaltyText, commentPosition, PdfContentByte.ALIGN_LEFT);
    return 7;
  }

  private float encodeExcellencyCommentText(SheetGraphics graphics, int nExcellencies, Position position, float yPosition) {
    StringBuilder numbers = new StringBuilder();
    for (int i = 1; i <= nExcellencies; i++) {
      numbers.append(Integer.toString(i));
    }
    String mobilityPenaltyText = numbers.toString() + ": " + resources.getString(getExcellencyCommentKey()); //$NON-NLS-1$
    Position commentPosition = new Position(position.x, yPosition);
    graphics.drawComment(mobilityPenaltyText, commentPosition, PdfContentByte.ALIGN_LEFT);
    return 7;
  }

  protected String getMarkerCommentKey() {
    return null;
  }

  protected String getExcellencyCommentKey() {
    return null;
  }

  protected boolean shouldShowExcellencies(IGenericCharacter character) {
    return false;
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
