package net.sf.anathema.character.reporting.pdf.layout;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.framework.reporting.pdf.PageSize;

public class Sheet {

  private Document document;
  private PageSize pageSize;

  public Sheet(Document document, PageSize pageSize) {
    this.document = document;
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
}
