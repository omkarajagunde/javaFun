
// interface for a company which would definitely have following feilds.......
interface Company {

    void directorNameDisplay();

    void industryFocus();

    void companyProduct();

}

// Abstract Class
abstract class Redmi implements Company {

    String companyName = "XioMi RedMi World";
    String companyProduct = "Mobile and consumer Electronics";

    // method overridden from interface
    public void industryFocus() {
        System.out.println("Estb. RedMi Products and mobiles in Indian Market... .. .");
    }

    // method overridden from interface
    public void companyProduct() {
        System.out.println(this.companyProduct);
    }

    // method of class REdmi..
    public void markertingStyle() {
        System.out.println("Retail Distribution");
    }

    // method of class REdmi.. THis will be overridden through anonymous class in
    // MAIN();
    public void projectedSales() {
        System.out.println("5000 Mobiles to be sold in a quarter.. .. . .");
    }

    // methods of class REdmi.. This is a overloaded methods
    public void salesGross(String month) {
        System.out.println("23 Crore in " + month);
    }

    // methods of class REdmi.. This is a overloaded methods
    public void salesGross(int year) {
        System.out.println("2000 Crore in Year " + year);
    }

}

// Redmi India class extending class Redmi (INHERITANCE....)
class RedmiIndia extends Redmi {

    private String directorName;
    int productNos;

    RedmiIndia(String directorName, int productNos) {
        this.directorName = directorName;
        this.productNos = productNos;

    }

    public void markertingStyle() {
        System.out.println("Digital Media and Internet Marketing.....");
    }

    // Method Overridden.....................
    public void directorNameDisplay() {
        System.out.println("Director appointed : \t" + directorName);
    }

}

class RedmiIndiaOperations {

    public static void main(String[] args) {
        RedmiIndia redmi = new RedmiIndia("ManuJAin", 5000);
        redmi.directorNameDisplay();

        redmi.projectedSales();
        redmi.markertingStyle();
        redmi.companyProduct();
        redmi.industryFocus();

        // Anonymous class overriding the projected Sales Method in RedmiIndia
        // class........
        RedmiIndia ri = new RedmiIndia("ManuJain", 10000) {
            public void projectedSales() {
                System.out.println("Actually " + this.productNos + " Mobiles were sold in a quarter.. .. . . ");
            }
        };

        ri.projectedSales();
        redmi.salesGross("july");
        redmi.salesGross(2018);

    }

}

/*
 * 
 * omkars-MacBook-Air:OmkarJava omkarajagunde$ javac CompanyAllConcepts.java
 * omkars-MacBook-Air:OmkarJava omkarajagunde$ java RedmiIndiaOperations
 * Director appointed : ManuJAin 5000 Mobiles to be sold in a quarter.. .. . .
 * Digital Media and Internet Marketing..... Mobile and consumer Electronics
 * Estb. RedMi Products and mobiles in Indian Market... .. . Actually 10000
 * Mobiles were sold in a quarter.. .. . . 23 Crore in july 2000 Crore in Year
 * 2018 omkars-MacBook-Air:OmkarJava omkarajagunde$
 * 
 */