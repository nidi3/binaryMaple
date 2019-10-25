package de.szut.simNil.binaryMaple.gui;

import java.util.List;
import java.util.Random;

public class StringController extends AbstractController<String> {

    private static Random r = new Random();

    private List<String> wordList = List.of("abecedarian", "abracadabra", "accoutrements", "adagio", "aficionado", "agita", "agog", "akimbo", "alfresco", "aloof", "ambrosial", "amok", "ampersand", "anemone", "anthropomorphic", "antimacassar", "aplomb", "apogee", "apoplectic", "appaloosa", "apparatus", "archipelago", "atingle", "avuncular", "azure", "babushka", "bailiwick", "bafflegab", "balderdash", "ballistic", "bamboozle", "bandwagon", "barnstorming", "beanpole", "bedlam", "befuddled", "bellwether", "berserk", "bibliopole", "bigmouth", "bippy", "blabbermouth", "blatherskite", "blindside", "blob", "blockhead", "blowback", "blowhard", "blubbering", "bluestockings", "boing", "boffola", "bombastic", "bonanza", "bonkers", "boondocks", "boondoggle", "borborygmus", "bozo", "braggadocio", "brainstorm", "brannigan", "breakneck", "brouhaha", "buckaroo", "bucolic", "buffoon", "bugaboo", "bugbear", "bulbous", "bumbledom", "bumfuzzle", "bumpkin", "bungalow", "bunkum", "bupkis", "burnsides", "busybody", "cacophony", "cahoots", "calamity", "calliope", "candelabra", "canoodle", "cantankerous", "catamaran", "catastrophe", "catawampus", "caterwaul", "chatterbox", "chichi", "chimerical", "chimichanga", "chitchat", "clandestine", "claptrap", "clishmaclaver", "clodhopper", "cockamamie", "cockatoo", "codswallop", "collywobbles", "colossus", "comeuppance", "concoction", "conniption", "contraband", "conundrum", "convivial", "copacetic", "corkscrew", "cornucopia", "cowabunga", "coxcomb", "crackerjack", "crescendo", "crestfallen", "cryptozoology", "cuckoo", "curlicue", "curmudgeon", "demitasse", "denouement", "desperado", "diaphanous", "diddly-squat", "digeridoo", "dilemma", "dillydally", "dimwit", "diphthong", "dirigible", "discombobulated", "dodecahedron", "doldrums", "donkeyman", "donnybrook", "doodad", "doohickey", "doppelganger", "dumbfounded", "dumbwaiter", "dunderhead", "earwig", "eavesdrop", "ebullient", "effervescence", "egads", "eggcorn", "egghead", "elixir", "ephemeral", "epiphany", "eucatastrophe", "extraterrestrial", "finagle", "fandango", "festooned", "fez", "fiasco", "fiddle-footed", "fiddlesticks", "finicky", "firebrand", "fishwife", "fisticuffs", "flabbergasted", "flapdoodle", "flibbertigibbet", "flimflam", "flippant", "floccinaucinihilipilification", "flophouse", "flotsam", "flummery", "flummoxed", "flyaway", "flyspeck", "folderol", "foofaraw", "foolhardy", "foolscap", "footloose", "fopdoodle", "fortuitous", "fracas", "frangipani", "freewheeling", "fricassee", "frippery", "frogman", "froufrou", "fuddy-duddy", "fussbudget", "futz", "gadfly", "gadzooks", "gallimaufry", "gangplank", "gangway", "gargoyle", "gasbag", "gazebo", "gazpacho", "gewgaw", "genteel", "ghostwriter", "gibberish", "gimcrack", "gizmo", "glabella", "glitch", "globetrotter", "gobbledygook", "gobsmacked", "goosebump", "gooseflesh", "gorgonzola", "gossamer", "grandiloquent", "greenhorn", "guffaw", "gumshoe", "guru", "gussied", "guttersnipe", "haberdashery", "haboob", "hairpin", "halcyon", "halfwit", "hangdog", "haphazard", "harebrained", "harumph", "harum-scarum", "headlong", "heartstrings", "heebie-jeebie", "heirloom", "helter-skelter", "hemidemisemiquaver", "heyday", "higgledy-piggledy", "highfalutin", "hijinks", "hillbilly", "hippocampus", "hippogriff", "hobbledehoy", "hobnobbed", "hocus-pocus", "hodgepodge", "hogwash", "hokum", "hoodoo", "hoodwink", "hooey", "hooligan", "hoopla", "hootenanny", "hornswoggle", "horsefeathers", "hotbed", "hotfoot", "hothead", "hubbub", "hullabaloo", "humbug", "humdinger", "humdrum", "hunky-dory", "hurly-burly", "hushpuppy", "huzzah", "hyperbole", "idiom", "idiosyncrasies", "igloo", "ignoramus", "impromptu", "incognito", "incorrigible", "incredulous", "indomitable", "indubitably", "infinitesimal", "interloper", "interrobang", "ironclad", "izzard", "jabberwocky", "jacuzzi", "jalopy", "jamboree", "jargogle", "jawbreaker", "jetsam", "jibber-jabber", "jitney", "jubilee", "juggernaut", "jujubes", "jumbo", "junket", "juxtaposition", "kaleidoscope", "kaput", "kerfuffle", "kerplunk", "kibosh", "killjoy", "kismet", "knickerbocker", "knickknack", "kowtow", "kumquat", "kvetch", "lackadaisical", "lagoon", "lambasted", "lampoon", "landlubber", "laughingstock", "lexicographer", "limburger", "lingo", "loco", "loggerhead", "logjam", "logophile", "logorrhea", "lollapalooza", "lollygag", "loofah", "loony", "loophole", "lugubrious", "lummox", "machinations", "madcap", "maelstrom", "magnificent", "majordomo", "malapropism", "malarkey", "manifesto", "mastermind", "mayhem", "mealymouthed", "mellifluous", "menagerie", "miasma", "miffed", "milquetoast", "misanthrope", "mishmash", "moocher", "mojo", "mollycoddle", "mondegreen", "moniker", "monkeyshines", "monsoon", "mnemonic", "moonstruck", "muckety-muck", "mudpuppy", "mudslinger", "muffuletta", "mufti", "mulligatawny", "mumbo-jumbo", "murmuration", "muumuu", "nabob", "namby-pamby", "nimrod", "nincompoop", "nitwit", "nomenclature", "nonplussed", "noodge", "nudnik", "numbskull", "onomatopoeia", "oomph", "orotund", "outfox", "outlandish", "oxymoron", "pachyderm", "pagoda", "palindrome", "palomino", "panache", "pandemonium", "pantaloons", "papyrus", "parabola", "parallelogram", "parapet", "paraphernalia", "pedagogue", "peewee", "pell-mell", "persimmon", "persnickety", "pettifogger", "phalanx", "phantasmagorical", "phantonym", "phylactery", "piffle", "pizzazz", "plethora", "pogo", "pogonip", "pollex", "pollywog", "poltroon", "pomposity", "poppycock", "portmanteau", "potpourri", "pseudonym", "pugnacious", "pulchritudinous", "pusillanimous", "pussyfoot", "quibble", "quicksilver", "quicksticks", "quiddle", "quinzee", "quirky", "quixotic", "quizzity", "rabble-rouser", "raconteur", "rainmaker", "ragamuffin", "ragtag", "ramshackle", "ransack", "rapscallion", "razzle-dazzle", "razzmatazz", "rejigger", "rendezvous", "resplendent", "rickrack", "ricochet", "riffraff", "rigmarole", "riposte", "roundabout", "roustabout", "rubberneck", "ruckus", "ruffian", "rugrat", "rumpus", "sabayon", "sashay", "sassafras", "scalawag", "scatterbrain", "schadenfreude", "schlep", "schluffy", "schmooze", "schmutz", "scintillating", "scrofulous", "scrumdiddlyumptious", "scuttlebutt", "serendipity", "sesquipedalian", "shabang", "shenanigans", "skedaddle", "skirmish", "skullduggery", "slapdash", "slapstick", "slipshod", "smithereens", "smorgasbord", "snollygoster", "sobriquet", "sojourn", "spellbind", "splendiferous", "squeegee", "squooshy", "staccato", "stupefaction", "succotash", "supercilious", "superfluous", "surreptitious", "Svengali", "swashbuckler", "switcheroo", "swizzlestick", "synchronicity", "syzygy", "talisman", "taradiddle", "tchotchke", "teepee", "telekinesis", "thingamabob", "thingamajig", "thunderstruck", "tidbit", "tintinnabulation", "toadstool", "toady", "tomfoolery", "tommyrot", "toothsome", "topsy-turvy", "trapezoid", "tub-thumper", "tumultuous", "typhoon", "ululation", "umlaut", "umpteen", "usurp", "uvula", "vagabond", "vamoose", "verboten", "verisimilitude", "vermicious", "vertigo", "verve", "virtuoso", "vivacious", "vuvuzela", "wackadoodle", "wallflower", "wanderlust", "whatchamacallit", "whatsis", "whimsical", "whippersnapper", "whirligig", "whirlybird", "whizbang", "whodunit", "whoop", "widget", "wigwam", "willy-nilly", "windbag", "wipeout", "wiseacre", "wisecrack", "wisenheimer", "wishy-washy", "woebegone", "wonky", "woozy", "wordplay", "wordsmith", "wunderkind", "wuthering", "xylophone", "yahoo", "yokel", "yo-yo", "zaftig", "zeitgeist", "zenzizenzizenzic", "zephyr", "zeppelin", "ziggurat", "zigzag", "zonked", "zoom", "zydeco");
    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    String getRandomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for( int i = 0; i < len; ++i)
            sb.append( alphabet.charAt(r.nextInt(alphabet.length())));
        return sb.toString();
    }

    @Override
    String getInput(String input) {
        return input;
    }

    @Override
    String getRandomValue() {
        if (r.nextDouble() < 0.9) {
            return wordList.get(r.nextInt(wordList.size()));
        }
        return getRandomString(Math.max(1, r.nextInt(10)));
    }

    @Override
    public String toString() {
        return "String";
    }
}