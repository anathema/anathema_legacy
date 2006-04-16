package net.sf.anathema.framework.reporting.encoding;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.framework.reporting.jasper.IJasperXmlConstants;
import net.sf.anathema.framework.styledtext.model.ITextFormat;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.lib.awt.ColorUtilities;

import org.dom4j.Element;

public class TextEncoding {

  private static final String SIMPLE_QUOTE = "\""; //$NON-NLS-1$
  private static final String JAVA_DOUBLE_QUOTE = "\\\""; //$NON-NLS-1$

  public static Element addStyledTextFieldExpression(Element textField, ITextPart[] textParts) {
    Element textFieldExpression = textField.addElement(IJasperXmlConstants.TAG_TEXT_FIELD_EXPRESSION);
    textFieldExpression.addAttribute(IJasperXmlConstants.ATTRIB_CLASS, IJasperXmlConstants.VALUE_CLASS_STRING);
    textFieldExpression.addCDATA(createQuotedStyledCData(textParts));
    return textFieldExpression;
  }

  public static String createQuotedStyledCData(ITextPart[] textParts) {
    String cData = SIMPLE_QUOTE;
    String unquotedCData = ""; //$NON-NLS-1$
    for (ITextPart part : textParts) {
      ITextFormat format = part.getFormat();
      if (format.isStyled()) {
        FontStyle fontStyle = format.getFontStyle();
        String fontName = format.getFontName();
        unquotedCData = unquotedCData.concat("<style"); //$NON-NLS-1$
        if (format.getFontSize() != null) {
          unquotedCData = unquotedCData.concat(" size=\\\"" + format.getFontSize() + JAVA_DOUBLE_QUOTE); //$NON-NLS-1$
        }
        if (format.isUnderline()) {
          unquotedCData = unquotedCData.concat(" isUnderline=\\\"true\\\""); //$NON-NLS-1$
        }
        if (fontStyle.isBold()) {
          unquotedCData = unquotedCData.concat(" isBold=\\\"true\\\""); //$NON-NLS-1$
        }
        if (fontStyle.isItalic()) {
          unquotedCData = unquotedCData.concat(" isItalic=\\\"true\\\""); //$NON-NLS-1$
        }
        if (fontName != null) {
          unquotedCData = unquotedCData.concat(" pdfFontName=\\\"" + fontName + JAVA_DOUBLE_QUOTE); //$NON-NLS-1$
        }
        else {
          unquotedCData = unquotedCData.concat(" pdfFontName=\\\"" + getHelveticaPdfName(fontStyle) + JAVA_DOUBLE_QUOTE); //$NON-NLS-1$
        }
        if (format.getColor() != null) {
          unquotedCData = unquotedCData.concat(" forecolor=\\\"" //$NON-NLS-1$
              + ColorUtilities.convertColorToHexString(format.getColor()) + JAVA_DOUBLE_QUOTE);
        }
        unquotedCData = unquotedCData.concat(">"); //$NON-NLS-1$
      }
      unquotedCData = unquotedCData.concat(part.getText());
      if (format.isStyled()) {
        unquotedCData = unquotedCData.concat("</style>"); //$NON-NLS-1$
      }
    }
    cData = cData.concat(unquotedCData);
    cData = cData.concat(SIMPLE_QUOTE);
    return cData;
  }

