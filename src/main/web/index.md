CSS is Awesome !
================

### `$ whoami` ###

**Igor Laborie**
Expert Java & Web, [Monkey Patch](http://monkeypatch.io)                  
[@ilaborie](https://twitter.com/ilaborie)
[igor@monkeypatch.io](mailto:igor@monkeypatch.io)

 ⚠️ _Je ne suis pas un designer_

### [The Rule of Least Power](https://www.w3.org/2001/tag/doc/leastPower.html) ###

> When designing computer systems, one is often faced with a choice between using a more or less powerful language for publishing information, for expressing constraints, or for solving some problem. This finding explores tradeoffs relating the choice of language to reusability of information. The "Rule of Least Power" suggests **choosing the least powerful language suitable** for a given purpose.


### Règles du jeu ###


1. Texte
1. HTML & CSS
1. SVG
1. JavaScript

⚠️ _... mais il y a toujours de bonnes raisons pour ne pas suivre ces règles_

### Le CSS c'est vaste ###

* Selectors
* Box model
* Float
* Media Query
* Transitions
* Gradients
* Responsive Design
* Media
* Variables
* Colors
* Shapes
* ...

### Plan ###

* Utiliser un pré-processeur ?
* Unités, `calc()`
* Pseudo éléments
* Animations
* Pseudo classes d'états
* Support dans les navigateurs



Utiliser un pré-processeur ?
----------------------------

### LiveCoding: boutons ###


<style scoped contenteditable="true">button {
    background: lightblue;
    border: medium solid purple;
  }
  button.danger {/*
     background: salmon;
     color: rebeccapurple;
  }*/
  </style>
  
  <div class="editable">
    <button type="button">Plop</button>
    <button type="button" class="danger">Plop !️</button>
  </div>
  

### Alors utilise-t-on un pré-processeurs ? ###

Oui, mais privilégiez:

* le CSS
* les post-processeurs


* [`currentColor`](https://css-tricks.com/currentcolor/)
* [`background-origin`](https://developer.mozilla.org/fr/docs/Web/CSS/background-origin)
* [CSS Variables (Custom Properties)](https://www.w3.org/TR/css-variables/)
* [CSS Color Module Level 4](https://www.w3.org/TR/css-color-4/)


Unités
------

### Une histoire d’unités CSS ###



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

### LiveCoding: Holy Grail Layout avec calc ###



### Bilan unités ###



Flexbox et Grid
---------------

### LiveCoding: Holy Grail Layout avec flexbox ###



### LiveCoding: Holy Grail Layout avec grid ###


* @supports
* grid

### Bilan Flexbox & Grid ###



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

```html
<div class="tabs">
  <input type="radio" name="tab" id="home">
  <input type="radio" name="tab" id="projects">
  <input type="radio" name="tab" id="about">
  <nav>
    <label for="home">Home</label>
    <label for="projects">Projects</label>
    <label for="about">About</label>
  </nav>
  <div data-for="home">Home page</div>
  <div data-for="projects">Projects page</div>
  <div data-for="about">About page</div>
</div>```

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