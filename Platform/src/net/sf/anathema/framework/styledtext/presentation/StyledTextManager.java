package net.sf.anathema.framework.styledtext.presentation;

import net.sf.anathema.framework.styledtext.model.IStyledTextChangeListener;
import net.sf.anathema.framework.styledtext.model.IStyledTextualDescription;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.lib.text.FontStyle;
import org.jmock.example.announcer.Announcer;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StyledTextManager implements IStyledTextManager {

  private final Announcer<IStyledTextChangeListener> listeners = Announcer.to(IStyledTextChangeListener.class);
  private final DefaultStyledDocument document;
  private ITextPart[] actualTextParts = new ITextPart[0];

  public StyledTextManager(DefaultStyledDocument document) {
    this.document = document;
    this.document.addDocumentListener(new DocumentListener() {
      @Override
      public void changedUpdate(DocumentEvent e) {
        fireTextChangedEvent();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        fireTextChangedEvent();
      }

      @Override
      public void insertUpdate(DocumentEvent e) {
        fireTextChangedEvent();
      }
    });
  }

  @Override
  public void setText(ITextPart[] textParts) {
    if (Arrays.equals(textParts, actualTextParts)) {
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
    boolean notIsUnderline = underlineAttribute == null || !underlineAttribute;
    return new TextFormat(FontStyle.getStyle(font.isBold(), font.isItalic()), !notIsUnderline);
  }

  protected void fireTextChangedEvent() {
    try {
      actualTextParts = extractTextParts();
    }
    catch (BadLocationException e) {
      throw new IllegalStateException(e);
    }
    listeners.announce().textChanged(actualTextParts);
  }

  @Override
  public void addStyledTextListener(IStyledTextChangeListener listener) {
    listeners.addListener(listener);
  }

  public static void initView(final IStyledTextManager manager, final IStyledTextualDescription textualDescription) {
    manager.addStyledTextListener(new IStyledTextChangeListener() {
      @Override
      public void textChanged(ITextPart[] newParts) {
        textualDescription.setText(newParts);
      }
    });
    manager.setText(textualDescription.getTextParts());
    textualDescription.addTextChangedListener(new IStyledTextChangeListener() {
      @Override
      public void textChanged(ITextPart[] newParts) {
        manager.setText(newParts);
      }
    });
  }
}