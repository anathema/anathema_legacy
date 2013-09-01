package net.sf.anathema.hero.concept.sheet.personal.encoder;

import net.sf.anathema.hero.concept.sheet.personal.content.PersonalInfoContent;
import net.sf.anathema.hero.concept.sheet.personal.content.PersonalInfoRow;
import net.sf.anathema.hero.concept.sheet.personal.content.TitledInfo;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.IVariableContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.BARE_LINE_HEIGHT;
import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.TEXT_PADDING;

public class PersonalInfoEncoder implements IVariableContentEncoder {

  private final Resources resources;

  public PersonalInfoEncoder(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) {
    PersonalInfoContent content = createContent(reportSession);
    Grid grid = new Grid(bounds, content.getNumberOfRows(), content.getNumberOfColumns());
    for (PersonalInfoRow row : content) {
      encodeRow(content, graphics, grid, row);
    }
  }

  private void encodeRow(PersonalInfoContent content, SheetGraphics graphics, Grid grid, PersonalInfoRow row) {
    for (TitledInfo info : row) {
      float entryWidth = grid.getWidth(info.columnCount);
      Position entryPosition = grid.getPosition(row.getCellIndex(info), content.indexOf(row));
      graphics.drawLabelledContent(info.title, info.info, entryPosition, entryWidth);
    }
  }

  protected final String getLabel(String key) {
    return resources.getString("Sheet.Label." + key) + ":";
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }

  @Override
  public String getHeader(ReportSession session) {
    return createContent(session).getHeader();
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportSession session, float width) {
    return getPreferredContentHeight(session);
  }

  public float getPreferredContentHeight(ReportSession session) {
    return createContent(session).getNumberOfRows() * BARE_LINE_HEIGHT + TEXT_PADDING;
  }

  private PersonalInfoContent createContent(ReportSession session) {
    return session.createContent(PersonalInfoContent.class);
  }
}
