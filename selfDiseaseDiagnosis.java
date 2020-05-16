
import java.util.HashMap; // util package is imported as we are going to use Collection classes like HashMap,List etc ...
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.io.InputStreamReader;
import java.io.BufferedReader;	// io package is imported as we are going to use BufferedRedaer to take input from the User ...

/**
 * 
 * Heirarchy of classes and interface as belows:
 * 			
 * 			interface Disease	class Illness	abstract class DiseaseTracker
 * 					|				 |						|
 * 					\				 |						/
 * 					 \				 |					   /
 * 				class Illness.Allergies, class Illness.ColdAndFlu
 * 									 |
 * 									 |
 * 									 |
 * 					class Person(main methods in this class)
 * 
 * In nutshell following concepts were used:
 * 
 * 1] Interface
 * 2] abstract class
 * 3] normal inner classes
 * 4] anonymous class
 * 5] Threading using Thread class
 * 6] Overriding methods
 * 7] Overloading methods
 * 8] Inheritance
 * 9] Use of this and super keywords
 * 10] I/O using BufferedReader
 * 11] Collection classes use - ArrayList, List, HashMap
 * 12] Use of Iterator
 * 13] Use of try, catch ... throwing Exception to JVM
 * 14] Use of default,static to give definition to method in interface
 * 15] Using genric types <T>
 * 16] public class usage to make object of class in another file
 * 			
 * 			
 */

// This class is used to change and reset the terminal colors for displaying error messages ...
class Colors{
	public static final String ANSI_RED = "\033[1;31m";
	public static final String ANSI_BLUE = "\033[1;34m";
	public static final String ANSI_GREEN = "\033[1;32m";
	public static final String ANSI_RESET = "\u001B[0m";

}

// Disease is declared as a interface as we know every disease has some associated attributes like causes, 
// symptoms, treatment, and ways to prevent diseases
interface Disease{


	// Any specific disease like here Allergies and ColdAndFlu will implement these four methods
	void displayCauses();
	void displaySymptoms();
	void displayTreatment();
	void displayPreventionMethods();

	/**
	 * method-name : getStrings()
	 * 
	 * This method will be used to load all the strings related to a specific disease
	 * here Allergies and Cold & Flu, also based on key in is decided weather to load strings
	 * of Allergies or to load strings of Cold and Flu 
	 * 
	 * Also notice this method is declared iand defined in interface using {static} keyword
	 * becuase be it any specific disease implmenting this interface all have to load strings 
	 * of their sepcific disease from file {DiseaseStrings.java}
	 * 
	 * @param key
	 * @return HashMap<String, String>
	 * @throws Exception
	 */
	static HashMap<String, String> getStrings(String key) throws Exception{

	
			if (key == "Allergies")
				return DiseaseStrings.getAllergiesHashmap();
			else
				return DiseaseStrings.getColdAndFluHashmap();
	}
}

/**
 * DiseaseTracker is a abstract class with 3 methods of which 2 methods are 
 * to be overloaded by the child class that will extend this class
 * 
 * These 2 methods are abstract becuase each specific disease will have its own symptoms 
 * and to check and display these symptoms methods cannot be defined here hence thses 2 methods 
 * are declared here and will be overridden by child...
 */
abstract class DiseaseTracker{

	// List of strings to store causes and symptoms of strings
	ArrayList<String> causesList;
	ArrayList<String> symptomsList;

	// abstract declarations will be overriden by class extending this class
	abstract void displayMenu() throws IOException;
	abstract void checkSymptoms() throws IOException;

