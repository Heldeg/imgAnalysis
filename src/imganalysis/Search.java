/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imganalysis;

/**
 *
 * @author EDDER
 */
public interface Search {
    public static final byte LARGE = 3;
    public static final byte MEDIUM = 2;
    public static final byte SMALL = 1;
    public static final byte MEDIUMLARGE = 4;
    public void referenceImageColorToMatrix();
    
    public void mapColorToMatrix();
    
    public boolean compareImg(int x, int y);
}
