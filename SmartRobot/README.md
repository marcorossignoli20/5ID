#SmartRobot

Le classi realizzate sono:

```
SmartClient.java
SmartProtocol.java
SmartServer.java
```

Alfred, il vostro assistente personale, per il momento è un ottimo suggeritore di film
Una volta inserito il genere desiderato, Alfred provvederà a fornirvi un film casuale del genere richiesto attingendo ad un database (IMDb) di ben 40000 film. Il database è rappresentato da un file csv quale:

```
movies.csv
```

Il file è contenuto nella cartella csv.
Per l'interfacciamento con il file csv è stato utilizzato il driver 

```
csvjdbc-1.0-31 
```

che permette di attingere ad un file csv in una modalità analoga ad un db SQL attraverso delle query.