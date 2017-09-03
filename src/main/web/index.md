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
0. Utiliser un pré-processeur ?/n1. Unités/n2. Flexbox et Grid/n3. Pseudo éléments/n4. Animations/n5. Pseudo classes d'état/n6. HTML/n7. Compatibilité des navigateurs/n8. Conclusion

Utiliser un pré-processeur ?
----------------------------

### Bordure des boutons ###

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

RawContent(content=px, cm, pt, ...)
: longueurs absolues (mesure physique)
RawContent(content=em, rem)
: fonction de la <code>font-size</code>
RawContent(content=ex, ch)
: hauteur d'un <code>x</code>, largeur d'un <code>0</code>
RawContent(content=vh, vw)
: (100vh, 100vw) = (hauteur, largeur) du <i>viewport</i>
RawContent(content=vmin, vmax)
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

### Avec flexbox & Grid ###

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
[Exemple Grid](./holy-grail-grid.html)

### Bilan Flexbox & Grid ###

#### Flexbbox ####

* Si on est sur une seule ligne ou colonne/n* [Flexbox, et le CSS redevient fun ! (Hubert SABLONNIÈRE)](https://www.youtube.com/watch?v=5F_ngjHDcJQ)/n* [Solved by Flexbox](https://philipwalton.github.io/solved-by-flexbox/)/n* [Flexbox Froggy](https://flexboxfroggy.com/)
#### Grid ####

* Si plusieurs lignes et colonnes/n* [Grid by exemples](https://gridbyexample.com/)/n* [Grid Garden](http://cssgridgarden.com/)

Pseudo éléments
---------------

### Le dinner d'un philosophe ###

```CSS
ExternalResource(resource=/cssIsAwesome/04_pseudo_elements/philosophe.css)
```
<div class="diner">
	<div class="table-wrapper">
		<div class="table-surface"></div>
		<div class="table">
			<div class="plate"></div>
		</div>
	</div>
	<div class="table-edge">
		<div class="table-leg"></div>
		<div class="table-leg"></div>
	</div>
</div>

### LiveCoding: Triangle avec des bordures ###

```CSS
ExternalResource(resource=/cssIsAwesome/04_pseudo_elements/border.css)
```
<div class="top"></div>
<div class="right"></div>
<div class="bottom"></div>
<div class="left"></div>

### Info-bulle ###

```CSS
ExternalResource(resource=/cssIsAwesome/04_pseudo_elements/popover.css)
```
<div class="popover">Hello</div>

### Bilan pseudo éléments ###

* [The :before and :after pseudo-elements](https://www.w3.org/TR/CSS22/generate.html#before-after-content)/n* mais aussi <code>::first-letter</code>, <code>::first-line</code>, <code>::selection</code>, <code>::backdrop</code>/n* [An Ultimate Guide To CSS Pseudo-Classes And Pseudo-Elements](https://www.smashingmagazine.com/2016/05/an-ultimate-guide-to-css-pseudo-classes-and-pseudo-elements)
⚠️ <code>::before</code> et <code>::after</code> ne marchent pas sur <code>input</code>, <code>img</code>, <code>iframe</code> (pas encore spécifié)
* Table et assiette de 
[CSS Diner](https://flukeout.github.io/)/n* [Dîner des philosophes](https://fr.wikipedia.org/wiki/D%C3%AEner_des_philosophes)

Animations
----------

### Texte de chargement ###

```CSS
ExternalResource(resource=/cssIsAwesome/05_animations/loader.css)
```
<div class="loader"></div>

### Dessiner ###

```CSS
ExternalResource(resource=/cssIsAwesome/05_animations/draw.css)
```
<svg viewBox="0 0 400 200">
	<circle cx="100" cy="100" r="80"></circle>
	<rect x="210" y="10" height="80" width="80"></rect>
</svg>

### Bilan animations ###

* [Utiliser les animations CSS](https://developer.mozilla.org/fr/docs/Web/CSS/Animations_CSS/Utiliser_les_animations_CSS)/n* [Text Spinner](http://tawian.io/text-spinners/)/n* [CSS only loader](https://www.pexels.com/blog/css-only-loaders/)/n* [Animate.css](https://daneden.github.io/animate.css/)/n* [How SVG Line Animation Works](https://css-tricks.com/svg-line-animation-works/)/n* [<code>$lt;progress$gt;</code>](https://developer.mozilla.org/fr/docs/Web/HTML/Element/Progress)

Pseudo classes d'état
---------------------

### Usage des info-bulles ###



### Pseudo états ###

* `:hover`/n* `:focus`/n* `:visited`/n* `:checked`/n* `:valid`/n* `:invalid`/n* `:empty`/n* `:target`/n* ...

### Checkbox ###

```CSS
ExternalResource(resource=/cssIsAwesome/06_pseudo_classes_d_etat/checkbox.css)
```
<blockquote>
	<p>The science of operations, as derived from mathematics more especially, is a science of itself, and has its own
		abstract truth and value.</p>
	<footer><cite>Ada Lovelace</cite></footer>
</blockquote>
<fieldset>
	<input type="checkbox" id="like">
	<label for="like"></label>
</fieldset>

### Switch ###

```CSS
ExternalResource(resource=/cssIsAwesome/06_pseudo_classes_d_etat/switch.css)
```
<label for="switch1">Switch</label>
<input id="switch1" type="checkbox" class="switch" checked>
<label for="switch1"></label>

### Panel ###

```CSS
ExternalResource(resource=/cssIsAwesome/06_pseudo_classes_d_etat/panel.css)
```
<div class="panel">
	<input type="checkbox" id="panel" checked>
	<header><label for="panel">Apollo 11</label></header>
	<div class="body">
		<blockquote>
			<p>The computer (or rather the software in it) was smart enough to recognize that it was being asked to perform
				more tasks than it should be performing. It then sent out an alarm, which meant to the astronaut, I'm
				overloaded
				with more tasks than I should be doing at this time and I'm going to keep only the more important tasks; i.e.,
				the ones needed for landing ... Actually, the computer was programmed to do more than recognize error
				conditions. A complete set of recovery programs was incorporated into the software. The software's action, in
				this case, was to eliminate lower priority tasks and re-establish the more important ones ... If the computer
				hadn't recognized this problem and taken recovery action, I doubt if Apollo 11 would have been the successful
				moon landing it was.[26]
			</p>
			<footer><cite>Letter from Margaret H. Hamilton, Director of Apollo Flight Computer Programming MIT Draper
				Laboratory, Cambridge, Massachusetts[27], titled "Computer Got Loaded", published in Datamation, March 1,
				1971</cite></footer>
		</blockquote>
	</div>
</div>

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
</div>
```

### Démo des onglets ###

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
</div>

### Bilan Pseudo classes ###



HTML
----

### Panel ###

```html
No resource: /cssIsAwesome/07_HTML/details.html
```
```CSS
ExternalResource(resource=/cssIsAwesome/07_HTML/panel.css)
```
No resource: /cssIsAwesome/07_HTML/panel.html

### Dialog ###

```CSS
ExternalResource(resource=/cssIsAwesome/07_HTML/dialog.css)
```
No resource: /cssIsAwesome/07_HTML/dialog.html

### Polyfill ###

* [Collapsible Panel Polyfill](https://github.com/chemerisuk/better-details-polyfill/)/n* [Dialog Polyfill](https://github.com/GoogleChrome/dialog-polyfill)

Compatibilité des navigateurs
-----------------------------

### Compat ###

#### [caniuse](http://caniuse.com) ####

#### [The CSS3 / CSS4 Test](http://css3test.com) ####

<dl>
	<dt>IE 7+, Firefox, Chrome</dt>
	<dd>Pseudo classes (CSS3 selectors <em>93</em>)</dd>

	<dt>IE 8+, Firefox, Chrome</dt>
	<dd><code>::before</code>, <code>::after</code> <em>98</em></dd>

	<dt>IE 9+, Firefox, Chrome</dt>
	<dd><code>currentColor</code> <em>98</em></dd>
	<dd><code>background-origin</code> <em>98</em></dd>
	<dd><code>box-shadow</code> <em>98</em></dd>
	<dd><code>calc</code> <em>97</em></dd>
	<dd><code>vh, vw, ...</code> <em>97</em></dd>

	<dt>IE 10+, Firefox, Chrome</dt>
	<dd><code>flexbox</code> <em>98</em></dd>
	<dd>Animations <em>98</em></dd>
</dl>


Conclusion
----------

### Bilan ###

0. Utilisez du CSS pour simpifier le code/n1. Utilisez intelligemment les pre/post processeurs/n2. HTML, SVG are Awesome !/n3. JavaScript, TypeScript could be Awesome !

### Traitez le CSS comme du code ###

0. Revue de code/n1. DRY/n2. Clean Code/n3. Single Responsibility Principle/n4. ...

### Liens ###

* [les slides]()/n* [le code]()/n* [Making Of]()

### Pour apprendre ###

* `(Ctrl|Cmd) + Shift + i`/n* ![CSS Secret](http://lea.verou.me/cover.png)
[CSS Secret by Lea Verou](https://www.amazon.fr/CSS-Secrets-Lea-Verou/dp/1449372635)/n* [CSS sur MDN](https://developer.mozilla.org/fr/docs/Web/CSS)/n* [CodePen](https://codepen.io/)
[JSFiddle](https://jsfiddle.net/)
[Dabblet](http://dabblet.com/)
.../n* [CSS Tricks]()/n* [Shop Talk Show]()/n* [CSS Flags]()

### 🦄 rocks ! ###

```CSS
ExternalResource(resource=/cssIsAwesome/09_conclusion/end.css)
```
No resource: /cssIsAwesome/09_conclusion/end.html