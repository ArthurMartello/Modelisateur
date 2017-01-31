Modélisateur 3D
===============
---

Réalisé dans le cadre du troisième semestre de DUT Informatique.
Arthur Martello - Benjamin Lelong - Simon Vandeputte

2016-2017

---

Guide d'utilisation :
--------------------

**Lancer le programme :**

Dans le dossier `./run` :

 - **Avec arguments :**

	> $ java run.jar [*modele.ply*] [*options*]

	Lance le modélisateur avec le modèle renseigné en paramètre chargé par défaut. Vous pourrez toujours changer le modèle affiché via le menu à gauche.

	**Options :**
	 - Par défaut, le modèle s'affiche avec les faces, l'éclairage, l'ombre et une grille en fond.
	 - Pour n'afficher que les faces au démarrage, on utilise l'option `-f`
	 - Pour n'afficher que les segments au démarrage, on utilise l'option `-s`
	 - Ces options sont cumulables.

 - **Sans arguments :**

	> $ java run.jar

	Lance le modélisateur sans modèle prédéfinit. Vous pourrez en choisir un dans votre base de données ou en ajouter un via le menu à gauche.

___
**Utilisation de la base de données :**

Les fichiers relatifs aux modèle et à la base de données se trouvent dans `./data`.

Vous pouvez y ajouter des fichiers de modèle au format `ply` pour pouvoir par la suite les inclure à la base de données.

Les boutons *Ajouter*, *Editer* ou *Supprimer* sur le projet interagissent avec la base de données. Ils n'influent en rien les autres fichiers dans le dossier `./data`. Vous pouvez également afficher les informations renseignées dans la base d'un modèle en appuyant sur le bouton *Informations*.

Vous pouvez rechercher dans la base de données via le champ de texte au dessus de celle-ci. Utilisez le bouton *Annuler* pour ré-afficher tous les modèles.

Pour que le modélisateur importe et affiche le modèle sélectionné, ce dernier doit respecter un certains nombre de critères relatifs au format `ply` :



- La première ligne doit être `ply`.
- La seconde ligne doit désigner son format.
-	L'en-tête doit être délimité par	sa dernière ligne: `end_header`.
-	L'en-tête doit contenir un nombre	de point sous la forme	`element vertex p`. Avec p un	entier.
-	L'en-tête doit contenir un nombre	de faces sous la forme `element face f`. Avec `f` un entier.
-	Les `element` sont definis par des propriétés appelées `property`.
- Les `property` sont soit `float32` soit `list`.
-	Des lignes de commentaires peuvent être ajoutées sous a forme `comment c` avec c une chaine de charactères.
-	Après le `end_header`, on retrouve les p points et les f faces.
-	Un point est sous la forme `x y z` ou `x y` selon le nombre de propriétés.
-	Une face est sous la forme `n a b c` avec `n` correspondant au nombre de points la composant. Ici	n = 3.

___
**Affichage :**

Il est possible de translater, faire tourner, de zoomer ou de dézoomer le modèle affiché.

- **Avec la souris :**

	- **Translation**
	Faire glisser la souris en maintenant le clic droit enfoncé.
	- **Rotation**
	Faire glisser la souris en maintenant le clic gauche enfoncé.
	- **Zoom**
	Utilisez la molette de la souris vers l'avant pour zoomer et vers l'arrière pour dézoomer.
	Vous pouvez aussi faire un double clic gauche pour zoomer et un double clic droit pour dézoomer.

- **Avec le clavier :**

	- **Translation**
	Utilisez les flèches directionnelles ou les touches `Z`, `Q`, `S` et `D`.
	- **Rotation**
	Utilisez les touches `8`, `4`, `2` et `6` du pavé numérique.
	- **Zoom**
	Utilisez les touches `+` du pavé numérique ou `P` pour zoomer et `-` du pavé numérique et `M` pour dézoomer.

- **Avec les boutons :**

	Des boutons reproduisant toutes les actions sont à votre dispositions en dessous du panneau d'affichage.

___
**Options d'affichage :**

La barre de menu en haut à gauche vous permet de modifier en direct l'affichage du modèle. Voici les différentes options:

- **Points**
Affiche tous les points du modèle, en rouge.
- **Segments**
Affiche tous les segments du modèle, en noir.
- **Faces**
Affiches toutes les faces du modèle, en nuance de gris si la lumière est appliquée, en gris sinon.
Le modèle sera transparent si cette option est désactivée.
- **Lumière**
Les faces du modèle seront illuminées. Certaines pourront donc se retrouver à l'ombre.
- **Ombre**
Si la lumière est elle aussi activée, une ombre suivra le modèle sur le plan derrière lui.
- **Grille**
Affiche une grille derrière le modèle.
