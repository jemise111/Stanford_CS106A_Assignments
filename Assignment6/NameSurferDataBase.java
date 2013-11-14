/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.*;
import java.util.*;
import acm.util.*;

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
			try {
				BufferedReader rd = new BufferedReader(new FileReader(filename));
				while(true) {
					String line = rd.readLine();
					if (line == null) break;
					addToDataBase(line);
				}
				rd.close();
			} catch (IOException ex) {
				throw new ErrorException(ex);
			}
	}
	
	private void addToDataBase (String line) {
		NameSurferEntry entry = new NameSurferEntry(line);
		dataBase.put(entry.getName(), entry); //creates HashMap entry to store name string and decade count string
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		if (dataBase.containsKey(name)) {
			return dataBase.get(name);
		} else {
			return null;
		}
	}
	
	private HashMap<String, NameSurferEntry> dataBase = new HashMap<String, NameSurferEntry>();
}

