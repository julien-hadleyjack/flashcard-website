flashcard-website
=================

###**MySQL und Tomcat lokal 

Die Login und Signup Funktion funktioniert zurzeit noch lokal und nicht über Juliens Server.
Hierfür muss folgendes aufgesetzt werden, falls ihr das ganze an eurem Rechner ausprobieren wollt:

1. Tomcat lokal installieren
2. Projekt in das Verzeichnis *$CATALINA_HOME/webapps/ *legen.
3. MYSQL Datenbank lokal aufsetzen mit der Datenbank *web_engineering*
4. In der Datenbank eine Tabelle *Users* anlegen mit (*id, email, pwhash, salt)*
5. *login.jsp* über *localhost:8080/login.jsp* aufrufen
