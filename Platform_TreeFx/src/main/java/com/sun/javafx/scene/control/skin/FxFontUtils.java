package com.sun.javafx.scene.control.skin;

import javafx.geometry.Bounds;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FxFontUtils {

  public static double calculateStringWidth(Font font, String text) {
    Bounds bounds = getBounds(font, text);
    return bounds.getWidth();
  }

  public static double calculateStringHeight(Font font, String text) {
    Bounds bounds = getBounds(font, text);
    return bounds.getHeight();
  }

  private static Bounds getBounds(Font font, String text) {
    Text object = new Text();
    object.setFont(font);
    object.setText(text);
    return object.getLayoutBounds();
  }
}