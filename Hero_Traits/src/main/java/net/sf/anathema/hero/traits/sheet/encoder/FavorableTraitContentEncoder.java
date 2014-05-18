package net.sf.anathema.hero.traits.sheet.encoder;

import com.itextpdf.text.pdf.PdfContentByte;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.groups.IdentifiedTraitTypeList;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.traits.sheet.content.FavorableTraitContent;
import net.sf.anathema.hero.traits.sheet.content.PdfTraitEncoder;

import java.util.ArrayList;
import java.util.List;

public class FavorableTraitContentEncoder<C extends FavorableTraitContent> extends AbstractContentEncoder<C> {

  private final List<INamedTraitEncoder> namedTraitEncoders = new ArrayList<>();
  private final PdfTraitEncoder traitEncoder = PdfTraitEncoder.createSmallTraitEncoder();

  public FavorableTraitContentEncoder(Class<C> contentClass) {
    super(contentClass);
  }

  public final void addNamedTraitEncoder(INamedTraitEncoder encoder) {
    namedTraitEncoders.add(encoder);
  }

  public PdfTraitEncoder getTraitEncoder() {
    return traitEncoder;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) {
    FavorableTraitContent content = createContent(reportSession);
    Position position = new Position(bounds.getMinX(), bounds.getMaxY());
    float width = bounds.width;
    float bottom = bounds.getMinY() + IVoidStateFormatConstants.TEXT_PADDING;
    if (!content.getMarkedTraitTypes().isEmpty()) {
      bottom += encodeMarkerCommentText(graphics, content, position, bottom);
    }
    float yPosition = encodeTraitGroups(graphics, content, position, width);
    float height = yPosition - bottom;
    for (INamedTraitEncoder encoder : namedTraitEncoders) {
      yPosition -= IVoidStateFormatConstants.LINE_HEIGHT;
      yPosition -= encoder.encode(graphics, reportSession, new Position(position.x, yPosition), width, height);
      height -= IVoidStateFormatConstants.LINE_HEIGHT;
    }
  }

  private float encodeTraitGroups(SheetGraphics graphics, FavorableTraitContent content, Position position, float width) {
    IdentifiedTraitTypeList[] groups = content.getIdentifiedTraitTypeGroups();
    float yPosition = position.y;
    for (IdentifiedTraitTypeList group : groups) {
      Position groupPosition = new Position(position.x, yPosition);
      yPosition -= encodeTraitGroup(graphics, content, group, groupPosition, width);
      yPosition -= IVoidStateFormatConstants.TEXT_PADDING;
    }
    return yPosition;
  }

  private float encodeTraitGroup(SheetGraphics graphics, FavorableTraitContent content, IdentifiedTraitTypeList group, Position position,
                                 float width) {
    float height = 0;
    float groupLabelWidth = IVoidStateFormatConstants.LINE_HEIGHT + IVoidStateFormatConstants.TEXT_PADDING;
    float traitX = position.x + groupLabelWidth;
    List<TraitType> traitTypes = group.getAll();
    float groupLabelX = position.x + 4;
    float markerX = groupLabelX + IVoidStateFormatConstants.TEXT_PADDING;
    for (int index = 0; index < group.size(); index++) {
      TraitType traitType = traitTypes.get(index);
      float yPosition = position.y - (index + 1) * traitEncoder.getTraitHeight();
      if (content.getMarkedTraitTypes().contains(traitType)) {
        encodeMarker(graphics, new Position(markerX, yPosition + 1));
      }
      TraitMap traitMap = content.getTraitMap();
      ValuedTraitType trait = traitMap.getTrait(traitType);
      String label = content.getTraitLabel(traitType);
      height += encodeFavorableTrait(graphics, content, label, trait, new Position(traitX, yPosition), width - groupLabelWidth);
    }
    Position groupLabelPosition = new Position(groupLabelX, position.y - height / 2f);
    addGroupLabel(graphics, content, group, groupLabelPosition);
    return height;
  }

  private void addGroupLabel(SheetGraphics graphics, FavorableTraitContent content, IdentifiedTraitTypeList group, Position position) {
    if (content.hasGroupLabel()) {
      return;
    }
    String groupLabel = content.getGroupLabel(group.getListId());
    graphics.drawVerticalText(groupLabel, position, PdfContentByte.ALIGN_CENTER);
  }

  private float encodeFavorableTrait(SheetGraphics graphics, FavorableTraitContent content, String label, ValuedTraitType trait, Position position,
                                     float width) {
    int value = trait.getCurrentValue();
    boolean favored = trait.isCasteOrFavored();
    return traitEncoder.encodeWithTextAndRectangle(graphics, label, position, width, value, favored, content.getTraitMax());
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

  private float encodeMarkerCommentText(SheetGraphics graphics, FavorableTraitContent content, Position position, float yPosition) {
    encodeMarker(graphics, new Position(position.x, yPosition));
    String mobilityPenaltyText = content.getMobilityPenaltyText();
    Position commentPosition = new Position(position.x + 5, yPosition);
    graphics.drawComment(mobilityPenaltyText, commentPosition, PdfContentByte.ALIGN_LEFT);
    return 7;
  }
}