	/**
	 * method-name : tokenizeStrings()
	 * 
	 * The strings that are been fetched from the HashMap residing in file
	 * DiseaseStrings have big one line string which must be splitted into a 
	 * List for e.g. numerous symptoms are included in one big string, and delimiter {\\} is used
	 * to indicate tokenizer how to split
	 * 
	 * Also each disease is required to tokenize their strings coming for
	 * HasMap hence this method will be common and inherited to the class 
	 * extending this class
	 * 
	 * 
	 * @param bigString
	 * @return List<String>
	 */
	public List<String> tokenizeStrings(String bigString){

		StringTokenizer tokenizer = new StringTokenizer(bigString, "\\");
		List<String> tokenList = new ArrayList<String>();

		while(tokenizer.hasMoreTokens()){
			tokenList.add(tokenizer.nextToken());
		}

		return tokenList;

	}

}


/**
 * 
 * This class is a general class that will hold two inner class 
 * 1] Allergies 2] Cold and flu ... as both classes are related to
 * person feeling Ill hence these two classes are declared as inner classes
 */
class Illness extends Thread{

	// Variables to store information of person
	String personName;
	int personAge;
	char maleOrFemale;

	/**
	 * Parameterised constructor and use to this keyword in order
	 * to intialize the variables for further use to Inner classes
	 * @param personName
	 * @param personAge
	 * @param maleOrFemale
	 */
	Illness(String personName, int personAge, char maleOrFemale){
		this.personName = personName;
		this.personAge = personAge;
		this.maleOrFemale = maleOrFemale;
	}

	/**
	 * normal Inner class that will actually iteract withe users
	 * to show them information reagarding allergies and check weather a 
	 * person is allergetic or not ...
	 */
	class Allergies extends DiseaseTracker implements Disease{

		// Variables to stroe data related to Allergies which is retrieved from HashMap in DiseaseStrings.java
		String diseaseInfo;
		String whyDiseaseCauses;
		List<String> whatCausesDisease;
		HashMap<String, String> allergies;
		List<String> diseaseSymptoms;
		List<String> diseaseTreatment;
		List<String> diseasePrevention;
		String diseaseDuration;
		BufferedReader reader;
		int symptomsCounter;

		// Instance of a iterator to iterator over list of symptoms, causes, treatments etc ...
		Iterator iterator;


		/**
		 * Default constructor in order initialse the strings realted to allergies
		 * also initialization of BufferedReader instance is done to accept data from users
		 * @throws Exception
		 */
		Allergies() throws Exception{

			allergies = Disease.getStrings("Allergies");
			whatCausesDisease = tokenizeStrings(allergies.get("whatCausesDisease"));
			diseaseInfo = allergies.get("diseaseInfo");
			whyDiseaseCauses = allergies.get("whyDiseaseCauses");
			diseaseSymptoms = tokenizeStrings(allergies.get("diseaseSymptoms"));
			diseaseTreatment = tokenizeStrings(allergies.get("diseaseTreatment"));
			diseasePrevention = tokenizeStrings(allergies.get("diseasePrevention"));
			diseaseDuration = allergies.get("diseaseDuration");

			reader = new BufferedReader(new InputStreamReader(System.in));



		}

		/**
		 * method-name : displayCauses()
		 * 
		 * This method is used to display the reason
		 * why allergies occur in people
		 */
		@Override
		public void displayCauses(){

			iterator = whatCausesDisease.iterator();

			System.out.println("\n\nThese are some of the Symptoms of Allergies :: ");
			while(iterator.hasNext()){
				System.out.println(Colors.ANSI_BLUE+"\t|----"+iterator.next()+Colors.ANSI_RESET);
			}

		}

		/**
		 * method-name : displaySymptoms()
		 * 
		 * This method is used to display the Symptoms
		 * of allergies
		 */
		@Override
		public void displaySymptoms(){

			iterator = diseaseSymptoms.iterator();

			System.out.println("\n\nThese are some of the Symptoms of Allergies :: ");
			while(iterator.hasNext()){
				System.out.println(Colors.ANSI_BLUE+"\t|----"+iterator.next()+Colors.ANSI_RESET);
			}

		}

