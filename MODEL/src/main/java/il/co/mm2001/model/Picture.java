package il.co.mm2001.model;

public class Picture extends BaseEntity{
    private String  name;
    private boolean isSelected;

    public Picture(String name) {
        isSelected = false;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public boolean isPictureSelected(){
        return this.isSelected;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPictureSelected(boolean isSelected){
        this.isSelected = isSelected;
    }
}
