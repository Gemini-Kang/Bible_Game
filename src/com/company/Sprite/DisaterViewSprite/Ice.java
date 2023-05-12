package com.company.Sprite.DisaterViewSprite;

import com.company.Sprite.Sprite;

import javax.swing.*;

public class Ice extends Sprite {
    public Ice(int x, int y) {
        setPosition(x, y);
        img = new ImageIcon("Ice.png");

    }
    @Override
    public String overlap(int x, int y) {
        return null;
    }
}
