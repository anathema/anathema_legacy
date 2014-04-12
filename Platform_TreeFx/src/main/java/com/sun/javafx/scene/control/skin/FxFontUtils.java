package com.sun.javafx.scene.control.skin;

import javafx.scene.text.Font;
import javafx.scene.text.TextBoundsType;

public class FxFontUtils {

  public static double calculateStringWidth(Font font, String text) {
    return Utils.computeTextWidth(font, text, 0);
  }

  public static double calculateStringHeight(Font font, String text) {
    return Utils.computeTextHeight(font, text, 0, TextBoundsType.VISUAL);
  }
}