		/**
		 * method-name : displayTreatment()
		 * 
		 * This method is used to display the some of the common
		 * methods to treat allergies
		 */
		@Override
		public void displayTreatment(){

			iterator = diseaseTreatment.iterator();

			System.out.println("\n\nThese are some of the Treatment methods on Allergies :: ");
			while(iterator.hasNext()){
				System.out.println(Colors.ANSI_BLUE+"\t|----"+iterator.next()+Colors.ANSI_RESET);
			}

		}

		/**
		 * method-name : displayPreventionMethods()
		 * 
		 * This method is used to display the some of the common
		 * methods to prevent allergies from happening
		 */
		@Override
		public void displayPreventionMethods(){

			iterator = diseasePrevention.iterator();

			System.out.println("\n\nThese are some of the Prevention methods to \n\t\tPrevent Allergies from happening :: ");

			while(iterator.hasNext()){
				System.out.println(Colors.ANSI_BLUE+"\t|----"+iterator.next()+Colors.ANSI_RESET);
			}

		}

		/**
		 * method-name : displayMenu()
		 * 
		 * This method is used to display the
		 * menu to user about allergies
		 */
		@Override
		public void displayMenu() throws IOException{

			char continueOrNot;
			int choice;

			do{

				System.out.println(""+personName+" choose what you want to see about Allergies :: ");
				System.out.println("\t|---- 1] Display allergy causes?");
				System.out.println("\t|---- 2] Display allergy symptoms?");
				System.out.println("\t|---- 3] Display allergy Treatment?");
				System.out.println("\t|---- 4] Display Methods to Prevent Allery?");
				System.out.println(Colors.ANSI_GREEN+"\t|---- 5] Check weather you have allergy or Not?"+Colors.ANSI_RESET);
				System.out.println("\t|---- 6] Exit from allergies Menu");
				System.out.print("\t|---------------- Enter One Choice --------------- :: ");
				
				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
					case 1:
						displayCauses();
						break;
					case 2:
						displaySymptoms();
						break;
					case 3:
						displayTreatment();
						break;
					case 4:
						displayPreventionMethods();
						break;
					case 5:
						checkSymptoms();
						break;
					case 6:
						reader.close();
						System.exit(0);						
					default:
						System.out.print("\t|---------------- Wrong Value Entered Try again! --------------- :: ");
						
						
						break;
				}
				System.out.print("\t|---------------- Hit [Y/N] to try again  --------------- :: ");
				continueOrNot = reader.readLine().charAt(0);


			}while(continueOrNot== 'y' || continueOrNot=='Y');
			
			
		}
		
