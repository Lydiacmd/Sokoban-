
- design pattern --> itérateur  : pour parcourir une collection (liste, tableau, arbre, etc.) sans exposer sa représentation interne (chaînage, tableau, etc.)

-  L'idée clé de ce pattern est de placer la responsabilité des accès et du parcours dans un objet itérateur.
  
-  Collection / Séquence : c'est l'objet qui stocke des elments.
        - elle as des ops par indice (lis(i),supprime(i))
        - Probleme : couteux et redondant 
  
-  iterateur : Un objet "curseur" qui maintient une pos-courante dans la Séq : 
        - Il se deplace, accéde a l'elem courant, peux modifier (ajout/supprime)
        - PK C'EST BIEN :
             - Le code client fait juste : “avance, lis, supprime si besoin”
             - il s'en fou si c'est un tableau un chat ou autre 
             - PRONS : Liste chaine ----> evite les ops (lis(i)/supprime(i)) qui parcours depuis le debut. 

- Exemple visuel :
  [ A, B, C, D ]
      ^
   curseur (entre B et C)

“ajouter à la position courante” → insère entre B et C
“supprimer à gauche” → supprime B



- INTERFACE !!!   =/= CLASS !!! : 
- Pas de Var
- Pas de Consructeur 
- Pas de code 
- ---> QUE :
- Noms de methodes 
- leurs types
  
INTERFACE / Animal:
    manger()
    dormir()

CLASS / 
Chien implements Animal
Chat implements Animal
