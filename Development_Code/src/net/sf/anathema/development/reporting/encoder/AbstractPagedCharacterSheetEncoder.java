package net.sf.anathema.development.reporting.encoder;

import net.sf.anathema.development.reporting.encoder.page.IPageFormat;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public abstract class AbstractPagedCharacterSheetEncoder extends AbstractCharacterSheetEncoder {

  public AbstractPagedCharacterSheetEncoder(IPageFormat pageFormat) throws InitializationException {
    super(pageFormat);
  }

  @Override
  protected void encodeContent(Element rootElement) {
    for (ICharacterSheetPageEncoder pageEncoder : getPageEncoders()) {
      Element groupElement = rootElement.addElement("group");
      groupElement.addAttribute(ATTRIB_NAME, pageEncoder.getGroupName());
      ElementUtilities.addAttribute(groupElement, ATTRIB_IS_START_NEW_PAGE, true);
      ElementUtilities.addAttribute(groupElement, ATTRIB_IS_START_NEW_COLUMN, true);
      Element groupHeaderElement = groupElement.addElement("groupHeader"); //$NON-NLS-1$
      Element bandElement = groupHeaderElement.addElement("band");
      addPrintWhenExpression(bandElement, pageEncoder.getPrintWhenExpression());
      int bandHeight = pageEncoder.encodeBand(bandElement); //$NON-NLS-1$      
      bandElement.addAttribute("height", String.valueOf(bandHeight)); //$NON-NLS-1$
    }
  }

  protected abstract ICharacterSheetPageEncoder[] getPageEncoders();
}