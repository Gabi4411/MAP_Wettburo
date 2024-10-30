# MAP_Wettburo

Wir haben ein Wettburo App gemacht, wo Players online betten konnen. Wir haben verschidene Events auf welchen Players Bets 
abschliessen konnen. Players kriegen auch verschidene Preise (Bonuses) laut: wie viel Geld sie eingezahlt haben, seit wann sie 
auf unseren App wetten und andere. Alles kann man kontrollieren von unseren Admin oder Players konnen alles was sie gemacht 
haben sehen, durch die Klasse: Transactions. Transactions zeigt der Player alle Abhebungen und Einzahlungen. Die Klasse 
Authentification ist selbstverstandlich, alle Players mussen erstmal sich authentifizieren wenn sie wetten wollen. Support ist 
eine Klasse die implementiert eine Methode wo Users die Probleme haben kontakt mit dem Admin nehmen um eine Losung zu finden. 
Damit die Players mehr uber unsere Events herausfinden konnen, steht fur ihnen da die Klasse Statistics. Zum Schluss alle 
Events brauchen Odds (Odds konnen nicht existieren ohne ein Event) damit die Player wetten konnen und dafur haben wir die 
Klasse Odds, das eine abstrakte Klasse ist fur die Unterklassen: Tennis-, Basket- und FootballOdds.

