Guillaume Troesch 2174593
Projet OpenGL

Petit ou Grand

L'application développée a été légèrement modifiée du sujet initial car nous pouvions 
laisser les sélections à notre imaginations. Le jeu présenté permet de jouer à 5 joueurs,
et les choix possibles sont réalisées à l'aide de boutons avec Java Android natif, plus
adaptés et plus agréables à utiliser en terme d'utilisation pour le joueur. La méthode 
onTouchEvent de la GLSurfaceView n'est donc ici pas utilisée (bien qu'on aurait pu utiliser
les coordonnées du MotionEvent dans l'écran et comparer si celles-ci étaient contenues dans
l'une des "hitbox" de l'un des boutons).

Le jeu présente 7 formes classées du plus petit au plus grand : Triangle, Carré, Trapèze, 
Pentagone, Hexagone, Biche et Ours. Toutes ces formes sont faites à partir de un ou 
plusieurs triangles, et héritent de la classe Shape qui permet l'initialisation et 
l'affichage des formes et des shaders. 

Déroulement du jeu :
61 cartes sont réparties entre 5 joueurs. Chaque joueur a une pile de 12 cartes. 
La dernière carte sert de carte de comparaison. Chaque joueur voit le nombre de cartes 
restantes dans sa pile en bas de l'écran, le joueur courant voit cette valeur en bleue. 
2 cartes sont affichées sur l'écran, la carte de comparaison à droite et une carte vide à 
gauche. Le joueur peut parier -, = ou +. Après avoir parié, sa carte s'affiche à gauche, 
lui indiquant s'il a gagné son pari. Les boutons changent alors pour lui proposer de passer
ou de continuer s'il a eu bon. S'il continue, la carte dont il vient de gagner le pari 
passe à droite, et sert de carte de comparaison. S'il passe après avoir gagné un pari, sa 
pile de carte est dépilée des cartes dont il a réussi le pari, et sa dernière carte pariée 
passe à droite comme carte de comparaison. S'il perd un pari, les cartes dont il a réussi 
le pari ne sont pas dépilées, et restent dans le même ordre dans sa pile, la carte de 
comparaison revient alors à celle au début de son tour de jeu.

Amélioration possible :
Le jeu devrait pouvoir permettre de jouer de 2 à 5 joueurs, une activity supplémentaire peut
être implémentée pour demander le nombre de joueurs avant le jeu, et les 61 cartes seraient
répartis entre ces joueurs.