		/**
		 * method-name : checkSymptoms()
		 * 
		 * This method is used to check weather
		 * the person is symptomatic with allergies, if yes
		 * how severely is he symptomatic
		 */
		@Override
		public void checkSymptoms() throws IOException{

			iterator = diseaseSymptoms.iterator();
			int totalSymptomsCount = 0;

			while (iterator.hasNext()) {
				String symptom = iterator.next().toString();
				System.out.print("\n\t\t|----Do you have "+ symptom + "?  [Y/N]:");
				char yesNo = reader.readLine().charAt(0);
				if(yesNo == 'y' || yesNo == 'Y')
					symptomsCounter += 1;

				totalSymptomsCount += 1;	


			}

			if ((symptomsCounter * 100 / totalSymptomsCount > 50) && (symptomsCounter * 100 / totalSymptomsCount <= 70))
				System.out.println(Colors.ANSI_RED+"\n\t\t\t|----"+personName+" there is 50% changes that you have been caught up with allergies ..."+Colors.ANSI_RESET);
			else if((symptomsCounter * 100 / totalSymptomsCount > 70) && (symptomsCounter * 100 / totalSymptomsCount <= 90))
				System.out.println(Colors.ANSI_RED+"\n\t\t\t|----"+personName+" there is 70% changes that you have been caught up with allergies ..."+Colors.ANSI_RESET);
			else if(symptomsCounter * 100 / totalSymptomsCount > 90)
				System.out.println(Colors.ANSI_RED+"\n\t\t\t|----"+personName+" there is 90% changes that you have been caught up with allergies ...\n"+
				"\n\t\tPlease Visit a doctor to get cured !!! and avoid other complications ..."+Colors.ANSI_RESET);
			else if((symptomsCounter * 100 / totalSymptomsCount <= 50)){
				
				if (maleOrFemale == 'M' || maleOrFemale == 'm')
					System.out.println(Colors.ANSI_GREEN+"\n\t\t\t|----Nope we dont think you have Cold and Flu ... Mr "+personName+Colors.ANSI_RESET);
				else
					System.out.println(Colors.ANSI_GREEN+"\n\t\t\t|----Nope we dont think you have Cold and Flu ...Mrs "+personName+Colors.ANSI_RESET);	
			}
		}


	}

	/**
	 * normal Inner class that will actually iteract withe users
	 * to show them information reagarding Cold and flu and check weather a 
	 * person has Cold and flu or not ...
	 */
	class ColdAndFlu extends DiseaseTracker implements Disease{

		// Variables to stroe data related to Allergies which is retrieved from HashMap in DiseaseStrings.java
		String diseaseInfo;
		String whyDiseaseCauses;
		List<String> whatCausesDisease;
		HashMap<String, String> coldAndFlu;
		List<String> diseaseSymptoms;
		List<String> diseaseTreatment;
		List<String> diseasePrevention;
		String diseaseDuration;
		BufferedReader reader;
		
		// Instance of a iterator to iterator over list of symptoms, causes, treatments etc ...
		Iterator iterator;

		int symptomsCounter;

		/**
		 * Default constructor in order initialse the strings realted to Cold and Flu
		 * also initialization of BufferedReader instance is done to accept data from users
		 * @throws Exception
		 */
		ColdAndFlu() throws Exception{

			coldAndFlu = Disease.getStrings("coldAndFlu");
			whatCausesDisease = tokenizeStrings(coldAndFlu.get("whatCausesDisease"));
			diseaseInfo = coldAndFlu.get("diseaseInfo");
			whyDiseaseCauses = coldAndFlu.get("whyDiseaseCauses");
			diseaseSymptoms = tokenizeStrings(coldAndFlu.get("diseaseSymptoms"));
			diseaseTreatment = tokenizeStrings(coldAndFlu.get("diseaseTreatment"));
			diseasePrevention = tokenizeStrings(coldAndFlu.get("diseasePrevention"));
			diseaseDuration = coldAndFlu.get("diseaseDuration");

			reader = new BufferedReader(new InputStreamReader(System.in));

		}

		/**
		 * method-name : displayCauses()
		 * 
		 * This method is used to display the reason
		 * why cold and flu occur in people
		 */
		@Override
		public void displayCauses(){

			iterator = whatCausesDisease.iterator();

			System.out.println("\n\nThese are some of the Causes of Cold and Flu :: ");
			while(iterator.hasNext()){
				System.out.println(Colors.ANSI_BLUE+"\t|----"+iterator.next()+Colors.ANSI_RESET);
			}
		}

		/**
		 * method-name : displaySymptoms()
		 * 
		 * This method is used to display the Symptoms
		 * of Cold and Flu
		 */
		@Override
		public void displaySymptoms(){

			iterator = diseaseSymptoms.iterator();

			System.out.println("\n\nThese are some of the Symptoms of Cold and Flu :: ");
			while(iterator.hasNext()){
				System.out.println(Colors.ANSI_BLUE+"\t|----"+iterator.next()+Colors.ANSI_RESET);
			}
		}
		
		/**
		 * method-name : displayTreatment()
		 * 
		 * This method is used to display the some of the common
		 * methods to treat Cold and Flu
		 */
		@Override
		public void displayTreatment(){

			iterator = diseaseTreatment.iterator();

			System.out.println("\n\nThese are some of the Treatment methods on Cold and Flu :: ");
			while(iterator.hasNext()){
				System.out.println(Colors.ANSI_BLUE+"\t|----"+iterator.next()+Colors.ANSI_RESET);
			}

		}

		/**
		 * method-name : displayPreventionMethods()
		 * 
		 * This method is used to display the some of the common
		 * methods to prevent Cold and Flu from happening
		 */
		@Override
		public void displayPreventionMethods(){

			iterator = diseasePrevention.iterator();

			System.out.println("\n\nThese are some of the Prevention methods to \nPrevent Cold and Flu from happening :: ");

			while(iterator.hasNext()){
				System.out.println(Colors.ANSI_BLUE+"\t|----"+iterator.next()+Colors.ANSI_RESET);
			}
			
		}

		/**
		 * method-name : displayMenu()
		 * 
		 * This method is used to display the
		 * menu to user about Cold and Flu
		 */
		@Override
		public void displayMenu() throws IOException{

			char continueOrNot;
			int choice;

			do{

				System.out.println(""+personName+" choose what you want to see about Cold and flu :: ");
				System.out.println("\t|---- 1] Display Cold and flu causes?");
				System.out.println("\t|---- 2] Display Cold and flu symptoms?");
				System.out.println("\t|---- 3] Display Cold and flu Treatment?");
				System.out.println("\t|---- 4] Display Methods to Prevent Cold and flu?");
				System.out.println(Colors.ANSI_GREEN+"\t|---- 5] Check weather you have Cold and flu or Not?"+Colors.ANSI_RESET);
				System.out.println("\t|---- 6] Exit from Cold and flu Menu");
				System.out.print("\t|---------------- Enter One Choice "+personName+"--------------- :: ");
				
				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
					case 1:
						displayCauses();
						break;
					case 2:
						displaySymptoms();
						break;
					case 3:
						displayTreatment();
						break;
					case 4:
						displayPreventionMethods();
						break;
					case 5:
						checkSymptoms();
						break;
					case 6:
						reader.close();
						System.exit(0);					
					default:
						System.out.print(Colors.ANSI_RED+"\t|---------------- Wrong Value Entered Try again! --------------- :: "+Colors.ANSI_RESET);
						break;
				}

				System.out.print("\t|---------------- Hit [Y/N] to try again  --------------- :: ");
				continueOrNot = reader.readLine().charAt(0);


			}while(continueOrNot== 'y' || continueOrNot=='Y');
			
		}

		/**
		 * method-name : checkSymptoms()
		 * 
		 * This method is used to check weather
		 * the person is symptomatic with Cold and Flu, if yes
		 * how severely is he symptomatic
		 */
		@Override
		public void checkSymptoms() throws IOException{

			iterator = diseaseSymptoms.iterator();
			int totalSymptomsCount = 0;

			while (iterator.hasNext()) {
				String symptom = iterator.next().toString();
				System.out.print("\n\t\t|----Do you have "+ symptom + "?  [Y/N]:");
				char yesNo = reader.readLine().charAt(0);
				if(yesNo == 'y' || yesNo == 'Y')
					symptomsCounter += 1;

				totalSymptomsCount += 1;	


			}

			if ((symptomsCounter * 100 / totalSymptomsCount > 50) && (symptomsCounter * 100 / totalSymptomsCount <= 70))
				System.out.println(Colors.ANSI_RED+"\n\t\t\t|----"+personName+" there is 50% changes that you have been caught up with COld and Flu ..."+Colors.ANSI_RESET);
			else if((symptomsCounter * 100 / totalSymptomsCount > 70) && (symptomsCounter * 100 / totalSymptomsCount <= 90))
				System.out.println(Colors.ANSI_RED+"\n\t\t\t|----"+personName+" there is 70% changes that you have been caught up with COld and Flu ..."+Colors.ANSI_RESET);
			else if(symptomsCounter * 100 / totalSymptomsCount > 90)
				System.out.println(Colors.ANSI_RED+"\n\t\t\t|----"+personName+" there is 90% changes that you have been caught up with COld and Flu ..."+
				"\n\t\t\tPlease Visit a doctor to get cured !!! and avoid other complications ..."+Colors.ANSI_RESET);
			else if((symptomsCounter * 100 / totalSymptomsCount <= 50)){
				
				if (maleOrFemale == 'M' || maleOrFemale == 'm')
					System.out.println(Colors.ANSI_GREEN+"\n\t\t\t|----Nope we dont think you have Cold and Flu ... Mr "+personName+Colors.ANSI_RESET);
				else
					System.out.println(Colors.ANSI_GREEN+"\n\t\t\t|----Nope we dont think you have Cold and Flu ...Mrs "+personName+Colors.ANSI_RESET);	
			}
		}

	}

}