  public static String createDynamicStyledCData(ITextPart[] textParts) {
    String unquotedCData = ""; //$NON-NLS-1$
    for (ITextPart part : textParts) {
      ITextFormat format = part.getFormat();
      if (format.isStyled()) {
        FontStyle fontStyle = format.getFontStyle();
        String fontName = format.getFontName();
        unquotedCData = unquotedCData.concat("<style"); //$NON-NLS-1$
        if (format.getFontSize() != null) {
          unquotedCData = unquotedCData.concat(" size=\"" + format.getFontSize() + SIMPLE_QUOTE); //$NON-NLS-1$
        }
        if (format.isUnderline()) {
          unquotedCData = unquotedCData.concat(" isUnderline=\"true\""); //$NON-NLS-1$
        }
        if (fontStyle.isBold()) {
          unquotedCData = unquotedCData.concat(" isBold=\"true\""); //$NON-NLS-1$
        }
        if (fontStyle.isItalic()) {
          unquotedCData = unquotedCData.concat(" isItalic=\"true\""); //$NON-NLS-1$
        }
        if (fontName != null) {
          unquotedCData = unquotedCData.concat(" pdfFontName=\"" + fontName + SIMPLE_QUOTE); //$NON-NLS-1$
        }
        else {
          unquotedCData = unquotedCData.concat(" pdfFontName=\"" + getHelveticaPdfName(fontStyle) + SIMPLE_QUOTE); //$NON-NLS-1$
        }
        if (format.getColor() != null) {
          unquotedCData = unquotedCData.concat(" forecolor=\"" //$NON-NLS-1$
              + ColorUtilities.convertColorToHexString(format.getColor()) + SIMPLE_QUOTE);
        }
        unquotedCData = unquotedCData.concat(">"); //$NON-NLS-1$
      }
      unquotedCData = unquotedCData.concat(part.getText());
      if (format.isStyled()) {
        unquotedCData = unquotedCData.concat("</style>"); //$NON-NLS-1$
      }
    }
    return unquotedCData;
  }

  private static String getHelveticaPdfName(FontStyle fontStyle) {
    String value = "Helvetica"; //$NON-NLS-1$
    if (fontStyle.isPlain()) {
      return value;
    }
    value += "-"; //$NON-NLS-1$
    if (fontStyle.isBold()) {
      value += "Bold"; //$NON-NLS-1$
    }
    if (fontStyle.isItalic()) {
      value += "Oblique"; //$NON-NLS-1$
    }
    return value;
  }

  public static Element addTextFieldExpression(Element textField, String stringExpression) {
    Element textFieldExpression = textField.addElement(IJasperXmlConstants.TAG_TEXT_FIELD_EXPRESSION);
    textFieldExpression.addAttribute(IJasperXmlConstants.ATTRIB_CLASS, IJasperXmlConstants.VALUE_CLASS_STRING);
    textFieldExpression.addCDATA(stringExpression);
    return textFieldExpression;
  }

  public static Element addHorizontalTextElement(Element textField, int fontSize, String textAlignment, boolean styled) {
    Element textElement = textField.addElement(IJasperXmlConstants.TAG_TEXT_ELEMENT);
    textElement.addAttribute(IJasperXmlConstants.ATTRIB_VERTICAL_ALIGNMENT, IJasperXmlConstants.VALUE_BOTTOM);
    textElement.addAttribute(IJasperXmlConstants.ATTRIB_TEXT_ALIGNMENT, textAlignment);
    textElement.addAttribute(IJasperXmlConstants.ATTRIB_IS_STYLED_TEXT, String.valueOf(styled));
    Element fontElement = textElement.addElement(IJasperXmlConstants.TAG_FONT);
    fontElement.addAttribute(IJasperXmlConstants.ATTRIB_SIZE, String.valueOf(fontSize));
    return textElement;
  }

  public static Element addVerticalTextElement(Element textField, int fontSize, String textAlignment) {
    Element textElement = textField.addElement(IJasperXmlConstants.TAG_TEXT_ELEMENT);
    textElement.addAttribute(IJasperXmlConstants.ATTRIB_VERTICAL_ALIGNMENT, IJasperXmlConstants.VALUE_TOP);
    textElement.addAttribute(IJasperXmlConstants.ATTRIB_ROTATION, IJasperXmlConstants.VALUE_LEFT);
    textElement.addAttribute(IJasperXmlConstants.ATTRIB_TEXT_ALIGNMENT, textAlignment);
    Element fontElement = textElement.addElement(IJasperXmlConstants.TAG_FONT);
    fontElement.addAttribute(IJasperXmlConstants.ATTRIB_SIZE, String.valueOf(fontSize));
    return textElement;
  }

  public static Element addTextFieldElement(Element parent) {
    Element textField = parent.addElement(IJasperXmlConstants.TAG_TEXT_FIELD);
    textField.addAttribute(IJasperXmlConstants.ATTRIB_IS_BLANK_WHEN_NULL, IJasperXmlConstants.VALUE_TRUE);
    textField.addAttribute(IJasperXmlConstants.ATTRIB_IS_STRETCH_WITH_OVERFLOW, IJasperXmlConstants.VALUE_TRUE);
    return textField;
  }
}