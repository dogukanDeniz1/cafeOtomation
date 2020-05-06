package kafe_otomasyon;

public class Urunler {
    private String name;
    private float value;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Urunler(){

    }
    public Urunler(String name, float value) {
        this.name = name;
        this.value = value;
        this.number = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void incNumber(){
        this.number++;
    }

    public void decNumber(){
        this.number--;
    }
}
