[![Build Status](https://drone.io/github.com/roar109/jboss-pass-encrypt/status.png)](https://drone.io/github.com/roar109/jboss-pass-encrypt/latest)
jboss-pass-encrypt
==================

## Encrypt


	mvn exec:java -Dexec.mainClass="org.rage.jboss.utils.EncryptMain" -Dexec.args="arg0Pass"

Output:

	[INFO] ------------------------------------------------------------------------
	[INFO] Building credentials-utils 1.0.0
	[INFO] ------------------------------------------------------------------------
	[INFO] 
	[INFO] --- exec-maven-plugin:1.4.0:java (default-cli) @ credentials-utils ---
	Encoded password: -307099e0bf23ba67207a6df87216de44

Java

	java -cp target/credentials-utils-1.0.0.jar org.rage.jboss.utils.EncryptMain arg0Pass
	
Output:

	Encoded password: -307099e0bf23ba67207a6df87216de44

## Decrypt

	mvn exec:java -Dexec.mainClass="org.rage.jboss.utils.DecryptModule" -Dexec.args="-307099e0bf23ba67207a6df87216de44"

Output:

	[INFO] ------------------------------------------------------------------------
	[INFO] Building credentials-utils 1.0.0
	[INFO] ------------------------------------------------------------------------
	[INFO] 
	[INFO] --- exec-maven-plugin:1.4.0:java (default-cli) @ credentials-utils ---
	arg0Pass
	
Java

	java -cp target/credentials-utils-1.0.0.jar org.rage.jboss.utils.DecryptModule -307099e0bf23ba67207a6df87216de44
	
Output:

	arg0Pass