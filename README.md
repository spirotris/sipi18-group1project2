#Sipi18-group1project2

Innehåll
* Introduktion
* Dokumentation
* Utgåvoanteckningar (Release Notes)
* Buggrapporter och feedback
* Rapport

Introduktion

 Författare och utvecklare är Emil Albrektsson, Marcus Laitala, Tommy Ohrling.

Dokumentation

 Programmet är ett spel där målet är att undvika monster, samla skatter och ta sig till dörren för att komma till nästa nivå tills det att man har kommit till sista nivån och klara spelet.
 
Utgåvoanteckningar (Release Notes) 

 Vid skrivande tidpunkt saknas det flera väggar och felmeddelande kan komma att uppstå efter flera omstarter om spelaren förlorar. Spelaren styrs med piltangenterna.
 
Buggrapporter och feedback

  Vid förslag och återkoppling kan det skickas via GitHub på följande länk:
  https://github.com/spirotris/sipi18-group1project2/issues
  
Rapport

 Det var en utmaning att komma in i TDD som grupp till en början. Men efter lite tragglande så fick vi till flytet i arbetet ganska bra. Allt flöt på bra och vi kom så långt att vi började fundera på lite större refaktoreringar i koden. Dessa gjordes lite olika varianter på och med utgång från omgjorda tester. Till sist valde vi den varianten som vi nu skickat in. Men när vi väl kom till den funktionella testningen så har vi mycket kvar att önska. 
 
Efter att senaste veckan jobbat för att hitta dessa buggar som vi inte lyckas reproducera genom tester så fick vi nu på söndag kväll ge upp. Vi har fyra nivåer i lite olika utförande där vi tyvärr har någon bugg som gör att inte alla väggar ritas ut i spelet. Vi valde även en lite annan approach istället för lasrar där vi har olika antal monster som slumpas runt på banan. Treasures finns i lite olika mängder per bana. Igenom dörren kan du gå först när du tagit alla treasures för aktuell nivå. För att komma vidare måste spelaren trycka på "Yes" vid "Game Over" - Do you want to restart?-rutan och då kommer spelaren till nästa nivå.
