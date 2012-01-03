package net.sf.anathema.character.library.overview;

import java.awt.Component;
import java.awt.Font;

import javax.swing.border.TitledBorder;

@SuppressWarnings("serial") // Will not be serialized
public class FontTitledBorder extends TitledBorder {
  
  public FontTitledBorder(String title){
    super(title);
  }
  
  @Override
  public Font getFont(Component c) {
    return super.getFont(c);
  }
}