
/*
 * Festival Interface
 */
interface Festival {

    void festivalName();

    void majorStates();

    void origin();

    void festivalDuration();

}

/*
 * abstract class GanpatiFestival
 */
abstract class GanpatiFestival implements Festival {

    String festivalname = "GanPati Utsav";
    String majorStates = "Maharashtra../..And whole world";
    String festivalDuration = "10/11 Days...";

    // method overridden from interface
    public void festivalDuration() {
        System.out.println(festivalDuration);
    }

    // method overridden from interface
    public void majorStates() {
        System.out.println(majorStates);
    }

    // method overridden from interface
    public void festivalName() {
        System.out.println(festivalname);
    }

}

class Ganpati extends GanpatiFestival {

    private String ganpatiName;
    private String origin;

    Ganpati(String ganpatiName, String origin) {
        this.ganpatiName = ganpatiName;
        this.origin = origin;
    }

    // method overridden from interface
    public void origin() {
        System.out.println(this.origin);
    }

    // method of class Ganpati........
    public void ganpatiName() {
        System.out.println(this.ganpatiName);
    }

}

class Dagdusheth {

    public static void main(String[] args) {
        Ganpati ganpati = new Ganpati("Dagdusheth", "PUNE");
        Ganpati ganpati1 = new Ganpati("LAL-BAGH-CHA-RAJA", "MUMBAI");

        ganpati.festivalName();
        ganpati.majorStates();
        ganpati.festivalDuration();
        System.out.println("\n********************");
        ganpati.ganpatiName();
        ganpati.origin();

        System.out.println("********************");
        ganpati1.ganpatiName();
        ganpati1.origin();

    }
}

/*
 * omkars-MacBook-Air:OmkarJava omkarajagunde$ javac
 * GanpatiFestivalAllConcepts.java omkars-MacBook-Air:OmkarJava omkarajagunde$
 * java Dagdusheth GanPati Utsav Maharashtra../..And whole world 10/11 Days...
 ********************
 * 
 * Dagdusheth PUNE
 ********************
 * LAL-BAGH-CHA-RAJA MUMBAI omkars-MacBook-Air:OmkarJava omkarajagunde$
 */