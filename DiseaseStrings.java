import java.util.HashMap; // Hashmap is used to store Strings in a structured format ...

public class DiseaseStrings{

	static HashMap<String, String> allergies = new HashMap<>();
	static HashMap<String, String> coldAndFlu = new HashMap<>();

	/**
	 * method-name : getAllergiesHashmap()
	 * 
	 * This method is used to load all the strings in its respective types
	 * from DiseaseStrings.java to selfDiseaseDiagnosis.java
	 * 
	 * @return HashMap<String, String>
	 */
	public static HashMap<String, String> getAllergiesHashmap(){

		allergies.put("diseaseName","Allergies");
		allergies.put("diseaseInfo","Allergies are an immune response triggered by allergens, an ordinarily harmful substance.");
		allergies.put("whyDiseaseCauses","People with allergies have especially sensitive immune systems that react when they contact allergens.");
		allergies.put("whatCausesDisease","foods (nuts, eggs, milk, soy, shellfish, wheat)\\pollen(dust particles)\\mold\\latex\\pet dander");
		allergies.put("diseaseSymptoms","Eye irritation\\Runny nose\\Stuffy nose\\Puffy, watery eyes\\Sneezing\\Inflamed\\itchy nose and throat\\"+
			"Hives or skin rashes\\Gastrointestinal distress \n\t\t(diarrhea, nausea, vomiting, excessing gas, indigestion)\\"+
			"Tingling or swelling of the lips, face, or tongue\\Itchiness\\Difficulty breathing or wheezing\\Fainting or lightheadedness");
		allergies.put("diseaseTreatment","Antihistamines(Nasal spray etc...)\\Decongestants\\Anti-inflammatory agents (e.g., corticosteroid)\\Allergy shots");
		allergies.put("diseasePrevention","Avoid the outdoors between 5-10 a.m. and save outside activities for late afternoon\n\t\t or after a heavy rain, when pollen levels are lower.\\"+
			"Keep windows in your living spaces closed to lower exposure to pollen.\\To keep cool, use air conditioners and avoid using window and attic fans.\\"+
			"Wear a medical alert bracelet or other means to communicate to others about your allergy\n\t\t in case of a reaction.\\"+
			"Discuss a prescription for epinephrine (e.g., EpiPen) with your healthcare provider, \n\t\tif you have risk of serious allergic reaction.\\"+
			"Review product labels carefully before buying or consuming any item\\Know what you are eating or drinking.");
		allergies.put("diseaseDuration","4-14 days may take 6 weeks");

		return allergies;

	}

	/**
	 * method-name : getColdAndFluHashmap()
	 * 
	 * This method is used to load all the strings in its respective types
	 * from DiseaseStrings.java to selfDiseaseDiagnosis.java
	 * 
	 * @return HashMap<String, String>
	 */
	public static HashMap<String, String> getColdAndFluHashmap(){

		coldAndFlu.put("diseaseName","Cold and Flu");
		coldAndFlu.put("diseaseInfo","Colds and influenza (flu) are the most common illnesses among college students.");
		coldAndFlu.put("whyDiseaseCauses","Both of these illnesses are upper respiratory infections, meaning they involve your nose, "+
			"throat, and lungs. Viruses cause both colds and flu by increasing inflammation of the membranes in the nose and throat.Most "+
			"transmission of these viruses occurs via hand-to-hand contact.");
		coldAndFlu.put("whatCausesDisease","The common cold, including chest cold and head cold,\n\t\t and seasonal flu are caused by viruses, Season changes");
		coldAndFlu.put("diseaseSymptoms","fever (100° F)\\headache\\intense pain and fatigue\\often dry cough\\a runny or stuffy nose "+
			"(nasal congestion)\\sneezing\\sore throat\\cough");
		coldAndFlu.put("diseaseTreatment","Consult doctor if fever > 102 degrees\\Rest more than usual and avoid exercise until symptoms are gone\\"+
			"Drink lots of clear fluids (e.g., water, tea).\\Avoid drinking alcohol because it weakens your immune system\\"+
			"Eat a well-balanced diet, including fruits, vegetables, and grains.");
		coldAndFlu.put("diseasePrevention","Wash your hands often (which is good advice for keeping healthy in any situation).\n\t\t "+
			"Keep them away from your nose, eyes, and mouth.\\Use an instant hand sanitizer when you can’t wash your hands.\\"+
			"Get regular exercise and eat well.\\Follow good sleep habits.\\Get a flu shot each fall");
		coldAndFlu.put("diseaseDuration","2-14 days");

		return coldAndFlu;
	}

}