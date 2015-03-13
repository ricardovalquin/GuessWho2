/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.ArrayList;
/**
 *
 * @author rick
 */
public class Characters {
    private String name;
    private ArrayList<String> Selfcharacteristic;
    private boolean state;
    private boolean choosed;
    
    public Characters(String name, String hairColor, String eyesColor, String skinColor, String moustache, String barb, String glases, String sex ){
        this.name = name;
        Selfcharacteristic = new ArrayList();
        Selfcharacteristic.add(hairColor);
        Selfcharacteristic.add(eyesColor);
        Selfcharacteristic.add(skinColor);
        Selfcharacteristic.add(moustache);
        Selfcharacteristic.add(barb);
        Selfcharacteristic.add(glases);
        Selfcharacteristic.add(sex);
        this.state = true;
        this.choosed = false;
    }

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSelfcharacteristic() {
        return Selfcharacteristic;
    }
}
