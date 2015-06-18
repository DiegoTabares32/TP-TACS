package tacs.tp.app.helpers;

public class KeyManager {

	public String getDespegarApiKey() {
		String key = null;

		key = System.getenv("DESPEGAR_API_KEY");

		return key;
	}

/*
Usage in linux:
export DESPEGAR_API_KEY=the key

to check: printenv DESPEGAR_API_KEY
*/

}
