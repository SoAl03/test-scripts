# Beispielskripte
Dieses Repository beinhaltet Beispielskripte und Testdaten zum Testen und Nachvollziehen der Datenerhebung an Fingerabdruckdaten.
Die Datenanalyse basiert auf der Bildqualität und dem Matching mehrerer Fingerabdrücke gegeneinander. Dafür kommen folgende Algorithmen zum Einsatz:
* NFIQ2 Version 2.2.0
* SourceAFIS für Java Version 3.17.1

In den Beispielskripten kommt sowohl die Programmiersprache Java, als auch Python zum Einsatz.

Die verwendeten Testdaten sind unter `./test_data` zu finden.

Weitere Informationen und Anpassungen für den Code lesen Sie bitte im Folgenden nach.

## NFIQ2
Für die Bildqualitätsanalyse können Sie das Python-Skript `./nfiq2_tester/nfiq2_computing.py` nutzen. 
Dafür installieren Sie bitte die `nfiq2.exe` von der offiziellen Seite des [NIST](https://github.com/usnistgov/NFIQ2/releases) und ändern, wenn nötig, die Pfadangabe in **Zeile 35**. 
Unter Umständen müssens Sie noch das Python-Paket `glob` nachinstallieren.

Das Skript lässt für jede Fingerabdruckaufnahme einen NFIQ2-Score berechnen und schreibt die Ergebnisse in die `./nfiq2_tester/results.txt`. Die `/nfiq2_tester/results.txt` besteht aus vielen Zeilen, die wie folgt aufgebaut sind: 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `path` &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `score`

Dies bedeutet, dass die Analyse des Bildes `path` ein Ergebnis von `score` ergab.

## SourceAFIS für Java
Zur Durchführung der folgenden Skripte müssen Sie zunächst die [**Dependency**](https://mvnrepository.com/artifact/com.machinezoo.sourceafis/sourceafis/3.17.1) des SourceAFIS-Algorithmus Version 3.17.1 in Ihr Projekt einbinden.
Für weitere Informationen können Sie sich gern auf der offiziellen [SourceAFIS für Java Website](https://sourceafis.machinezoo.com/) erkundigen.

### Minuzienextraktor
Der Minuzienextraktor `./Extractor/src/extractor/Extract_Features.java` extrahiert die Besonderheiten der Testdaten und speichert diese als `.template`-Datei in einer vorbestimmten Ordnerstruktur mit individuellen Namen.

### Matcher
Der Matcher `./Matcher/src/matcher/Match_Templates.java` liest die `.template`-Dateien ein, vergleicht diese paarweise und schreibt das Ergebnis in `./Matcher/output.txt`. 
Dabei bestimmt die Variable `contactless` in **Zeile 19**, welche Fingerabdrücke gegeneinader verglichen werden: 
* `true`: vergleiche alle kontaktlosen Bilder gegeneinander oder
* `false`: vergleiche alle kontaktbasierten Bilder gegeneinander.

Die `./Matcher/output.txt` besteht aus vielen Zeilen, die wie folgt aufgebaut sind: 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `path1`; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `path2`; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `score`

Dies bedeutet, dass der Vergleich `path1`(als Analysegrundlage) gegen `path2`(als Vergleichsbild) ein Ergebnis von `score` ergab.
