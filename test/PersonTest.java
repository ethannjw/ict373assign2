import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    RootObservable root = RootObservable.getInstance();
    Address harryHousehold;
    Address weasleyHousehold;
    Person harryPotter;
    Person ginnyWeasly;
    Person arthurWeasley;
    Person mollyWeasley;
    Person jamesPotter;
    Person lilyEvansPotter;
    Person siriusPotter;
    Person albusPotter;
    Person lilyLunaPotter;
    Person ronWeasly;
    Person hermioneGranger;
    Person roseWeasley;
    Person hugoWeasley;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Person.InvalidPersonParameterException {
        root.loadRootPerson(CreatePerson.createHarryPotter());
        harryHousehold = new Address("1A", "Harry Street 1", "Gryffindor", 4340);
        weasleyHousehold = new Address("20A", "Weasley Street 1", "Gryffindor", 4570);
        harryPotter = new Person("Harry", "Potter", "Potter", "male", harryHousehold, "Harry James Potter is a fictional character and the titular protagonist in J. K. Rowling's series of eponymous novels. The majority of the books' plot covers seven years in the life of the orphan Harry, who, on his eleventh birthday, learns he is a wizard. Thus, he attends Hogwarts School of Witchcraft and Wizardry to practice magic under the guidance of the kindly headmaster Albus Dumbledore and other school professors along with his best friends Ron Weasley and Hermione Granger. Harry also discovers that he is already famous throughout the novel's magical community, and that his fate is tied with that of Lord Voldemort – the internationally feared Dark Wizard and murderer of his parents, Lily and James. The film and book series revolve around Harry's struggle to adapt to the wizarding world and defeat Voldemort.");
        ginnyWeasly = new Person("Ginny", "Weasley", "Potter", "Female", harryHousehold, "Ginevra Molly Weasley is a fictional character in J. K. Rowling's Harry Potter novel series. Ginny is introduced in the first book Harry Potter and the Philosopher's Stone, as the youngest sibling and only girl in the Weasley family. She becomes Harry's main love interest and eventually marries him at the conclusion of the series.");
        arthurWeasley = new Person("Arthur", "Weasley", "Weasley", "male", weasleyHousehold, "Arthur Weasley is the patriarch of the Weasleys, a family of wizards who are considered \"blood traitors\" by Death Eaters for their interest in the Muggle world. He is married to Molly Weasley, with whom he has seven children, including Ron, Harry's best friend. During his time at Hogwarts, Arthur belonged to the house of Gryffindor. Arthur is described as being tall and thin, and as having a receding hairline and horn-rimmed glasses. An affable, light-hearted man, he tends not to be the authority figure in the family; his wife Molly handles that area. Arthur works for the Ministry of Magic, initially in the Misuse of Muggle Artefacts Office. He is obsessed with learning about Muggle customs and inventions and owns a large collection of mostly Muggle used items. His department lacks funding, and his salary is only just able to provide for a vast family, leaving his family finances precarious.");
        mollyWeasley = new Person("Molly", "Prewett", "Weasley", "female", weasleyHousehold, "Molly Weasley (née Prewett)[36] is the wife of Arthur Weasley and the matriarch of the Weasley family. She is the mother of seven children, including Ron Weasley, who becomes Harry Potter's best friend. Molly is born into the pure-blood Prewett family, being the sister of Gideon and Fabian Prewett. The character is first introduced in Harry Potter and the Philosopher's Stone, when she kindly tells Harry how to cross the barrier through to Platform Nine and Three-Quarters. In Harry Potter and the Chamber of Secrets, she is furious with Fred, George, and Ron after she discovers that they flew their parents' enchanted car to rescue Harry from his aunt and uncle who had imprisoned him in his room. At the beginning of the school year, Molly sends Ron a Howler, screaming at him in anger that he and Harry flew the family car again, this time to Hogwarts. In Harry Potter and the Prisoner of Azkaban, the Weasleys win the Daily Prophet draw and use the gold on a trip to Egypt to visit Bill. They return to Britain and stay at the Leaky Cauldron with Harry and Hermione. Harry overhears Mr and Mrs Weasley arguing one night about telling Harry the truth about the supposed connection between Sirius Black and Harry; Arthur feels Harry should know the truth but Molly, feeling the truth would terrify him, assures him Harry will be perfectly safe at Hogwarts with Dumbledore's protection, and orders Percy Weasley to keep an eye on Harry at the school.");
        jamesPotter = new Person("James", "Potter", "Potter", "male", harryHousehold, "Harry Potter's parents, who first met at Hogwarts. They were killed by Voldemort, who was attempting to kill the fifteen-month-old Harry.");
        lilyEvansPotter = new Person("Lily", "Evans", "Potter", "female", harryHousehold, "Harry Potter's parents, who first met at Hogwarts. They were killed by Voldemort, who was attempting to kill the fifteen-month-old Harry.");
        siriusPotter = new Person("Sirius", "Potter", "", "male", harryHousehold, "James Sirius Potter is the first-born child of Harry and Ginny, born two years before his brother Albus Severus. He was named after his paternal grandfather James Potter and his father's godfather Sirius Black, and he is described as being similar in character to both of his namesakes as well as his uncles, Fred and George Weasley, with a penchant for practical jokes and general misbehaviour. James is portrayed by Will Dunn in Deathly Hallows: Part 2.[HP7] James Sirius Potter was born on 16 November 2004.");
        albusPotter = new Person("Albus", "Potter", "", "male", harryHousehold, "Albus Severus Potter is Harry and Ginny's second-born child. Albus is said to look much like Harry and to be the only one of his siblings to have inherited Lily Potter's green eyes. He is named after Albus Dumbledore and Severus Snape, and would be eleven at the time of the epilogue. Albus is portrayed by Arthur Bowen in Harry Potter and the Deathly Hallows – Part 2.\nAlbus is one of the main characters in the two-part stage play Harry Potter and the Cursed Child. He has been played in the West End production by Sam Clemmett, Theo Ancient, Joe Idris-Roberts, and Dominic Short. Albus Severus Potter was born in 2006.");
        lilyLunaPotter = new Person("Lily", "Potter", "", "female", harryHousehold, "Lily Luna Potter is the daughter of Harry and Ginny, the youngest of their three children. Her name honours two women important to her parents—her paternal grandmother Lily Potter, and her parents' good friend Luna Lovegood. She is two years younger than her brother Albus Severus, making her around nine, and is close in age to her cousin Hugo. It is also stated that she has her mother's and possibly both of her own grandmothers' red hair. In the epilogue of Harry Potter and the Deathly Hallows, she accompanied her parents as they escorted James and Albus Potter to Platform 9¾ to the Hogwarts Express.");
        ronWeasly = new Person("Ron", "Weasley", "Weasley", "male", weasleyHousehold, "Ronald Bilius Weasley is a fictional character in J. K. Rowling's Harry Potter fantasy novel series. His first appearance was in the first book of the series, Harry Potter and the Philosopher's Stone, as the best friend of Harry Potter and Hermione Granger. He is a member of the Weasley family, a pure blood family that resides in \"The Burrow\" outside Ottery St. Catchpole. Along with Harry and Hermione, he is a member of Gryffindor house. Ron is present in most of the action throughout the series.");
        hermioneGranger = new Person("Hermione", "Granger", "Weasley", "Female", weasleyHousehold, "Hermione Jean Granger is a fictional character in J. K. Rowling's Harry Potter series. She first appears in Harry Potter and the Philosopher's Stone, as a new student on her way to Hogwarts. After Harry and Ron save her from a mountain troll in the girls' restroom, she becomes best friends with them and often uses her quick wit, deft recall, and encyclopaedic knowledge to lend aid in dire situations. Rowling has stated that Hermione resembles herself as a young girl, with her insecurity and fear of failure.");
        roseWeasley = new Person("Rose", "Weasley", "", "female", weasleyHousehold, "Rose Weasley is Hermione and Ron's daughter and older child. Rose is introduced in the Harry Potter and the Deathly Hallows epilogue in which she is leaving for her first year at Hogwarts, as is her cousin Albus Severus Potter. According to Ron she inherited her mother's brains and ambition.");
        hugoWeasley = new Person("Hugo", "Weasley", "", "male", weasleyHousehold, "Hugo Weasley is Hermione and Ron's son and younger child. He is close in age to Harry and Ginny's daughter and youngest child, Lily Luna Potter, and had not yet started at Hogwarts by the epilogue of Deathly Hallows.");
    }

    @org.junit.jupiter.api.Test
    void checkRoot() {
        assertEquals(harryPotter, root.getRootPerson());
    }

    @org.junit.jupiter.api.Test
    void checkSetRootPerson() {
        root.setRootPerson(ginnyWeasly);
        assertEquals(ginnyWeasly, root.getRootPerson());
    }

    @org.junit.jupiter.api.Test
    void checkSetSelectedPerson() {
        root.setSelectedPerson(ginnyWeasly);
        assertEquals(ginnyWeasly, root.getSelectedPerson());
    }

    @org.junit.jupiter.api.Test
    void checkUpdateRootToSelected() {
        root.setSelectedPerson(ginnyWeasly);
        root.updateRootToSelected();
        assertEquals(ginnyWeasly, root.getRootPerson());
    }

    @org.junit.jupiter.api.Test
    void checkChangedMethod() {
        assertFalse(root.getChanged());
        root.setChanged(true);
        assertTrue(root.getChanged());
    }

    @org.junit.jupiter.api.Test
    void checkChangedDueToChangingRoot() {
        assertFalse(root.getChanged());
        root.setRootPerson(ginnyWeasly);
        assertTrue(root.getChanged());
    }

    @org.junit.jupiter.api.Test
    void getFirstName() {
        assertEquals(root.getRootPerson().getFirstName(), "Harry");
    }

    @org.junit.jupiter.api.Test
    void setFirstName() {
        root.getRootPerson().setFirstName("new person");
        assertEquals(root.getRootPerson().getFirstName(), "new person");
    }

    @org.junit.jupiter.api.Test
    void getLastnameAtBirth() {
        assertEquals(root.getRootPerson().getLastnameAtBirth(), "Potter");
    }

    @org.junit.jupiter.api.Test
    void setLastnameAtBirth() {
        root.getRootPerson().setLastnameAtBirth("changed");
        assertEquals(root.getRootPerson().getLastnameAtBirth(), "changed");
    }

    @org.junit.jupiter.api.Test
    void getLastnameUponMarriage() {
        assertEquals(root.getRootPerson().getLastnameUponMarriage(), "Potter");
    }

    @org.junit.jupiter.api.Test
    void setLastnameUponMarriage() {
        root.getRootPerson().setLastnameUponMarriage("changed");
        assertEquals(root.getRootPerson().getLastnameUponMarriage(), "changed");
    }

    @org.junit.jupiter.api.Test
    void getGender() {
        assertEquals(root.getRootPerson().getGender(), "Male");
    }

    @org.junit.jupiter.api.Test
    void setGender() throws Person.InvalidPersonParameterException {
        // test male and female and their case insensitivity
        root.getRootPerson().setGender("feMale");
        assertEquals(root.getRootPerson().getGender(), "Female");
        root.getRootPerson().setGender("mAle");
        assertEquals(root.getRootPerson().getGender(), "Male");
        assertThrows(Person.InvalidPersonParameterException.class, () -> root.getRootPerson().setGender("invalid"));

    }

    @org.junit.jupiter.api.Test
    void getAddress() {
        Address harryHousehold = new Address("1A", "Harry Street 1", "Gryffindor", 4340);
        assertEquals(harryHousehold, root.getRootPerson().getAddress());
    }

    @org.junit.jupiter.api.Test
    void setAddress() {
        Address testAddress = new Address("123", "test street", "suburb", 1234);
        root.getRootPerson().setAddress(testAddress);
        assertEquals(testAddress, root.getRootPerson().getAddress());
    }

    @org.junit.jupiter.api.Test
    void getDescription() {
        assertEquals(root.getRootPerson().getDescription(), "Harry James Potter is a fictional character and the titular protagonist in J. K. Rowling's series of eponymous novels. The majority of the books' plot covers seven years in the life of the orphan Harry, who, on his eleventh birthday, learns he is a wizard. Thus, he attends Hogwarts School of Witchcraft and Wizardry to practice magic under the guidance of the kindly headmaster Albus Dumbledore and other school professors along with his best friends Ron Weasley and Hermione Granger. Harry also discovers that he is already famous throughout the novel's magical community, and that his fate is tied with that of Lord Voldemort – the internationally feared Dark Wizard and murderer of his parents, Lily and James. The film and book series revolve around Harry's struggle to adapt to the wizarding world and defeat Voldemort.");
    }

    @org.junit.jupiter.api.Test
    void setDescription() {
        root.getRootPerson().setDescription("changed");
        assertEquals(root.getRootPerson().getDescription(), "changed");
    }

    @org.junit.jupiter.api.Test
    void getChildren() throws Person.InvalidPersonParameterException {
        List<Person> childrenArray = root.getRootPerson().getChildren();
        List<Person> childrenSpouseArray = root.getRootPerson().getSpouse().getChildren();
        assertEquals(3, childrenArray.size());
        assertTrue(childrenArray.contains(siriusPotter));
        assertTrue(childrenArray.contains(albusPotter));
        assertTrue(childrenArray.contains(lilyLunaPotter));
        assertEquals(3, childrenSpouseArray.size());
        assertTrue(childrenSpouseArray.contains(siriusPotter));
        assertTrue(childrenSpouseArray.contains(albusPotter));
        assertTrue(childrenSpouseArray.contains(lilyLunaPotter));

    }

    @org.junit.jupiter.api.Test
    void setChildren() throws Person.InvalidPersonParameterException {
        Person testChild = new Person();
        testChild.setFirstName("Test Child");
        root.getRootPerson().setChildren(testChild);
        // test that the child is added using the search method to test the search method also
        assertTrue(root.getRootPerson().getChildren().contains(testChild));
        // test that the child is also set in person's spouse
        assertTrue(root.getRootPerson().getSpouse().getChildren().contains(testChild));

    }

    @org.junit.jupiter.api.Test
    void searchChildren() throws Person.InvalidPersonParameterException {
        Person testChild = new Person();
        root.getRootPerson().setChildren(testChild);
        // test that the child is added using the search method to test the search method also
        assertTrue(root.getRootPerson().searchChildren(lilyLunaPotter));
        assertTrue(root.getRootPerson().searchChildren(albusPotter));
        assertTrue(root.getRootPerson().searchChildren(siriusPotter));
        assertTrue(root.getRootPerson().searchChildren(testChild));
    }

    @org.junit.jupiter.api.Test
    void getParents() {
        List<Person> parentsArray = root.getRootPerson().getParents();
        assertEquals(2, parentsArray.size());
        assertTrue(parentsArray.contains(jamesPotter));
        assertTrue(parentsArray.contains(lilyEvansPotter));

    }

    @org.junit.jupiter.api.Test
    void setParents() throws Person.InvalidPersonParameterException {
        Person testParent = new Person();
        Person testChild = new Person();
        // Test that person already has 2 parents will throw exception
        assertThrows(Person.InvalidPersonParameterException.class, () -> root.getRootPerson().setParents(testParent));
        // test adding an additional parent to empty person
        testChild.setParents(testParent);
        assertTrue(testChild.getParents().contains(testParent));
        // test that adding parent will also add testChild as their child
        assertTrue(testParent.searchChildren(testChild));

    }

    @org.junit.jupiter.api.Test
    void searchParents() {
        assertTrue(root.getRootPerson().searchParents(jamesPotter));
        assertTrue(root.getRootPerson().searchParents(lilyEvansPotter));
    }

    @org.junit.jupiter.api.Test
    void getSpouse() {
        assertEquals(ginnyWeasly, root.getRootPerson().getSpouse());
    }

    @org.junit.jupiter.api.Test
    void setSpouse() throws Person.InvalidPersonParameterException {
        Person testSpouse = new Person();
        // check that remarry is possible. since root person already has spouse set
        root.getRootPerson().setSpouse(testSpouse);
        assertEquals(testSpouse, root.getRootPerson().getSpouse());

        // check that same gender will not be added
        Person maleSpouse = new Person();
        maleSpouse.setGender("Male");
        assertThrows(Person.InvalidPersonParameterException.class, () -> root.getRootPerson().setSpouse(maleSpouse));

        // check that if both not married will add children
        // use new person.
        // first set harry his child and spouse
        harryPotter.setSpouse(ginnyWeasly);
        harryPotter.setChildren(albusPotter);
        // now check if ginny has her child also
        assertTrue(ginnyWeasly.searchChildren(albusPotter));

        // check that adding children first will not affect the outcome
        ronWeasly.setChildren(roseWeasley);
        ronWeasly.setSpouse(hermioneGranger);
        assertTrue(hermioneGranger.searchChildren(roseWeasley));
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        assertTrue(root.getRootPerson().equals(harryPotter));
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
        assertEquals(harryPotter.hashCode(), root.getRootPerson().hashCode());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals("Harry Potter", root.getRootPerson().toString());
    }
}