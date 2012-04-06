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

  public Body startPortraitPage() {
    startNewPage(pageSize.getPortraitRectangle());
    return createPortraitBody();
  }

  public Body startPortraitPage(float contentHeight) {
    startNewPage(pageSize.getPortraitRectangle());
    return new Body(PageConfiguration.ForPortrait(pageSize), contentHeight);
  }

  public Body startLandscapePage() {
    startNewPage(pageSize.getLandscapeRectangle());
    return createLandscapeBody();
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

  public SheetPage createPage(SheetGraphics graphics, ReportSession session) {
    EncodingMetrics metrics = EncodingMetrics.From(graphics, session);
    RegisteredEncoderList registeredEncoderList = new RegisteredEncoderList(resources, encoders);
    return new SheetPage(registeredEncoderList, metrics, graphics);
  }
}