/**
 * This class is used as a driver class to that includes main()
 * method. And includes the main menu from where the above innerclasses
 * objects are created and their respective methods are been called
 */
class Person extends Illness{

	static String personName;
	static int personAge;
	static char maleOrFemale;

	/**
	 * Initialization of variables taking place here .. in this
	 * parameterized constructor ...
	 * @param personName
	 * @param personAge
	 * @param maleOrFemale
	 */
	Person(String personName, int personAge, char maleOrFemale){
		super(personName,personAge, maleOrFemale);
		this.personName = personName;
		this.personAge = personAge;
		this.maleOrFemale = maleOrFemale;
	}

	/**
	 * method-name : displayMenu()
	 * 
	 * This method will display menu of respective 
	 * allergies class
	 * 
	 * @param allergies
	 * @throws Exception
	 */
	public void displayMenu(Allergies allergies) throws Exception {
		allergies.displayMenu();
		
	}

	/**
	 * method-name : displayMenu()
	 * 
	 * Notice this method is a overloaded method that has same name 
	 * but different parameter 
	 * 
	 * This method will display menu of respective 
	 * ColdAndFlu class
	 * @param coldAnfFlu
	 * @throws Exception
	 */
	public void displayMenu(ColdAndFlu coldAnfFlu) throws Exception {
		coldAnfFlu.displayMenu();

	}

