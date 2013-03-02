package net.sf.anathema.character.library.overview;

import javax.swing.border.TitledBorder;
import java.awt.Component;
import java.awt.Font;

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