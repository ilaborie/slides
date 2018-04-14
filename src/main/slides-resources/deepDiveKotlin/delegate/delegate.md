* lateinit : évite les null check pour les propriétés qui ne peuvent etre initialisées immédiatement (ex référence de vues sur `Activity`, `Fragment`).
  * Ne peut pas être utilisé avec les types primitifs
* Lazy : utile pour les propriétés qui ne sont pas systèmatiquement utilisées.<br>
  ⚠️ À manipuler avec précaution dans les activités Android ( avec le cycle de vie, cela peut référencer une ancienne instance)
* Delegate : Observable, Not null, ...
