package astronomy;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 1,
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007"},
   d2 = {"Lastronomy/SolarSystem;", "", "()V", "bodies", "", "Lastronomy/AstronomicalBody;", "getBodies", "()Ljava/util/Collection;"}
)
public final class SolarSystem {
   @NotNull
   private static final Collection bodies;
   public static final SolarSystem INSTANCE;

   @NotNull
   public final Collection getBodies() {
      return bodies;
   }

   private SolarSystem() {
      super();
   }

   static {
      SolarSystem var0 = new SolarSystem();
      INSTANCE = var0;
      bodies = (Collection)CollectionsKt.listOf(new AstronomicalBody[]{(AstronomicalBody)(new DwarfPlanet("Ceres")), (AstronomicalBody)(new DwarfPlanet("Pluto")), (AstronomicalBody)(new Planet("Mercury", PlanetKind.Terrestrial, (Collection)CollectionsKt.emptyList(), false)), (AstronomicalBody)(new Planet("Venus", PlanetKind.Terrestrial, (Collection)CollectionsKt.emptyList(), false)), (AstronomicalBody)(new Planet("Earth", PlanetKind.Terrestrial, (Collection)CollectionsKt.listOf(new Moon("Moon")), false)), (AstronomicalBody)(new Planet("Mars", PlanetKind.Terrestrial, (Collection)CollectionsKt.listOf(new Moon[]{new Moon("Phobos"), new Moon("Deimos")}), false)), (AstronomicalBody)(new Planet("Jupiter", PlanetKind.GasGiant, (Collection)CollectionsKt.listOf(new Moon[]{new Moon("Metis"), new Moon("Adrastea"), new Moon("Amalthea"), new Moon("Thebe"), new Moon("Io"), new Moon("Europa"), new Moon("Ganymede"), new Moon("Callisto"), new Moon("Themisto"), new Moon("Leda"), new Moon("Himalia"), new Moon("Lysithea"), new Moon("Elara"), new Moon("Dia"), new Moon("Carpo"), new Moon("S/2003"), new Moon("Euporie"), new Moon("S/2003"), new Moon("S/2011"), new Moon("S/2003"), new Moon("S/2010"), new Moon("Thelxinoe"), new Moon("Euanthe"), new Moon("Helike"), new Moon("Orthosie"), new Moon("S/2016"), new Moon("Iocaste"), new Moon("S/2003"), new Moon("Praxidike"), new Moon("Harpalyke"), new Moon("Mneme"), new Moon("Hermippe"), new Moon("Thyone"), new Moon("Ananke"), new Moon("Herse"), new Moon("Aitne"), new Moon("Kale"), new Moon("Taygete"), new Moon("S/2003"), new Moon("Chaldene"), new Moon("S/2003"), new Moon("S/2003"), new Moon("S/2003"), new Moon("Erinome"), new Moon("Aoede"), new Moon("Kallichore"), new Moon("Kalyke"), new Moon("Carme"), new Moon("Callirrhoe"), new Moon("Eurydome"), new Moon("Pasithee"), new Moon("S/2010"), new Moon("Kore"), new Moon("Cyllene"), new Moon("S/2011"), new Moon("Eukelade"), new Moon("S/2017"), new Moon("S/2003"), new Moon("Pasiphae"), new Moon("Hegemone"), new Moon("Arche"), new Moon("Isonoe"), new Moon("S/2003"), new Moon("S/2003"), new Moon("Sinope"), new Moon("Sponde"), new Moon("Autonoe"), new Moon("Megaclite"), new Moon("S/2003")}), true)), (AstronomicalBody)(new Planet("Saturn", PlanetKind.GasGiant, (Collection)CollectionsKt.listOf(new Moon[]{new Moon("S/2009 S 1"), new Moon("Pan"), new Moon("Daphnis"), new Moon("Atlas"), new Moon("Prometheus"), new Moon("Pandora"), new Moon("Epimetheus"), new Moon("Janus"), new Moon("Aegaeon"), new Moon("Mimas"), new Moon("Methone"), new Moon("Anthe"), new Moon("Pallene"), new Moon("Enceladus"), new Moon("Tethys"), new Moon("Telesto"), new Moon("Calypso"), new Moon("Dione"), new Moon("Helene"), new Moon("Polydeuces"), new Moon("Rhea"), new Moon("Titan"), new Moon("Hyperion"), new Moon("Iapetus"), new Moon("Kiviuq"), new Moon("Ijiraq"), new Moon("Phoebe"), new Moon("Paaliaq"), new Moon("Skathi"), new Moon("Albiorix"), new Moon("S/2007 S 2"), new Moon("Bebhionn"), new Moon("Erriapus"), new Moon("Skoll"), new Moon("Siarnaq"), new Moon("Tarqeq"), new Moon("S/2004 S 13"), new Moon("Greip"), new Moon("Hyrrokkin"), new Moon("Jarnsaxa"), new Moon("Tarvos"), new Moon("Mundilfari"), new Moon("S/2006 S 1"), new Moon("S/2004 S 17"), new Moon("Bergelmir"), new Moon("Narvi"), new Moon("Suttungr"), new Moon("Hati"), new Moon("S/2004 S 12"), new Moon("Farbauti"), new Moon("Thrymr"), new Moon("Aegir"), new Moon("S/2007 S 3"), new Moon("Bestla"), new Moon("S/2004 S 7"), new Moon("S/2006 S 3"), new Moon("Fenrir"), new Moon("Surtur"), new Moon("Kari"), new Moon("Ymir"), new Moon("Loge"), new Moon("Fornjot")}), true)), (AstronomicalBody)(new Planet("Uranus", PlanetKind.IceGiant, (Collection)CollectionsKt.listOf(new Moon[]{new Moon("Cordelia"), new Moon("Ophelia"), new Moon("Bianca"), new Moon("Cressida"), new Moon("Desdemona"), new Moon("Juliet"), new Moon("Portia"), new Moon("Rosalind"), new Moon("Cupid"), new Moon("Belinda"), new Moon("Perdita"), new Moon("Puck"), new Moon("Mab"), new Moon("Miranda"), new Moon("Ariel"), new Moon("Umbriel"), new Moon("Titania"), new Moon("Oberon"), new Moon("Francisco"), new Moon("Caliban"), new Moon("Stephano"), new Moon("Trinculo"), new Moon("Sycorax"), new Moon("Margaret"), new Moon("Prospero"), new Moon("Setebos"), new Moon("Ferdinand")}), true)), (AstronomicalBody)(new Planet("Neptune", PlanetKind.IceGiant, (Collection)CollectionsKt.listOf(new Moon[]{new Moon("Naiad"), new Moon("Thalassa"), new Moon("Despina"), new Moon("Galatea"), new Moon("Larissa"), new Moon("S/2004 N 1"), new Moon("Proteus"), new Moon("Triton"), new Moon("Nereid"), new Moon("Halimede"), new Moon("Sao"), new Moon("Laomedeia"), new Moon("Psamathe"), new Moon("Neso")}), true)), (AstronomicalBody)(new Star("Sun"))});
   }
}
