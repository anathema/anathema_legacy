package net.sf.anathema.character.generic.framework.util;

import java.awt.Color;
import java.awt.Container;
import java.awt.SystemColor;

import javax.swing.JLabel;

public class ExperienceUtilities {

  public static void setLabelColor(Container container, boolean enabled) {
    if (container instanceof JLabel) {
      container.setEnabled(true);
      if (enabled) {
        container.setForeground(SystemColor.textText);
      }
      else {
        container.setForeground(Color.DARK_GRAY);
      }
    }
  }  
}