	/**
	 * mathod-name : displayMenu()
	 * 
	 * This method is also a overloaded method that takes no
	 * parameters and drives menus of allergies class and 
	 * ColdAndFlu class respectively...
	 * 
	 * @param None
	 * @throws Exception
	 */
	public void displayMenu() throws Exception {
		
			char continueOrNot;
			int choice;
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			do{

				System.out.println(""+personName+" choose what you want to see about :: ");
				System.out.println("\t|---- 1] Allergies?");
				System.out.println("\t|---- 2] Cold and Flu?");
				System.out.println("\t|---- 3] Exit Main Menu ?");
				System.out.print("\t|---------------- Enter One Choice "+personName+"--------------- :: ");
				
				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
					case 1:
						Person personAllergy = new Person(personName,personAge,maleOrFemale);
						Illness.Allergies allr = new Illness(personName,personAge,maleOrFemale).new Allergies();
						personAllergy.displayMenu(allr);
						break;
					case 2:
						Person personColdAndFlu = new Person(personName,personAge,maleOrFemale);

						/**
						 * Anonymous inner class which shows that at runtime cold and flu symptoms
						 * now a days can also be the symptoms of Covid 19 ... which can be found 
						 * at runtime hence this anonymous inner class ...
						 */
						Illness.ColdAndFlu coldFlu = new Illness(personName,personAge,maleOrFemale).new ColdAndFlu(){
							/**
							 * method-name : checkSymptoms()
							 * 
							 * This method is used to check weather
							 * the person is symptomatic with Cold and Flu, if yes
							 * how severely is he symptomatic
							 */
							@Override
							public void checkSymptoms() throws IOException{

								iterator = diseaseSymptoms.iterator();
								int totalSymptomsCount = 0;

								while (iterator.hasNext()) {
									String symptom = iterator.next().toString();
									System.out.print("\n\t\t|----Do you have "+ symptom + "?  [Y/N]:");
									char yesNo = reader.readLine().charAt(0);
									if(yesNo == 'y' || yesNo == 'Y')
										symptomsCounter += 1;

									totalSymptomsCount += 1;	


								}

								if ((symptomsCounter * 100 / totalSymptomsCount > 50) && (symptomsCounter * 100 / totalSymptomsCount <= 70))
									System.out.println(Colors.ANSI_RED+"\n\t\t\t|----"+personName+" there is 50% changes that you have been caught up with COld and Flu ..."+Colors.ANSI_RESET);
								else if((symptomsCounter * 100 / totalSymptomsCount > 70) && (symptomsCounter * 100 / totalSymptomsCount <= 90))
									System.out.println(Colors.ANSI_RED+"\n\t\t\t|----"+personName+" there is 70% changes that you have been caught up with COld and Flu ...\n\t\t\t also Covid19 is active nowadays please go to doctor and consult ..."+Colors.ANSI_RESET);
								else if(symptomsCounter * 100 / totalSymptomsCount > 90)
									System.out.println(Colors.ANSI_RED+"\n\t\t\t|----"+personName+" there is 90% changes that you have been caught up with COVID 19 ...\n"+
									"\t\t\tPlease Visit a Govt hospital to test yourself and get your report..."+Colors.ANSI_RESET);
								else if((symptomsCounter * 100 / totalSymptomsCount <= 50)){
									
									if (maleOrFemale == 'M' || maleOrFemale == 'm')
										System.out.println(Colors.ANSI_GREEN+"\n\t\t\t|----Nope we dont think you have Cold and Flu ... Mr "+personName+Colors.ANSI_RESET);
									else
										System.out.println(Colors.ANSI_GREEN+"\n\t\t\t|----Nope we dont think you have Cold and Flu ...Mrs "+personName+Colors.ANSI_RESET);	
								}
							}

						};
						personColdAndFlu.displayMenu(coldFlu);
						break;
					case 3:
						reader.close();
						System.exit(0);					
					default:
						System.out.print(Colors.ANSI_RED+"\t|---------------- Wrong Value Entered Try again! --------------- :: "+Colors.ANSI_RESET);
						break;
				}

				System.out.print("\t|---------------- want main menu [y/Y] "+personName+" --------------- :: ");
				continueOrNot = reader.readLine().charAt(0);


			}while(continueOrNot == 'y' || continueOrNot =='Y');
	}

	/**
	 * Main method that will be called by JVM....
	 * @param arr
	 * @throws Exception
	 */
	public static void main(String... arr) throws Exception{

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your Name :: ");
		String personName = reader.readLine();
		System.out.println(""+personName+" Enter your Age :: ");
		int age = Integer.parseInt(reader.readLine());
		char maleOrFemale;

		while (true){
			System.out.println(""+personName+" Enter your Gender [M/F] :: ");
			maleOrFemale = reader.readLine().charAt(0);
			if (maleOrFemale == 'M' || maleOrFemale == 'm' || maleOrFemale == 'F' || maleOrFemale == 'f'){
				break;
			}
			else
				System.out.println(""+personName+" Please specify your gender as M or F ...");
		}

		final char morf = maleOrFemale;

		/**
		 * A thread is used to run the application to ease the 
		 * difficulties and tasks on the main Thread ...
		 */
		Thread thread = new Thread(){

			@Override
			public void run(){
				Person person = new Person(personName, age, morf);
				try {
					person.displayMenu();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
			}
		};
		thread.start();	// calling start() mthod starts the execution of our thread ...
		
	}

}











