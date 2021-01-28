Guillaume Troesch 2174593
Projet OpenGL

Petit ou Grand

L'application d�velopp�e a �t� l�g�rement modifi�e du sujet initial car nous pouvions 
laisser les s�lections � notre imaginations. Le jeu pr�sent� permet de jouer � 5 joueurs,
et les choix possibles sont r�alis�es � l'aide de boutons avec Java Android natif, plus
adapt�s et plus agr�ables � utiliser en terme d'utilisation pour le joueur. La m�thode 
onTouchEvent de la GLSurfaceView n'est donc ici pas utilis�e (bien qu'on aurait pu utiliser
les coordonn�es du MotionEvent dans l'�cran et comparer si celles-ci �taient contenues dans
l'une des "hitbox" de l'un des boutons).

Le jeu pr�sente 7 formes class�es du plus petit au plus grand : Triangle, Carr�, Trap�ze, 
Pentagone, Hexagone, Biche et Ours. Toutes ces formes sont faites � partir de un ou 
plusieurs triangles, et h�ritent de la classe Shape qui permet l'initialisation et 
l'affichage des formes et des shaders. 

D�roulement du jeu :
61 cartes sont r�parties entre 5 joueurs. Chaque joueur a une pile de 12 cartes. 
La derni�re carte sert de carte de comparaison. Chaque joueur voit le nombre de cartes 
restantes dans sa pile en bas de l'�cran, le joueur courant voit cette valeur en bleue. 
2 cartes sont affich�es sur l'�cran, la carte de comparaison � droite et une carte vide � 
gauche. Le joueur peut parier -, = ou +. Apr�s avoir pari�, sa carte s'affiche � gauche, 
lui indiquant s'il a gagn� son pari. Les boutons changent alors pour lui proposer de passer
ou de continuer s'il a eu bon. S'il continue, la carte dont il vient de gagner le pari 
passe � droite, et sert de carte de comparaison. S'il passe apr�s avoir gagn� un pari, sa 
pile de carte est d�pil�e des cartes dont il a r�ussi le pari, et sa derni�re carte pari�e 
passe � droite comme carte de comparaison. S'il perd un pari, les cartes dont il a r�ussi 
le pari ne sont pas d�pil�es, et restent dans le m�me ordre dans sa pile, la carte de 
comparaison revient alors � celle au d�but de son tour de jeu.

Am�lioration possible :
Le jeu devrait pouvoir permettre de jouer de 2 � 5 joueurs, une activity suppl�mentaire peut
�tre impl�ment�e pour demander le nombre de joueurs avant le jeu, et les 61 cartes seraient
r�partis entre ces joueurs.