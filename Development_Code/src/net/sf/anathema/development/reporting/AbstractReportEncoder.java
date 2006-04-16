package net.sf.anathema.development.reporting;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.development.reporting.encoder.page.IPageFormat;
import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;
import net.sf.anathema.framework.reporting.jasper.IJasperXmlConstants;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public abstract class AbstractReportEncoder extends AbstractJasperEncoder implements IReportEncoder {

  private static final String TAG_JASPER_REPORT = "jasperReport"; //$NON-NLS-1$
  private static final String VALUE_ALL_SECTIONS_NO_DETAIL = "AllSectionsNoDetail"; //$NON-NLS-1$
  private static final String ATTRIB_BOTTOM_MARGIN = "bottomMargin"; //$NON-NLS-1$
  private static final String ATTRIB_COLUMN_COUNT = "columnCount"; //$NON-NLS-1$
  private static final String ATTRIB_COLUMN_WIDTH = "columnWidth"; //$NON-NLS-1$
  private static final String ATTRIB_LEFT_MARGIN = "leftMargin"; //$NON-NLS-1$
  private static final String ATTRIB_PAGE_HEIGHT = "pageHeight"; //$NON-NLS-1$
  private static final String ATTRIB_PAGE_WIDTH = "pageWidth"; //$NON-NLS-1$
  private static final String ATTRIB_RIGHT_MARGIN = "rightMargin"; //$NON-NLS-1$
  private static final String ATTRIB_TOP_MARGIN = "topMargin"; //$NON-NLS-1$
  private static final String ATTRIB_WHEN_NO_DATA_TYPE = "whenNoDataType"; //$NON-NLS-1$
  private static final String TAG_PARAMETER = "parameter"; //$NON-NLS-1$
  protected static final String ATTRIB_NAME = "name"; //$NON-NLS-1$
  private Map<String, String> parameterClassesByName = new HashMap<String, String>();
  private final IPageFormat pageFormat;

  public AbstractReportEncoder(IPageFormat pageFormat) {
    this.pageFormat = pageFormat;
    addParameterClasses(parameterClassesByName);
  }

  protected abstract void addParameterClasses(Map<String, String> parameterClasses);

  public final Document encode() {
    Element rootElement = createReportRoot();
    addReportFont(rootElement, "Helvetica", true);
    addReportFont(rootElement, "Symbol", false);
    addParameters(rootElement);
    encodeContent(rootElement);
    return createJasperDocument(rootElement);
  }

  private void addReportFont(Element rootElement, String fontName, boolean isDefault) {
    Element reportFontElement = rootElement.addElement("reportFont");
    reportFontElement.addAttribute("name", fontName);
    reportFontElement.addAttribute("fontName", fontName);
    reportFontElement.addAttribute("pdfFontName", fontName);
    reportFontElement.addAttribute("isDefault", String.valueOf(isDefault));
  }

  protected abstract void encodeContent(Element rootElement);

  protected final void addParameters(Element rootElement) {
    for (String parameterName : parameterClassesByName.keySet()) {
      Element parameterElement = rootElement.addElement(TAG_PARAMETER);
      parameterElement.addAttribute(ATTRIB_NAME, parameterName);
      parameterElement.addAttribute(IJasperXmlConstants.ATTRIB_CLASS, parameterClassesByName.get(parameterName));
    }
  }

  protected final Document createJasperDocument(Element rootElement) {
    Document document = DocumentHelper.createDocument(rootElement);
    document.addDocType("jasperReport", //$NON-NLS-1$
        "-//JasperReports//DTD Report Design//EN", //$NON-NLS-1$
        "http://jasperreports.sourceforge.net/dtds/jasperreport.dtd"); //$NON-NLS-1$
    return document;
  }

  protected final Element createReportRoot() {
    Element root = DocumentHelper.createElement(TAG_JASPER_REPORT);
    root.addAttribute(ATTRIB_NAME, getReportName());
    root.addAttribute(ATTRIB_PAGE_WIDTH, String.valueOf(pageFormat.getPageSize().width));
    root.addAttribute(ATTRIB_PAGE_HEIGHT, String.valueOf(pageFormat.getPageSize().height));
    root.addAttribute(ATTRIB_COLUMN_WIDTH, String.valueOf(pageFormat.getColumnWidth()));
    root.addAttribute(ATTRIB_LEFT_MARGIN, String.valueOf(pageFormat.getInsets().left));
    root.addAttribute(ATTRIB_RIGHT_MARGIN, String.valueOf(pageFormat.getInsets().right));
    root.addAttribute(ATTRIB_TOP_MARGIN, String.valueOf(pageFormat.getInsets().top));
    root.addAttribute(ATTRIB_BOTTOM_MARGIN, String.valueOf(pageFormat.getInsets().bottom));
    root.addAttribute(ATTRIB_COLUMN_COUNT, String.valueOf(1));
    root.addAttribute(ATTRIB_WHEN_NO_DATA_TYPE, VALUE_ALL_SECTIONS_NO_DETAIL);
    return root;
  }

  protected final int getColumnWidth() {
    return pageFormat.getColumnWidth();
  }

  protected abstract String getReportName();
  
  protected final IPageFormat getPageFormat() {
    return pageFormat;
  }
}