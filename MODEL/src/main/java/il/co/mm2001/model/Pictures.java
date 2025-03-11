package il.co.mm2001.model;

import java.util.ArrayList;

public class Pictures extends BaseList<Picture, Pictures> {
    public Pictures(){
        getAll();
    }
    public void getAll(){
        add(new Picture("popeye"));
        add(new Picture("olive"));
        add(new Picture("bluto"));
        add(new Picture("mickey"));
        add(new Picture("minnie"));
        add(new Picture("pluto"));
        add(new Picture("potato"));
        add(new Picture("pinocchio"));
        add(new Picture("zootopia"));
        add(new Picture("sulley"));
        add(new Picture("remy"));
        add(new Picture("joy"));
        add(new Picture("tigger"));
        add(new Picture("anna"));
        add(new Picture("peterpan"));
        add(new Picture("dumbo"));
        add(new Picture("rapunzel"));
        add(new Picture("marlin"));
        add(new Picture("eeyore"));
        add(new Picture("miguel"));
    }
}
