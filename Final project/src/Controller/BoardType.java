/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public abstract class BoardType {

    public abstract void rungame();
    public abstract void drawCell(GraphicsContext g, Canvas c,ColorPicker cp,boolean shape);
    public abstract void clickactivatecell(int y, int x,Canvas c);
    public abstract void dragactivatecell(int y, int x,Canvas c);
    public abstract void randomcell();
    public abstract void resetGrid();
    public abstract void loadnewmove(byte[][] newmove,int a,int b,boolean dynamic);

}
