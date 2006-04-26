package net.sf.anathema.framework.styledtext.presentation;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.framework.styledtext.model.IStyledTextChangeListener;
import net.sf.anathema.framework.styledtext.model.IStyledTextualDescription;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.lib.lang.ArrayUtilities;

public class StyledTextManager implements IStyledTextManager {

  private final List<IStyledTextChangeListener> listeners = new ArrayList<IStyledTextChangeListener>();
  private final DefaultStyledDocument document;
  private ITextPart[] actualTextParts = new ITextPart[0];

  public StyledTextManager(DefaultStyledDocument document) {
    this.document = document;
    this.document.addDocumentListener(new DocumentListener() {
      public void changedUpdate(DocumentEvent e) {
        fireTextChangedEvent();
      }

      public void removeUpdate(DocumentEvent e) {
        fireTextChangedEvent();
      }

      public void insertUpdate(DocumentEvent e) {
        fireTextChangedEvent();
      }
    });
  }

  public void setText(ITextPart[] textParts) {
    if (ArrayUtilities.equals(textParts, actualTextParts)) {
      return;
    }
    try {
      document.remove(0, document.getLength());
      SimpleAttributeSet[] attributes = initAttributes(textParts);
      for (int i = 0; i < textParts.length; i++) {
        document.insertString(document.getLength(), textParts[i].getText(), attributes[i]);
      }
      this.actualTextParts = textParts;
    }
    catch (BadLocationException e) {
      throw new IllegalStateException(e);
    }
  }

  private SimpleAttributeSet[] initAttributes(ITextPart[] textParts) {
    SimpleAttributeSet[] textAttributes = new SimpleAttributeSet[textParts.length];
    for (int index = 0; index < textAttributes.length; index++) {
      textAttributes[index] = new SimpleAttributeSet();
      StyleConstants.setItalic(textAttributes[index], textParts[index].getFormat().getFontStyle().isItalic());
      StyleConstants.setBold(textAttributes[index], textParts[index].getFormat().getFontStyle().isBold());
      StyleConstants.setUnderline(textAttributes[index], textParts[index].getFormat().isUnderline());
    }
    return textAttributes;
  }

  public ITextPart[] getTextParts() {
    return actualTextParts;
  }

  private ITextPart[] extractTextParts() throws BadLocationException {
    List<ITextPart> textParts = new ArrayList<ITextPart>();
    int documentLength = document.getLength();
    if (documentLength <= 0) {
      return new ITextPart[0];
    }
    AttributeSet currentAttributeSet = null;
    int currentPartStartIndex = 0;
    for (int index = 0; index < documentLength; index++) {
      Element characterElement = document.getCharacterElement(index);
      AttributeSet attributes = characterElement.getAttributes();
      if (currentAttributeSet == null) {
        currentAttributeSet = attributes;
        continue;
      }
      if (currentAttributeSet.equals(attributes)) {
        continue;
      }
      textParts.add(createTextPart(currentPartStartIndex, index, currentAttributeSet));
      currentAttributeSet = attributes;
      currentPartStartIndex = index;
    }
    textParts.add(createTextPart(currentPartStartIndex, documentLength, currentAttributeSet));
    return textParts.toArray(new ITextPart[textParts.size()]);
  }

  private ITextPart createTextPart(int startIndex, int nextStartIndex, AttributeSet attributeSet)
      throws BadLocationException {
    String nextTextPartText = document.getText(startIndex, nextStartIndex - startIndex);
    TextFormat textFormat = createTextFormat(attributeSet);
    return new TextPart(nextTextPartText, textFormat);
  }

  private TextFormat createTextFormat(AttributeSet attributeSet) {
    Font font = document.getFont(attributeSet);
    Boolean underlineAttribute = (Boolean) attributeSet.getAttribute(StyleConstants.Underline);
    boolean notIsUnderline = underlineAttribute == null || !underlineAttribute.booleanValue();
    return new TextFormat(FontStyle.getFrom(font), !notIsUnderline);
  }

  protected void fireTextChangedEvent() {
    try {
      actualTextParts = extractTextParts();
    }
    catch (BadLocationException e) {
      throw new IllegalStateException(e);
    }
    for (IStyledTextChangeListener listener : listeners) {
      listener.textChanged(actualTextParts);
    }
  }

  public synchronized void addStyledTextListener(IStyledTextChangeListener listener) {
    listeners.add(listener);
  }

  public static void initView(final IStyledTextManager manager, final IStyledTextualDescription textualDescription) {
    manager.addStyledTextListener(new IStyledTextChangeListener() {
      public void textChanged(ITextPart[] newParts) {
        textualDescription.setText(newParts);
      }
    });
    manager.setText(textualDescription.getTextParts());
    textualDescription.addTextChangedListener(new IStyledTextChangeListener() {
      public void textChanged(ITextPart[] newParts) {
        manager.setText(newParts);
      }
    });
  }
}