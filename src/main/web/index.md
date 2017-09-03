### `$ whoami` ###

**Igor Laborie**
Expert Java & Web, [Monkey Patch](http://monkeypatch.io)                  
[@ilaborie](https://twitter.com/ilaborie)
[igor@monkeypatch.io](mailto:igor@monkeypatch.io)

 ⚠️ _Je ne suis pas un designer_

### [The Rule of Least Power](https://www.w3.org/2001/tag/doc/leastPower.html) ###

> When designing computer systems, one is often faced with a choice between using a more or less powerful language for publishing information, for expressing constraints, or for solving some problem. This finding explores tradeoffs relating the choice of language to reusability of information. The "Rule of Least Power" suggests <strong>choosing the least powerful language suitable</strong> for a given purpose.

### Règles du jeu ###

0. Texte/n1. HTML (sémantique) & CSS (layout, style, animations simples)/n2. SVG (formes et animations complexes)/n3. JavaScripts
*⚠️... mais il y a toujours de bonnes raisons pour ne pas suivre ces règles*

### Le CSS c'est vaste ###

* Selectors/n* Box model/n* Float/n* Media Query/n* Transitions/n* Gradients/n* Responsive Design/n* Media/n* Variables/n* Colors/n* Shapes/n* ...

Plan
----
0. Utiliser un pré-processeur ?/n1. Unités/n2. Flexbox et Grid/n3. Pseudo éléments/n4. Animations/n5. Pseudo classes d'état/n6. Compatibilité des navigateurs/n7. Conclusion

Utiliser un pré-processeur ?
----------------------------

### LiveCoding: boutons ###

```CSS
ExternalResource(resource=/cssIsAwesome/01_preprocessor/boutons.css)
```
<button type="button">Plop</button>
<button type="button" class="danger">Plop !️</button>

### Alors utilise-t-on un pré-processeurs ? ###

Oui, mais privilégiez:
* le CSS/n* les post-processeurs
* [`currentColor`](https://css-tricks.com/currentcolor/)/n* [`background-origin`](https://developer.mozilla.org/fr/docs/Web/CSS/background-origin)/n* [CSS Variables (aka Custom Properties)](https://www.w3.org/TR/css-variables/)/n* [CSS Color Module Level 4](https://www.w3.org/TR/css-color-4/)

Unités
------

### Une histoire d’unités CSS ###

![RawContent(content=Une histoire d’unités CSS)](https://www.commitstrip.com/wp-content/uploads/2016/10/Strip-High-Level-CSS-650-final-2.jpg "RawContent(content=Une histoire d’unités CSS)"})

### Les unités de longueur ###

px, cm, pt, ...
: longueurs absolues (mesure physique)
em, rem
: fonction de la <code>font-size</code>
ex, ch
: hauteur d'un <code>x</code>, largeur d'un <code>0</code>
vh, vw
: (100vh, 100vw) = (hauteur, largeur) du <i>viewport</i>
vmin, vmax
: min(1vh, 1vw), max(1vh, 1vw)

### Holy Grail Layout avec calc ###

```html
<body>
 <header>Header</header>
 <div>
   <nav>Menu</nav>
   <main>Content</main>
   <aside>Side</aside>    
 </div>
 <footer>Footer</footer>
</body>
```
[Live coding](./holy-grail.html)

### Bilan unités ###

* [Unités](https://developer.mozilla.org/fr/docs/Web/CSS/length) et [Truc et astuces](https://www.w3.org/Style/Examples/007/units.fr.html)/n* [`calc`](https://developer.mozilla.org/fr/docs/Web/CSS/calc)

Flexbox et Grid
---------------

### Holy Grail Layout avec flexbox & Grid ###

```html
<body>
 <header>Header</header>
 <div>
   <nav>Menu</nav>
   <main>Content</main>
   <aside>Side</aside>    
 </div>
 <footer>Footer</footer>
</body>
```
[Exemple Flexbox](./holy-grail-flexbox.html)
[Exemple Flexbox](./holy-grail-grid.html)

### Bilan Flexbox & Grid ###

### Grid ###

* Si plusieurs lignes et colonnes/n* [Grid by exemples](https://gridbyexample.com/)/n* [Grid Garden](http://cssgridgarden.com/)

Pseudo éléments
---------------

### LiveCoding: le dinner d'un philosophe ###



### LiveCoding: Triangle avec des bordures ###



### LiveCoding: Info-bulle ###



### Bilan pseudo éléments ###



Animations
----------

### LiveCoding: texte de chargement ###



### Bilan animations ###



Pseudo classes d'état
---------------------

### Usage des info-bulles ###



### LiveCoding: Checkbox ###



### LiveCoding: Switch ###



### LiveCoding: Collapsible panel ###



### Principe pour les onglets ###



### Démo des onglets ###



### Bilan Pseudo classes ###



Compatibilité des navigateurs
-----------------------------

### Partie 1 ###



### Partie 2 ###



Conclusion
----------

### Bilan ###



### Traitez le CSS comme du code ###



### Liens ###



### Pour apprendre ###



### 🦄 rocks ! ###


<div style="font-size: 146px;">
    💎
    ⚠️🐈🦄❤️😱💣😍
</div>