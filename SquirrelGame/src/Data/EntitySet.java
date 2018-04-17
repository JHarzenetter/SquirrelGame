package Data;

public class EntitySet {

    private Entity[] list = new Entity[20];
    private int l = 0;


    public void insertEntity(Entity e) {
        if (l < 20) {
            list[l] = e;
            l++;
        }
    }

    public void deleteEntity(Entity e) {
        for (int i = 0; i < l; i++) {
            if (list[i] == e) {
                for (int k = i; k < l - 1; k++) {
                    list[k] = list[k + 1];
                }
                l--;
                return;
            }
        }
    }

   /* public void nextStep() {
        for (int i = 0; i < l; i++) {
            list[i].nextStep();
        }
    }*/

    public String toString(){
        String s = "";
        for(int i=0; i<l; i++)
            s = s+""+list[i];
        return s;
    }
}
