package net.sf.anathema.character.reporting.pdf.layout;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.lib.resources.IResources;

public class Sheet {

  private Document document;
  private final EncoderRegistry encoders;
  private final IResources resources;
  private PageSize pageSize;

  public Sheet(Document document, EncoderRegistry encoders, IResources resources, PageSize pageSize) {
    this.document = document;
    this.encoders = encoders;
    this.resources = resources;
    this.pageSize = pageSize;
  }

  public SheetPage startPortraitPage(SheetGraphics graphics, ReportSession session) {
    startNewPage(pageSize.getPortraitRectangle());
    Body body = createPortraitBody();
    return createPage(body, graphics, session);
  }

  public SheetPage startPortraitPage(SheetGraphics graphics, ReportSession session, float contentHeight) {
    startNewPage(pageSize.getPortraitRectangle());
    Body body = new Body(PageConfiguration.ForPortrait(pageSize), contentHeight);
    return createPage(body, graphics, session);
  }

  public SheetPage startLandscapePage(SheetGraphics graphics, ReportSession session) {
    startNewPage(pageSize.getLandscapeRectangle());
    Body body = createLandscapeBody();
    return createPage(body, graphics, session);
  }

  private void startNewPage(Rectangle rectangle) {
    document.setPageSize(rectangle);
    startNewPage();
  }

  private void startNewPage() {
    if (document.isOpen()) {
      document.newPage();
    } else {
      document.open();
    }
  }

  private Body createPortraitBody() {
    return new Body(PageConfiguration.ForPortrait(pageSize));
  }

  private Body createLandscapeBody() {
    return new Body(PageConfiguration.ForLandscape(pageSize));
  }

  private SheetPage createPage(Body body, SheetGraphics graphics, ReportSession session) {
    EncodingMetrics metrics = EncodingMetrics.From(graphics, session);
    RegisteredEncoderList registeredEncoderList = new RegisteredEncoderList(resources, encoders);
    return new SheetPage(body, registeredEncoderList, metrics, graphics);
  }
}
